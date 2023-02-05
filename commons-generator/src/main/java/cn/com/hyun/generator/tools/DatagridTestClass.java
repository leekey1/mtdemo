package cn.com.hyun.generator.tools;

import java.io.File;
import java.io.IOException;

public class DatagridTestClass extends CreateClass {


    public DatagridTestClass(EventInfo eventinfo) {
        super(eventinfo);
    }

    /**
     *
     */
    public void writeClass(String outDir, String currentpath) throws IOException {
        String newOurDir = outDir;
        String dirPath = mkDir(newOurDir);

        if (!"".equals(eventinfo.getEventName())) {
            String filePath = dirPath + File.separator + eventinfo.getEventName() +TESTController_FIX+ JAVA_EXT;

            String templateFile = currentpath + TEMPLATE_FOLDER + "Test" + DAT_EXT;
            writeClass(filePath, templateFile, 1);

        }

    }

    protected ClassWriter getWriterInstance(int classtype) {
        ClassWriter clsWriter = null;
        clsWriter = new EntityClassWriter(eventinfo);

        return clsWriter;
    }

    @Override
    protected String getPackage() {
        return PACKAGE_NAME + eventinfo.getDomain()+"Test";
    }

}