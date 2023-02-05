package cn.com.hyun.generator.tools;

import java.io.File;
import java.io.IOException;

/**
 * Created by GengChao on 2017/11/1.
 */
public class DatagridDtoClass extends CreateClass {


    public DatagridDtoClass(EventInfo eventinfo) {
        super(eventinfo);
    }

    /**
     *
     */
    public void writeClass(String outDir, String currentpath) throws IOException {
        String newOurDir = outDir ;
        String dirPath = mkDir(newOurDir);

        if (!"".equals(eventinfo.getEventName())) {
            String filePath = dirPath + File.separator + eventinfo.getEventName() +DTO_FIX+ JAVA_EXT;

            String templateFile = currentpath + TEMPLATE_FOLDER + "Dto" + DAT_EXT;
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
        return PACKAGE_NAME + eventinfo.getDomain() + ".dto";
    }

}