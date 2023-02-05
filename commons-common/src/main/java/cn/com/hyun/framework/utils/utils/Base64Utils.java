package cn.com.hyun.framework.utils.utils;

import cn.com.hyun.framework.exception.FrameworkUtilException;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by hyunwoo
 */
public final class Base64Utils {
    private Base64Utils() {
    }

    public static String decode(String base64String) {
        try {
            return new String(Base64.decodeBase64(base64String), CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new FrameworkUtilException(e);
        }
    }

    public static String encode(String string) {
        try {
            return Base64.encodeBase64String(string.getBytes());
        } catch (Exception e) {
            throw new FrameworkUtilException(e);
        }
    }

}
