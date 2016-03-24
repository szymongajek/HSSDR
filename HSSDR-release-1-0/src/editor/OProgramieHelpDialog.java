/*
 * Created by JFormDesigner on Sun Jan 23 19:44:44 CET 2011
 */

package editor;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;

import util.Logger;
import util.UtilHSSDR;

import com.jgoodies.forms.layout.*;

import controller.Start;

/**
 * @author sz gajek
 */
public class OProgramieHelpDialog extends JDialog {
	
	 private static final String HTML_FILE_NAME = "helpContents/aboutPL.html";
	 
	private JEditorPane loadHtml() {
	       return GuiUtil.loadHtml(HTML_FILE_NAME);
	    }
	
	public OProgramieHelpDialog(Frame owner) {
		super(owner);
		initComponents();

	}

	public OProgramieHelpDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		scrollPane = new JScrollPane();
		htmlContent_EditorPane = loadHtml();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("O Programie");
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
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane scrollPane;
	private JEditorPane htmlContent_EditorPane;
	// JFormDesigner - End of variables declaration //GEN-END:variables
	
	
}
