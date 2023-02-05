package cn.com.hyun.framework.zk.config;

/**
 * Created by hyunwoo
 */
public class ZKConfiguration {
    private final StringBuilder nodesBuilder = new StringBuilder();

    public static ZKConfiguration newInstance() {
        return new ZKConfiguration();
    }

    public ZKConfiguration addNode(String host, int port) {
        nodesBuilder.append(host).append(":").append(port).append(",");
        return this;
    }

    public String getConnectString() {
        if (nodesBuilder.length() <= 1) {
            return nodesBuilder.toString();
        }

        return nodesBuilder.substring(0, nodesBuilder.length() - 1);
    }
}
