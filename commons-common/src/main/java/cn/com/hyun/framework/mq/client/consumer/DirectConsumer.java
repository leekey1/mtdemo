package cn.com.hyun.framework.mq.client.consumer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static cn.com.hyun.framework.mq.config.ConnectionHelper.*;

/**
 * Created by hyunwoo
 */
public class DirectConsumer extends AbstractConsumer {

    public DirectConsumer(BrokerConfig brokerConfig) {
        checkDirectConfig(brokerConfig);
        newConcreteConsumer(brokerConfig);
    }

    @Override
    protected QueueingConsumer newConcreteConsumer(BrokerConfig brokerConfig) {
        try {
            this.messageConsumer = newBasicConsumer(brokerConfig);
            this.channel.exchangeDeclare(brokerConfig.getExchanger(), EXCHANGER_TYPE_DIRECT, true);
            this.channel.queueDeclare(brokerConfig.getChannel(), true, false, false, arguments);
            this.channel.queueBind(brokerConfig.getChannel(), brokerConfig.getExchanger(), brokerConfig.getRoute());
            this.channel.basicConsume(brokerConfig.getChannel(), false, messageConsumer);
        } catch (Exception exception) {
            log.error("构造DirectConsumer异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
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
}
