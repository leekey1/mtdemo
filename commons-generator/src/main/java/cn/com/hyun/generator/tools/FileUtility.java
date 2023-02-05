package cn.com.hyun.generator.tools;

import java.io.File;

/**
 *
 */
public class FileUtility {
	
	/**
	 *
	 */
	public static String packageToPath(String dir, String packagename){

		String work = packagename.replace('.', File.separatorChar);
		
		return dir + File.separator + work;
	}
	
	/**
	 *
	 */
	public static boolean isExist(String path){
		File file = new File(path);
		
		return file.exists();	
	}
	
}
