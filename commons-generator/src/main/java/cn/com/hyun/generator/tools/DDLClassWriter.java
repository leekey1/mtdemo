package cn.com.hyun.generator.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 * 
 * @author test
 */
public class DDLClassWriter extends ClassWriter {

	public DDLClassWriter(InputInfo inputInfo) {
		super(inputInfo);
	}

	protected void createContext() {
		SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd");
		EventInfo eventInfo = (EventInfo) inputInfo;
		this.context = new ReplaceContext();
		this.context.setReplaceInfo("TableName", eventInfo.getTableEnname());
		this.context.setReplaceInfo("TABLZNENAME", eventInfo.getTableJpname());
		String pkey = "";
		List<Attribute> attrList = eventInfo.getAttrList();
		for (Attribute attr : attrList) {
			if (attr.isDbpkey()) {
				if (pkey.length() > 0) {
					pkey = pkey + "," + attr.getDbencolumn();
				} else {
					pkey = pkey + attr.getDbencolumn();
				}

			}
		}
		this.context.setReplaceInfo("PKEY", pkey);
	}

	protected void writeField(IndentWriter writer, ClassTemplatePart template) {
		if (template.isDynamicFieldPartflg()) {
			EventInfo eventInfo = (EventInfo) inputInfo;
			String dynamicString = template.getDynamicTemplate();
			String[] expressionStrings = dynamicString.split(":");
			String headerstring = expressionStrings[0].trim();
			String[] hedadrMethod = headerstring.split("[|]");
			String tempExpress = expressionStrings[1].trim();

			List<Attribute> attrList = eventInfo.getAttrList();
			for (Attribute attr : attrList) {
				String methodString = ReplaceUtil.replace(tempExpress,
						this.context.createRelace("FIELDTYPE", attr.getFieldType()));
				String dbencolumn = attr.getDbencolumn();
				String fieldStatment = ReplaceUtil.replace(methodString,
						this.context.createRelace("FIELDNAME", dbencolumn));
				String length = attr.getFieldLength();
				if (length == null || "".equals(length)) {
					length = "";
				} else if (length.indexOf(".") > 0) {
					length = "(" + length.substring(0, length.indexOf(".")) + ")";
				} else {
					length = "(" + length + ")";
				}
				fieldStatment = ReplaceUtil.replace(fieldStatment, this.context.createRelace("FIELDLENGTH", length));
				String fieldnull = attr.getFieldNull();
				if (fieldnull == null || "".equals(fieldnull)) {
					fieldnull = "DEFAULT NULL";
				}
				fieldStatment = ReplaceUtil.replace(fieldStatment, this.context.createRelace("CUSTCONTENT", fieldnull));
				String AUTO_INCREMENT = "";
				if ("ID".equals(dbencolumn)) {
					AUTO_INCREMENT = "AUTO_INCREMENT";
				}
				fieldStatment = ReplaceUtil.replace(fieldStatment,
						this.context.createRelace("AUTO_INCREMENT", AUTO_INCREMENT));
				fieldStatment = ReplaceUtil.replace(fieldStatment,
						this.context.createRelace("FIELDZNNAME", attr.getDbjpcolumn()));

				writer.println(fieldStatment);
			}

		}
	}

	protected void writeMethod(IndentWriter writer, ClassTemplatePart template) {

	}

	private void writeGetMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		String[] getset = getMethodString.split("@");
		String getExpressSource = getset[0];
		String setgetExpressSource = getset[1];
		String[] getexpress = getExpressSource.split("[|]");

		String[] setexpress = setgetExpressSource.split("[|]");
		for (Attribute attr : attrList) {
			writer.println(TAB + "/**获取" + attr.getDbjpcolumn() + "*/");
			for (String statment : getexpress) {
				String methodString = ReplaceUtil.replace(statment,
						this.context.createRelace("FIELDTYPE", attr.getMsAttributeType()));
				methodString = ReplaceUtil.replace(methodString,
						this.context.createRelace("FIELDMETHODNAME", attr.getAttrMethodName()));
				String fieldStatment = ReplaceUtil.replace(methodString,
						this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
				writer.println(TAB + fieldStatment);
			}
			writer.println();
			writer.println(TAB + "/**设置" + attr.getDbjpcolumn() + "*/");
			for (String statment : setexpress) {
				String methodString = ReplaceUtil.replace(statment,
						this.context.createRelace("FIELDTYPE", attr.getMsAttributeType()));
				methodString = ReplaceUtil.replace(methodString,
						this.context.createRelace("FIELDMETHODNAME", attr.getAttrMethodName()));
				String fieldStatment = ReplaceUtil.replace(methodString,
						this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
				writer.println(TAB + fieldStatment);
			}
		}
	}

	private void writeSetMethod(IndentWriter writer, List<Attribute> attrList, String setMethodString) {
		String[] express = setMethodString.split("[|]");
		for (Attribute attr : attrList) {
			writer.println(TAB + "/**" + attr.getDbjpcolumn() + "をセットする*/");
			for (String statment : express) {
				String methodString = ReplaceUtil.replace(statment,
						this.context.createRelace("FIELDTYPE", attr.getMsAttributeType()));
				methodString = ReplaceUtil.replace(methodString,
						this.context.createRelace("FIELDMETHODNAME", attr.getAttrMethodName()));
				String fieldStatment = ReplaceUtil.replace(methodString,
						this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
				writer.println(TAB + fieldStatment);
			}
		}
	}

	private void writeExternalMethod(IndentWriter writer, List<Attribute> attrList, String getMethodString) {
		for (Attribute attr : attrList) {
			String pofColType = TypeUtil.getTypeObject(attr.getPofColumType());
			String methodString = ReplaceUtil.replace(getMethodString,
					this.context.createRelace("FIELDTYPE", pofColType));
			String fieldStatment = ReplaceUtil.replace(methodString,
					this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
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
			String fieldStatment = ReplaceUtil.replace(statments[1],
					this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
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
			String fieldStatment = ReplaceUtil.replace(statments[1],
					this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
			fieldStatment = TypeUtil.typeToString(attr.getPofColumType(), fieldStatment);
			fieldStatment = TAB + PLUS + DOUBLE_QUOT + AT + DOUBLE_QUOT + PLUS + fieldStatment;
			writer.println(TAB + fieldStatment);
			count++;
		}
		for (Attribute attr : subk2list) {
			String fieldStatment = ReplaceUtil.replace(statments[1],
					this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
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
			writer.println(TAB + "/**親POFをセットする*/");
		} else if (getMethodString.indexOf("get") != -1) {
			writer.println(TAB + "/**親POFを取得する*/");
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
			String fieldStatment = ReplaceUtil.replace(getMethodString,
					this.context.createRelace("FIELDNAME", attr.getMsAttributeName()));
			writer.println(fieldStatment);
		}
	}

	@Override
	protected void writeImport(IndentWriter writer, ClassTemplatePart template) {
		EventInfo eventInfo = (EventInfo) inputInfo;
		List<Attribute> attrList = eventInfo.getAttrList();
		Map typeMap = new HashMap();
		for (Attribute attr : attrList) {
			String importString = TypeUtil.getAllType(attr.getMsAttributeType());

			if (!"".equals(importString) && !typeMap.containsKey(attr.getMsAttributeType())) {
				writer.println(IMPORT + importString + SEMICOLON);
				typeMap.put(attr.getMsAttributeType(), attr.getMsAttributeType());
				// break;
			}
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
			if (!"".equals(eventInfo.getCondition())
					&& eventInfo.getCondition().toLowerCase().indexOf("select") != -1) {
				writer.println(TAB + TAB + TAB + TAB + "query=\"" + eventInfo.getCondition() + "\",");
				// } else if (!"".equals(eventInfo.getCondition()) &&
				// eventInfo.getCondition().toLowerCase().indexOf("select") ==
				// -1) {
				//
			} else {
				writer.println(TAB + TAB + TAB + TAB + "query=\"select * from " + eventInfo.getTableEnname()
						+ " where ora_hash(PKEY, ?) = ? \",");
			}

			writer.println(TAB + TAB + TAB + TAB + "resultClass=" + eventInfo.getEventName() + "Pof.class");
			writer.println(TAB + TAB + ")");
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
					writer.println(TAB + TAB + "if (" + compareString[0] + ")" + " != 0) {");
					writer.println(TAB + TAB + TAB + "result = " + compareString[0] + ")" + ";");
				} else if (compareString != null && compareString.length == 2) {
					writer.println(TAB + TAB + "if (" + compareString[0] + ") {");
					writer.println(TAB + TAB + TAB + "result = " + "1" + ";");
					writer.println(TAB + TAB + "} else if (" + compareString[1] + ") {");
					writer.println(TAB + TAB + TAB + "result = " + "-1" + ";");
				}

			} else {
				if (compareString != null && compareString.length == 1) {
					writer.println(TAB + TAB + "} else if (" + compareString[0] + ")" + " != 0) {");
					writer.println(TAB + TAB + TAB + "result = " + compareString[0] + ")" + ";");
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
				writer.println(TAB + TAB + "} else if (" + compareString[0] + ")" + " != 0) {");
				writer.println(TAB + TAB + TAB + "result = " + compareString[0] + ")" + ";");
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
				writer.println(TAB + TAB + TAB + "result = " + compareString[0] + ")" + ";");
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
			compareString[0] = "this." + attr.getMsAttributeName() + ".compareTo(" + eventInfo.getEventNameVar()
					+ "Pof." + "get" + attr.getAttrMethodName() + "()";
		} else if ("double".equals(attr.getPofColumType()) || "long".equals(attr.getPofColumType())
				|| "int".equals(attr.getPofColumType())) {
			compareString = new String[2];
			compareString[0] = "this." + attr.getMsAttributeName() + " > " + eventInfo.getEventNameVar() + "Pof."
					+ "get" + attr.getAttrMethodName() + "()";
			compareString[1] = "this." + attr.getMsAttributeName() + " < " + eventInfo.getEventNameVar() + "Pof."
					+ "get" + attr.getAttrMethodName() + "()";
		}
		return compareString;
	}
}
