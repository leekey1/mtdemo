package cn.com.hyun.generator.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * 
 * @author test
 */
public class RepositoryClassWriter extends ClassWriter {

	private boolean lostCacheflag = false;

	public void setLostCacheflag(boolean lostCacheflag) {
		this.lostCacheflag = lostCacheflag;
	}

	public RepositoryClassWriter(InputInfo inputInfo) {
		super(inputInfo);
	}
	
	protected void createContext() {
		EventInfo eventInfo = (EventInfo) inputInfo;
		String eventname = eventInfo.getEventName();

		String bpkeys = "";
		String paramkeys = "";
		int count = 0;
		List<Attribute> attrList = eventInfo.getAttrList();
		for (Attribute attr : attrList) {
			if (attr.isDbpkey()) {
				count++;
				if (bpkeys.length() == 0) {
					//@Param("processDefCd") String processDefCd
					bpkeys = "@Param(\"" + attr.getMsAttributeName() + "\")" + " String " + attr.getMsAttributeName();
					paramkeys = "@param " + attr.getMsAttributeName();
				} else {
					bpkeys = bpkeys +", " + "@Param(\"" + attr.getMsAttributeName() + "\")" + " String " + attr.getMsAttributeName();
					paramkeys = paramkeys +"\n     " + "* @param" + attr.getMsAttributeName();
				}
				
			}
		}
		
		SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd");
		String classname = eventInfo.getEventName();
		this.context = new ReplaceContext();
		this.context.setReplaceInfo("EventName", eventname);
		this.context.setReplaceInfo("EventNameVar", eventInfo.getEventNameVar());
		this.context.setReplaceInfo("ClassName", classname);
		this.context.setReplaceInfo("author", "test");
		this.context.setReplaceInfo("TableName", eventInfo.getTableEnname());
		this.context.setReplaceInfo("date", formator.format(new Date()));
		this.context.setReplaceInfo("EventJpName", eventInfo.getTableJpname());
		this.context.setReplaceInfo("KEY", eventInfo.getKeyAttr());
		this.context.setReplaceInfo("DOMAIN", eventInfo.getDomain());
		this.context.setReplaceInfo("PACKAGE_NAME", PACKAGE_NAME);
		this.context.setReplaceInfo("MULITIKEY", bpkeys);
		this.context.setReplaceInfo("MULITIPARAM", paramkeys);
		
	}
	@Override
	protected void writeField(IndentWriter writer, ClassTemplatePart template) {
		if(template.isDynamicFieldPartflg()) {
			String dynamicString = template.getDynamicTemplate();
			String[] expressionStrings = dynamicString.split(":");
			String tempExpress = expressionStrings[1].trim();

			EventInfo eventInfo = (EventInfo) inputInfo;
		    String methodString = ReplaceUtil.replace(tempExpress, this.context.createRelace("CacheName", eventInfo.getCacheName()));
			writer.println(TAB + methodString);

		}
	}
	@Override
	protected void writeMethod(IndentWriter writer, ClassTemplatePart template) {
		if(template.isDynamicMethodPartflg()) {
			String dynamicString = template.getDynamicTemplate();
			String[] expressionStrings = dynamicString.split(":");
			String headerstring = expressionStrings[0].trim();
			String tempExpress = expressionStrings[1];
			String[] hedadrMethod = headerstring.split("[|]");
			EventInfo eventInfo = (EventInfo) inputInfo;
			List<Attribute> attrList = eventInfo.getAttrList();
			if ("find".equals(hedadrMethod[1])) {
				writeFindMethod(writer, attrList, tempExpress);
			} else if ("mulitkey".equals(hedadrMethod[1])) {
				writeMulitkeyMethod(writer, attrList, tempExpress);
			}
		}
	}

	@Override
	protected void writeImport(IndentWriter writer, ClassTemplatePart template) {	
	}
	private void writeFindMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		EventInfo eventInfo = (EventInfo) inputInfo;
		String[] express = getMethodString.split("[|]");

		if (eventInfo.getBpkeyAttr() != null && !"".equals(eventInfo.getBpkeyAttr())) {
			for (String statment : express) {
				String methodString = ReplaceUtil.replace(statment, this.context.createRelace("BPKEY", eventInfo.getBpkeyAttr()));
				methodString = ReplaceUtil.replace(methodString, this.context.createRelace("BPKEYM", eventInfo.getBpkeyMeAttr()));
				methodString = ReplaceUtil.replace(methodString, this.context.createRelace("EventName", eventInfo.getEventName()));
				writer.println(methodString);
			}
		}
	}
	private void writeMulitkeyMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		String bpkeys = "";
		String paramkeys = "";
		int count = 0;
		for (Attribute attr : attrList) {
			if (attr.isDbpkey()) {
				count++;
				if (bpkeys.length() == 0) {
					//@Param("processDefCd") String processDefCd
					bpkeys = "@Param(\"" + attr.getMsAttributeName() + "\")" + " String " + attr.getMsAttributeName();
					paramkeys = "@param " + attr.getMsAttributeName();
				} else {
					bpkeys = bpkeys +", " + "@Param(\"" + attr.getMsAttributeName() + "\")" + " String " + attr.getMsAttributeName();
					paramkeys = paramkeys +"\n     " + "* @param" + attr.getMsAttributeName();
				}
				
			}
		}
		if (count > 1) {
			EventInfo eventInfo = (EventInfo) inputInfo;
			String[] express = getMethodString.split("[|]");

				for (String statment : express) {
					String methodString = ReplaceUtil.replace(statment, this.context.createRelace("PARAMKEYS", paramkeys));
					methodString = ReplaceUtil.replace(methodString, this.context.createRelace("BPKEYS", bpkeys));
					methodString = ReplaceUtil.replace(methodString, this.context.createRelace("EventName", eventInfo.getEventName()));
					writer.println(methodString);
				}		
		}

	}
}
