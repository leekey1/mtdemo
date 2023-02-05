package cn.com.hyun.framework.utils.utils;

/**
 * Created by hyunwoo
 */
public final class StringUtils extends org.apache.commons.lang3.StringUtils {
    private StringUtils() {
    }

    public static boolean notEquals(final CharSequence cs1, final CharSequence cs2) {
        return !equals(cs1, cs2);
    }

    public static boolean notEqualsIgnoreCase(final CharSequence cs1, final CharSequence cs2) {
        return !equalsIgnoreCase(cs1, cs2);
    }
}
