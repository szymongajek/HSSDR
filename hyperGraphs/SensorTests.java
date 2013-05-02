package hyperGraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import rectangularBoard.Field;
import rectangularBoard.Interior;


public class SensorTests {

	public static boolean isDoorWatched(DoorsAttributes door, HLH hlh) {
		ObjectHE room1 = door.getNode1().getObjectEdge();
		ObjectHE room2;
		if (door.getNode2()!=null)
			room2 = door.getNode2().getObjectEdge();
		else 
			room2=null;
		
		Collection<HGSensor> col = hlh.getSensors();
		
		ArrayList<HGSensor> sensorsToCheck1 = new ArrayList<HGSensor>();
		ArrayList<HGSensor> sensorsToCheck2 = new ArrayList<HGSensor>();
		
		for (HGSensor sensor : col) {
			if (sensor.parentEdge==room1) 
				sensorsToCheck1.add(sensor);
			else if (sensor.parentEdge==room2)
				sensorsToCheck2.add(sensor);
		}
		
		for (HGSensor sensor : sensorsToCheck1) {
			if (sensor.isPointInRange(door.getMiddleX(),door.getMiddleY()) )
				return true;
		}
		
		for (HGSensor sensor : sensorsToCheck2) {
			if (sensor.isPointInRange(door.getMiddleX(),door.getMiddleY()) )
				return true;
		}
		
		return false;
	}
	public static boolean isPassageWatched(DoorsAttributes d1,DoorsAttributes d2, HLH hlh) {
		
		ObjectHE room=DoorsAttributes.findCommonRoom(d1, d2);

		ArrayList<HGSensor> sensorsInRoom = new ArrayList<HGSensor>();
		
		for (HGSensor sensor : hlh.getSensors()) {
			if (sensor.parentEdge==room) 
				sensorsInRoom.add(sensor);
		}

		int [][] darkArea = new int  [room.getInterior().length][];
		
		for (int i=0;i<room.getInterior().length;i++)
		{
			darkArea[i]=room.getInterior()[i].clone();
		}
		
		for (HGSensor sensor : sensorsInRoom) {
			for (int i = 0; i < darkArea.length; i++) {
				for (int j = 0; j < darkArea[i].length; j++) {
					if (darkArea[i][j]==Interior.ROOM){
						Field f = new Field(i,j);
						if (sensor.isPointInRange(f.cellMiddleX(),f.cellMiddleY()))
								if ( pointShadowed(f.cellMiddleX(),f.cellMiddleY(),sensor.sx,sensor.sy,room.getInterior()) ) 
									darkArea[i][j]=Interior.OUTSIDE;  // in sensor range
						 
					}
						
				}
			}
		}
		
		PathCalulator pc = new PathCalulator(d1,d2,darkArea);
		
		return !pc.doorsAccessible();
	}
	
	
	static boolean pointShadowed(double x1, double y1, double x2, double y2, int[][] interior) {
		
		Line2D.Double line = new Line2D.Double(x1,y1,x2,y2);
		
		for (int i = 0; i < interior.length; i++)  
			for (int j = 0; j < interior[i].length; j++){
				if (interior[i][j]==Interior.OUTSIDE){ // zaslaniaja sciany
					Rectangle2D.Double  rect= new Rectangle2D.Double(i+.01,j+.01,.98,.98);
					if (line.intersects(rect))
						return false;
				}
				}
		
		return true;
	}
	
	
	static class MyCanvas extends JComponent {
		
			/**
		 * 
		 */
		private static final long serialVersionUID = -631784331698004719L;
			Arc2D.Float arc; 
			
		  public MyCanvas(Arc2D.Float arc) {
				super();
				 this.arc=arc;
			}

		public void paint(Graphics g) {
		    Graphics2D g2 = (Graphics2D) g;

		    // Draw the pie chart
		    g2.translate(100, 100);
		    g2.setColor(Color.gray);
		    g2.draw(arc);
		    g2.setColor(Color.red);
		    g2.fill(arc);
		    g2.setColor(Color.black);
		  }
		
		 public   void show( ) {
			    JFrame window = new JFrame();
			    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    window.setBounds(30, 30, 500, 500);
			    window.getContentPane().add(this);
			    window.setVisible(true);
			  }
		}

}
 /*
  * 
  * screeny
  * 1 rozklad pom bez czujnikow
  * 2 screen z czujnikami - zly i dobry projekt - test wybrac ladniejszy
  * napisac pkt 2.1 
  * 
  */
