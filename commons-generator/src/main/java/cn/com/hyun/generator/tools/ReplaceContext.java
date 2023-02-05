package cn.com.hyun.generator.tools;

import java.util.ArrayList;

public class ReplaceContext {
	
    protected ArrayList<String[]> infoList = new ArrayList<String[]>();

	public Object[] getReplaceInfo() {

		return infoList.toArray();
	}

	public void setReplaceInfo(String orgstr, String replace) {
		String tempOrgstr = "\\{" + orgstr + "\\}";
		String[] tempArray = new String[2];
		tempArray[0] = tempOrgstr;
		tempArray[1] = replace;
		infoList.add(tempArray);
	}

	public Object[] createRelace(String orgstr, String replace) {
		ArrayList<String[]> tmpList = new ArrayList<String[]>();
		String tempOrgstr = "\\{" + orgstr + "\\}";
		String[] tempArray = new String[2];
		tempArray[0] = tempOrgstr;
		tempArray[1] = replace;
		tmpList.add(tempArray);
		return tmpList.toArray();
	}
    
}
