package cn.com.hyun.framework.web.util;

import cn.com.hyun.framework.utils.StringUtils;
import cn.com.hyun.framework.web.exception.WebUtilsException;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

/**
 * Created by hyunwoo
 */
public final class HttpHeaderUtils {
    private HttpHeaderUtils() {
    }

    public static String getHeader(HttpServletRequest httpServletRequest, String name) {
        if (null == httpServletRequest || StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("参数错误");
        }

        Enumeration<String> headNames = httpServletRequest.getHeaderNames();
        while (headNames.hasMoreElements()) {
            String headName = headNames.nextElement();
            String headValue = httpServletRequest.getHeader(headName);
            if (name.equalsIgnoreCase(headName)) {
                try {
                    return new String(Base64.decodeBase64(headValue), CharEncoding.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    throw new WebUtilsException(e);
                }
            }
        }

        return null;
    }

    public static void setHeader(HttpServletResponse httpServletResponse, String name, String value) {
        if (null == httpServletResponse || StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("参数错误");
        }

        httpServletResponse.addHeader(name, Base64.encodeBase64String(value.getBytes()));
    }
}
