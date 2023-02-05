package cn.com.hyun.framework.zk.utils;

import cn.com.hyun.framework.zk.client.ZKClient;
import cn.com.hyun.framework.zk.exception.DistributedException;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;

/**
 * Created by hyunwoo
 */
public class ZKDistributed {
    private static final int BASE_SLEEP_MILLIONS = 200;
    private static final int MAX_SLEEP_MILLIONS = 3000;
    private static final int MAX_RETRIES = 10;
    private final ZKClient zkClient;

    public ZKDistributed(ZKClient zkClient) {
        this.zkClient = zkClient;
    }

    public Long autoIncrease(String path) {
        try {
            DistributedAtomicLong distributedAtomicInteger = initAtomicLong(path);
            return distributedAtomicInteger.increment().postValue();
        } catch (Exception e) {
            throw new DistributedException(e);
        }
    }

    public Long autoIncreaseFrom(String path, long from) {
        try {
            DistributedAtomicLong distributedAtomicInteger = initAtomicLong(path);
            distributedAtomicInteger.initialize(from);
            return distributedAtomicInteger.increment().postValue();
        } catch (Exception e) {
            throw new DistributedException(e);
        }
    }

    public boolean compareAndSet(String path, long expectedValue, long newValue) {
        try {
            DistributedAtomicLong distributedAtomicInteger = initAtomicLong(path);
            return distributedAtomicInteger.compareAndSet(expectedValue, newValue).succeeded();
        } catch (Exception e) {
            throw new DistributedException(e);
        }
    }

    private DistributedAtomicLong initAtomicLong(String path) {
        return new DistributedAtomicLong(zkClient.getCuratorFramework(),
                path, new BoundedExponentialBackoffRetry(BASE_SLEEP_MILLIONS, MAX_SLEEP_MILLIONS, MAX_RETRIES));
    }
}
