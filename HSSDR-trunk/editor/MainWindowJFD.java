/*
 * Created by JFormDesigner on Sun Jan 10 19:15:58 CET 2016
 */

package editor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.jgoodies.forms.layout.*;

/**
 * @author sz g
 */
public class MainWindowJFD extends JFrame {
	public MainWindowJFD() {
		initComponents();
	}

	private void open_menuItemActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void import_menuItemActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void clear_menuItemActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void exit_menuItemActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void settings_menuItemActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void about_menuItemActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void helpTests_menuItemActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void zoomModeActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void RoomLabelFocusLost(FocusEvent e) {
		// TODO add your code here
	}

	private void roomTypesActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void roomTypesMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void roomTypesFocusLost(FocusEvent e) {
		// TODO add your code here
	}

	private void layoutEditorMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void layoutEditorMouseMoved(MouseEvent e) {
		// TODO add your code here
	}

	private void checkLayouActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void DashedModeActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void showHideLineLenActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void addDoorsbuttonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void undoDivisionbuttonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void clearButtonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void hyperGraphEditorMousePressed(MouseEvent e) {
		// TODO add your code here
	}

	private void hyperGraphEditorMouseReleased(MouseEvent e) {
		// TODO add your code here
	}

	private void hyperGraphEditorMouseDragged(MouseEvent e) {
		// TODO add your code here
	}

	private void hyperGraphEditorMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void panel3ComponentShown(ComponentEvent e) {
		// TODO add your code here
	}

	private void filesListValueChanged(ListSelectionEvent e) {
		// TODO add your code here
	}

	private void saveFileButtonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void SolidModeActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - szymon gajek
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
		help_menuItem = new JMenuItem();
		about_menuItem = new JMenuItem();
		helpPL_menuItem = new JMenuItem();
		aboutPL_menuItem = new JMenuItem();
		panel5 = new JPanel();
		zoomMode = new JButton();
		zoomLabel = new JLabel();
		label5 = new JLabel();
		RoomLabel = new JTextField();
		label9 = new JLabel();
		roomTypes = new JComboBox();
		label8 = new JLabel();
		areaValueLabel = new JLabel();
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		layoutEditor = new JPanel();
		panel6 = new JPanel();
		SolidMode = new JButton();
		DashedMode = new JButton();
		showHideLineLen = new JButton();
		addDoorsbutton = new JButton();
		undoButton = new JButton();
		clearButton2 = new JButton();
		panel7 = new JPanel();
		leftValidationPanel = new JPanel();
		checkLayoutButton = new JButton();
		scrollPane6 = new JScrollPane();
		validationMessage = new JTextArea();
		panel2 = new JPanel();
		scrollPane2 = new JScrollPane();
		hyperGraphEditor = new HyperGraphEditor();
		panel3 = new JPanel();
		validationEditor = new ValidationEditor();
		label6 = new JLabel();
		scrollPane3 = new JScrollPane();
		testFilesSelectionTable = new JTable();
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

				//---- help_menuItem ----
				help_menuItem.setText("Help");
				help_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						about_menuItemActionPerformed(e);
					}
				});
				menu3.add(help_menuItem);

				//---- about_menuItem ----
				about_menuItem.setText("About");
				menu3.add(about_menuItem);
				menu3.addSeparator();

				//---- helpPL_menuItem ----
				helpPL_menuItem.setText("Pomoc");
				menu3.add(helpPL_menuItem);

				//---- aboutPL_menuItem ----
				aboutPL_menuItem.setText("O Programie");
				aboutPL_menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						helpTests_menuItemActionPerformed(e);
					}
				});
				menu3.add(aboutPL_menuItem);
			}
			menuBar1.add(menu3);
		}
		setJMenuBar(menuBar1);

		//======== panel5 ========
		{

			// JFormDesigner evaluation mark
			panel5.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel5.getBorder())); panel5.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel5.setLayout(new GridBagLayout());
			((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {8, 85, 0, 0};
			((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {29, 23, 23, 23, 23, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

			//---- zoomMode ----
			zoomMode.setText("Start Zooming");
			zoomMode.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					zoomModeActionPerformed(e);
				}
			});
			panel5.add(zoomMode, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 3, 0), 0, 0));
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
			label9.setText("Area Type");
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
				new Insets(0, 0, 3, 3), 0, 0));
		}
		contentPane.add(panel5, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.DEFAULT));

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

					//======== layoutEditor ========
					{
						layoutEditor.setBackground(Color.white);
						layoutEditor.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								layoutEditorMouseClicked(e);
							}
						});
						layoutEditor.addMouseMotionListener(new MouseMotionAdapter() {
							@Override
							public void mouseMoved(MouseEvent e) {
								layoutEditorMouseMoved(e);
							}
						});
						layoutEditor.setLayout(null);

						{ // compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < layoutEditor.getComponentCount(); i++) {
								Rectangle bounds = layoutEditor.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = layoutEditor.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							layoutEditor.setMinimumSize(preferredSize);
							layoutEditor.setPreferredSize(preferredSize);
						}
					}
					scrollPane1.setViewportView(layoutEditor);
				}
				panel1.add(scrollPane1, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.CENTER));

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
				panel1.add(panel6, cc.xy(1, 2, CellConstraints.FILL, CellConstraints.FILL));

				//======== panel7 ========
				{
					panel7.setLayout(new FormLayout(
						"100dlu, $lcgap, 376dlu:grow",
						"fill:54dlu"));

					//======== leftValidationPanel ========
					{
						leftValidationPanel.setLayout(new FormLayout(
							"default:grow",
							"36dlu:grow, $lgap"));

						//---- checkLayoutButton ----
						checkLayoutButton.setText("Check Layout");
						checkLayoutButton.setSelectedIcon(null);
						checkLayoutButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
						checkLayoutButton.setPreferredSize(new Dimension(145, 33));
						checkLayoutButton.setRequestFocusEnabled(false);
						checkLayoutButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								checkLayouActionPerformed(e);
							}
						});
						leftValidationPanel.add(checkLayoutButton, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.CENTER));
					}
					panel7.add(leftValidationPanel, cc.xy(1, 1));

					//======== scrollPane6 ========
					{

						//---- validationMessage ----
						validationMessage.setText("text");
						validationMessage.setBackground(Color.white);
						validationMessage.setEditable(false);
						validationMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
						scrollPane6.setViewportView(validationMessage);
					}
					panel7.add(scrollPane6, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.FILL));
				}
				panel1.add(panel7, cc.xy(1, 4));
			}
			tabbedPane1.addTab("Layout Editor", panel1);


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
				panel2.add(scrollPane2, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.CENTER));
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
						"145dlu, $ugap, 288dlu:grow",
						"default, $lgap, default, $pgap, default"));

					//---- label6 ----
					label6.setText("Choose Test Suite to edit");
					validationEditor.add(label6, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

					//======== scrollPane3 ========
					{

						//---- testFilesSelectionTable ----
						testFilesSelectionTable.setToolTipText("Select enabled test files using checkboxes");
						scrollPane3.setViewportView(testFilesSelectionTable);
					}
					validationEditor.add(scrollPane3, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));

					//======== scrollPane4 ========
					{

						//======== panel10 ========
						{
							panel10.setLayout(new FormLayout(
								"315dlu:grow",
								"default, $pgap, fill:default:grow"));

							//---- fileNameLabel ----
							fileNameLabel.setText("---");
							panel10.add(fileNameLabel, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

							//---- fileContent ----
							fileContent.setRows(28);
							fileContent.setFont(new Font("Arial", Font.PLAIN, 13));
							panel10.add(fileContent, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
						}
						scrollPane4.setViewportView(panel10);
					}
					validationEditor.add(scrollPane4, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.FILL));

					//---- saveFileButton ----
					saveFileButton.setText("Save file");
					saveFileButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							saveFileButtonActionPerformed(e);
						}
					});
					validationEditor.add(saveFileButton, cc.xy(3, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
				}
				panel3.add(validationEditor, cc.xy(1, 1));
			}
			tabbedPane1.addTab("Project Validation", panel3);

		}
		contentPane.add(tabbedPane1, cc.xywh(2, 3, 2, 1, CellConstraints.FILL, CellConstraints.FILL));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - szymon gajek
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
	private JMenuItem help_menuItem;
	private JMenuItem about_menuItem;
	private JMenuItem helpPL_menuItem;
	private JMenuItem aboutPL_menuItem;
	private JPanel panel5;
	private JButton zoomMode;
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
	private JPanel layoutEditor;
	private JPanel panel6;
	private JButton SolidMode;
	private JButton DashedMode;
	private JButton showHideLineLen;
	private JButton addDoorsbutton;
	private JButton undoButton;
	private JButton clearButton2;
	private JPanel panel7;
	private JPanel leftValidationPanel;
	private JButton checkLayoutButton;
	private JScrollPane scrollPane6;
	private JTextArea validationMessage;
	private JPanel panel2;
	private JScrollPane scrollPane2;
	private HyperGraphEditor hyperGraphEditor;
	private JPanel panel3;
	private ValidationEditor validationEditor;
	private JLabel label6;
	private JScrollPane scrollPane3;
	private JTable testFilesSelectionTable;
	private JScrollPane scrollPane4;
	private JPanel panel10;
	private JLabel fileNameLabel;
	private JTextArea fileContent;
	private JButton saveFileButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
