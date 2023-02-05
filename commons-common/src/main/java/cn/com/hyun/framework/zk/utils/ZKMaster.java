package cn.com.hyun.framework.zk.utils;

import cn.com.hyun.framework.zk.client.ZKClient;
import cn.com.hyun.framework.zk.exception.MutexLockException;
import cn.com.hyun.framework.zk.handler.MasterCompeteHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

/**
 * Created by hyunwoo
 */
public class ZKMaster {
    private final ZKClient zkClient;
    private LeaderSelector leaderSelector;

    public ZKMaster(ZKClient zkClient) {
        this.zkClient = zkClient;
    }

    public void competeMaster(String path, final MasterCompeteHandler masterCompeteHandler) {
        try {
            leaderSelector = new LeaderSelector(zkClient.getCuratorFramework(), path,
                    new LeaderSelectorListenerAdapter() {
                        @Override
                        public void takeLeadership(CuratorFramework client) throws Exception {
                            masterCompeteHandler.handleMaster();
                        }
                    });
            leaderSelector.autoRequeue();
            leaderSelector.start();
        } catch (Exception e) {
            throw new MutexLockException(e);
        }
    }

    public void close() {
        if (null != leaderSelector) {
            leaderSelector.close();
        }
    }
}
