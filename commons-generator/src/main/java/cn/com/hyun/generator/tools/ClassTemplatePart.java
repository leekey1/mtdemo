package cn.com.hyun.generator.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ClassTemplatePart {
	public ClassTemplatePart(){}
	private String calssName;
	private List<String> templateParts = new ArrayList<String>();
	private boolean dynamicFieldPartflg = false;
	private boolean dynamicMethodPartflg = false;
	private boolean dynamicImportPartflg = false;
	private boolean dynamicAnnotationPartflg = false;
	private String dynamicTemplate;
	private static Random rand = new Random(10);
	public String getDynamicTemplate() {
		return dynamicTemplate;
	}
	public void setDynamicTemplate(String dynamicTemplate) {
		this.dynamicTemplate = dynamicTemplate;
	}
	public boolean isDynamicFieldPartflg() {
		return dynamicFieldPartflg;
	}
	public void setDynamicFieldPartflg(boolean dynamicFieldPartflg) {
		this.dynamicFieldPartflg = dynamicFieldPartflg;
	}
	public boolean isDynamicMethodPartflg() {
		return dynamicMethodPartflg;
	}
	public void setDynamicMethodPartflg(boolean dynamicMethodPartflg) {
		this.dynamicMethodPartflg = dynamicMethodPartflg;
	}
	public List<String> getTemplateParts() {
		return templateParts;
	}
	public void setTemplateParts(List<String> templateParts) {
		this.templateParts = templateParts;
	}
	public String getCalssName() {
		return calssName;
	}
	public void setCalssName(String calssName) {
		this.calssName = calssName;
	}
    public boolean isDynamicImportPartflg() {
		return dynamicImportPartflg;
	}
	public void setDynamicImportPartflg(boolean dynamicImportPartflg) {
		this.dynamicImportPartflg = dynamicImportPartflg;
	}
	
	public boolean isDynamicAnnotationPartflg() {
		return dynamicAnnotationPartflg;
	}
	public void setDynamicAnnotationPartflg(boolean dynamicAnnotationPartflg) {
		this.dynamicAnnotationPartflg = dynamicAnnotationPartflg;
	}
	public void writeTemplateClass(IndentWriter writer, ReplaceContext context) {
		
        for (String line: templateParts) {
        	//context.setReplaceInfo("SerialVersionUID", String.valueOf(rand.nextLong()));
        	line = ReplaceUtil.replace(line, context.createRelace("SerialVersionUID", String.valueOf(rand.nextLong())));
        	String newline = ReplaceUtil.replace(line, context.getReplaceInfo());
        	writer.println(newline);
        }
    }
}