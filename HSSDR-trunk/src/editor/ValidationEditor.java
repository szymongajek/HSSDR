package editor;
 
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

import util.Logger;
import util.UtilHSSDR;

import controller.TestChooser;

public class ValidationEditor extends JPanel implements TestChooser   {
	/**
	 * 
	 */
	private String TEST_DIR="test_files";
	
	private static final long serialVersionUID = 3832800528108005941L;
	static final String TEST_ON="on";
	static final String TEST_OFF="off";
	
	JTable testFilesSelectionTable;
	MyTableModel tableModel;

	public void loadFileList(JTable testFilesSelectionTable) {
		this.testFilesSelectionTable=testFilesSelectionTable;
		tableModel = new MyTableModel();
		tableModel.setNewDataClearCheckboxes(UtilHSSDR.getDirContentOrCreate(TEST_DIR));
		this.testFilesSelectionTable.setModel(tableModel);
//		this.testFilesSelectionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.testFilesSelectionTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		this.testFilesSelectionTable.getColumnModel().getColumn(1).setPreferredWidth( 220);
	}

	
	class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Enabled",
                                        "File name"
                                        };
        private Object[][] data = {
	    { new Boolean(false), ""}
        };

        public void setNewDataClearCheckboxes(String[] fileNames){
        	
        	data =  new Object[fileNames.length][];	
        	
        	//sort file names list
        	Arrays.sort(fileNames);
        	
        	for(int i=0; i<fileNames.length; i++){
        		data[i]=new Object []{ new Boolean(false), fileNames[i]};
        	}
        }
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        @Override
		public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
 
        public String getfileNameAt(int row) {
            return (String)data[row][1];
        }
        public boolean isSelectedAt(int row) {
            return (Boolean)data[row][0];
        }
 
 

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @Override
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        @Override
		public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
                return col == 0;
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        @Override
		public void setValueAt(Object value, int row, int col) {

            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        private List<String> getSelectedFiles() {
            int numRows = getRowCount();
            List<String> filesList = new ArrayList<String>();

            for (int i=0; i < numRows; i++) {
            	if (isSelectedAt(i)){
            		filesList.add(getfileNameAt(i));
            	}
            }
            
            return filesList;
        }
    }
	
	public void showFileContents(int selectedIndex,JLabel fileNameLabel,  JTextArea ta) {
		String fileName = tableModel.getfileNameAt(selectedIndex) ;
		if (fileName== null || fileName.length()==0) return;
		
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
            Logger.LOGGER.debug("Exception in write() "+e);
        }
		
	}

	
	public List<String>   getTestFilesList() {
		
		return tableModel.getSelectedFiles();
	}

	@Override
	public String getTestFilesDirectory() {
		return TEST_DIR;
	}

	
	}

