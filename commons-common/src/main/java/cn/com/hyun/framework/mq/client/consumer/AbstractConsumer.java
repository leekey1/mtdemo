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
            log.error("????????????????????????,host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, ??????{}???, ??????????????????:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), SLEEP_SECONDS, ExceptionUtils.getStackTrace(exception));
            try {
                Thread.sleep(SLEEP_SECONDS * 1000L);
            } catch (InterruptedException interruptedException) {
                log.error("???????????????:{}", ExceptionUtils.getStackTrace(interruptedException));
            }

            try {
                ConnectionHelper.close(channel);
                log.info("??????????????????, ?????????Consumer????????????");
                messageConsumer = newConcreteConsumer(brokerConfig);
                channel = messageConsumer.getChannel();
            } catch (Exception closeException) {
                log.error("??????Consumer??????:{}", ExceptionUtils.getStackTrace(closeException));
            }
        } else {
            try {
                log.error("?????????????????????, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                        brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                        brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
                log.error("????????????:{}????????????, ????????????????????????:{}", message, DEAD_EXCHANGER);
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(), false);
            } catch (IOException e) {
                log.error("????????????????????????:{}", ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Override
    public void close() {
        isClose = true;
    }
}
