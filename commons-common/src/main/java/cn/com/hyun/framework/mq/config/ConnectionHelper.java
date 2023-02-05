package cn.com.hyun.framework.mq.config;

import cn.com.hyun.framework.mq.exception.MQException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by hyunwoo
 */
public class ConnectionHelper {
    public static final String EXCHANGER_TYPE_FANOUT = "fanout";
    public static final String EXCHANGER_TYPE_DIRECT = "direct";
    public static final String EXCHANGER_TYPE_TOPIC = "topic";
    private static final Logger log = LoggerFactory.getLogger(ConnectionHelper.class);
    private static final int TIMEOUT = 5000;
    private static final int RECOVERY_INTERVAL = 5000;

    private ConnectionHelper() {
    }

    public static Connection initConnection(BrokerConfig brokerConfig) throws MQException {
        if (null == brokerConfig) {
            throw new NullPointerException();
        }

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setTopologyRecoveryEnabled(true);
        connectionFactory.setConnectionTimeout(TIMEOUT);
        connectionFactory.setNetworkRecoveryInterval(RECOVERY_INTERVAL);
        connectionFactory.setRequestedHeartbeat(0);
        connectionFactory.setHost(brokerConfig.getHost());
        connectionFactory.setPort(brokerConfig.getPort());
        connectionFactory.setVirtualHost(brokerConfig.getVirtualHost());
        connectionFactory.setUsername(brokerConfig.getUsername());
        connectionFactory.setPassword(brokerConfig.getPassword());

        Connection connection;
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            throw new MQException(e);
        }

        connection.addShutdownListener((ShutdownSignalException exception) ->
                log.error("连接关闭:{}", exception.getReason().toString())
        );

        return connection;
    }

    public static void close(Channel channel) {
        try {
            if (null != channel && channel.isOpen()) {
                channel.close();
                Connection connection = channel.getConnection();
                if (connection.isOpen()) {
                    connection.close();
                }
            }
        } catch (Exception exception) {
            log.error("Close Exception:{}", ExceptionUtils.getStackTrace(exception));
        }
    }

    public static void checkConfig(BrokerConfig brokerConfig) {
        Assert.notNull(brokerConfig, "broker config is null");
    }

    public static void checkExchangerConfig(BrokerConfig brokerConfig) {
        Assert.notNull(brokerConfig.getExchanger(), "exchanger is null");
        Assert.hasText(brokerConfig.getExchanger(), "exchanger is empty");
    }

    public static void checkChannelConfig(BrokerConfig brokerConfig) {
        Assert.notNull(brokerConfig.getChannel(), "channel is null");
        Assert.hasText(brokerConfig.getChannel(), "channel is empty");
    }

    public static void checkRouteConfig(BrokerConfig brokerConfig) {
        Assert.notNull(brokerConfig.getRoute(), "route is null");
        Assert.hasText(brokerConfig.getRoute(), "route is empty");
    }
}
