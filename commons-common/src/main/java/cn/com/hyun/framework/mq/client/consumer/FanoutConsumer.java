package cn.com.hyun.framework.mq.client.consumer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static cn.com.hyun.framework.mq.config.ConnectionHelper.*;

/**
 * Created by hyunwoo
 */
public class FanoutConsumer extends AbstractConsumer {

    public FanoutConsumer(BrokerConfig brokerConfig) {
        this(brokerConfig, false);
    }

    public FanoutConsumer(BrokerConfig brokerConfig, boolean instant) {
        this.instant = instant;
        checkDirectConfig(brokerConfig);
        newConcreteConsumer(brokerConfig);
    }

    @Override
    protected QueueingConsumer newConcreteConsumer(BrokerConfig brokerConfig) {
        try {
            this.messageConsumer = newBasicConsumer(brokerConfig);
            this.channel.exchangeDeclare(brokerConfig.getExchanger(), EXCHANGER_TYPE_FANOUT, true);
            if (BooleanUtils.isTrue(instant)) {
                String queueName = this.channel.queueDeclare().getQueue();
                this.channel.queueBind(queueName, brokerConfig.getExchanger(), queueName);
                this.channel.basicConsume(queueName, false, messageConsumer);
            } else {
                this.channel.queueDeclare(brokerConfig.getChannel(), true, false, false, arguments);
                this.channel.queueBind(brokerConfig.getChannel(), brokerConfig.getExchanger(), brokerConfig.getChannel());
                this.channel.basicConsume(brokerConfig.getChannel(), false, messageConsumer);
            }
        } catch (Exception exception) {
            log.error("构造FanoutConsumer异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
        }

        return this.messageConsumer;
    }

    private void checkDirectConfig(BrokerConfig brokerConfig) {
        checkConfig(brokerConfig);
        checkExchangerConfig(brokerConfig);
        if (BooleanUtils.isFalse(instant)) {
            checkChannelConfig(brokerConfig);
        }
    }
}
