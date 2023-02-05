package cn.com.hyun.framework.utils.utils;

import java.util.Date;

/**
 * Created by hyunwoo
 */
public final class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private DateUtils() {
    }

    public static Date now() {
        return new Date();
    }
}