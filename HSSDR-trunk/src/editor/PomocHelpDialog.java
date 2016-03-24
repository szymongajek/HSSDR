/*
 * Created by JFormDesigner on Wed Feb 24 12:34:38 CET 2016
 */

package editor;

import java.awt.*;
import javax.swing.*;

import com.jgoodies.forms.layout.*;

/**
 * @author szymon gajek
 */
public class PomocHelpDialog extends JDialog {
	
	 private static final String HTML_FILE_NAME = "helpContents/helpPL.html";
	 
		private JEditorPane loadHtml() {
		       return GuiUtil.loadHtml(HTML_FILE_NAME);
		    }
	
	public PomocHelpDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	public PomocHelpDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane = new JScrollPane();
		htmlContent_EditorPane = loadHtml();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Pomoc");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== scrollPane ========
		{
			scrollPane.setViewportView(htmlContent_EditorPane);
		}
		contentPane.add(scrollPane, cc.xy(1, 1));
		setSize(875, 705);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane;
	private JEditorPane htmlContent_EditorPane;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
