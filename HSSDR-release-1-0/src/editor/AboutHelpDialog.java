/*
 * Created by JFormDesigner on Wed Feb 24 11:30:46 CET 2016
 */

package editor;

import java.awt.*;
import javax.swing.*;

import com.jgoodies.forms.layout.*;

/**
 * @author szymon gajek
 */
public class AboutHelpDialog extends JDialog {
	
	 private static final String HTML_FILE_NAME = "helpContents/about.html";
	 
		private JEditorPane loadHtml() {
		       return GuiUtil.loadHtml(HTML_FILE_NAME);
		    }
		
	
	public AboutHelpDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	public AboutHelpDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		htmlContent_EditorPane = loadHtml();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("About");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(htmlContent_EditorPane);
		}
		contentPane.add(scrollPane1, cc.xy(1, 1));
		setSize(875, 705);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JEditorPane htmlContent_EditorPane;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
