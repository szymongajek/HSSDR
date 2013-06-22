/*
 * Created by JFormDesigner on Sun Jan 23 16:56:22 CET 2011
 */

package editor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.jgoodies.forms.layout.*;

/**
 * @author sz gajek
 */
public class SettingsDialog extends JDialog {
	
	MainWindow mainWindow;
	
	public SettingsDialog(Frame owner) {
		super(owner);
		this.mainWindow=(MainWindow)owner;
		initComponents();
		
		horSize_TF.setText(String.valueOf(mainWindow.sizeX));
		vertSize_TF.setText(String.valueOf(mainWindow.sizeY));
		gridSize_TF.setText(String.valueOf(mainWindow.gridSize));
		gridMeters_TF.setText(String.valueOf(mainWindow.gridToMeters));
		
		if (mainWindow.getDashedLineMeansVis()){
			dashedModeVIS_radio.setSelected(true);
		}else {
			dashedModeACC_radio.setSelected(true);
		}
		
		floorNumberComboBox.setSelectedIndex(MainWindow.DELAULT_FLOORS_COUNT-1);
	}

	public SettingsDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void cancel_buttonActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void apply_buttonActionPerformed(ActionEvent e) {
		
		applyBasicSettings();
		this.dispose();
	}
	
	private void applyBasicSettings(){
		int sizeX;
		int sizeY;
		int gridSize;
		float gridToMeters;
		int sensorRange;
		
		sizeX=Integer.parseInt( horSize_TF.getText());
		sizeY=Integer.parseInt( vertSize_TF.getText());
		gridSize=Integer.parseInt( gridSize_TF.getText());
		gridToMeters=Float.parseFloat( gridMeters_TF.getText());
		mainWindow.initLayout(sizeX, sizeY, gridToMeters, gridSize);
		
		sensorRange=Integer.parseInt( sensorRange_TF.getText());
		mainWindow.setSensorRange(sensorRange);
	}
	
	private void horSize_TFCaretUpdate(CaretEvent e) {
		try {
			Integer.parseInt( horSize_TF.getText());
		}catch (NumberFormatException ex ){
			JOptionPane.showMessageDialog(this, "Enter a valid number");
			horSize_TF.setText(String.valueOf(mainWindow.sizeX));
		}
	}
	
	private void vertSize_TFCaretUpdate(CaretEvent e) {
		try {
			Integer.parseInt( vertSize_TF.getText());
		}catch (NumberFormatException ex ){
			JOptionPane.showMessageDialog(this, "Enter a valid number");
			vertSize_TF.setText(String.valueOf(mainWindow.sizeY));
		}
	}

	private void gridSize_TFCaretUpdate(CaretEvent e) {
		try {
			Integer.parseInt( gridSize_TF.getText());
		}catch (NumberFormatException ex ){
			JOptionPane.showMessageDialog(this, "Enter a valid number");
			gridSize_TF.setText(String.valueOf(mainWindow.gridSize));
		}
	}

	private void gridMeters_TFCaretUpdate(CaretEvent e) {
		try {
			Float.parseFloat( gridMeters_TF.getText());
		}catch (NumberFormatException ex ){
			JOptionPane.showMessageDialog(this, "Enter a valid number");
			gridMeters_TF.setText(String.valueOf(mainWindow.gridToMeters));
		}
	}

	private void dashedModeACC_radioItemStateChanged(ItemEvent e) {
//		if (e.getStateChange()==ItemEvent.SELECTED){
//			mainWindow.setDashedLineMeansVis(false);
//		}
	}

	private void dashedModeVIS_radioItemStateChanged(ItemEvent e) {
//		if (e.getStateChange()==ItemEvent.SELECTED){
//			mainWindow.setDashedLineMeansVis(true);
//		}
	}

	private void applyAndResetActionPerformed(ActionEvent e) {
		int floorCount = Integer.parseInt((String )floorNumberComboBox.getSelectedItem() );
		
		mainWindow.clearAll();
		
		mainWindow.initLayoutEditorsList(  floorCount);
		mainWindow.initFloorsEditor();
		
		mainWindow.initGraph();
		
		mainWindow.initFloorsCombo();
		
		if (dashedModeVIS_radio.isSelected()){
			mainWindow.setDashedLineMeansVis(true);
		}else {
			mainWindow.setDashedLineMeansVis(false);
		}
		
		applyBasicSettings();
		
		this.dispose();
	}

	private void floorNumberComboBoxItemStateChanged(ItemEvent e) {
		// TODO add your code here
	}

	private void sensorRange_TFFocusLost(FocusEvent e) {
		String newValue = sensorRange_TF.getText();
		
		try{
			Integer.parseInt(newValue);
		}catch (NumberFormatException ex ){
			sensorRange_TF.setText(String.valueOf(mainWindow.DEFAULT_SENSOR_RANGE));
		}
	}

	 

	 

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - sz gajek
		panel1 = new JPanel();
		horSize_lab = new JLabel();
		horSize_TF = new JTextField();
		vertSize_lab = new JLabel();
		vertSize_TF = new JTextField();
		gridSize_lab = new JLabel();
		gridSize_TF = new JTextField();
		gridMeters_lab = new JLabel();
		gridMeters_TF = new JTextField();
		sensorRange_lab = new JLabel();
		sensorRange_TF = new JTextField();
		panel3 = new JPanel();
		dashedLineLab = new JLabel();
		dashedModeACC_radio = new JRadioButton();
		label1 = new JLabel();
		dashedModeVIS_radio = new JRadioButton();
		panel4 = new JPanel();
		floorsNumberLab = new JLabel();
		floorNumberComboBox = new JComboBox();
		label4 = new JLabel();
		panel2 = new JPanel();
		applyAndReset = new JButton();
		apply_button = new JButton();
		cancel_button = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Settings");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"105dlu:grow",
			"2*(default, $lgap), 31dlu, $lgap, bottom:46dlu:grow, $lgap, bottom:default, $lgap, 9dlu"));

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new FormLayout(
				"29dlu, $lcgap, 67dlu, $lcgap, 91dlu, $lcgap, 133dlu",
				"4*(default, $lgap), default"));

			//---- horSize_lab ----
			horSize_lab.setText("Horizontal Size");
			panel1.add(horSize_lab, cc.xy(3, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

			//---- horSize_TF ----
			horSize_TF.addCaretListener(new CaretListener() {
				@Override
				public void caretUpdate(CaretEvent e) {
					horSize_TFCaretUpdate(e);
				}
			});
			panel1.add(horSize_TF, cc.xy(5, 1));

			//---- vertSize_lab ----
			vertSize_lab.setText("Vertical Size");
			panel1.add(vertSize_lab, cc.xy(3, 3, CellConstraints.CENTER, CellConstraints.CENTER));

			//---- vertSize_TF ----
			vertSize_TF.addCaretListener(new CaretListener() {
				@Override
				public void caretUpdate(CaretEvent e) {
					vertSize_TFCaretUpdate(e);
				}
			});
			panel1.add(vertSize_TF, cc.xy(5, 3));

			//---- gridSize_lab ----
			gridSize_lab.setText("Grid size[px]");
			panel1.add(gridSize_lab, cc.xy(3, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));

			//---- gridSize_TF ----
			gridSize_TF.addCaretListener(new CaretListener() {
				@Override
				public void caretUpdate(CaretEvent e) {
					gridSize_TFCaretUpdate(e);
				}
			});
			panel1.add(gridSize_TF, cc.xy(5, 5));

			//---- gridMeters_lab ----
			gridMeters_lab.setText("Grid size[m]");
			panel1.add(gridMeters_lab, cc.xy(3, 7, CellConstraints.CENTER, CellConstraints.DEFAULT));

			//---- gridMeters_TF ----
			gridMeters_TF.addCaretListener(new CaretListener() {
				@Override
				public void caretUpdate(CaretEvent e) {
					gridMeters_TFCaretUpdate(e);
				}
			});
			panel1.add(gridMeters_TF, cc.xy(5, 7));

			//---- sensorRange_lab ----
			sensorRange_lab.setText("Sensor range[m]");
			panel1.add(sensorRange_lab, cc.xy(3, 9));

			//---- sensorRange_TF ----
			sensorRange_TF.setText("6");
			sensorRange_TF.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					sensorRange_TFFocusLost(e);
				}
			});
			panel1.add(sensorRange_TF, cc.xy(5, 9));
		}
		contentPane.add(panel1, cc.xy(1, 3, CellConstraints.LEFT, CellConstraints.TOP));

		//======== panel3 ========
		{
			panel3.setLayout(new FormLayout(
				"22dlu, $lcgap, 73dlu, $lcgap, 95dlu, $lcgap, 129dlu",
				"2*(default)"));

			//---- dashedLineLab ----
			dashedLineLab.setText("Dashed Line Meaning");
			panel3.add(dashedLineLab, cc.xy(3, 1));

			//---- dashedModeACC_radio ----
			dashedModeACC_radio.setText("Accesibility");
			dashedModeACC_radio.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					dashedModeACC_radioItemStateChanged(e);
				}
			});
			panel3.add(dashedModeACC_radio, cc.xy(5, 1));

			//---- label1 ----
			label1.setText("(requires reset)");
			panel3.add(label1, cc.xy(7, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

			//---- dashedModeVIS_radio ----
			dashedModeVIS_radio.setText("Visibility and Adjency");
			dashedModeVIS_radio.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					dashedModeVIS_radioItemStateChanged(e);
				}
			});
			panel3.add(dashedModeVIS_radio, cc.xy(5, 2));
		}
		contentPane.add(panel3, cc.xy(1, 5, CellConstraints.LEFT, CellConstraints.TOP));

		//======== panel4 ========
		{
			panel4.setLayout(new FormLayout(
				"27dlu, $lcgap, 67dlu, $lcgap, 95dlu, $lcgap, 125dlu",
				"3*(default, $lgap), default"));

			//---- floorsNumberLab ----
			floorsNumberLab.setText("Floors number");
			panel4.add(floorsNumberLab, cc.xy(3, 1));

			//---- floorNumberComboBox ----
			floorNumberComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"1",
				"2",
				"3",
				"4",
				"5",
				"6",
				"7",
				"8",
				"9",
				"10",
				"11",
				"12",
				"15",
				"20",
				"25",
				"30"
			}));
			floorNumberComboBox.setSelectedIndex(2);
			floorNumberComboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					floorNumberComboBoxItemStateChanged(e);
				}
			});
			panel4.add(floorNumberComboBox, cc.xy(5, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

			//---- label4 ----
			label4.setText("(requires reset)");
			panel4.add(label4, cc.xy(7, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		}
		contentPane.add(panel4, cc.xy(1, 7, CellConstraints.LEFT, CellConstraints.TOP));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"3*(default:grow, $lcgap), 43dlu:grow, 2*($lcgap, 43dlu), $lcgap, 3dlu",
				"default"));

			//---- applyAndReset ----
			applyAndReset.setText("Apply and Reset");
			applyAndReset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					applyAndResetActionPerformed(e);
				}
			});
			panel2.add(applyAndReset, cc.xy(7, 1));

			//---- apply_button ----
			apply_button.setText("Apply");
			apply_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					apply_buttonActionPerformed(e);
				}
			});
			panel2.add(apply_button, cc.xy(9, 1));

			//---- cancel_button ----
			cancel_button.setText("Cancel");
			cancel_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cancel_buttonActionPerformed(e);
				}
			});
			panel2.add(cancel_button, cc.xy(11, 1));
		}
		contentPane.add(panel2, cc.xy(1, 9, CellConstraints.FILL, CellConstraints.BOTTOM));
		setSize(520, 375);
		setLocationRelativeTo(getOwner());

		//---- dashedMeaning_BG ----
		ButtonGroup dashedMeaning_BG = new ButtonGroup();
		dashedMeaning_BG.add(dashedModeACC_radio);
		dashedMeaning_BG.add(dashedModeVIS_radio);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - sz gajek
	private JPanel panel1;
	private JLabel horSize_lab;
	private JTextField horSize_TF;
	private JLabel vertSize_lab;
	private JTextField vertSize_TF;
	private JLabel gridSize_lab;
	private JTextField gridSize_TF;
	private JLabel gridMeters_lab;
	private JTextField gridMeters_TF;
	private JLabel sensorRange_lab;
	private JTextField sensorRange_TF;
	private JPanel panel3;
	private JLabel dashedLineLab;
	private JRadioButton dashedModeACC_radio;
	private JLabel label1;
	private JRadioButton dashedModeVIS_radio;
	private JPanel panel4;
	private JLabel floorsNumberLab;
	private JComboBox floorNumberComboBox;
	private JLabel label4;
	private JPanel panel2;
	private JButton applyAndReset;
	private JButton apply_button;
	private JButton cancel_button;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
