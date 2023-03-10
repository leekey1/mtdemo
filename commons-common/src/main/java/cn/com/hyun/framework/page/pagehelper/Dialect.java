package cn.com.hyun.framework.page.pagehelper;

public enum Dialect {
    mysql, mariadb, sqlite, oracle, hsqldb, postgresql, sqlserver, db2, informix, h2, sqlserver2012;

    public static Dialect of(String dialect) {
        try {
            Dialect d = Dialect.valueOf(dialect.toLowerCase());
            return d;
        } catch (IllegalArgumentException e) {
            String dialects = null;
            for (Dialect d : Dialect.values()) {
                if (dialects == null) {
                    dialects = d.toString();
                } else {
                    dialects += "," + d;
                }
            }
            throw new IllegalArgumentException("Mybatis分页插件dialect参数值错误，可选值为[" + dialects + "]");
        }
    }

    public static String[] dialects() {
        Dialect[] dialects = Dialect.values();
        String[] ds = new String[dialects.length];
        for (int i = 0; i < dialects.length; i++) {
            ds[i] = dialects[i].toString();
        }
        return ds;
    }

    public static String fromJdbcUrl(String jdbcUrl) {
        String[] dialects = dialects();
        for (String dialect : dialects) {
            if (jdbcUrl.indexOf(":" + dialect + ":") != -1) {
                return dialect;
            }
        }
        return null;
    }
}
