package cn.com.hyun.framework.web.util;

import cn.com.hyun.framework.utils.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hyunwoo
 */

public final class HttpContextUtils extends org.springframework.web.bind.ServletRequestUtils {

    private HttpContextUtils() {
        throw new UnsupportedOperationException("不允许创建实例");
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getSession();
    }

    public static String getParameter(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("参数错误");
        }

        return getRequest().getParameter(name);
    }

    public static String getRequsetMethod() {
        return getRequest().getMethod();
    }

    public static String getRequestIp() {
        String xForwardFor = "X-Forwarded-For";
        String xRealIp = "X-Real-IP";
        HttpServletRequest request = getRequest();

        String ip = request.getRemoteAddr();
        if (StringUtils.isNotBlank(request.getHeader(xForwardFor))) {
            ip = request.getHeader(xForwardFor);
        } else if (StringUtils.isNotBlank(request.getHeader(xRealIp))) {
            ip = request.getHeader(xRealIp);
        }

        return ip;
    }

    public static String getOriginUrl() {
        return getRequest().getHeader(HttpHeaders.REFERER);
    }

    public static String getUserAgent() {
        return getRequest().getHeader(HttpHeaders.USER_AGENT);
    }

    public static void addSession(String key, String value) {
        Assert.notNull(key, "Key Is Null");
        Assert.notNull(value, "Value is Null");
        getSession().setAttribute(key, value);
    }

    public static Object getSession(String key) {
        Assert.notNull(key, "Key Is Null");
        return getSession().getAttribute(key);
    }
}
