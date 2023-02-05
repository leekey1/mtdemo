package cn.com.hyun.framework.utils;

import cn.com.hyun.framework.exception.FrameworkUtilException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by hyunwoo
 */
public final class JsonUtils {
    private static ObjectMapper objectMapper;
    private static boolean hiddenNull = false;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    private JsonUtils() {
    }

    public static String toJson(Object object) {
        return toJson(object, true);
    }

    public static String toJson(Object object, boolean hiddenNull) {
        if (hiddenNull) {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        } else {
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        }

        try {
            if (null == object) {
                return StringUtils.EMPTY;
            }

            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new FrameworkUtilException(e);
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }

            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new FrameworkUtilException(e);
        }
    }

    public static <T> List<T> toBeanList(String json, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(json)) {
                return Collections.EMPTY_LIST;
            }

            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new FrameworkUtilException(e);
        }
    }

    public static String getString(String jsonStr, String property) {
        if (StringUtils.isEmpty(jsonStr) || StringUtils.isEmpty(property)) {
            throw new IllegalArgumentException();
        }

        try {
            String result = jsonStr;
            StringTokenizer tokenizer = new StringTokenizer(property, "\\.");
            while (tokenizer.hasMoreTokens()) {
                JSONObject jsonObject = JSONObject.fromObject(result);
                result = jsonObject.get(tokenizer.nextElement()).toString();
            }

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
