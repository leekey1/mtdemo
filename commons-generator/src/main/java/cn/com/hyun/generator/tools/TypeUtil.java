package cn.com.hyun.generator.tools;


import java.util.List;

public class TypeUtil implements Keywords, Symbols {
    public static String typeToString(String type, String field) {
    	if (JAVA_STRING.equals(type)) {
    		return field;
    	} else if (LONG.equals(type)) {
    		String newField = STRING_VALUEOF + field + PARENTH_R;
    		return newField;
    	} else if (JAVA_BIGDECIMAL.equals(type)) {
    		String newField =  field + TOPLAINSTRING;
    		return newField;
    		
    	} else {
    		return field;
    	}
    }
    public static String convertPofTypeToJmon(String poftype, String jmontype, String field) {
    	if (JAVA_STRING.equals(poftype)) {
    		return field;
    	} else if (LONG.equals(poftype) && "string".equals(jmontype)) {
    		return typeToString(poftype, field);
    	} else if (JAVA_BIGDECIMAL.equals(poftype) && "string".equals(jmontype)) {
    		String newField =  field + TOPLAINSTRING;
    		return newField;
    	} else if (JAVA_BIGDECIMAL.equals(poftype) && "float".equals(jmontype)) {
    		String newField =  field + DOUBLEVALUE;
    		return newField;
    	} else {
    		return field;
    	}
    }
    public static String convertJmonTypeToPof(String jmontype, String poftype, String field) {
    	if (JAVA_STRING.equals(poftype)) {
    		return field;
    	} else if (LONG.equals(poftype) && "string".equals(jmontype)) {
    		return StringToType(poftype, field);
    	} else if (JAVA_BIGDECIMAL.equals(poftype)) {
    		String newField =  NEW + JAVA_BIGDECIMAL + PARENTH_L + field + PARENTH_R;
    		return newField;
    	} else {
    		return field;
    	}
    }
    public static String getAllType(String type) {
    	if (JAVA_BIGDECIMAL.equals(type)) {
    		String newField =  "java.math.BigDecimal";
    		return newField;
    	} else if ("Date".equals(type)) {
        		String newField =  "java.util.Date";
        		return newField;
    	} else {
    		return "";
    	}
    	
    }
    public static String StringToType(String type, String field) {
    	if (JAVA_STRING.equals(type)) {
    		return field;
    	} else if (LONG.equals(type)) {
    		String newField = LONG_VALUEOF + field + PARENTH_R;
    		return newField;
    	} else if (JAVA_BIGDECIMAL.equals(type)) {
    		String newField =  NEW + JAVA_BIGDECIMAL + PARENTH_L + field + PARENTH_R;
    		return newField;
    	} else {
    		return field;
    	}
    }
    public static String convertMs2JavaType(String type) {
    	if ("string".equals(type)) {
    		return JAVA_STRING;
    	} else if ("integer".equals(type)) {
    		return LONG;
    	} else if ("float".equals(type)) {
    		return "double";
    	} else {
    		return type;
    	}
    }
    public static String getTypeObject(String type) {
    	if (JAVA_BIGDECIMAL.equals(type)) {
    		return JAVA_BIGDECIMAL;
    	} else if (LONG.equals(type)) {
    		return "Long";
    	} else if (INT.equals(type)) {
    		return "Int";
    	} else {
    		return type;
    	}
    }
    public static String dbtypeTojavatype(String type, int length, boolean flatflag) {
    	if ("VARCHAR2".equals(type) || "VARCHAR".equals(type) || "CHAR".equals(type)) {
    		return "String";
    	} else if ("DATE".equals(type)||"DATETIME".equals(type)) {
    		return "Date";
    	} else if ("NUMBER".equals(type) && length <= 10 && !flatflag) {
    		return "Integer";
    	} else if ("NUMBER".equals(type) && length > 10 && !flatflag) {
    		return "Long";
    	} else if ("NUMBER".equals(type) && flatflag) {
    		return "BigDecimal";
    	} else if ("CLOB".equals(type)) {
    		return "String";
    	} else {
    		return "";
    	}
    }
    public static String dbtypeToMySqltype(String type, int length, boolean flatflag) {
    	if ("VARCHAR2".equals(type)) {
    		return "VARCHAR";
    	} else if ("DATE".equals(type)) {
    		return "datetime";
    	} else if ("NUMBER".equals(type) && length <= 10 && !flatflag) {
    		return "int";
    	} else if ("NUMBER".equals(type) && length > 10 && !flatflag) {
    		return "bigint";
    	} else if ("NUMBER".equals(type) && flatflag) {
    		return "decimal";
    	} else if ("CLOB".equals(type)) {
    		return "LONGTEXT";
    	} else {
    		return type;
    	}
    }
    public static String[] getSubKeyTypeForJava(EventInfo eventInfo) {
    	String[] subkeytype = new String[2];
        int countsubkey1 = 0;
        int countsubkey2 = 0;
        String subkey1type = "String";
        String subkey2type = "String";
        String tempsubkey1type = "";
        String tempsubkey2type = "";
        if (Constants.PATTERN_B.equals(eventInfo.getPatternId())
        		|| Constants.PATTERN_C.equals(eventInfo.getPatternId())) {
    		List<Attribute> attrList = eventInfo.getAttrList();
    		for (Attribute attr : attrList) {
    			if (attr.isMsSubKey()) {
    				
    				if ("float".equals(attr.getMsAttributeType())) {
    					countsubkey1++;
    					tempsubkey1type = "Double";
    				} else if ("integer".equals(attr.getMsAttributeType())) {
    					countsubkey1++;
    					tempsubkey1type = "Long";
    				}
    			}
    			if (attr.isMsSubKey2()) {
    				
    				if ("float".equals(attr.getMsAttributeType())) {
    					countsubkey2++;
    					tempsubkey2type = "Double";
    				} else if ("integer".equals(attr.getMsAttributeType())) {
    					countsubkey2++;
    					tempsubkey2type = "Long";
    				}
    			}
    		}
    		if (countsubkey1 == 1) {
    			subkey1type = tempsubkey1type;
    		}
    		if (countsubkey2 == 1) {
    			subkey2type = tempsubkey2type;
    		}
        }
        subkeytype[0] = subkey1type;
        subkeytype[1] = subkey2type;
        return subkeytype;
    } 
    public static String[] getSubKeyTypeForMs(EventInfo eventInfo) {
    	String[] subkeytype = new String[2];
        int countsubkey1 = 0;
        int countsubkey2 = 0;
        String subkey1type = "string";
        String subkey2type = "string";
        String tempsubkey1type = "";
        String tempsubkey2type = "";
        if (Constants.PATTERN_B.equals(eventInfo.getPatternId())
        		|| Constants.PATTERN_C.equals(eventInfo.getPatternId())) {
    		List<Attribute> attrList = eventInfo.getAttrList();
    		for (Attribute attr : attrList) {
    			if (attr.isMsSubKey()) {
    				
    				if ("float".equals(attr.getMsAttributeType())) {
    					countsubkey1++;
    					tempsubkey1type = "float";
    				} else if ("integer".equals(attr.getMsAttributeType())) {
    					countsubkey1++;
    					tempsubkey1type = "integer";
    				}
    			}
    			if (attr.isMsSubKey2()) {
    				
    				if ("float".equals(attr.getMsAttributeType())) {
    					countsubkey2++;
    					tempsubkey2type = "float";
    				} else if ("integer".equals(attr.getMsAttributeType())) {
    					countsubkey2++;
    					tempsubkey2type = "integer";
    				}
    			}
    		}
    		if (countsubkey1 == 1) {
    			subkey1type = tempsubkey1type;
    		}
    		if (countsubkey2 == 1) {
    			subkey2type = tempsubkey2type;
    		}
        }
        subkeytype[0] = subkey1type;
        subkeytype[1] = subkey2type;
        return subkeytype;
    }
}
