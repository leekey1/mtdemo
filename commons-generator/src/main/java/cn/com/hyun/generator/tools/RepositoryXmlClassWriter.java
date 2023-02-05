package cn.com.hyun.generator.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * 
 * @author test
 */
public class RepositoryXmlClassWriter extends ClassWriter {


	public RepositoryXmlClassWriter(InputInfo inputInfo) {
		super(inputInfo);
	}
	
	protected void createContext() {
		SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd");
		EventInfo eventInfo = (EventInfo) inputInfo;
		
		int count = 0;
		List<Attribute> attrList = eventInfo.getAttrList();
		for (Attribute attr : attrList) {
			if (attr.isDbpkey()) {
				count++;	
			}
		}
		String mulitiParam = "parameterType=\"string\"";
		if (count > 1) {
			mulitiParam = "";
		}
		
		this.context = new ReplaceContext();
		this.context.setReplaceInfo("EventName", eventInfo.getEventName());
		this.context.setReplaceInfo("EventNameVar", eventInfo.getEventNameVar());
		this.context.setReplaceInfo("author", "test");
		this.context.setReplaceInfo("TableName", eventInfo.getTableEnname());
		this.context.setReplaceInfo("date", formator.format(new Date()));
		this.context.setReplaceInfo("EventJpName", eventInfo.getTableJpname());
		this.context.setReplaceInfo("KEYFIELD", eventInfo.getKeyDbAttr());
		this.context.setReplaceInfo("KEYFIELDMETHODNAME", eventInfo.getKeyAttr());
		this.context.setReplaceInfo("DOMAIN", eventInfo.getDomain());
		this.context.setReplaceInfo("PACKAGE_NAME", PACKAGE_NAME);
		this.context.setReplaceInfo("MULITIPARAM", mulitiParam);

	}
	protected void writeField(IndentWriter writer, ClassTemplatePart template) {
		if(template.isDynamicFieldPartflg()) {
			EventInfo eventInfo = (EventInfo) inputInfo;
			String dynamicString = template.getDynamicTemplate();
			String[] expressionStrings = dynamicString.split(":");
			String headerstring = expressionStrings[0].trim();
			String[] hedadrMethod = headerstring.split("[|]");
			String tempExpress = expressionStrings[1];

				List<Attribute> attrList = eventInfo.getAttrList();
				int i = 0;
				for (Attribute attr : attrList) {
					i++;
					
					String methodString = ReplaceUtil.replace(tempExpress, this.context.createRelace("FIELDNAME", attr.getDbencolumn()));
					//String fieldStatment = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
					if (i == attrList.size()) {
						writer.println(methodString.replaceAll(",", ""));
					} else {
						writer.println(methodString);
					}
				}
//			}
		}
	}
	protected void writeMethod(IndentWriter writer, ClassTemplatePart template) {
		if(template.isDynamicMethodPartflg()) {
			String dynamicString = template.getDynamicTemplate();
			String[] expressionStrings = dynamicString.split(":");
			String headerstring = expressionStrings[0].trim();
			String tempExpress = expressionStrings[1];
			String[] hedadrMethod = headerstring.split("[|]");
			EventInfo eventInfo = (EventInfo) inputInfo;
			List<Attribute> attrList = eventInfo.getAttrList();
			if ("insertValue".equals(hedadrMethod[1])) {
				writeInsertValue(writer, attrList, tempExpress);		
			} else if ("dynamicUpdate".equals(hedadrMethod[1])) {
				writedynamicUpdate(writer, attrList, tempExpress);
			} else if ("update".equals(hedadrMethod[1])) {
				writeUpdate(writer, attrList, tempExpress);
			} else if ("find".equals(hedadrMethod[1])) {
				writeFindMethod(writer, attrList, tempExpress);
			} else if("mulitiKey".equals(hedadrMethod[1])) {
				writeMulitkeyMethod(writer, attrList, tempExpress);
			} else if ("deleteWhere".equals(hedadrMethod[1])
					|| "suspendWhere".equals(hedadrMethod[1])
					|| "updateWhere".equals(hedadrMethod[1])
					|| "dynamicUpdateWhere".equals(hedadrMethod[1])) {
				writeKeyWhereMethod(writer, attrList, tempExpress);
			}

		}
	}
	private void writeFindMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		EventInfo eventInfo = (EventInfo) inputInfo;
		String[] express = getMethodString.split("[|]");

		if (eventInfo.getBpkeyAttr() != null && !"".equals(eventInfo.getBpkeyAttr())) {
			for (String statment : express) {
				String methodString = ReplaceUtil.replace(statment, this.context.createRelace("BPKEY", eventInfo.getBpkeyAttr()));
				methodString = ReplaceUtil.replace(methodString, this.context.createRelace("EventName", eventInfo.getEventName()));
				methodString = ReplaceUtil.replace(methodString, this.context.createRelace("DBBPKEY", eventInfo.getBpkeyDbAttr()));
				methodString = ReplaceUtil.replace(methodString, this.context.createRelace("TableName", eventInfo.getTableEnname()));
				methodString = ReplaceUtil.replace(methodString, this.context.createRelace("BPKEYM", eventInfo.getBpkeyMeAttr()));
				writer.println(methodString);
			}
		}
	}
	private void writeInsertValue(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		//String[] express = getMethodString.split("[|]");
		int i = 0;
		System.out.println("----------------------------------------------------------------------------------------------------------------- ");
		System.out.println("状态          " + "类型           " + "字段      "+"            字段名称");
		System.out.println("----------------------------------------------------------------------------------------------------------------- ");
		for (Attribute attr : attrList) {
			i++;
			System.out.println("已生成        " + attr.getMsAttributeType() + "         "+attr.getMsAttributeName()+"             "+attr.getDbjpcolumn());
			String methodString = ReplaceUtil.replace(getMethodString, this.context.createRelace("FIELDMETHODNAME", attr.getMsAttributeName()));
			//String fieldStatment = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
			if (i == attrList.size()) {
				writer.println(methodString.replaceAll(",", ""));
			} else {
				writer.println(methodString);
			}
		}
		System.out.println("共计 " + attrList.size()+"个字段");
		System.out.println("================================================================================================================= ");
//		for (Attribute attr : attrList) {
//			//writer.println(TAB + "/**" + attr.getDbjpcolumn() + "���擾����*/");
//			//writer.println(TAB + "@Column(name=\"" + attr.getDbencolumn() + "\")");
//			
//			for (String statment : express) {
//				String methodString = ReplaceUtil.replace(statment, this.context.createRelace("FIELDTYPE", attr.getPofColumType()));
//				methodString = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDMETHODNAME", attr.getAttrMethodName()));
//				String fieldStatment = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
//				
//				writer.println(TAB + fieldStatment);
//			}
//		}
	}
	private void writedynamicUpdate(IndentWriter writer, List<Attribute> attrList, String setMethodString) {
		String[] express = setMethodString.split("[|]");
		int i = 0;
		for (Attribute attr : attrList) {
			//writer.println(TAB + "/**" + attr.getDbjpcolumn() + "���Z�b�g����*/");
			i++;
			if (!attr.isDbpkey()) {
				
				for (String statment : express) {
					String methodString = ReplaceUtil.replace(statment, this.context.createRelace("FIELDTYPE", attr.getPofColumType()));
					methodString = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDMETHODNAME", attr.getMsAttributeName()));
					String fieldStatment = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDNAME", attr.getDbencolumn()));
										
					//String fieldStatment = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
					if (i == attrList.size()) {
						writer.println(fieldStatment.replaceAll(",", ""));
					} else {
						writer.println(fieldStatment);
					}
					//writer.println(fieldStatment);
				}				
			}
		}
	}
	private void writeUpdate(IndentWriter writer, List<Attribute> attrList, String setMethodString) {
		String[] express = setMethodString.split("[|]");
		int i = 0;
		for (Attribute attr : attrList) {
			i++;
			//writer.println(TAB + "/**" + attr.getDbjpcolumn() + "���Z�b�g����*/");
			if (!attr.isDbpkey()) {
				
				for (String statment : express) {
					String methodString = ReplaceUtil.replace(statment, this.context.createRelace("FIELDTYPE", attr.getPofColumType()));
					methodString = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDMETHODNAME", attr.getMsAttributeName()));
					String fieldStatment = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDNAME", attr.getDbencolumn()));
										
					//String fieldStatment = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
					if (i == attrList.size()) {
						writer.println(fieldStatment.replaceAll(",", ""));
					} else {
						writer.println(fieldStatment);
					}
					//writer.println(fieldStatment);
				}				
			}
		}
	}
	private void writeExternalMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		for (Attribute attr : attrList) {
			String pofColType = TypeUtil.getTypeObject(attr.getPofColumType());
			String methodString = ReplaceUtil.replace(getMethodString, this.context.createRelace("FIELDTYPE", pofColType));
			String fieldStatment = ReplaceUtil.replace(methodString, this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
			writer.println(TAB + fieldStatment);
		}
	}
	private void writeGetIdMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		String[] statments = getMethodString.split("[|]");
		int count = 0;
		List<Attribute> pklist = new ArrayList<Attribute>();
		List<Attribute> subklist = new ArrayList<Attribute>();
		List<Attribute> subk2list = new ArrayList<Attribute>();
		for (Attribute attr : attrList) {
			if (attr.isMsPkey()) {
				pklist.add(attr);
			}
			if (attr.isMsSubKey()) {
				subklist.add(attr);
			}
			if (attr.isMsSubKey2()) {
				subk2list.add(attr);
			}
		}
		for (Attribute attr : pklist) {
			String fieldStatment = ReplaceUtil.replace(statments[1], this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
			fieldStatment = TypeUtil.typeToString(attr.getPofColumType(), fieldStatment);
			if (count == 0) {
				fieldStatment = statments[0] + EQUAL + fieldStatment.trim();
			} else if (count > 0) {
				fieldStatment = TAB + PLUS + DOUBLE_QUOT + AT + DOUBLE_QUOT + PLUS + fieldStatment;
			}
			writer.println(TAB + fieldStatment);
			count++;
		}
		for (Attribute attr : subklist) {
			String fieldStatment = ReplaceUtil.replace(statments[1], this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
			fieldStatment = TypeUtil.typeToString(attr.getPofColumType(), fieldStatment);
			fieldStatment = TAB + PLUS + DOUBLE_QUOT + AT + DOUBLE_QUOT + PLUS + fieldStatment;
			writer.println(TAB + fieldStatment);
			count++;
		}
		for (Attribute attr : subk2list) {
			String fieldStatment = ReplaceUtil.replace(statments[1], this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
			fieldStatment = TypeUtil.typeToString(attr.getPofColumType(), fieldStatment);
			fieldStatment = TAB + PLUS + DOUBLE_QUOT + AT + DOUBLE_QUOT + PLUS + fieldStatment;
			writer.println(TAB + fieldStatment);
			count++;
		}
		writer.println(TAB + TAB + SEMICOLON);

	}
	private void writeParentMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		String newline = ReplaceUtil.replace(getMethodString, context.getReplaceInfo());
		String[] statments = newline.split("[|]");
		if (getMethodString.indexOf("set") != -1) {
			writer.println(TAB + "/**�ePOF���Z�b�g����*/");
		} else if (getMethodString.indexOf("get") != -1) {
			writer.println(TAB + "/**�ePOF���擾����*/");
		}

		for (String statment : statments) {
			writer.println(TAB + statment);
		}
	}
	private void writeParentField(IndentWriter writer, String getMethodString) {
		String newline = ReplaceUtil.replace(getMethodString, context.getReplaceInfo());
		writer.println(TAB + newline);

	}
	private void writeToStringMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		for (Attribute attr : attrList) {
			String fieldStatment = ReplaceUtil.replace(getMethodString, this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
			writer.println(fieldStatment);
		}
	}
	@Override
	protected void writeImport(IndentWriter writer, ClassTemplatePart template) {
		EventInfo eventInfo = (EventInfo) inputInfo;
		List<Attribute> attrList = eventInfo.getAttrList();
		for (Attribute attr : attrList) {
			String importString = TypeUtil.getAllType(attr.getPofColumType());
			if (!"".equals(importString)) {
				writer.println(IMPORT + importString + SEMICOLON);
				break;
			}
		}
		String patternId = eventInfo.getPatternId();
		if (Constants.PATTERN_A.equals(eventInfo.getCohepattern())
				&& Constants.PATTERN_D.equals(eventInfo.getPatternId())) {
			patternId = Constants.PATTERN_A;
		}
		if (!Constants.PATTERN_A.equals(patternId)) {
			writer.println();
			writer.println(IMPORT + "javax.persistence.JoinColumn" + SEMICOLON);
			writer.println(IMPORT + "javax.persistence.ManyToOne" + SEMICOLON);
		} else {
			writer.println(IMPORT + "javax.persistence.NamedNativeQueries" + SEMICOLON);
			writer.println(IMPORT + "javax.persistence.NamedNativeQuery" + SEMICOLON);
		}

	}
	
	protected void writeAnnotation(IndentWriter writer, ClassTemplatePart template) {
		EventInfo eventInfo = (EventInfo) inputInfo;
		String patternId = eventInfo.getPatternId();
		if (Constants.PATTERN_A.equals(eventInfo.getCohepattern())
				&& Constants.PATTERN_D.equals(eventInfo.getPatternId())) {
			patternId = Constants.PATTERN_A;
		}
		if (Constants.PATTERN_A.equals(patternId)) {
			writer.println("@NamedNativeQueries(");
			writer.println(TAB + TAB + "@NamedNativeQuery(");
			writer.println(TAB + TAB + TAB + TAB + "name=\"" + eventInfo.getEventName() + "Pof.Load\",");
			if (!"".equals(eventInfo.getCondition()) && eventInfo.getCondition().toLowerCase().indexOf("select") != -1) {
				writer.println(TAB + TAB + TAB + TAB + "query=\"" + eventInfo.getCondition() + "\",");
//			} else if (!"".equals(eventInfo.getCondition()) && eventInfo.getCondition().toLowerCase().indexOf("select") == -1) {
//				
			} else {
				writer.println(TAB + TAB + TAB + TAB + "query=\"select * from " + eventInfo.getTableEnname() + " where ora_hash(PKEY, ?) = ? \",");
			}
			
			writer.println(TAB + TAB + TAB + TAB + "resultClass=" + eventInfo.getEventName() + "Pof.class");
			writer.println(TAB + TAB +")");
			writer.println(")");
		}
	}
	private void writeCompareToMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		EventInfo eventInfo = (EventInfo) inputInfo;
		String[] statments = getMethodString.split("[|]");
		String tabstring = "";
		for (String statment : statments) {
			if (!"".equals(statment)) {
				writer.println(TAB + statment);
			} else if ("".equals(statment)) {
				tabstring = statment;
			}
			
		}

		int count = 0;
		List<Attribute> pklist = new ArrayList<Attribute>();
		List<Attribute> subklist = new ArrayList<Attribute>();
		List<Attribute> subk2list = new ArrayList<Attribute>();
		for (Attribute attr : attrList) {
			if (attr.isMsPkey()) {
				pklist.add(attr);
			}
			if (attr.isMsSubKey()) {
				subklist.add(attr);
			}
			if (attr.isMsSubKey2()) {
				subk2list.add(attr);
			}
		}
		for (Attribute attr : pklist) {
			String[] compareString = getCompareStament(eventInfo, attr);
			
			if (count == 0) {
				if (compareString != null && compareString.length == 1) {
					writer.println(TAB + TAB + "if (" + compareString[0] + ")" +  " != 0) {");
					writer.println(TAB + TAB + TAB + "result = " + compareString[0] + ")" +  ";");
				} else if (compareString != null && compareString.length == 2) {
					writer.println(TAB + TAB + "if (" + compareString[0] + ") {");
					writer.println(TAB + TAB + TAB + "result = " + "1" + ";");
					writer.println(TAB + TAB + "} else if (" + compareString[1] + ") {");
					writer.println(TAB + TAB + TAB + "result = " + "-1" + ";");
				}

				
			} else {
				if (compareString != null && compareString.length == 1) {
					writer.println(TAB + TAB + "} else if (" + compareString[0] + ")" +  " != 0) {");
					writer.println(TAB + TAB + TAB + "result = " + compareString[0] + ")" +  ";");
				} else if (compareString != null && compareString.length == 2) {
					writer.println(TAB + TAB + "} else if (" + compareString[0] + ") {");
					writer.println(TAB + TAB + TAB + "result = " + "1" + ";");
					writer.println(TAB + TAB + "} else if (" + compareString[1] + ") {");
					writer.println(TAB + TAB + TAB + "result = " + "-1" + ";");
				}
			}
			count++;
		}
		for (Attribute attr : subklist) {
			String[] compareString = getCompareStament(eventInfo, attr);
			
			if (compareString != null && compareString.length == 1) {
				writer.println(TAB + TAB + "} else if (" + compareString[0] + ")" +  " != 0) {");
				writer.println(TAB + TAB + TAB + "result = " + compareString[0] + ")" +  ";");
			} else if (compareString != null && compareString.length == 2) {
				writer.println(TAB + TAB + "} else if (" + compareString[0] + ") {");
				writer.println(TAB + TAB + TAB + "result = " + "1" + ";");
				writer.println(TAB + TAB + "} else if (" + compareString[1] + ") {");
				writer.println(TAB + TAB + TAB + "result = " + "-1" + ";");
			}

		}
		for (Attribute attr : subk2list) {
			String[] compareString = getCompareStament(eventInfo, attr);
			
			if (compareString != null && compareString.length == 1) {
				writer.println(TAB + TAB + "} else if (" + compareString[0] + ")" + " != 0) {");
				writer.println(TAB + TAB + TAB + "result = " + compareString[0] + ")" +  ";");
			} else if (compareString != null && compareString.length == 2) {
				writer.println(TAB + TAB + "} else if (" + compareString[0] + ") {");
				writer.println(TAB + TAB + TAB + "result = " + "1" + ";");
				writer.println(TAB + TAB + "} else if (" + compareString[1] + ") {");
				writer.println(TAB + TAB + TAB + "result = " + "-1" + ";");
			}
		}
		
		writer.println(TAB + TAB + "}");
		writer.println(TAB + TAB + "return result;");
		writer.println(TAB + "}");
	}
	
	private String[] getCompareStament(EventInfo eventInfo, Attribute attr) {
		String[] compareString = null;
		if ("String".equals(attr.getPofColumType()) || "BigDecimal".equals(attr.getPofColumType())) {
			compareString = new String[1];
			compareString[0] =  "this." + attr.getMsAttributeName() + ".compareTo(" + eventInfo.getEventNameVar() + "Pof." + "get" + attr.getAttrMethodName() + "()";
		} else if ("double".equals(attr.getPofColumType()) || "long".equals(attr.getPofColumType()) || "int".equals(attr.getPofColumType())) {
			compareString = new String[2];
			compareString[0] =  "this." + attr.getMsAttributeName() + " > " + eventInfo.getEventNameVar() + "Pof."  + "get" +  attr.getAttrMethodName() + "()";
			compareString[1] =  "this." + attr.getMsAttributeName() + " < " + eventInfo.getEventNameVar() + "Pof."  + "get" +  attr.getAttrMethodName() + "()";
		}
		return compareString;
	}
	
	private void writeMulitkeyMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		//{KEYFIELD} = #{{KEYFIELDMETHODNAME}}
		String bpkeys = "";
		String wherekey = "";
		int count = 0;
		for (Attribute attr : attrList) {
			if (attr.isDbpkey()) {
				count++;
				if (wherekey.length() == 0) {
					//@Param("processDefCd") String processDefCd
					wherekey = attr.getDbencolumn() + " = #{" + attr.getMsAttributeName() + "}";
				} else {
					wherekey = wherekey + " AND " + attr.getDbencolumn() + " = #{" + attr.getMsAttributeName() + "}";
				}
				
			}
		}
		if (count > 1) {
			EventInfo eventInfo = (EventInfo) inputInfo;
			String[] express = getMethodString.split("[|]");

				for (String statment : express) {
					String methodString = ReplaceUtil.replace(statment, this.context.createRelace("WHEREKEY", wherekey));
					methodString = ReplaceUtil.replace(methodString, this.context.createRelace("TableName", eventInfo.getTableEnname()));
					methodString = ReplaceUtil.replace(methodString, this.context.createRelace("EventName", eventInfo.getEventName()));
					writer.println(methodString);
				}		
		}

	}
	
	private void writeKeyWhereMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		//{KEYFIELD} = #{{KEYFIELDMETHODNAME}}
		String bpkeys = "";
		String wherekey = "";
		int count = 0;
		for (Attribute attr : attrList) {
			if (attr.isDbpkey()) {
				count++;
				if (wherekey.length() == 0) {
					//@Param("processDefCd") String processDefCd
					wherekey = attr.getDbencolumn() + " = #{" + attr.getMsAttributeName() + "}";
				} else {
					wherekey = wherekey + " AND " + attr.getDbencolumn() + " = #{" + attr.getMsAttributeName() + "}";
				}		
			}
		}
		EventInfo eventInfo = (EventInfo) inputInfo;
		String[] express = getMethodString.split("[|]");

		for (String statment : express) {
			String methodString = ReplaceUtil.replace(statment, this.context.createRelace("WHEREKEY", wherekey));
			writer.println(methodString);
		}
	}
}
