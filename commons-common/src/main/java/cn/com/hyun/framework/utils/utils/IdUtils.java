package cn.com.hyun.framework.utils.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by hyunwoo
 */
public final class IdUtils {
    private static SecureRandom random = new SecureRandom();

    private IdUtils() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static long randomLong() {
        return random.nextLong();
    }

    public static String randomBase62() {
        return EncodeUtils.encodeBase62(random.nextLong());
    }
}
