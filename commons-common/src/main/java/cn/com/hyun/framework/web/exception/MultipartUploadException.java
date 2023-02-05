package cn.com.hyun.framework.web.exception;

/**
 * Created by hyunwoo
 */
public class MultipartUploadException extends ExpectedException {
    public MultipartUploadException() {
        super();
    }

    public MultipartUploadException(String message) {
        super(message);
    }

    public MultipartUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipartUploadException(Throwable cause) {
        super(cause);
    }

    protected MultipartUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
