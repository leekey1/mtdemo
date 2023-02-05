package cn.com.hyun.framework.zk.client;

import cn.com.hyun.framework.zk.config.ZKConfiguration;
import cn.com.hyun.framework.zk.exception.ValueModifiedException;
import cn.com.hyun.framework.zk.exception.ZKException;
import cn.com.hyun.framework.zk.handler.AsyncHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

/**
 * Created by zhg
 */
public class ZKAsyncClient extends ZKAbstractClient {

    public ZKAsyncClient(ZKConfiguration zkConfiguration) {
        super(zkConfiguration);
    }

    public void add(String path, String value, final AsyncHandler asyncHandler) {
        try {
            checkAdd(path, value);
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .inBackground(handleCallback(asyncHandler)).forPath(path, value.getBytes());
        } catch (Exception e) {
            throw new ZKException(e);
        }
    }

    public void addEphemeral(String path, final AsyncHandler asyncHandler) {
        try {
            checkParameter(path);
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .inBackground(handleCallback(asyncHandler)).forPath(path);
        } catch (Exception e) {
            throw new ZKException(e);
        }
    }

    public void addEphemeral(String path, String value, final AsyncHandler asyncHandler) {
        try {
            checkAdd(path, value);
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .inBackground(handleCallback(asyncHandler)).forPath(path, value.getBytes());
        } catch (Exception e) {
            throw new ZKException(e);
        }
    }

    public String addSequentialEphemeral(String path, final AsyncHandler asyncHandler) {
        try {
            checkParameter(path);
            return curatorFramework.create().creatingParentsIfNeeded().withProtection()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL).inBackground(handleCallback(asyncHandler)).forPath(path);
        } catch (Exception e) {
            throw new ZKException(e);
        }
    }

    public String addSequentialEphemeral(String path, String value, final AsyncHandler asyncHandler) {
        try {
            checkAdd(path, value);
            return curatorFramework.create().creatingParentsIfNeeded().withProtection()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL).inBackground(handleCallback(asyncHandler)).forPath(path, value.getBytes());
        } catch (Exception e) {
            throw new ZKException(e);
        }
    }

    public String update(String path, String value, AsyncHandler asyncHandler) {
        Stat stat = checkUpdate(path, value);
        return doUpdate(path, value, stat.getVersion(), asyncHandler);
    }

    public String updateWithVersion(String path, String value, int version, AsyncHandler asyncHandler) {
        checkUpdate(path, value);
        return doUpdate(path, value, version, asyncHandler);
    }

    private String doUpdate(String path, String value, int version, final AsyncHandler asyncHandler) {
        try {
            String oldValue = new String(curatorFramework.getData().forPath(path), CHARSET_UTF8);
            curatorFramework.setData().withVersion(version).inBackground(handleCallback(asyncHandler))
                    .forPath(path, value.getBytes());
            return oldValue;
        } catch (KeeperException.BadVersionException exception) {
            throw new ValueModifiedException("the value of " + path + " is modified by another thread");
        } catch (Exception e) {
            throw new ZKException(e);
        }
    }

    public String delete(String path, AsyncHandler asyncHandler) {
        Stat stat = checkDelete(path);
        if (null == stat) {
            return null;
        }

        return doDelete(path, stat.getVersion(), asyncHandler);
    }

    public String deleteWithVersion(String path, int version, AsyncHandler asyncHandler) {
        Stat stat = checkDelete(path);
        if (null == stat) {
            return null;
        }

        return doDelete(path, version, asyncHandler);
    }

    private String doDelete(String path, int version, final AsyncHandler asyncHandler) {
        try {
            String oldValue = new String(curatorFramework.getData().forPath(path), CHARSET_UTF8);
            curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().withVersion(version)
                    .inBackground(handleCallback(asyncHandler)).forPath(path);
            return oldValue;
        } catch (KeeperException.BadVersionException exception) {
            throw new ValueModifiedException("the value of " + path + " is modified by another thread");
        } catch (Exception e) {
            throw new ZKException(e);
        }
    }

    private BackgroundCallback handleCallback(final AsyncHandler asyncHandler) {
        return (CuratorFramework client, CuratorEvent event) -> {
            if (event.getResultCode() == 0) {
                asyncHandler.handleSuccess();
            } else {
                asyncHandler.handleError();
            }
        };
    }
}
