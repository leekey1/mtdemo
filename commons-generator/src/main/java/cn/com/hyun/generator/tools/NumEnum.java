package cn.com.hyun.generator.tools;

public enum NumEnum {
    NumEnum_1("1","one"),
    NumEnum_2("2","two"),
    NumEnum_3("3","three"),
    NumEnum_4("4","four"),
    NumEnum_5("5", "five");
    private String key;
    private String value;

    NumEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static boolean isNotHave(String key) {
        return !isHave(key);
    }

    public static boolean isHave(String key) {
        return NumEnum.getByKey(key) != null ;
    }

    public static NumEnum getByKey(String key) {
        for (NumEnum raiseMethod: NumEnum.values()) {
            if (raiseMethod.getKey().equals(key)) {
                return raiseMethod;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}