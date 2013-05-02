package editor;
 
import java.io.*;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;

import controller.TestChooser;

public class ValidationEditor extends JPanel implements TestChooser   {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3832800528108005941L;
	static final String TEST_ON="on";
	static final String TEST_OFF="off";
	
	JList filesList;
	JList fileChooseingList;

	public void loadFileList(JList filesList, JList fileChooseingList) {
		this.fileChooseingList=fileChooseingList;
		this.filesList=filesList;
		
		File dir = new File(TEST_DIR);

		String[] children = dir.list();
		if (children == null) {
		    System.out.println("directory missing: "+TEST_DIR);
		    boolean success = dir.mkdir();
		    if (success) {
		      System.out.println("Directory: " + TEST_DIR + " created");
		    }else {
		    	throw new RuntimeException("Directory"+TEST_DIR +"cannot be created.");
		    }
		    children = dir.list();
		}

//		 It is also possible to filter the list of returned files.
//		 This example does not return any files that start with `.'.
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return !name.endsWith(".txt");
		    }
		};
//		children = dir.list(filter);


//		 The list of files can also be retrieved as File objects
		File[] files = dir.listFiles();

//		 This filter only returns directories
		FileFilter fileFilter = new FileFilter() {
		    public boolean accept(File file) {
		        return file.isDirectory();
		    }
		};
		files = dir.listFiles(fileFilter);
		
		
		filesList.setListData(children);
		
		DefaultListModel model = new DefaultListModel();
		
		for (int i=0; i<children.length; i++){
			model.addElement(TEST_OFF);
		}
		
		fileChooseingList.setModel(model);
	}

	public void showFileContents(JLabel fileNameLabel, ListSelectionEvent e, JTextArea ta) {
		 
		String fileName = (String)filesList.getSelectedValue()  ;
		if (fileName== null || fileName.length()==0) return;
		
		e.getFirstIndex();
		
		 fileNameLabel.setText(fileName);
		try {  
            Reader r = new InputStreamReader(
                    new FileInputStream(TEST_DIR+"/"+fileName), "UTF-8"); 
            ta.read(r, null);
            r.close();
//			ta.setEditable(false);  
		}catch (IOException ioe) {  
			ioe.printStackTrace();  
		}
		
	}

	public void saveToFile(JTextArea ta, JLabel fileNameLabel) {

		String fileName = TEST_DIR+"/"+fileNameLabel.getText();
		try{
            Writer w = new OutputStreamWriter(
                    new FileOutputStream(fileName), "UTF-8");
            w.write(ta.getText());
            w.close();
        }catch(Exception e){
            System.out.println("Exception in write() "+e);
        }
		
	}

	public void changeSelectedTests( ListSelectionEvent e) {

		if (e.getValueIsAdjusting()) return;
		int changed =  fileChooseingList.getSelectedIndex();
		
		if (changed==-1) return; 
		
		DefaultListModel model = (DefaultListModel)(fileChooseingList.getModel());
		String el = (String) model.getElementAt(changed);
		if (el.equals(TEST_OFF) )
			model.setElementAt(TEST_ON,changed);
		else 
			model.setElementAt(TEST_OFF,changed);
		
	}
	
	public ArrayList<String>   getTestFilesList() {
		DefaultListModel chooseModel = (DefaultListModel)(fileChooseingList.getModel());
		 
		ArrayList<String> ret = new ArrayList<String>();
		
		for(int i=0; i<chooseModel.getSize(); i++){
			if (chooseModel.getElementAt(i).equals(TEST_ON)){
				ret.add((String)filesList.getModel().getElementAt(i));
			}
		}
		
		return ret;
	}

	
	}

