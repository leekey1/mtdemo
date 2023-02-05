package cn.com.hyun.generator.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 */
public class TemplateReader {
	
	private BufferedReader in;
	private boolean eof;
	
	// �p�[�c����؂�s�̐擪
	public static final String SEPARAT_LINE = "//--";
    private ArrayList<ClassTemplatePart>  classTemplateList = new  ArrayList<ClassTemplatePart>();
	public ArrayList<ClassTemplatePart> getClassTemplateList() {
		return classTemplateList;
	}
	public void setClassTemplateList(ArrayList<ClassTemplatePart> classTemplateList) {
		this.classTemplateList = classTemplateList;
	}
	/**
	 * Constructor for FixPartsWriter.
	 */
	public TemplateReader(BufferedReader in)throws IOException{
		this.in = in;
		eof = false;
		ClassTemplatePart temppart = new ClassTemplatePart();
		classTemplateList.add(temppart);
		// �ŏ��̋�؂�s�܂ŋ�ǂ݂���
		while(true){
			String read = in.readLine();
			
			if(read == null){
				break;
			}

			//read = new String(read.getBytes("ISO-8859-1"), "UTF-8");
			if(read.startsWith(SEPARAT_LINE)){
				ClassTemplatePart temppart2 = new ClassTemplatePart();
				if (read.indexOf("Import") != -1) {
					temppart2.setDynamicImportPartflg(true);
					temppart2.setDynamicTemplate(read);
				} else if (read.indexOf("Field") != -1) {
					temppart2.setDynamicFieldPartflg(true);
					temppart2.setDynamicTemplate(read);
				} else if (read.indexOf("Method") != -1) {
					temppart2.setDynamicMethodPartflg(true);
					temppart2.setDynamicTemplate(read);
				} else if (read.indexOf("Annotation") != -1) {
					temppart2.setDynamicAnnotationPartflg(true);
					temppart2.setDynamicTemplate(read);
				}
				classTemplateList.add(temppart2);
				temppart = new ClassTemplatePart();
				classTemplateList.add(temppart);
			} else {
				temppart.getTemplateParts().add(read);
			}
		}
			
	}
	public static ArrayList<ClassTemplatePart> read(String templateFile) {
		FileReader fr;
		ArrayList<ClassTemplatePart> tempList = null;
		try {
			fr = new FileReader(templateFile);
			TemplateReader tempReader = new TemplateReader(new BufferedReader(fr));
			tempList = tempReader.getClassTemplateList();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
        return tempList;
	}
}
