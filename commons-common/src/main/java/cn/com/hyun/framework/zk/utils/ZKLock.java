package cn.com.hyun.framework.zk.utils;

import cn.com.hyun.framework.zk.client.ZKClient;
import cn.com.hyun.framework.zk.exception.MutexLockException;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * Created by hyunwoo
 */
public class ZKLock {
    private final ZKClient zkClient;
    private InterProcessLock interProcessLock;

    public ZKLock(ZKClient zkClient) {
        this.zkClient = zkClient;
    }

    public void acquire(String path) {
        try {
            interProcessLock = new InterProcessMutex(zkClient.getCuratorFramework(), path);
            interProcessLock.acquire();
        } catch (Exception e) {
            throw new MutexLockException(e);
        }
    }

    public boolean tryAcquire(String path, long timeout) {
        try {
            interProcessLock = new InterProcessMutex(zkClient.getCuratorFramework(), path);
            return interProcessLock.acquire(timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new MutexLockException(e);
        }
    }

    public void release(String path) {
        try {
            if (null != interProcessLock && interProcessLock.isAcquiredInThisProcess()) {
                interProcessLock.release();
                zkClient.delete(path);
            }
        } catch (Exception e) {
            throw new MutexLockException(e);
        }
    }
}
