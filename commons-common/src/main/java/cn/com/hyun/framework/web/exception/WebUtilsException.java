package cn.com.hyun.framework.web.exception;

/**
 * Created by hyunwoo
 */
public class WebUtilsException extends RuntimeException {
    public WebUtilsException() {
    }

    public WebUtilsException(String message) {
        super(message);
    }

    public WebUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebUtilsException(Throwable cause) {
        super(cause);
    }

    public WebUtilsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
