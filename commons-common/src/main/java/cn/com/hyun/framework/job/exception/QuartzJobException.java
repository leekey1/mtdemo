package cn.com.hyun.framework.job.exception;

/**
 * Created by hyunwoo
 */
public class QuartzJobException extends RuntimeException {

    public QuartzJobException() {
    }

    public QuartzJobException(String message) {
        super(message);
    }

    public QuartzJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuartzJobException(Throwable cause) {
        super(cause);
    }

    public QuartzJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
