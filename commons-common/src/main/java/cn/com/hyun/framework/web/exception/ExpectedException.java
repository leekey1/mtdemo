package cn.com.hyun.framework.web.exception;

/**
 * Created by hyunwoo
 */
public abstract class ExpectedException extends RuntimeException {

    public ExpectedException() {
    }

    public ExpectedException(String message) {
        super(message);
    }

    public ExpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpectedException(Throwable cause) {
        super(cause);
    }

    public ExpectedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
