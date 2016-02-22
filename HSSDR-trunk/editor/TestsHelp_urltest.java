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

import com.jgoodies.forms.layout.*;

import controller.Start;

/**
 * @author sz gajek
 */
public class TestsHelp_urltest extends JDialog {
	Path path = Paths.get("editor", "helpContents", "help.html");
	File currentDir = Paths.get("editor", "helpContents").toFile();

	
	
	 private JEditorPane createEditorPane() {
	        JEditorPane editorPane = new JEditorPane();
	        editorPane.setEditable(false);
	        java.net.URL helpURL = Start.class.getResource(
	                                        "help.html");
	        
	        
	        if (helpURL != null) {
	            try {
	                editorPane.setPage(helpURL);
	            } catch (IOException e) {
	                System.err.println("Attempted to read a bad URL: " + helpURL);
	                Logger.LOGGER.error("",e);
	                
	            }
	        } else {
	            System.err.println("Couldn't find file:  "+helpURL);
	        }

	        return editorPane;
	    }
	
	
	void loadHTML() {
		String html;

		// html = "<html>"+
		// "<h4>Symbols available in test: </h4>"+
		// "<img alt='' src='file:img/pietra2.bmp'/>"+
		// "<img alt='' src='img/pietra2.bmp'/>"+
		// "<img alt='' src='pietra2.bmp'/>"+"</html>";

		try {
			byte[] encoded = Files.readAllBytes(path);
			html = new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			Logger.LOGGER.error(path, e);
			html = "no help file available";
		}

		textPane1.setContentType("text/html");
		textPane1.setText(html);

		HTMLDocument doc = (HTMLDocument) textPane1.getDocument();

		try {
			// doc.setBase( currentDir.toURI().toURL());
			Logger.LOGGER.debug(currentDir.toURI().toURL());

			doc
					.setBase(new URL(
							"file:/Q:/HSSDR/workspace/HSSDR-trunk/editor/helpContents/"));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		
		Logger.LOGGER.debug((HTMLDocument) textPane1.getDocument());
		Logger.LOGGER.debug(((HTMLDocument) textPane1.getDocument()).getBase());
		Logger.LOGGER.debug(((HTMLDocument) textPane1.getDocument())
				.getDocumentProperties());

	}

	void initUrl(){
		try {
			url = new URL(
					"file:/Q:/HSSDR/workspace/HSSDR-trunk/editor/help.html");
			// textPane1 =new JEditorPane(path.toFile().toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// try {
		// textPane1 = new
		// JEditorPane(Paths.get("editor","helpContents","pietra2.bmp"
		// ).toFile().toURL().toExternalForm());
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
	}
	public TestsHelp_urltest(Frame owner) {
		super(owner);
		 initUrl();
		initComponents();
//		loadHTML();

	}

	public TestsHelp_urltest(Dialog owner) {
		super(owner);
		 initUrl();
		initComponents();
//		loadHTML();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - szymon gajek
		scrollPane1 = new JScrollPane();
		
		textPane1 = createEditorPane();
		
//		try {
//			 textPane1 =new JEditorPane(url);
//		}  catch (IOException e) {
//			e.printStackTrace();
//		}

		
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout("default:grow",
				"fill:default:grow"));

		// ======== scrollPane1 ========
		{
			scrollPane1.setViewportView(textPane1);
		}
		contentPane.add(scrollPane1, cc.xy(1, 1));
		setSize(515, 495);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - szymon gajek
	private JScrollPane scrollPane1;
	private JEditorPane textPane1;
	// JFormDesigner - End of variables declaration //GEN-END:variables
	private URL url;
	
	
}
