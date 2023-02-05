package cn.com.hyun.generator.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceUtil {
	public static String replace(String orgstr, Object[] relaceArray) {
		String tempOrgstr = orgstr;
		for (int i=0; i < relaceArray.length; i++) {
			String[] result = (String[]) relaceArray[i];
			Pattern pattern = Pattern.compile(result[0]);
			Matcher matcher = pattern.matcher(tempOrgstr);
			tempOrgstr = matcher.replaceAll(result[1]);
		}
		return tempOrgstr;
	}
}
