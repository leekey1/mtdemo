package cn.com.hyun.framework.mq.client.consumer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static cn.com.hyun.framework.mq.config.ConnectionHelper.*;

/**
 * Created by hyunwoo
 */
public class RPCConsumer extends AbstractConsumer {

    public RPCConsumer(BrokerConfig brokerConfig) {
        checkDirectConfig(brokerConfig);
        newConcreteConsumer(brokerConfig);
    }

    @Override
    protected QueueingConsumer newConcreteConsumer(BrokerConfig brokerConfig) {
        try {
            this.messageConsumer = newBasicConsumer(brokerConfig);
            this.channel.exchangeDeclare(brokerConfig.getExchanger(), EXCHANGER_TYPE_TOPIC, true);
            this.channel.queueDeclare(brokerConfig.getChannel(), true, false, true, arguments);
            this.channel.queueBind(brokerConfig.getChannel(), brokerConfig.getExchanger(), brokerConfig.getRoute());
            this.channel.basicConsume(brokerConfig.getChannel(), false, messageConsumer);
        } catch (Exception exception) {
            log.error("构造PRCConsumer异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
        }

        return this.messageConsumer;
    }

    private void checkDirectConfig(BrokerConfig brokerConfig) {
        checkConfig(brokerConfig);
        checkExchangerConfig(brokerConfig);
        checkChannelConfig(brokerConfig);
        checkRouteConfig(brokerConfig);
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
                    AMQP.BasicProperties requestProperties = delivery.getProperties();
                    AMQP.BasicProperties replyProperties = new AMQP.BasicProperties.Builder()
                            .correlationId(requestProperties.getCorrelationId()).build();

                    message = new String(delivery.getBody(), "UTF-8");
                    String response = messageHandler.handleMessage(message);

                    channel.basicPublish("", requestProperties.getReplyTo(), false, replyProperties, response.getBytes());
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                } catch (Exception exception) {
                    reConnectChannel(exception, delivery, message);
                }
            }
        }).start();
    }
}
