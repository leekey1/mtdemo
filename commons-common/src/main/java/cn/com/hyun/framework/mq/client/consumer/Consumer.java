package cn.com.hyun.framework.mq.client.consumer;

/**
 * Created by hyunwoo
 */
public interface Consumer {
    void consume(MessageHandler messageHandler);

    void close();
}
