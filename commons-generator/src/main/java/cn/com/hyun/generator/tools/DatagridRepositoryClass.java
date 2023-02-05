
package cn.com.hyun.generator.tools;

import java.io.File;
import java.io.IOException;


/**
 * <p>
 * dao�N���X���쐬���܂��B
 * </p>
 * @author test
 */
public class DatagridRepositoryClass extends CreateClass {


	public DatagridRepositoryClass(EventInfo eventinfo) {
		super(eventinfo);
	}

	/**
	 *
	 * @param outDir
	 * @param currentpath
	 * @throws IOException
	 */
	public void writeClass(String outDir, String currentpath) throws IOException{
		String newOurDir = outDir;
		String dirPath = mkDir(newOurDir);
		
		//if (eventinfo.isPofflag()) {
			String filePath = dirPath + File.separator + eventinfo.getEventName() + REPOSITORY_FIX + JAVA_EXT;
			classNameList.add(getPackage() + "." + eventinfo.getEventName() + REPOSITORY_FIX);
			String templateFile =  currentpath + TEMPLATE_FOLDER + REPOSITORY_FIX + DAT_EXT;
			writeClass(filePath, templateFile, 1);	
			//writeClass(filePath, templateFile, 1);	
		//}
	}

	@Override
	protected String getPackage() {
		
		return PACKAGE_NAME+ eventinfo.getDomain() + ".dao";
	}

	@Override
	protected ClassWriter getWriterInstance(int classtype) {
		ClassWriter clsWriter = null;
		if (classtype == 1) {
			clsWriter = new RepositoryClassWriter(eventinfo);
		} 
		return clsWriter;
	}

}
