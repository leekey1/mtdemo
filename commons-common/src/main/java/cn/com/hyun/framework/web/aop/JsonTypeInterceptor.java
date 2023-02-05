package cn.com.hyun.framework.web.aop;

import cn.com.hyun.framework.utils.StringUtils;
import cn.com.hyun.framework.web.response.JsonResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * Created by hyunwoo
 */
public class JsonTypeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(JsonTypeInterceptor.class);
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Class clazz = method.getDeclaringClass();

            RestController restController = (RestController) clazz.getAnnotation(RestController.class);
            if (restController != null) {
                return isJsonRequest(request, response);
            }

            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if (responseBody != null) {
                return isJsonRequest(request, response);
            }
        }

        return true;
    }

    private boolean isJsonRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);

        if (StringUtils.isBlank(contentType)
                || !StringUtils.contains(contentType, MediaType.APPLICATION_JSON_VALUE)) {
            log.error("请求:{}, 验证失败, 不是json请求, contentType:{}", request.getRequestURI(), contentType);

            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            response.getWriter().write(objectMapper.writeValueAsString(new JsonResponse(false, "contentType != application/json")));
            return false;
        }

        return true;
    }
}
