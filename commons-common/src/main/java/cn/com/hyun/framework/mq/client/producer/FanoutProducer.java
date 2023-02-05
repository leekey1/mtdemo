package cn.com.hyun.framework.mq.client.producer;

import cn.com.hyun.framework.mq.config.BrokerConfig;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static cn.com.hyun.framework.mq.config.ConnectionHelper.*;

/**
 * Created by hyunwoo
 */
public class FanoutProducer extends AbstractProducer {

    public FanoutProducer(BrokerConfig brokerConfig) {
        this(brokerConfig, false);
    }

    public FanoutProducer(BrokerConfig brokerConfig, boolean instant) {
        this.instant = instant;
        checkFanoutConfig(brokerConfig);

        try {
            this.brokerConfig = brokerConfig;
            this.channel = newBasicChannel(brokerConfig);
            this.channel.exchangeDeclare(brokerConfig.getExchanger(), EXCHANGER_TYPE_FANOUT, true);
            if (BooleanUtils.isFalse(instant)) {
                this.channel.queueDeclare(brokerConfig.getChannel(), true, false, false, arguments);
                this.channel.queueBind(brokerConfig.getChannel(), brokerConfig.getExchanger(), brokerConfig.getRoute());
            }
        } catch (Exception exception) {
            log.error("构造FanoutProducer异常, host:{}, port:{}, virtualHost:{}, exchanger:{}, channel:{}, route:{}, exception:{}",
                    brokerConfig.getHost(), brokerConfig.getPort(), brokerConfig.getVirtualHost(), brokerConfig.getExchanger(),
                    brokerConfig.getChannel(), brokerConfig.getRoute(), ExceptionUtils.getStackTrace(exception));
        }
    }

    private void checkFanoutConfig(BrokerConfig brokerConfig) {
        checkConfig(brokerConfig);
        checkExchangerConfig(brokerConfig);
        if (BooleanUtils.isFalse(instant)) {
            checkChannelConfig(brokerConfig);
        }
    }
}
