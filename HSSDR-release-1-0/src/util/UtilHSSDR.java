package util;

import java.io.File;
import java.io.FilenameFilter;

public class UtilHSSDR {

	public static  String[] getDirContentOrCreate(String dirName) {
		File dir = new File(dirName);
		
		if (dir.list() == null) {
		    Logger.LOGGER.debug("directory missing: "+dirName);
		    boolean success = dir.mkdir();
		    if (success) {
		    	Logger.LOGGER.debug("Directory: " + dirName + " created");
		    }else {
		    	throw new RuntimeException("Directory"+dirName +"cannot be created.");
		    }
		}
		
//		  filter the list of returned files. do not return any files that start with '.'.
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return !name.startsWith(".");
		    }
		};
		String []  children= dir.list(filter);
		return children;
	}
	
}
