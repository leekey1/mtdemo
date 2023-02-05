package cn.com.hyun.framework.mq.client.producer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.UUID;

import static cn.com.hyun.framework.mq.config.ConnectionHelper.*;

/**
 * Created by hyunwoo
 */
public class RPCProducer extends AbstractProducer {

    protected static final int PRC_TIMEOUT = 5000;
    private String replyQueueName;
    private String correlationId;
    private QueueingConsumer callbackConsumer;


    public RPCProducer(BrokerConfig brokerConfig) {
        checkDirectConfig(brokerConfig);

        try {
            this.brokerConfig = brokerConfig;
            this.channel = newBasicChannel(brokerConfig);
            this.channel.exchangeDeclare(brokerConfig.getExchanger(), EXCHANGER_TYPE_TOPIC, true);

            this.correlationId = UUID.randomUUID().toString().replaceAll("-", "");
            this.replyQueueName = this.channel.queueDeclare().getQueue();
            this.callbackConsumer = new QueueingConsumer(channel);
            this.channel.basicConsume(replyQueueName, true, callbackConsumer);
        } catch (Exception exception) {
            log.error("构造PRCProducer异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
        }
    }

    private void checkDirectConfig(BrokerConfig brokerConfig) {
        checkConfig(brokerConfig);
        checkExchangerConfig(brokerConfig);
        checkRouteConfig(brokerConfig);
    }

    @Override
    public void produce(String message) {
        try {
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .replyTo(replyQueueName).correlationId(correlationId).build();
            channel.basicPublish(brokerConfig.getExchanger(), brokerConfig.getRoute(), false,
                    properties, message.getBytes());

            while (true) {
                QueueingConsumer.Delivery delivery = callbackConsumer.nextDelivery(PRC_TIMEOUT);
                if (delivery.getProperties().getCorrelationId().equals(correlationId)) {
                    this.response = new String(delivery.getBody(), "UTF-8");
                    break;
                }
            }
        } catch (Exception exception) {
            log.error("生产消息异常:{}", ExceptionUtils.getStackTrace(exception));
        }
    }
}
