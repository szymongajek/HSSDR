/*
 * Created by JFormDesigner on Sun Jan 23 20:25:40 CET 2011
 */

package editor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.*;
import com.jgoodies.forms.layout.*;

/**
 * @author sz gajek
 */
public class AboutDialog extends JDialog {
	public AboutDialog(Frame owner) {
		super(owner);
		initComponents();
		
//		BufferedImage myPicture;
//		try {
//			myPicture = ImageIO.read(Paths.get("editor","helpContents","pietra2.bmp" ).toFile());
//
//			pictureLabel = new JLabel(new ImageIcon(myPicture));
//			//			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
////			add(picLabel);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - szymon gajek
		scrollPane1 = new JScrollPane();
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(Paths.get("editor","helpContents","pietra2.bmp" ).toFile());

			pictureLabel = new JLabel(new ImageIcon(myPicture));
			//			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//			add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		CellConstraints cc = new CellConstraints();
//
//		//======== this ========
		Container contentPane = getContentPane();
//		contentPane.setLayout(new FormLayout(
//			"default:grow",
//			"fill:default:grow"));

		//======== scrollPane1 ========
		{

			//---- pictureLabel ----
//			pictureLabel.setText("text");
//			scrollPane1.setViewportView(pictureLabel);
		}
//		contentPane.add(scrollPane1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.FILL));
		contentPane.add(pictureLabel);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - szymon gajek
	private JScrollPane scrollPane1;
	private JLabel pictureLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
