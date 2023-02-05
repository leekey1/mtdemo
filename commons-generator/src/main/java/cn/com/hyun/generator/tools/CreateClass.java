
package cn.com.hyun.generator.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public abstract class CreateClass implements Keywords {

	protected List classNameList = new ArrayList();
	protected EventInfo eventinfo;
	protected List inputlist;



	public CreateClass(EventInfo eventinfo) {
		this.eventinfo = eventinfo;
	}
	public CreateClass(List inputlist) {
		this.inputlist = inputlist;
	}

	/**
	 *
	 * @param outDir
	 * @param currentpath
	 * @throws IOException
	 */
	public abstract void writeClass(String outDir, String currentpath) throws IOException;

	protected String mkDir(String outDir) {
		String packagePath = getPackage();
		String dirPath = FileUtility.packageToPath(outDir, packagePath);
		
		
		//
		File dir = new File(dirPath);
		
		if (!dir.exists()) {
		  dir.mkdirs();
		}
		return dirPath;
	}
	protected void writeClass(String filePath, String templateFile, int classtype, String accesstype)
	throws IOException, FileNotFoundException {
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
		IndentWriter iw = new IndentWriter(fw);
		//FileReader fr = new FileReader(templateFile);
		Reader frs = new InputStreamReader(new FileInputStream(templateFile), "UTF-8");
		TemplateReader tempReader = new TemplateReader(new BufferedReader(frs));
		ClassWriter clsWriter = getWriterInstance(classtype);
		clsWriter.setAccesstype(accesstype);
		clsWriter.setCurrentPath(templateFile.substring(0, templateFile.lastIndexOf("/")));
		clsWriter.write(iw, tempReader.getClassTemplateList());
		
		// �G���[�`�F�b�N
		if(iw.checkError()){
			throw new IOException(filePath + "");
		}
		iw.close();
		frs.close();
		
	}
	protected void writeClass(String filePath, String templateFile, int classtype)
			throws IOException, FileNotFoundException {
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
		IndentWriter iw = new IndentWriter(fw);
		//FileReader fr = new FileReader(templateFile);
		Reader frs = new InputStreamReader(new FileInputStream(templateFile), "UTF-8");
		TemplateReader tempReader = new TemplateReader(new BufferedReader(frs));
		ClassWriter clsWriter = getWriterInstance(classtype);
		clsWriter.setCurrentPath(templateFile.substring(0, templateFile.lastIndexOf("/")));
		clsWriter.write(iw, tempReader.getClassTemplateList());
		
		//
		if(iw.checkError()){
			throw new IOException(filePath + "");
		}
		iw.close();
		frs.close();
		
	}
	public List getClassNameList() {
		return classNameList;
	}
	protected abstract ClassWriter getWriterInstance(int classtype);
	protected abstract String getPackage();
	
}
