package cn.com.hyun.framework.web.util;

import cn.com.hyun.framework.utils.ArrayUtils;
import cn.com.hyun.framework.utils.StringUtils;
import cn.com.hyun.framework.web.exception.WebUtilsException;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyunwoo
 */
public final class HttpCookieUtils {
    private HttpCookieUtils() {
    }

    public static Map<String, String> getCookieValues(HttpServletRequest httpServletRequest) {
        if (null == httpServletRequest) {
            throw new IllegalArgumentException("参数错误");
        }

        Map<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = httpServletRequest.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                try {
                    String name = cookie.getName();
                    String value = new String(Base64.decodeBase64(cookie.getValue()), CharEncoding.UTF_8);
                    cookieMap.put(name, value);
                } catch (UnsupportedEncodingException e) {
                    throw new WebUtilsException(e);
                }
            }
        }

        return cookieMap;
    }

    public static String getCookieValue(HttpServletRequest httpServletRequest, String name) {
        if (null == httpServletRequest || StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("参数错误");
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                if (name.equals(cookieName)) {
                    try {
                        return new String(Base64.decodeBase64(cookieValue), CharEncoding.UTF_8);
                    } catch (UnsupportedEncodingException e) {
                        throw new WebUtilsException(e);
                    }
                }
            }
        }

        return null;
    }

    public static Cookie getCookie(HttpServletRequest httpServletRequest, String name) {
        if (null == httpServletRequest || StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("参数错误");
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (name.equals(cookieName)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    public static void setCookie(HttpServletResponse httpServletResponse, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        setCookie(httpServletResponse, cookie);
    }

    public static void setCookie(HttpServletResponse httpServletResponse, Cookie cookie) {
        if (null == httpServletResponse || null == cookie) {
            throw new IllegalArgumentException("参数错误");
        }

        httpServletResponse.addCookie(cookie);
    }
}
