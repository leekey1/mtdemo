package cn.com.hyun.framework.zk.handler;

/**
 * Created by hyunwoo
 */
public interface PathChangeHandler {

    void handleAdd(String path, String value);

    void handleUpdate(String path, String value);

    void handleDelete(String path, String value);
}
