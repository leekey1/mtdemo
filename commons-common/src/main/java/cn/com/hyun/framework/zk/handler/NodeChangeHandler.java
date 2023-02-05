package cn.com.hyun.framework.zk.handler;

/**
 * Created by hyunwoo
 */
public interface NodeChangeHandler {

    void handleChange(String newData);
}
