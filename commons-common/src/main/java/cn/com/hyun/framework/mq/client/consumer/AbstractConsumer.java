package cn.com.hyun.framework.mq.client.consumer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import cn.com.hyun.framework.mq.config.ConnectionHelper;
import com.rabbitmq.client.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static cn.com.hyun.framework.mq.config.ConnectionHelper.EXCHANGER_TYPE_FANOUT;
import static cn.com.hyun.framework.mq.config.ConnectionHelper.initConnection;

/**
 * Created by hyunwoo
 */
abstract class AbstractConsumer implements Consumer {
    protected static final Logger log = LoggerFactory.getLogger(AbstractConsumer.class);
    private static final String DEAD_EXCHANGER = "dead";
    protected static final Map<String, Object> arguments = new HashMap<String, Object>() {
        {
//            put("x-message-ttl", 30000);
            put("x-dead-letter-exchange", DEAD_EXCHANGER);
        }
    };
    private static final int SLEEP_SECONDS = 5;
    protected boolean instant = false;
    protected BrokerConfig brokerConfig;
    protected QueueingConsumer messageConsumer;
    protected Channel channel;
    private volatile boolean isClose = false;

    protected QueueingConsumer newBasicConsumer(BrokerConfig brokerConfig) throws IOException, TimeoutException {
        Connection connection = initConnection(brokerConfig);
        Channel newChannel = connection.createChannel();
        newChannel.exchangeDeclare(DEAD_EXCHANGER, EXCHANGER_TYPE_FANOUT, true);
        newChannel.queueDeclare(DEAD_EXCHANGER, true, false, false, arguments);
        newChannel.queueBind(DEAD_EXCHANGER, DEAD_EXCHANGER, DEAD_EXCHANGER);

        newChannel.basicQos(1, true);
        QueueingConsumer consumer = new QueueingConsumer(newChannel);

        this.brokerConfig = brokerConfig;
        this.channel = consumer.getChannel();

        return consumer;
    }

    protected QueueingConsumer newConcreteConsumer(BrokerConfig brokerConfig) {
        return null;
    }

    @Override
    public void consume(final MessageHandler messageHandler) {
        new Thread(() -> {
            while (true) {
                if (isClosed()) {
                    break;
                }

                QueueingConsumer.Delivery delivery = null;
                String message = null;
                try {
                    delivery = messageConsumer.nextDelivery();
                    message = new String(delivery.getBody(), "UTF-8");
                    messageHandler.handleMessage(message);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                } catch (Exception exception) {
                    reConnectChannel(exception, delivery, message);
                }
            }
        }).start();
    }

    protected boolean isClosed() {
        if (isClose) {
            ConnectionHelper.close(channel);
            return true;
        }
        return false;
    }

    protected void reConnectChannel(Exception exception, QueueingConsumer.Delivery delivery, String message) {
        if (exception instanceof ShutdownSignalException || exception instanceof ConsumerCancelledException) {
            log.error("连接断线或被关闭,host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, 等待{}秒, 重新恢复连接:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), SLEEP_SECONDS, ExceptionUtils.getStackTrace(exception));
            try {
                Thread.sleep(SLEEP_SECONDS * 1000L);
            } catch (InterruptedException interruptedException) {
                log.error("等待被打断:{}", ExceptionUtils.getStackTrace(interruptedException));
            }

            try {
                ConnectionHelper.close(channel);
                log.info("关闭断开连接, 新建立Consumer通道连接");
                messageConsumer = newConcreteConsumer(brokerConfig);
                channel = messageConsumer.getChannel();
            } catch (Exception closeException) {
                log.error("构造Consumer异常:{}", ExceptionUtils.getStackTrace(closeException));
            }
        } else {
            try {
                log.error("消费者发生异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                        brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                        brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
                log.error("消费消息:{}发生异常, 投递到死信交换器:{}", message, DEAD_EXCHANGER);
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(), false);
            } catch (IOException e) {
                log.error("处理错误消息异常:{}", ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Override
    public void close() {
        isClose = true;
    }
}
