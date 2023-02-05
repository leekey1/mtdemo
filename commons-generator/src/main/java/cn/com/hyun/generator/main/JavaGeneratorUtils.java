package cn.com.hyun.generator.main;


import cn.com.hyun.generator.tools.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by helloworld on 2017/10/23.
 *
 * @author GengChao
 * @version $Id: $Id
 */
public abstract class JavaGeneratorUtils {

    /**
     * <p>Constructor for JavaGeneratorUtils.</p>
     */
    public JavaGeneratorUtils() {
    }

    public static void main(String[] args) {

        try {
            String baseDir = "/Users/anjung-geun/zhg/workspace_java/mtdemo/commons-generator/";

            for(int i=0;i<args.length;i++){
                if(args[i].trim()=="-basedir" && i + 1 < args.length){
                    baseDir = args[i+1];
                    return;
                }
            }

            if(!baseDir.endsWith(baseDir)) baseDir += "/";


            String outdir = baseDir + "/out";
            String currentpath = baseDir + "src/main/resources/";

            String eventListFile = baseDir + "src/main/resources/db/DB.xls";
            String fileName = "DB.xls";

            //
            EventListReader evtlistreader = EventListReader.getInstance(eventListFile);

            List<EventInfo> repos = evtlistreader.readerRepositorys();

            for (EventInfo colum : repos) {
                //Dao
                evtlistreader.readerEntity(colum, colum.getTableEnname(), eventListFile, fileName);
                System.out.println("================================================================================================================= ");
                System.out.println("表 " + colum.getTableEnname());
                DatagridRepositoryClass repository = new DatagridRepositoryClass(colum);
                repository.writeClass(outdir, currentpath);
                //XML
                DatagridRepositoryXmlClass repoXml = new DatagridRepositoryXmlClass(colum);
                repoXml.writeClass(outdir, currentpath);
                //Entity
                DatagridEntityClass entity = new DatagridEntityClass(colum);
                entity.writeClass(outdir, currentpath);
                //Controller
                DatagridControllerClass controller = new DatagridControllerClass(colum);
                controller.writeClass(outdir, currentpath);
                //Service
                DatagridServiceClass service = new DatagridServiceClass(colum);
                service.writeClass(outdir, currentpath);
                //Validator
                DatagridValidatorClass validator = new DatagridValidatorClass(colum);
                validator.writeClass(outdir, currentpath);
                //Dto
                DatagridDtoClass dto = new DatagridDtoClass(colum);
                dto.writeClass(outdir, currentpath);
                //DDL
                DDLCreateClass ddl = new DDLCreateClass(colum);
                ddl.writeClass(outdir, currentpath);
//                //TestController
//                DatagridTestClass test = new DatagridTestClass(colum);
//                test.writeClass(outdir, currentpath);
//
//                //TestService
//                DatagridTestServiceClass testService = new DatagridTestServiceClass(colum);
//                testService.writeClass(outdir, currentpath);
            }

            System.out.println("生成成功");

        } catch (IOException e) {
            System.err.println("generation fail.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
