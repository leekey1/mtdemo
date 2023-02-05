package cn.com.hyun.framework.page.pagehelper;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
