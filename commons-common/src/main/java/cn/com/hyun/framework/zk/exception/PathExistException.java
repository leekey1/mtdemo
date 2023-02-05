package cn.com.hyun.framework.zk.exception;

/**
 * Created by hyunwoo
 */
public class PathExistException extends RuntimeException {

    public PathExistException() {
    }

    public PathExistException(String message) {
        super(message);
    }

    public PathExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathExistException(Throwable cause) {
        super(cause);
    }

    public PathExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
