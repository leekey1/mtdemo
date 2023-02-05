package cn.com.hyun.framework.job.exception;

/**
 * Created by hyunwoo
 */
public class ZKMutexLockException extends RuntimeException {

    public ZKMutexLockException() {
    }

    public ZKMutexLockException(String message) {
        super(message);
    }

    public ZKMutexLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZKMutexLockException(Throwable cause) {
        super(cause);
    }

    public ZKMutexLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
