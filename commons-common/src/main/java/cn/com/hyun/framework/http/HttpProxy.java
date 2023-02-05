package cn.com.hyun.framework.http;

import java.util.Objects;

public class HttpProxy {
    private String host;
    private int port;

    public HttpProxy() {
    }

    public HttpProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpProxy)) return false;
        HttpProxy httpProxy = (HttpProxy) o;
        return getPort() == httpProxy.getPort() &&
                Objects.equals(getHost(), httpProxy.getHost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHost(), getPort());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HttpProxy{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
