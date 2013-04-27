/*
 * Created by JFormDesigner on Mon Nov 17 23:00:37 CET 2008
 */

package editor;

import hyperGraphs.HLH;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import rectangularBoard.Path;
import revitXmlTesting.RevitPluginParser;
import sensors.Sensor;
import util.Logger;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import controller.Controller;
import controller.MessageDisplayer;

enum TABS{
	LAYOUT_EDITOR,HYPER_GRPAH_EDITOR, FLOORS, VALIDATION
}

/**
 * @author szymon gajek
 */
public class MainWindow extends JFrame implements MessageDisplayer   { 
 
	private static final long serialVersionUID = -319515686608844806L;

	public static final int  DEFAULT_SENSOR_RANGE=6;
	
	public static final int DELAULT_FLOORS_COUNT=3;
	
	public static final int DEFAULT_SIZE_X=900;
	public static final int DEFAULT_SIZE_Y=420;
	public static final int DEFAULT_GRID_SIZE=20;
	public static final float DEFAULT_GRID_METERS=1;
	
	private Controller controller;
	
	public int sizeX=DEFAULT_SIZE_X;
	public int sizeY=DEFAULT_SIZE_Y;
	public int gridSize=DEFAULT_GRID_SIZE;
	public float gridToMeters=DEFAULT_GRID_METERS;
	
	boolean showLineLen =true;
	
	int sensor1x, sensor1y;
	 
	int drawing_mode=Path.LINE_SOLID;
	
	Boolean eventInitiatedBySoftware=false;
	
	private int floorCount=DELAULT_FLOORS_COUNT;
	public  int currentFloor=0;
	
	private int sensorRange = DEFAULT_SENSOR_RANGE;
	
	private ArrayList<LayoutEditor> layoutEditorsList;
	LayoutEditor currentLayoutEditor;
	
	private TABS currentTab = TABS.LAYOUT_EDITOR;

	public MainWindow(Controller controller) {
		this.controller=controller;
		initAll();

	}
	
	public void initAll(){
		initComponents();
		initButtonProps(clearButton2);
		initButtonProps(SolidMode);
		initButtonProps(DashedMode);
		initButtonProps(showHideLineLen);
		initButtonProps(addDoorsbutton);
		initButtonProps(undoButton);
		
		initLayout(sizeX, sizeY, gridToMeters, gridSize);
		resetButtons();
		
		validationEditor.loadFileList(filesList, fileChooseingList);
	}
	
	
	public void initLayout(int sizeX, int sizeY, float gridToMeters, int gridSize ){
		 
		for (LayoutEditor editor : layoutEditorsList) {
			editor.initLayout(sizeX,				
					sizeY,gridSize, gridToMeters);
		}
		
		tabbedPane1.validate();
		hyperGraphEditor.initLayout(sizeX,sizeY+150);
	}
	
	public void resetButtons(){
		SolidMode.setVisible(false);
		DashedMode.setVisible(false);
		addDoorsbutton.setVisible(false);
		undoButton.setVisible(false);
		roomTypes.setVisible(false);
		showHideLineLen.setVisible(false); //TODO przywrocic/usunac -nie miesci sie combo
	}
	
	public void clearAll(){
		controller.clearGraph();
		
		for (LayoutEditor editor : layoutEditorsList) {
			editor.clear();
		}
		showLineLen =true;
		
		drawing_mode=Path.LINE_SOLID;
		
		SolidMode.setVisible(false);
		DashedMode.setVisible(false);
		addDoorsbutton.setVisible(false);
		undoButton.setVisible(false);
		roomTypes.setVisible(false);
		if (divisionTree!=null){
			divisionTree.delete();
			divisionTree=null;
		}
	}
	
	public void initGraph(){
		controller.initGraph(gridToMeters, sensorRange, floorCount);
	}
 
	
	private void layoutEditorMouseClicked(MouseEvent e) {

		currentLayoutEditor.clearHihglightedRooms();
		
		switch (currentLayoutEditor.mode) {
		case EMPTY:
			currentLayoutEditor.mode=Mode.DRAWING_OUTLINE;
			 currentLayoutEditor.beginRootPath(e.getX(), e.getY());
			break;
		case DRAWING_OUTLINE:
			if (e.getButton()==MouseEvent.BUTTON1){
				currentLayoutEditor.mode = Mode.EMPTY;
				currentLayoutEditor.clear();
				controller.clearGraph();
				currentLayoutEditor.hideLineLen();
				break;	
			}else if (e.getButton()==MouseEvent.BUTTON3){
				Point ret = currentLayoutEditor.removeLastLineFromRoot(); 
				if (ret!=null){
					Robot robot;
					try {
						robot = new Robot();  
						robot.mouseMove(ret.x+currentLayoutEditor.getLocationOnScreen().x ,ret.y+currentLayoutEditor.getLocationOnScreen().y );
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				}
				break;
			}
		case OUTLINE_FINISHED:
			if (currentLayoutEditor.selectDevelopedPath(e.getX(), e.getY())){ // zaznaczamy
				currentLayoutEditor.mode=Mode.AREA_SELECTED;
				roomTypes.setVisible(true);
				synchronized(eventInitiatedBySoftware) {
					  eventInitiatedBySoftware=true;
					  roomTypes.setSelectedIndex(currentLayoutEditor.getDevelopedPath().getRoomType());
					}
				this.setSelectedAreaInfo(currentLayoutEditor.getDevelopedPath());
			}else { // nic nie zaznaczone
				this.setSelectedAreaInfo(null);
			}
			break;
		case DIVIDING_AREA:
			if (e.getButton()==MouseEvent.BUTTON1){
				currentLayoutEditor.mode = Mode.AREA_SELECTED;
				currentLayoutEditor.clearEditedPath();
				currentLayoutEditor.hideLineLen();
				break;	
			}else if (e.getButton()==MouseEvent.BUTTON3){
				Point ret = currentLayoutEditor.removeLastLineFromEdited(); 
				if (ret!=null){
					Robot robot;
					try {
						robot = new Robot();  
						robot.mouseMove(ret.x+currentLayoutEditor.getLocationOnScreen().x ,ret.y+currentLayoutEditor.getLocationOnScreen().y );
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				}
				break;
			}
		case AREA_SELECTED:
			if (e.getButton()==MouseEvent.BUTTON1){
				if (currentLayoutEditor.startNewPath(e.getX(), e.getY(),drawing_mode)){
					currentLayoutEditor.mode = Mode.DIVIDING_AREA;
				}else {
					if (! currentLayoutEditor.selectDevelopedPath(e.getX(), e.getY())){ //odznaczamy path
						currentLayoutEditor.mode=Mode.OUTLINE_FINISHED;
						roomTypes.setVisible(false);
						
						this.setSelectedAreaInfo(null);
					}else {
						synchronized(eventInitiatedBySoftware) {
							  eventInitiatedBySoftware=true;
							  roomTypes.setSelectedIndex(currentLayoutEditor.getDevelopedPath().getRoomType());
							}
						this.setSelectedAreaInfo(currentLayoutEditor.getDevelopedPath());
					}
				}
				
			}else if (e.getButton()==MouseEvent.BUTTON3){
				//dodaj czujnik
				sensor1x=e.getX();
				sensor1y=e.getY();
				
				currentLayoutEditor.mode=Mode.SENSOR_ADDING;
				
				currentLayoutEditor.setAddingSensor( sensor1x,sensor1y,sensor1x,sensor1y );
			}
			break;
		case SENSOR_ADDING:
			if (e.getButton()==MouseEvent.BUTTON3){
				Sensor sensor =currentLayoutEditor.addSensor(sensor1x, sensor1y,e.getX(),e.getY());
				controller.addSensor(sensor, currentLayoutEditor.getDevelopedPath());
			}
			currentLayoutEditor.mode=Mode.AREA_SELECTED;
			currentLayoutEditor.setAddingSensor(null);
			break;
		case ADD_DOORS:
			Object [] newDoorsObjects=currentLayoutEditor.putDoors(e.getX(), e.getY());
			
			if (newDoorsObjects!= null){
				Doors d = (Doors) newDoorsObjects[4];
				controller.putDoors((String)newDoorsObjects[0],(String)newDoorsObjects[1],(String)newDoorsObjects[2],(String)newDoorsObjects[3],d.x1,d.y1,d.x2,d.y2 );
			}
			
		default:
			break;
		}
		 repaint();
	}

	private void layoutEditorMouseMoved(MouseEvent e) {
		int len;
		
		switch (currentLayoutEditor.mode) {
		case EMPTY:
			currentLayoutEditor.markGrid(e.getX(), e.getY()  );
			break;
		case DRAWING_OUTLINE:
			len = currentLayoutEditor.continueRootPath(e.getX(), e.getY());
			if ( len == -1){ // zapetlona  lamana
				currentLayoutEditor.hideLineLen();
				
				currentLayoutEditor.mode =Mode.OUTLINE_FINISHED;
				controller.startOutline(currentLayoutEditor.getRootPath(), gridToMeters, DEFAULT_SENSOR_RANGE, currentFloor);
   		
				SolidMode.setVisible(true);
				DashedMode.setVisible(true);
				addDoorsbutton.setVisible(true);
				undoButton.setVisible(true);
			}else { // wyswietl metraz
				 
				if (showLineLen)
					currentLayoutEditor.setShowLineLen(len, e.getX()-30, e.getY()-15 );
			}
			break;
		case OUTLINE_FINISHED:
			currentLayoutEditor.markGrid(e.getX(), e.getY()  );
			currentLayoutEditor.highlightPath(e.getX(), e.getY());
			break;
		case AREA_SELECTED:
			currentLayoutEditor.markGrid(e.getX(), e.getY()  );
			currentLayoutEditor.highlightPath(e.getX(), e.getY());
			break;
		case DIVIDING_AREA:
			len = currentLayoutEditor.continueNewPath(e.getX(), e.getY(),drawing_mode);
			if (len==-1){
				currentLayoutEditor.hideLineLen();
				currentLayoutEditor.developNestedPaths();
				
				controller.developArea(currentLayoutEditor.getDevelopedPath(),currentLayoutEditor.getDevelopedPath().getNestedPaths(),0);
				currentLayoutEditor.mode =Mode.OUTLINE_FINISHED;
				currentLayoutEditor.clearDevelopedPath();
				currentLayoutEditor.clearEditedPath();

			} else { // wyswietl metraz
				 
				if (showLineLen)
					currentLayoutEditor.setShowLineLen(len, e.getX()-30, e.getY()-15 );
			}
			break;
		case ADD_DOORS:
			currentLayoutEditor.markDoors(e.getX(), e.getY()  );
			break;
		case SENSOR_ADDING:
			currentLayoutEditor.setAddingSensor( sensor1x,sensor1y,e.getX(), e.getY() );
			break;
		default:
			currentLayoutEditor.markGrid(e.getX(), e.getY()  );
			break;
		}
		 repaint();
	}

	public void setSelectedAreaInfo(Path selected){
		if (selected==null){
			RoomLabel.setText("---");
			areaValueLabel.setText("");
		}else{
			RoomLabel.setText(selected.getUserLabel());
			areaValueLabel.setText(String.valueOf(selected.getAreaValue()));
		}
	}
	
	public void setDashedLineMeansVis(boolean meansVis){
		HLH.setDashedLineMeansVisible(meansVis);
	}
	public boolean getDashedLineMeansVis( ){
		return HLH.isDashedLineMeansVisible();
	}

	private void drawModeActionPerformed(ActionEvent e) {
		
//		mode=prevDravingMode;
//		
//		drawMode.setVisible(false);
		
	}
	
	private void clearButtonActionPerformed(ActionEvent e) {
		
		clearAll();
//		applyAffineTransofrm();
	}
	
	private void zoomInButtonActionPerformed(ActionEvent e) {
		
		switch (currentTab){
		case  LAYOUT_EDITOR:
			currentLayoutEditor.zoomIn();
			currentLayoutEditor.validate();// ????
			break;
		case FLOORS:
			break;
		case HYPER_GRPAH_EDITOR:
			hyperGraphEditor.zoomIn();
			hyperGraphEditor.validate();// ????
			break;
		case VALIDATION:
			break;
		}

		tabbedPane1.validate();
		scrollPane1.validate();// ????
		zoomLabel.setText(currentLayoutEditor.getZoomedPerc() + "%");
	}

	private void zoomOutButtonActionPerformed(ActionEvent e) {
		
		switch (currentTab){
		case  LAYOUT_EDITOR:
			currentLayoutEditor.zoomOut();
			currentLayoutEditor.validate();// ????
			break;
		case FLOORS:
			
			break;
		case HYPER_GRPAH_EDITOR:
			hyperGraphEditor.zoomOut();
			hyperGraphEditor.validate();// ????
			break;
		case VALIDATION:
			break;
		}

		tabbedPane1.validate();
		scrollPane1.validate();// ????
		zoomLabel.setText(currentLayoutEditor.getZoomedPerc() + "%");

	}
	 
	private void initButtonProps(JButton button){
    	button.setPreferredSize(new Dimension(150,50));
    }

	private void SolidModeActionPerformed(ActionEvent e) {
		this.drawing_mode=Path.LINE_SOLID;
	}

	private void DashedModeActionPerformed(ActionEvent e) {
		this.drawing_mode=Path.LINE_DASHED;
	}

	private void showHideLineLenActionPerformed(ActionEvent e) {
		if (showLineLen){
			showLineLen=false;
			showHideLineLen.setText("Show line length");
		}else {
			showLineLen=true;
			showHideLineLen.setText("Hide line length");
		}
		 
	}

	private void hyperGraphEditorMousePressed(MouseEvent e) {
		hyperGraphEditor.selectObject(e.getX(), e.getY());
	}

	private void hyperGraphEditorMouseReleased(MouseEvent e) {
		hyperGraphEditor.releaseObject(e.getX(), e.getY());
	}

	private void hyperGraphEditorMouseDragged(MouseEvent e) {
		 hyperGraphEditor.dragObject(e.getX(), e.getY(), !e.isControlDown());//e.getButton()==MouseEvent.BUTTON1);
	}
 
	private void hyperGraphEditorMouseClicked(MouseEvent e) {

	}
	private void hyperGraphEditorMouseMoved(MouseEvent e) {
		 if (hyperGraphEditor.getAreaKind(e.getX(), e.getY()) ==ObjectPainter.AREA_EMPTY ){
			 setCursor(Cursor.DEFAULT_CURSOR);
		 }else if (hyperGraphEditor.getAreaKind(e.getX(), e.getY()) ==ObjectPainter.AREA_OBJECTHE_RESIZE ) {
			 setCursor(Cursor.SE_RESIZE_CURSOR);
		 }else   {
			 setCursor(Cursor.HAND_CURSOR);
		 }
		 repaint();
	}


	private void addDoorsbuttonActionPerformed(ActionEvent e) {
		if (currentLayoutEditor.mode!=Mode.ADD_DOORS){
			currentLayoutEditor.prevDravingMode=currentLayoutEditor.mode;
			currentLayoutEditor.mode=Mode.ADD_DOORS;
			setCursor(Cursor.HAND_CURSOR);
		}else {
			currentLayoutEditor.mode = currentLayoutEditor.prevDravingMode;
			setCursor(Cursor.DEFAULT_CURSOR);
		}
		repaint();
	
	}
	private void undoDivisionbuttonActionPerformed(ActionEvent e) {
		if (divisionTree!= null){
			divisionTree.delete();
			divisionTree=null;
			currentLayoutEditor.setDivisionPath(null);
		}else {
			divisionTree = new DivisionTree(this);
			divisionTree.createTree(controller.getGraph().getFloorRootEdge(currentFloor));
			 
		}
		repaint();
	
	}
	void markDivisionPath(String label){
		currentLayoutEditor.setDivisionPath(currentLayoutEditor.findPathByLabel(label));
		repaint();
	}
	public void deleteDivision(String label) {
		controller.deleteDivision(label);
		currentLayoutEditor.deletgeSubPathsFrom(label);
		currentLayoutEditor.resetEditedPath();
		
		divisionTree.delete();
		divisionTree=null;
		currentLayoutEditor.setDivisionPath(null);
		
		divisionTree = new DivisionTree(this);
		divisionTree.createTree(controller.getGraph().getFloorRootEdge(currentFloor));
		repaint();
	}
	
	protected void floorsComboFocusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	protected void floorsComboMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	protected void floorsComboActionPerformed(ActionEvent e) {
		currentFloor = floorsCombo.getSelectedIndex();
		
		if(currentFloor<floorCount){//czy wybrano pietro, a nie opcje wszystkie prietra w hg ed
			changeFloor(currentFloor);
		}else{
			controller.setHGBrowserCurrentFloor(-1);
		}
		
	}

	private void changeFloor(int newFloor){
		currentLayoutEditor=layoutEditorsList.get(newFloor );
		scrollPane1.setViewportView(currentLayoutEditor);
		repaint();
		controller.setHGBrowserCurrentFloor(newFloor);
	}


	private void RoomLabelFocusLost(FocusEvent e) {

		Path selected = currentLayoutEditor.getDevelopedPath();
		if (selected == null) return;
		
		selected.setUserLabel(RoomLabel.getText());
		controller.setRoomUserLabel(selected.toString(), RoomLabel.getText());
		
	}

	private void roomTypesActionPerformed(ActionEvent e) {
		synchronized(eventInitiatedBySoftware) {
			  if (eventInitiatedBySoftware) {
			    eventInitiatedBySoftware=false; // clear your flag.
			    return; // don't want to process this event.
			  }
			  
			 Path selected = currentLayoutEditor.getDevelopedPath();
			 if (selected == null) return;
			selected.setRoomType( roomTypes.getSelectedIndex() );
			controller.setRoomType(selected.toString(),  HLH.ROOM_TYPES.values()[ roomTypes.getSelectedIndex()].toString() ) ;
		}
	}
	private void roomTypesFocusLost(FocusEvent e) {
	}

	private void roomTypesMouseClicked(MouseEvent e) {
	}
	private void panel3ComponentShown(ComponentEvent e) {
		validationEditor.loadFileList(filesList, fileChooseingList);
	}

	private void filesListValueChanged(ListSelectionEvent e) {
		validationEditor.showFileContents(fileNameLabel,e, fileContent);
	}

	private void saveFileButtonActionPerformed(ActionEvent e) {
		validationEditor.saveToFile(fileContent,fileNameLabel);
	}

	private void fileChooseingListValueChanged(ListSelectionEvent e) {
		validationEditor.changeSelectedTests(e);
	}

	private void exit_menuItemActionPerformed(ActionEvent e) {
		System.exit(0);
	}

	private void import_menuItemActionPerformed(ActionEvent e) {
		
			final JFileChooser fc = new JFileChooser();
	        int returnVal = fc.showOpenDialog(this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            //This is where a real application would open the file.
	        	importOutline(file);
	        }  
	
	}

	private void clear_menuItemActionPerformed(ActionEvent e) {
		clearAll();
		initGraph();
	}

	private void open_menuItemActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void settings_menuItemActionPerformed(ActionEvent e) {
		SettingsDialog sd = new SettingsDialog(this);
		sd.setVisible(true);
	}

	private void helpTests_menuItemActionPerformed(ActionEvent e) {
		TestsHelp th = new TestsHelp(this);
		th.setVisible(true);
	}

	private void about_menuItemActionPerformed(ActionEvent e) {
		AboutDialog ad = new AboutDialog(this);
		ad.setVisible(true);
	}

	private void tabbedPaneTabChanged(ChangeEvent e) {
		switch (tabbedPane1.getSelectedIndex()){
		case 0:
			currentTab=TABS.LAYOUT_EDITOR;
			int prevSelected =floorsCombo.getSelectedIndex();
			setFloorsComboContents(false);
			if(prevSelected==floorCount){
				floorsCombo.setSelectedIndex(0);
				changeFloor(0);
			}else{
				floorsCombo.setSelectedIndex(prevSelected);
				changeFloor(prevSelected);
			}
			floorsCombo.setEnabled(true);
			break;
		case 1:
			currentTab=TABS.FLOORS;
			clearDevelopedPathSelection();
			floorsCombo.setEnabled(false);
			break;
		case 2:
			currentTab=TABS.HYPER_GRPAH_EDITOR;
			int prevSelected2 =floorsCombo.getSelectedIndex();
			setFloorsComboContents(true);
			floorsCombo.setSelectedIndex(prevSelected2);
			floorsCombo.setEnabled(true);
			break;
		case 3:
			currentTab=TABS.VALIDATION;
			floorsCombo.setEnabled(false);
			break;
		}
		
	}
	
	public  void initLayoutEditorsListeners(){
		for (LayoutEditor editor : layoutEditorsList) {
			editor.setBackground(Color.white);
			editor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					layoutEditorMouseClicked(e);
				}
			});
			editor.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					layoutEditorMouseMoved(e);
				}
			});
		}
		
	}
	
	private void initFloorEditorListener(){
		   floorsEditor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					floorsEditor.floorsEditorMouseClicked(e);
				}
			});
		   floorsEditor.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					floorsEditor.floorsEditorMouseMoved(e); 
				}
			});
		
	}
	
	public void initLayoutEditorsList(int pFloorCount){
		this.floorCount = pFloorCount;
		this.currentFloor=0;
		layoutEditorsList=new ArrayList<LayoutEditor>();
		for (int i = 0; i < floorCount ; i++) {
			layoutEditorsList.add(new LayoutEditor(i));
		}
		currentLayoutEditor = layoutEditorsList.get(0);
		
		initLayoutEditorsListeners();
	}
	public void initFloorsEditor(){
		floorsEditor.reset(layoutEditorsList);
		floorsEditor.initLayout(sizeX );
	}

	
	private void initComponents() {
		
		initLayoutEditorsList(DELAULT_FLOORS_COUNT);
	 	 panelFloors=new JPanel()  ;
		 scrollPane_floors = new JScrollPane();
		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - sz gajek
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		open_menuItem = new JMenuItem();
		import_menuItem = new JMenuItem();
		save_menuItem = new JMenuItem();
		clear_menuItem = new JMenuItem();
		exit_menuItem = new JMenuItem();
		menu2 = new JMenu();
		settings_menuItem = new JMenuItem();
		menu3 = new JMenu();
		about_menuItem = new JMenuItem();
		helpLayout_menuItem = new JMenuItem();
		helpHyper_menuItem = new JMenuItem();
		helpTests_menuItem = new JMenuItem();
		panel5 = new JPanel();
		panel5 = new JPanel();
		panel4 = new JPanel();
		zoomInButton = new JButton();
		zoomOutButton = new JButton();
		zoomLabel = new JLabel();
		label5 = new JLabel();
		RoomLabel = new JTextField();
		label9 = new JLabel();
		roomTypes = new JComboBox();
		initRoomTypeList();
		label8 = new JLabel();
		areaValueLabel = new JLabel();
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		
		panel6 = new JPanel();
		SolidMode = new JButton();
		DashedMode = new JButton();
		showHideLineLen = new JButton();
		addDoorsbutton = new JButton();
		undoButton = new JButton();
		clearButton2 = new JButton();
		panel7 = new JPanel();
		label10 = new JLabel();
		scrollPane6 = new JScrollPane();
		validationMessage = new JTextArea();
		panel2 = new JPanel();
		scrollPane2 = new JScrollPane();
		hyperGraphEditor = new HyperGraphEditor();
		panel3 = new JPanel();
		validationEditor = new ValidationEditor();
		label7 = new JLabel();
		label6 = new JLabel();
		scrollPane5 = new JScrollPane();
		fileChooseingList = new JList();
		scrollPane3 = new JScrollPane();
		filesList = new JList();
		scrollPane4 = new JScrollPane();
		panel10 = new JPanel();
		fileNameLabel = new JLabel();
		fileContent = new JTextArea();
		saveFileButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setBackground(new Color(102, 102, 255));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setIconImage(((ImageIcon)UIManager.getIcon("Tree.leafIcon")).getImage());
		setTitle("HSSDR");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"left:57dlu, $lcgap, 493dlu, $lcgap, right:12dlu:grow",
			"top:4dlu, $lgap, fill:[380dlu,default]"));

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("File");

				//---- open_menuItem ----
				open_menuItem.setText("Open");
				open_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						open_menuItemActionPerformed(e);
					}
				});
				menu1.add(open_menuItem);

				//---- import_menuItem ----
				import_menuItem.setText("Import Outline");
				import_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						import_menuItemActionPerformed(e);
					}
				});
				menu1.add(import_menuItem);

				//---- save_menuItem ----
				save_menuItem.setText("Save");
				menu1.add(save_menuItem);

				//---- clear_menuItem ----
				clear_menuItem.setText("Clear");
				clear_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						clear_menuItemActionPerformed(e);
					}
				});
				menu1.add(clear_menuItem);

				//---- exit_menuItem ----
				exit_menuItem.setText("Exit");
				exit_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						exit_menuItemActionPerformed(e);
					}
				});
				menu1.add(exit_menuItem);
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("Settings");

				//---- settings_menuItem ----
				settings_menuItem.setText("Settings");
				settings_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						settings_menuItemActionPerformed(e);
					}
				});
				menu2.add(settings_menuItem);
			}
			menuBar1.add(menu2);

			//======== menu3 ========
			{
				menu3.setText("Help");

				//---- about_menuItem ----
				about_menuItem.setText("About");
				about_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						about_menuItemActionPerformed(e);
					}
				});
				menu3.add(about_menuItem);

				//---- helpLayout_menuItem ----
				helpLayout_menuItem.setText("Layout Sketching");
				menu3.add(helpLayout_menuItem);

				//---- helpHyper_menuItem ----
				helpHyper_menuItem.setText("Hypergraphs");
				menu3.add(helpHyper_menuItem);

				//---- helpTests_menuItem ----
				helpTests_menuItem.setText("Project Testing");
				helpTests_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						helpTests_menuItemActionPerformed(e);
					}
				});
				menu3.add(helpTests_menuItem);
			}
			menuBar1.add(menu3);
		}
		setJMenuBar(menuBar1);

		//======== panel5 ========
		{
 
			panel5.setLayout(new GridBagLayout());
			((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {8, 85, 0, 0};
			((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {29, 23, 23, 23, 23, 32,23, 32,33, 32,33, 0};
			((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

//			panel5.add(zoomMode, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
//				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//				new Insets(0, 0, 3, 0), 0, 0));
//			panel5.add(zoomLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
//				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//				new Insets(0, 0, 3, 3), 0, 0));
			
			//======== panel4 ========
			{
				panel4.setLayout(new FormLayout(
					"2*(default)",
					"default"));

				//---- zoomInButton ----
				zoomInButton.setText("+");
				zoomInButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						zoomInButtonActionPerformed(e);
					}
				});
				panel4.add(zoomInButton, cc.xy(1, 1));

				//---- zoomOutButton ----
				zoomOutButton.setText("-");
				zoomOutButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						zoomOutButtonActionPerformed(e);
					}
				});
				panel4.add(zoomOutButton, cc.xy(2, 1));
			}
			panel5.add(panel4, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 3, 3), 0, 0));
			panel5.add(zoomLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 3, 3), 0, 0));

			//---- label5 ----
			label5.setText("Area Label");
			panel5.add(label5, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 3, 3), 0, 0));

			//---- RoomLabel ----
			RoomLabel.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					RoomLabelFocusLost(e);
				}
			});
			panel5.add(RoomLabel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 3, 3), 0, 0));

			//---- label9 ----
			label9.setText("Area type");
			panel5.add(label9, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 3, 3), 0, 0));

			//---- roomTypes ----
			roomTypes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					roomTypesActionPerformed(e);
				}
			});
			roomTypes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					roomTypesMouseClicked(e);
				}
			});
			roomTypes.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					roomTypesFocusLost(e);
				}
			});
			panel5.add(roomTypes, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 3, 3), 0, 0));

			//---- label8 ----
			label8.setText("Area [m2]:");
			panel5.add(label8, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 3, 3), 0, 0));
			panel5.add(areaValueLabel, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 3), 0, 0));
			
			//---- label8 ----;
			labelFloors = new JLabel();
			labelFloors.setText("Floor");
			panel5.add(labelFloors, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 3, 3), 0, 0));
			
			//---- roomTypes ----
			floorsCombo= new JComboBox();
			initFloorsCombo();
			floorsCombo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					floorsComboActionPerformed(e);
				}
			});
			floorsCombo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					floorsComboMouseClicked(e);
				}
			});
			floorsCombo.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					floorsComboFocusLost(e);
				}
			});
			panel5.add(floorsCombo, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 3), 0, 0));
		}
		contentPane.add(panel5, cc.xywh(1, 3, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

		//======== tabbedPane1 ========
		{
			tabbedPane1.setFont(new Font("Tahoma", Font.BOLD, 14));

			//======== panel1 ========
			{
				panel1.setBackground(Color.white);
				panel1.setLayout(new FormLayout(
					"491dlu",
					"fill:266dlu, 36dlu, $lgap, default"));

				//======== scrollPane1 ========
				{

					//---- layoutEditor ----
				 	scrollPane1.setViewportView(currentLayoutEditor);
				}
				panel1.add(scrollPane1, cc.xywh(1, 1, 1, 1, CellConstraints.CENTER, CellConstraints.CENTER));

				//======== panel6 ========
				{
					panel6.setBackground(new Color(204, 204, 204));
					panel6.setLayout(new FlowLayout());

					//---- SolidMode ----
					SolidMode.setText("Solid");
					SolidMode.setSelectedIcon(null);
					SolidMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
					SolidMode.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SolidModeActionPerformed(e);
						}
					});
					panel6.add(SolidMode);

					//---- DashedMode ----
					DashedMode.setText("Dashed");
					DashedMode.setSelectedIcon(null);
					DashedMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
					DashedMode.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							DashedModeActionPerformed(e);
						}
					});
					panel6.add(DashedMode);

					//---- showHideLineLen ----
					showHideLineLen.setText("Hide line length");
					showHideLineLen.setFont(new Font("Tahoma", Font.PLAIN, 20));
					showHideLineLen.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							showHideLineLenActionPerformed(e);
						}
					});
					panel6.add(showHideLineLen);

					//---- addDoorsbutton ----
					addDoorsbutton.setText("Add doors");
					addDoorsbutton.setFont(new Font("Tahoma", Font.PLAIN, 20));
					addDoorsbutton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							addDoorsbuttonActionPerformed(e);
						}
					});
					panel6.add(addDoorsbutton);

					//---- undoButton ----
					undoButton.setText("Undo");
					undoButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
					undoButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							undoDivisionbuttonActionPerformed(e);
						}
					});
					panel6.add(undoButton);

					//---- clearButton2 ----
					clearButton2.setText("Clear");
					clearButton2.setFont(new Font("Tahoma", Font.PLAIN, 20));
					clearButton2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							clearButtonActionPerformed(e);
						}
					});
					panel6.add(clearButton2);
				}
				panel1.add(panel6, cc.xywh(1, 2, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

				//======== panel7 ========
				{
					panel7.setLayout(new FormLayout(
						"81dlu, $lcgap, 437dlu:grow",
						"fill:54dlu"));

					//---- label10 ----
					label10.setText("Validation output:");
					label10.setFont(new Font("Tahoma", Font.BOLD, 13));
					panel7.add(label10, cc.xywh(1, 1, 2, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//======== scrollPane6 ========
					{

						//---- validationMessage ----
						validationMessage.setText("");
						validationMessage.setBackground(Color.white);
						validationMessage.setEditable(false);
						validationMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
						scrollPane6.setViewportView(validationMessage);
					}
					panel7.add(scrollPane6, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				}
				panel1.add(panel7, cc.xy(1, 4));
			}
			tabbedPane1.addTab("Layout Editor", panel1);


			//======== panelFloors ========
			{
				panelFloors.setLayout(new FormLayout(
					"492dlu",
					"top:361dlu"));

				//======== scrollPane2 ========
				{

					floorsEditor = new FloorsEditor(layoutEditorsList, this);
					floorsEditor.initLayout(sizeX );
					
					initFloorEditorListener();
					
					scrollPane_floors.setViewportView(floorsEditor);
				}
				panelFloors.add(scrollPane_floors, cc.xywh(1, 1, 1, 1, CellConstraints.CENTER, CellConstraints.CENTER));
			}
			tabbedPane1.addTab("Floors View", panelFloors);
			
			
			//======== panel2 ========
			{
				panel2.setLayout(new FormLayout(
					"492dlu",
					"top:361dlu"));

				//======== scrollPane2 ========
				{

					//---- hyperGraphEditor ----
					hyperGraphEditor.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							hyperGraphEditorMouseClicked(e);
						}
						@Override
						public void mousePressed(MouseEvent e) {
							hyperGraphEditorMousePressed(e);
						}
						@Override
						public void mouseReleased(MouseEvent e) {
							hyperGraphEditorMouseReleased(e);
						}
					});
					hyperGraphEditor.addMouseMotionListener(new MouseMotionAdapter() {
						@Override
						public void mouseDragged(MouseEvent e) {
							hyperGraphEditorMouseDragged(e);
						}
					});
					scrollPane2.setViewportView(hyperGraphEditor);
				}
				panel2.add(scrollPane2, cc.xywh(1, 1, 1, 1, CellConstraints.CENTER, CellConstraints.CENTER));
			}
			tabbedPane1.addTab("Hypergraph View", panel2);


			//======== panel3 ========
			{
				panel3.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentShown(ComponentEvent e) {
						panel3ComponentShown(e);
					}
				});
				panel3.setLayout(new FormLayout(
					"default:grow",
					"fill:default:grow"));

				//======== validationEditor ========
				{
					validationEditor.setLayout(new FormLayout(
						"right:47dlu, $lcgap, 120dlu, $lcgap, center:17dlu, $ugap, 288dlu",
						"default, $lgap, 227dlu, 2*($lgap, default)"));

					//---- label7 ----
					label7.setText("Enabled tests");
					validationEditor.add(label7, cc.xy(1, 1));

					//---- label6 ----
					label6.setText("Choose file to edit");
					validationEditor.add(label6, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

					//======== scrollPane5 ========
					{

						//---- fileChooseingList ----
						fileChooseingList.setVisibleRowCount(20);
						fileChooseingList.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								fileChooseingListValueChanged(e);
							}
						});
						scrollPane5.setViewportView(fileChooseingList);
					}
					validationEditor.add(scrollPane5, cc.xy(1, 3));

					//======== scrollPane3 ========
					{

						//---- filesList ----
						filesList.setVisibleRowCount(20);
						filesList.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								filesListValueChanged(e);
							}
						});
						scrollPane3.setViewportView(filesList);
					}
					validationEditor.add(scrollPane3, cc.xy(3, 3));

					//======== scrollPane4 ========
					{

						//======== panel10 ========
						{
							panel10.setLayout(new FormLayout(
								"default:grow",
								"default, $pgap, fill:default:grow"));

							//---- fileNameLabel ----
							fileNameLabel.setText("---");
							panel10.add(fileNameLabel, cc.xywh(1, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

							//---- fileContent ----
							fileContent.setRows(20);
							fileContent.setFont(new Font("Arial", Font.PLAIN, 13));
							panel10.add(fileContent, cc.xy(1, 3));
						}
						scrollPane4.setViewportView(panel10);
					}
					validationEditor.add(scrollPane4, cc.xy(7, 3));

					//---- saveFileButton ----
					saveFileButton.setText("Save file");
					saveFileButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							saveFileButtonActionPerformed(e);
						}
					});
					validationEditor.add(saveFileButton, cc.xywh(7, 5, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
				}
				panel3.add(validationEditor, cc.xy(1, 1));
			}
			tabbedPane1.addTab("Project Validation", panel3);

		}
		contentPane.add(tabbedPane1, cc.xywh(2, 3, 2, 1, CellConstraints.FILL, CellConstraints.FILL));
		
		tabbedPane1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				tabbedPaneTabChanged(e);
				
			}

		});
		
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	
	private void initRoomTypeList(){
		
		synchronized(eventInitiatedBySoftware) {
			  eventInitiatedBySoftware=true;
			  roomTypes.setModel(new javax.swing.DefaultComboBoxModel(HLH.ROOM_TYPES.values()));
			  roomTypes.setSelectedIndex(0);
			}
		
	}
	
	void initFloorsCombo() {
		
		setFloorsComboContents(false);
		floorsCombo.setSelectedIndex(0);

	}
	
	void setFloorsComboContents(boolean addWholeGraph){
		String[] model;
		if (!addWholeGraph){
			model= new String[floorCount];
		}else{
			model= new String[floorCount+1];
			model[floorCount]="all";
		}
		
		for ( int i=0 ; i < floorCount; i++) {
			model[i] =  String.valueOf(i);
		}
		floorsCombo.setModel(new javax.swing.DefaultComboBoxModel(model));
	}
		
	private void importOutline(File file ) {
		
		//wczytaj dane z pliku
		RevitPluginParser parser = new RevitPluginParser();
		parser.SCALE=1;
		parser.XOFFSET=25;
		parser.YOFFSET=4;
		parser.sym_reflection_y=true;
		Path outline =parser.parseFileWithMainOutline(file);
		
		currentLayoutEditor.hideLineLen();
		
		currentLayoutEditor.setRootPath(outline);
		
		currentLayoutEditor.mode =Mode.OUTLINE_FINISHED;
		controller.startOutline(currentLayoutEditor.getRootPath(), gridToMeters, DEFAULT_SENSOR_RANGE);
	
		SolidMode.setVisible(true);
		DashedMode.setVisible(true);
		addDoorsbutton.setVisible(true);
		undoButton.setVisible(true);
		
	}
	

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - sz gajek
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem open_menuItem;
	private JMenuItem import_menuItem;
	private JMenuItem save_menuItem;
	private JMenuItem clear_menuItem;
	private JMenuItem exit_menuItem;
	private JMenu menu2;
	private JMenuItem settings_menuItem;
	private JMenu menu3;
	private JMenuItem about_menuItem;
	private JMenuItem helpLayout_menuItem;
	private JMenuItem helpHyper_menuItem;
	private JMenuItem helpTests_menuItem;
	private JPanel panel5;
	private JPanel panel4;
	private JButton zoomInButton;
	private JButton zoomOutButton;
	private JLabel zoomLabel;
	private JLabel label5;
	private JTextField RoomLabel;
	private JLabel label9;
	private JComboBox roomTypes;
	private JLabel label8;
	private JLabel areaValueLabel;
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	
	
	private JPanel panel6;
	private JButton SolidMode;
	private JButton DashedMode;
	private JButton showHideLineLen;
	private JButton addDoorsbutton;
	private JButton undoButton;
	private JButton clearButton2;
	private JPanel panel7;
	private JLabel label10;
	private JScrollPane scrollPane6;
	private JTextArea validationMessage;
	private JPanel panel2;
	private JScrollPane scrollPane2;
	private HyperGraphEditor hyperGraphEditor;
	private JPanel panel3;
	private ValidationEditor validationEditor;
	private JLabel label7;
	private JLabel label6;
	private JScrollPane scrollPane5;
	private JList fileChooseingList;
	private JScrollPane scrollPane3;
	private JList filesList;
	private JScrollPane scrollPane4;
	private JPanel panel10;
	private JLabel fileNameLabel;
	private JTextArea fileContent;
	private JButton saveFileButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	// dodane recznie
	 
	private DivisionTree divisionTree;
	
 	private JPanel panelFloors  ;
	private JScrollPane scrollPane_floors ;
	private JComboBox floorsCombo;
	private JLabel  labelFloors;
	
	FloorsEditor floorsEditor;
	 
	
	public HyperGraphEditor getHyperGraphEditor(){
		return hyperGraphEditor;
	}
	public ValidationEditor getValidationEditor(){
		return validationEditor;
	}

//	public void displayMessageAndHighlight(GraphMessage[] tab) {
//		 System.out.println(tab);
//		 for (int i = 0; i < tab.length; i++) {
//			 JOptionPane.showMessageDialog(null, tab[i].getMessage(), "Plugin info", 1);
////			 TODO podswietlanie wierz
//		}
//	}

	public void displayMessageAndHighlight(String message, ArrayList<String> roomsToHighlight) {
		validationMessage.setText(message);
		currentLayoutEditor.hihglightRooms(roomsToHighlight);
		repaint();
	}
 
	
	public int getFloorCount() {
		return floorCount;
	}

	public LayoutEditor getFloor(int nr) {
		return this.layoutEditorsList.get(nr);
	}

	public void clearDevelopedPathSelection(){
		for (LayoutEditor floor : layoutEditorsList) {
			floor.clearDevelopedPath();
		}
	}
	
	public int getSensorRange() {
		return sensorRange;
	}

	public void setSensorRange(int sensorRange) {
		this.sensorRange = sensorRange;
	}
}
