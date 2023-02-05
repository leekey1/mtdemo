
package cn.com.hyun.generator.tools;

import java.io.File;
import java.io.IOException;


/**
 * <p>
 * dao
 * </p>
 * @author test
 */
public class DDLCreateClass extends CreateClass {


	public DDLCreateClass(EventInfo eventinfo) {
		super(eventinfo);
	}

	public void writeClass(String outDir, String currentpath) throws IOException{
		String newOurDir = outDir;
		String dirPath = mkDir(newOurDir);
		//if (eventinfo.isPofflag()) {
			if (!"".equals(eventinfo.getEventName())) {
				String filePath = dirPath + File.separator + eventinfo.getEventName() + ".sql";
				String templateFile =  currentpath + TEMPLATE_FOLDER + "TableDDL" + DAT_EXT;
				writeClass(filePath, templateFile, 1);
			} 

	}

	
	protected ClassWriter getWriterInstance(int classtype) {
		ClassWriter clsWriter = null;
		clsWriter = new DDLClassWriter(eventinfo);

		
		return clsWriter;
	}

	@Override
	protected String getPackage() {
		return PACKAGE_NAME +"sql" ;
	}

}
