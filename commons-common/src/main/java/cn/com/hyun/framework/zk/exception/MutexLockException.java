package cn.com.hyun.framework.zk.exception;

/**
 * Created by hyunwoo
 */
public class MutexLockException extends RuntimeException {

    public MutexLockException() {
    }

    public MutexLockException(String message) {
        super(message);
    }

    public MutexLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public MutexLockException(Throwable cause) {
        super(cause);
    }

    public MutexLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
