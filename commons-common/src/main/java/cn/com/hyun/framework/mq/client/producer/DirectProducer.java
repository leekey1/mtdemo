package cn.com.hyun.framework.mq.client.producer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static cn.com.hyun.framework.mq.config.ConnectionHelper.*;

/**
 * Created by hyunwoo
 */
public class DirectProducer extends AbstractProducer {

    public DirectProducer(BrokerConfig brokerConfig) {
        checkDirectConfig(brokerConfig);

        try {
            this.brokerConfig = brokerConfig;
            this.channel = newBasicChannel(brokerConfig);
            this.channel.exchangeDeclare(brokerConfig.getExchanger(), EXCHANGER_TYPE_DIRECT, true);
            this.channel.queueDeclare(brokerConfig.getChannel(), true, false, false, arguments);
            this.channel.queueBind(brokerConfig.getChannel(), brokerConfig.getExchanger(), brokerConfig.getRoute());
        } catch (Exception exception) {
            log.error("构造DirectProducer异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
        }
    }

    private void checkDirectConfig(BrokerConfig brokerConfig) {
        checkConfig(brokerConfig);
        checkExchangerConfig(brokerConfig);
        checkChannelConfig(brokerConfig);
        checkRouteConfig(brokerConfig);
    }
}
