package cn.com.hyun.framework.job.exception;

/**
 * Created by hyunwoo
 */
public class ZKException extends RuntimeException {

    public ZKException() {
    }

    public ZKException(String message) {
        super(message);
    }

    public ZKException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZKException(Throwable cause) {
        super(cause);
    }

    public ZKException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
