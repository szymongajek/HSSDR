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
	}

	public SettingsDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void cancel_buttonActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void apply_buttonActionPerformed(ActionEvent e) {
		
		int sizeX;
		int sizeY;
		int gridSize;
		float gridToMeters;
		
		sizeX=Integer.parseInt( horSize_TF.getText());
		sizeY=Integer.parseInt( vertSize_TF.getText());
		gridSize=Integer.parseInt( gridSize_TF.getText());
		gridToMeters=Float.parseFloat( gridMeters_TF.getText());
		
		mainWindow.initLayout(sizeX, sizeY, gridToMeters, gridSize);
		
		if (dashedModeVIS_radio.isSelected()){
			mainWindow.setDashedLineMeansVis(true);
		}else {
			mainWindow.setDashedLineMeansVis(false);
		}
		
		this.dispose();
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
		panel3 = new JPanel();
		label3 = new JLabel();
		dashedModeACC_radio = new JRadioButton();
		dashedModeVIS_radio = new JRadioButton();
		separator1 = new JSeparator();
		panel2 = new JPanel();
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
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new FormLayout(
				"29dlu, $lcgap, 67dlu, $lcgap, 58dlu",
				"3*(default, $lgap), default"));

			//---- horSize_lab ----
			horSize_lab.setText("Horizontal Size");
			panel1.add(horSize_lab, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

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
			panel1.add(vertSize_lab, cc.xywh(3, 3, 1, 1, CellConstraints.CENTER, CellConstraints.CENTER));

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
			panel1.add(gridSize_lab, cc.xywh(3, 5, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

			//---- gridSize_TF ----
			gridSize_TF.addCaretListener(new CaretListener() {
				@Override
				public void caretUpdate(CaretEvent e) {
					gridSize_TFCaretUpdate(e);
				}
			});
			panel1.add(gridSize_TF, cc.xy(5, 5));

			//---- gridMeters_lab ----
			gridMeters_lab.setText("Grid size[meters]");
			panel1.add(gridMeters_lab, cc.xywh(3, 7, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

			//---- gridMeters_TF ----
			gridMeters_TF.addCaretListener(new CaretListener() {
				@Override
				public void caretUpdate(CaretEvent e) {
					gridMeters_TFCaretUpdate(e);
				}
			});
			panel1.add(gridMeters_TF, cc.xy(5, 7));
		}
		contentPane.add(panel1, cc.xy(1, 3));

		//======== panel3 ========
		{
			panel3.setLayout(new FormLayout(
				"22dlu, $lcgap, 73dlu, $lcgap, 95dlu, $lcgap, default",
				"2*(default)"));

			//---- label3 ----
			label3.setText("Dashed Line Meaning");
			panel3.add(label3, cc.xy(3, 1));

			//---- dashedModeACC_radio ----
			dashedModeACC_radio.setText("Accesibility");
			dashedModeACC_radio.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					dashedModeACC_radioItemStateChanged(e);
				}
			});
			panel3.add(dashedModeACC_radio, cc.xy(5, 1));

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
		contentPane.add(panel3, cc.xy(1, 5));
		contentPane.add(separator1, cc.xy(1, 7));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"43dlu:grow, 2*($lcgap, 43dlu), $lcgap, 3dlu",
				"default"));

			//---- apply_button ----
			apply_button.setText("Apply");
			apply_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					apply_buttonActionPerformed(e);
				}
			});
			panel2.add(apply_button, cc.xy(3, 1));

			//---- cancel_button ----
			cancel_button.setText("Cancel");
			cancel_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cancel_buttonActionPerformed(e);
				}
			});
			panel2.add(cancel_button, cc.xy(5, 1));
		}
		contentPane.add(panel2, cc.xy(1, 9));
		setSize(470, 330);
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
	private JPanel panel3;
	private JLabel label3;
	private JRadioButton dashedModeACC_radio;
	private JRadioButton dashedModeVIS_radio;
	private JSeparator separator1;
	private JPanel panel2;
	private JButton apply_button;
	private JButton cancel_button;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
