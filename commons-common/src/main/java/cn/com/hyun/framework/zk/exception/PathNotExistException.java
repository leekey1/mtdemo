package cn.com.hyun.framework.zk.exception;

/**
 * Created by hyunwoo
 */
public class PathNotExistException extends RuntimeException {

    public PathNotExistException() {
    }

    public PathNotExistException(String message) {
        super(message);
    }

    public PathNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathNotExistException(Throwable cause) {
        super(cause);
    }

    public PathNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
