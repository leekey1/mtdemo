package cn.com.hyun.framework.zk.utils;

import cn.com.hyun.framework.zk.client.ZKClient;
import cn.com.hyun.framework.zk.exception.WatcherException;
import cn.com.hyun.framework.zk.exception.ZKException;
import cn.com.hyun.framework.zk.handler.NodeChangeHandler;
import cn.com.hyun.framework.zk.handler.PathChangeHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;

import java.io.IOException;

/**
 * Created by hyunwoo
 */
public class ZKWatcher {
    private final ZKClient zkClient;
    private NodeCache nodeCache;
    private PathChildrenCache pathChildrenCache;

    public ZKWatcher(ZKClient zkClient) {
        this.zkClient = zkClient;
    }

    public void watchPath(String path, final NodeChangeHandler nodeChangeHandler) {
        try {
            nodeCache = new NodeCache(zkClient.getCuratorFramework(), path, false);
            nodeCache.start();
            nodeCache.getListenable().addListener(() -> {
                ChildData childData = nodeCache.getCurrentData();
                if (null == childData) {
                    nodeChangeHandler.handleChange(null);
                } else {
                    nodeChangeHandler.handleChange(new String(nodeCache.getCurrentData().getData(), "UTF-8"));
                }
            });
        } catch (Exception e) {
            throw new WatcherException(e);
        }
    }

    public void watchPathChildren(final String parentPath, final PathChangeHandler pathChangeHandler) {
        try {
            pathChildrenCache = new PathChildrenCache(zkClient.getCuratorFramework(), parentPath, true);
            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            pathChildrenCache.getListenable().addListener((CuratorFramework client, PathChildrenCacheEvent event) -> {
                String path = event.getData().getPath();
                String value = new String(event.getData().getData(), "UTF-8");
                switch (event.getType()) {
                    case CHILD_ADDED:
                        pathChangeHandler.handleAdd(path, value);
                        break;
                    case CHILD_UPDATED:
                        pathChangeHandler.handleUpdate(path, value);
                        break;
                    case CHILD_REMOVED:
                        pathChangeHandler.handleDelete(path, value);
                        break;
                    default:
                        break;
                }
            });
        } catch (Exception e) {
            throw new WatcherException(e);
        }
    }

    public void closeNodeWatch() {
        try {
            if (null != nodeCache) {
                nodeCache.close();
            }
        } catch (IOException e) {
            throw new ZKException(e);
        }
    }

    public void closePathWatch() {
        try {
            if (null != pathChildrenCache) {
                pathChildrenCache.close();
            }
        } catch (IOException e) {
            throw new ZKException(e);
        }
    }
}
