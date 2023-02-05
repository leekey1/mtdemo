package cn.com.hyun.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyunwoo
 */
public final class ParameterFilterUtils {
    private static final Map<String, String> filterMap = new HashMap();

    static {
        filterMap.put("select", "");
        filterMap.put("insert", "");
        filterMap.put("update", "");
        filterMap.put("delete", "");
        filterMap.put("union", "");
        filterMap.put("into", "");
        filterMap.put("load_file", "");
        filterMap.put("outfile", "");
        filterMap.put("script", "");
        filterMap.put("!", "");
        filterMap.put("@", "");
        filterMap.put("#", "");
        filterMap.put("$", "");
        filterMap.put("^", "");
        filterMap.put("*", "");
        filterMap.put("%", "");
        filterMap.put("\\(", "");
        filterMap.put("\\)", "");
        filterMap.put("\\+", "");
        filterMap.put("\\-", "");
        filterMap.put("\\[", "");
        filterMap.put("\\]", "");
        filterMap.put("\\{", "");
        filterMap.put("\\}", "");
        filterMap.put(";", "");
        filterMap.put(":", "");
        filterMap.put("&", "&amp;");
        filterMap.put("<", "&lt;");
        filterMap.put(">", "&gt;");
        filterMap.put("\"", "&quot;");
        filterMap.put("\\'", "&apos;");
        filterMap.put("？", "&iexcl;");
        filterMap.put("?", "&not;");
        filterMap.put(",", "");
        filterMap.put(".", "");
        filterMap.put("/", "");
        filterMap.put("\r", "");
        filterMap.put("\n", "");
    }

    private ParameterFilterUtils() {
        throw new UnsupportedOperationException("不允许创建实例");
    }

    public static String filterParameter(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return parameter;
        }

        for (Map.Entry<String, String> entry : filterMap.entrySet()) {
            if (parameter.contains(entry.getKey())) {
                parameter = parameter.replaceAll(entry.getKey(), entry.getValue());
            }
        }
        return parameter;
    }

    public static String escapeParameter(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return parameter;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : parameter.toCharArray()) {
            stringBuilder.append("/").append(character);
        }
        return stringBuilder.toString();
    }

}
