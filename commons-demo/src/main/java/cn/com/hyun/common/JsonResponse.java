package cn.com.hyun.common;

import cn.com.hyun.framework.utils.JsonUtils;
import lombok.Data;

import java.util.Date;
import java.util.function.Supplier;

/**
 * Created by zhg on 2017/2/20.
 */
@Data
public class JsonResponse<T> {
    private boolean success = true;
    private String message;
    private T data;
    private Date timestamp = new Date();

    public JsonResponse() {
    }

    public JsonResponse(String message) {
        this.message = message;
    }

    public JsonResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> JsonResponse<T> build(Supplier<T> supplier) {
        JsonResponse<T> response = new JsonResponse<>();
        response.setData(supplier.get());
        return response;
    }

    public static <T> String buildString(Supplier<T> supplier) {
        return JsonUtils.toJson(build(supplier));
    }

    public static JsonResponse<String> message(String message) {
        return new JsonResponse<>(message);
    }

    public static String messageString(String message) {
        return JsonUtils.toJson(message(message));
    }

}
