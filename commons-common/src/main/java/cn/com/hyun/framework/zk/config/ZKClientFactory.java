package cn.com.hyun.framework.zk.config;

import cn.com.hyun.framework.zk.client.ZKAsyncClient;
import cn.com.hyun.framework.zk.client.ZKClient;

/**
 * Created by hyunwoo
 */
public class ZKClientFactory {
    private ZKClientFactory() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ZKConfiguration zkConfiguration = new ZKConfiguration();

        public Builder node(String host, int port) {
            zkConfiguration.addNode(host, port);
            return this;
        }

        public ZKClient build() {
            return new ZKClient(zkConfiguration);
        }

        public ZKAsyncClient buildAsync() {
            return new ZKAsyncClient(zkConfiguration);
        }
    }
}
