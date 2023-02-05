package cn.com.hyun.framework.mq.config;

import org.springframework.beans.factory.annotation.Required;

import java.util.Objects;

/**
 * Created by hyunwoo
 */
public class BrokerConfig {
    private String host;
    private int port = 5672;
    private String virtualHost = "/";
    private String username;
    private String password;
    private String channel;
    private String exchanger = "";
    private String route = "";

    public static BrokerConfig newInstance() {
        return new BrokerConfig();
    }

    public String getHost() {
        return host;
    }

    @Required
    public BrokerConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public BrokerConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public BrokerConfig setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
        return this;
    }

    public String getUsername() {
        return username;
    }

    @Required
    public BrokerConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    @Required
    public BrokerConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getExchanger() {
        return exchanger;
    }

    public BrokerConfig setExchanger(String exchanger) {
        this.exchanger = exchanger;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    @Required
    public BrokerConfig setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getRoute() {
        return route;
    }

    public BrokerConfig setRoute(String route) {
        this.route = route;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrokerConfig)) return false;
        BrokerConfig that = (BrokerConfig) o;
        return getPort() == that.getPort() &&
                Objects.equals(getHost(), that.getHost()) &&
                Objects.equals(getVirtualHost(), that.getVirtualHost()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getExchanger(), that.getExchanger()) &&
                Objects.equals(getChannel(), that.getChannel()) &&
                Objects.equals(getRoute(), that.getRoute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHost(), getPort(), getVirtualHost(), getUsername(), getPassword(), getExchanger(), getChannel(), getRoute());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Connection{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", virtualHost='").append(virtualHost).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", exchanger='").append(exchanger).append('\'');
        sb.append(", channel='").append(channel).append('\'');
        sb.append(", route='").append(route).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
