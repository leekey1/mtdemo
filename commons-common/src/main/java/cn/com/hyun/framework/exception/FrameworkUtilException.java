package cn.com.hyun.framework.exception;

/**
 * Created by hyunwoo
 */
public class FrameworkUtilException extends RuntimeException {
    public FrameworkUtilException() {
        super();
    }

    public FrameworkUtilException(String message) {
        super(message);
    }

    public FrameworkUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameworkUtilException(Throwable cause) {
        super(cause);
    }

    protected FrameworkUtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
