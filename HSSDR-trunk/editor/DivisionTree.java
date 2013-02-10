package editor;
import hyperGraphs.HyperEdge;
import hyperGraphs.ObjectHE;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
 
public class DivisionTree extends JFrame implements TreeSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 210570486414371057L;
	JTree tree;
	private JButton deleteDivision; 
	MainWindow mainWindow;
	
	private Icon customOpenIcon = new ImageIcon("images/node_open.gif");
	  private Icon customClosedIcon = new ImageIcon("images/node_closed.gif");
	  private Icon customLeafIcon = new ImageIcon("images/leaf.gif");
	
	public DivisionTree(MainWindow mainWindow){
		super("Selecting division"); 
	    setSize(275, 400);
	    setVisible(true);
		setAlwaysOnTop(true);
		this.mainWindow=mainWindow;
		
		// button usun
		deleteDivision = new JButton();
		deleteDivision.setText("Delete division");
		deleteDivision.setPreferredSize(new Dimension(275,50));
		deleteDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDivisionActionPerformed(e);
			}
		});
 
	}
	
	protected void deleteDivisionActionPerformed(ActionEvent e) {
		mainWindow.deleteDivision(tree.getLastSelectedPathComponent().toString());
		repaint();
	}

	public void valueChanged(TreeSelectionEvent event) {
		mainWindow.markDivisionPath(tree.getLastSelectedPathComponent().toString());
 
	  }
	
	public void createTree(ObjectHE rootEdge){
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootEdge);
		tree = new JTree(root);
		addChildElements(rootEdge,root);
		expandAll();
		tree.addTreeSelectionListener(this);
		
		DefaultTreeCellRenderer renderer3 = new DefaultTreeCellRenderer();
	    renderer3.setOpenIcon(customOpenIcon);
	    renderer3.setClosedIcon(customClosedIcon);
	    renderer3.setLeafIcon(customLeafIcon);
	    tree.setCellRenderer(renderer3);
	    
  
	    JScrollPane pane = new JScrollPane (tree);
	    pane.setPreferredSize(new Dimension(275,310));
	    add(pane,BorderLayout.NORTH );
		
		add( deleteDivision,BorderLayout.SOUTH);
		
	}
	
	public void expandAll( ) {
	    int row = 0;
	    while (row < tree.getRowCount()) {
	      tree.expandRow(row);
	      row++;
	      }
	    }
	
	public static  void addChildElements(  ObjectHE edge,DefaultMutableTreeNode nodeForEgde   ){
 
		for (int i = 0; i < edge.getChildElements().size(); i++) {
			HyperEdge  e =  edge.getChildElements().get(i);
			if (e instanceof ObjectHE){
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(e);
				nodeForEgde.add(child);
				addChildElements((ObjectHE)e, child);
			}
		}
	}
	
	public void delete(){
 
		 dispose();
	}
	

}
