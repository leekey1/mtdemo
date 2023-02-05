package cn.com.hyun.framework.mq.client.producer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import cn.com.hyun.framework.mq.config.ConnectionHelper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
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
abstract class AbstractProducer implements Producer {
    protected static final Logger log = LoggerFactory.getLogger(AbstractProducer.class);
    private static final String DEAD_EXCHANGER = "dead";
    protected static final Map<String, Object> arguments = new HashMap<String, Object>() {
        {
//            put("x-message-ttl", 30000);
            put("x-dead-letter-exchange", DEAD_EXCHANGER);
        }
    };
    protected boolean instant = false;
    protected BrokerConfig brokerConfig;
    protected Channel channel;
    protected String response;

    protected Channel newBasicChannel(BrokerConfig brokerConfig) throws IOException, TimeoutException {
        Connection connection = initConnection(brokerConfig);

        Channel newChannel = connection.createChannel();
        newChannel.exchangeDeclare(DEAD_EXCHANGER, EXCHANGER_TYPE_FANOUT, true);
        newChannel.queueDeclare(DEAD_EXCHANGER, true, false, false, arguments);
        newChannel.queueBind(DEAD_EXCHANGER, DEAD_EXCHANGER, DEAD_EXCHANGER);

        newChannel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.info("消息发送成功, 从服务器得到响应, deliveryTag:{}", deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                log.error("消息发送失败, 未从服务器得到响应, deliveryTag:{}", deliveryTag);
            }
        });
        newChannel.confirmSelect();
        newChannel.basicQos(1, true);

        return newChannel;
    }

    @Override
    public void produce(String message) {
        try {
            channel.basicPublish(brokerConfig.getExchanger(), brokerConfig.getRoute(), false,
                    MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        } catch (Exception exception) {
            log.error("生产消息异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
        }
    }

    @Override
    public void close() {
        ConnectionHelper.close(this.channel);
    }

    @Override
    public String getResponse() {
        return response;
    }
}
