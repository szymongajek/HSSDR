package revitXmlTesting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import rectangularBoard.Path;


 

public class RoomPainter extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 565393500570355882L;
	int grid_size=10;
	private int sizeX,sizeY;  
//	int path_offset=200;
	RoomPanel rPanel;
	
	
	class RoomPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6685549095468269710L;
		Room room;
		
		public RoomPanel(Room room){
			this.room = room; 
			this.setPreferredSize(new Dimension(sizeX,sizeY));
			this.setSize(sizeX, sizeY);
			repaint();
		}
		int calcXY(int x){
			return x *grid_size;
		}
		/**
		 * wspolrzedne jpanelu Y rosna w dol-> pokoje sa rysowane do gory nogami
		 */
		protected void paintComponent(Graphics g ){
		 
			Graphics2D g2D = (Graphics2D)g;
			g2D.setColor(Color.white);
			g2D.fillRect(0, 0, this.getWidth() , this.getHeight() );
			
			Path toDraw = room.path;
			
//			interior
			colorPathInterior(toDraw, Color.ORANGE, g2D);
			
//			 grid
			g2D.setColor(new Color(120,150,223));
			for (int i = grid_size; i <sizeX; i=i+grid_size) {
				g2D.drawLine (i, 0, i, sizeY);
			}
			for (int i = grid_size; i <sizeY; i=i+grid_size) {
				g2D.drawLine(0, i, sizeX, i);
			}
			
			
			// paint path
			if (toDraw.size()>1){
	 			g2D.setColor(Color.DARK_GRAY);
	 			  BasicStroke normal_stroke = new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
				g2D.setStroke(normal_stroke);
				 
	 			int x1,x2,y1,y2;
	 			for (int i = 0; i < toDraw.size()-1; i++) {
	 				x1= toDraw.getX(i) ;
	 				y1= toDraw.getY(i) ;
	 				
	 				x2= toDraw.getX(i+1) ;
	 				y2= toDraw.getY(i+1) ;
	 				
	 				g2D.drawLine(calcXY(x1), calcXY(y1), calcXY(x2), calcXY(y2)); 
				}///for	
	 		}//(toDraw.size()>1)
			
			//paint doors
			
			for (Door d : room.getDoors()){
				g2D.setColor(Color.blue);
				int dx=calcXY(d.bbox.minX);
				int dy=calcXY(d.bbox.minY);
				int dmx=calcXY(d.bbox.maxX);
				int dmy=calcXY(d.bbox.maxY);;  
//				g2D.drawRect(dx,dy,dmx-dx, dmy-dy);
				g2D.setColor(Color.GREEN);
				g2D.drawOval(dx, dy, 5, 5);
				g2D.setColor(Color.RED);
				g2D.drawOval(dmx, dmy, 5, 5);
				
			}
		}
		private void colorPathInterior(Path p, Color color, Graphics2D g2D ){
			if (p!=null){
				g2D.setColor(color);
				int [][] interior = p.getInterior();
				for (int i = 0 ; i < interior.length  ; i++ )  
					for (int   j=0;   j < interior[0].length;   j++) {
 					if (interior[i][j]==2){
 						g2D.fillRect(i*grid_size, j*grid_size,  grid_size ,  grid_size );
 					}
				}
			}
	}
	}
	public RoomPainter(Room room,int MAX_COORD){
		super(room.name+": "+room.path);
		
		sizeX= MAX_COORD*grid_size;
		sizeY= MAX_COORD*grid_size;  
		
		
		System.out.println(room.name+": "+room.path);
		this.rPanel = new RoomPanel(room); 
		add(rPanel);
		this.setPreferredSize(new Dimension(sizeX+20,sizeY+40));
		this.setSize(sizeX+20,sizeY+40);
		setVisible(true);
		repaint();
		
	}
	
	
}
