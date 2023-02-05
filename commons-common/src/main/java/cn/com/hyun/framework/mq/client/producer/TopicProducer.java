package cn.com.hyun.framework.mq.client.producer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static cn.com.hyun.framework.mq.config.ConnectionHelper.*;

/**
 * Created by hyunwoo
 */
public class TopicProducer extends AbstractProducer {

    public TopicProducer(BrokerConfig brokerConfig) {
        this(brokerConfig, false);
    }

    public TopicProducer(BrokerConfig brokerConfig, boolean instant) {
        this.instant = instant;
        checkTopicConfig(brokerConfig);

        try {
            this.brokerConfig = brokerConfig;
            this.channel = newBasicChannel(brokerConfig);
            this.channel.exchangeDeclare(brokerConfig.getExchanger(), EXCHANGER_TYPE_TOPIC, true);
            if (BooleanUtils.isFalse(instant)) {
                this.channel.queueDeclare(brokerConfig.getChannel(), true, false, false, arguments);
                this.channel.queueBind(brokerConfig.getChannel(), brokerConfig.getExchanger(), brokerConfig.getRoute());
            }
        } catch (Exception exception) {
            log.error("构造TopicProducer异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
        }
    }

    private void checkTopicConfig(BrokerConfig brokerConfig) {
        checkConfig(brokerConfig);
        checkExchangerConfig(brokerConfig);
        if (BooleanUtils.isFalse(instant)) {
            checkChannelConfig(brokerConfig);
        }
        checkRouteConfig(brokerConfig);
    }
}
