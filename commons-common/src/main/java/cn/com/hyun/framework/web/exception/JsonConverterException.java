package cn.com.hyun.framework.web.exception;

/**
 * Created by hyunwoo
 */
public class JsonConverterException extends ExpectedException {
    public JsonConverterException() {
        super();
    }

    public JsonConverterException(String message) {
        super(message);
    }

    public JsonConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonConverterException(Throwable cause) {
        super(cause);
    }

    protected JsonConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
