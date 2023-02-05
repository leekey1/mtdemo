package cn.com.hyun.framework.mq.client.producer;

/**
 * Created by hyunwoo
 */
public interface Producer {
    void produce(String message);

    void close();

    String getResponse();
}
