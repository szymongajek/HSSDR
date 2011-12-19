/*
 * Created by JFormDesigner on Sun Jan 23 19:44:44 CET 2011
 */

package editor;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.layout.*;

/**
 * @author sz gajek
 */
public class TestsHelp extends JDialog {
	public TestsHelp(Frame owner) {
		super(owner);
		initComponents();
		
		textPane1.setText("Symbols available in test: " +
				"\n  -logic: " +
				"\n      -forall, exists, in, :, forall, <=>, (), =, >=, <=, !=, and, or, #" +
				"\n  -design:" +
				"\n      -adjacent(room1, room2): if rooms are directly adjacent" +
				"\n      -accessible(room1, room2): if rooms are directly accessible " +
				"\n      -Rooms(room): if argument is a room " +
				"\n      -type(room): get set type of a room   " +
				"\n      -Doors(doors): if argument is a door  " +
				"\n      -doorsInRoom(doors, room): if doors belong to room " +
				"\n      -doorsDist(doors1, doors2): distance between doors1 and doors2 in meters " +
				"\n      -area(room): area of room in square meters " +
				"\n      -Sensors(sensor): if argument is a sensor " +
				"\n      -sensorInRoom(sensor, room): if sensor is in room " +
				"\n      -surveilledDoors(doors): if doors are watched by a sensor " +
				"\n      -isPassageWatched(doors1,doors2): if it is impossible to get from doors1 and doors2 " +
				"\n      -without entering a sensor range, doors1 and doors2 are situated in one room");
	}

	public TestsHelp(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - sz gajek
		scrollPane1 = new JScrollPane();
		textPane1 = new JTextPane();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(textPane1);
		}
		contentPane.add(scrollPane1, cc.xy(1, 1));
		setSize(515, 495);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - sz gajek
	private JScrollPane scrollPane1;
	private JTextPane textPane1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
