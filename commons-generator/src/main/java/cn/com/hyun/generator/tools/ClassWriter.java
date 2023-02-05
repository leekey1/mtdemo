package cn.com.hyun.generator.tools;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * 
 * @author test
 */
public abstract class ClassWriter implements Symbols, Keywords {
	protected String correlatorId;
    protected ReplaceContext context;
    protected InputInfo inputInfo;
    protected List<InputInfo> inputlist;
    protected String currentPath;
    protected int classtype = 1;
    protected String accesstype;
    public ClassWriter(InputInfo inputInfo) {
    	this.inputInfo = inputInfo;
    }
    public ClassWriter(List<InputInfo> inputlist) {
    	this.inputlist = inputlist;
    }
	public void write(IndentWriter writer, List<ClassTemplatePart> clsTmpList) {
		createContext();
        for (ClassTemplatePart tempPart: clsTmpList) {
        	
        	if (tempPart.isDynamicFieldPartflg()) {
        		writeField(writer, tempPart);
        	} else if (tempPart.isDynamicMethodPartflg()) {
        		writeMethod(writer, tempPart);
        	} else if (tempPart.isDynamicImportPartflg()) {
        		writeImport(writer, tempPart);
        	} else if (tempPart.isDynamicAnnotationPartflg()) {
        		writeAnnotation(writer, tempPart);        		
        	} else {
        		tempPart.writeTemplateClass(writer, context);
        	}
        }
	}

	protected abstract void createContext();
	protected abstract void writeImport(IndentWriter writer, ClassTemplatePart template);
	protected abstract void writeField(IndentWriter writer, ClassTemplatePart template);
	protected abstract void writeMethod(IndentWriter writer, ClassTemplatePart template);
	protected void writeAnnotation(IndentWriter writer, ClassTemplatePart template) {
		
	}

	public void setClasstype(int classtype) {
		this.classtype = classtype;
	}
	public String getCorrelatorId() {
		return correlatorId;
	}

	public void setCorrelatorId(String correlatorId) {
		this.correlatorId = correlatorId;
	}
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}
	public String getAccesstype() {
		return accesstype;
	}
	public void setAccesstype(String accesstype) {
		this.accesstype = accesstype;
	}
	
}
