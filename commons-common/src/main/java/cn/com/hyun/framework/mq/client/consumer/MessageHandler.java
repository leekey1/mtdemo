package cn.com.hyun.framework.mq.client.consumer;

import cn.com.hyun.framework.mq.exception.MQException;

/**
 * Created by hyunwoo
 */
public interface MessageHandler {
    String handleMessage(String message) throws MQException;
}
