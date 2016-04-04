package sketchUpXmlTesting;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;

import util.Logger;

import controller.TestChooser;

public class SketchUpTestChooser implements TestChooser{

	private String TEST_DIR="su_test_files";
	public  List<String>   getTestFilesList() {
	 	 
		File dir = new File(TEST_DIR);

		if (dir.list() == null) {
			Logger.LOGGER.debug("directory missing: "+TEST_DIR);
		    boolean success = dir.mkdir();
		    if (success) {
		    	Logger.LOGGER.debug("Directory: " + TEST_DIR + " created");
		    }else {
		    	throw new RuntimeException("Directory"+TEST_DIR +"cannot be created.");
		    }
		}
		
//		 It is also possible to filter the list of returned files.
//		 This example does not return any files that start with `.'.
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return !name.startsWith(".");
		    }
		};
		String [] children = dir.list(filter);
	 
		return Arrays.asList(children );
	}
	@Override
	public String getTestFilesDirectory() {
		return TEST_DIR;
	}

}
