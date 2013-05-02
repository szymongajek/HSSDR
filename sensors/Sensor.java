package sensors;

import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class Sensor {
	 /**
	  * lokalizacja
	  */
	public final int sx,sy;
	/**
	  * kierunek
	  */
	public final int dx,dy;
	
	public static int range;
	public static int angleSize=90;
	
	Arc2D.Double arc;
	
	
	public Sensor(int sx, int sy, int dx, int dy) {
		this.sx = sx;
		this.sy = sy;
		this.dx = dx;
		this.dy = dy;
		createArc();
	}
	
	 
	private void createArc(){
		int startAngle;
		
		int ydif=-( dy- sy);
		int xdif= dx- sx;
		double tang=0;
		double radAng=0;
		
		if (xdif==0){
			startAngle= ydif<0?270:90;
		}else {
			tang =((double)ydif)/xdif;
			radAng=Math.atan(tang)+(xdif<0?Math.PI:0.0);
			startAngle=(int) Math.round(Math.toDegrees(radAng));
		}
		
		startAngle-= angleSize/2;
		 
//		System.out.println("xdif: "+xdif+" ydif: "+ydif+" tang: "+tang+" radAng"+radAng+" startAngle: "+startAngle);
		
		this.arc=new Arc2D.Double(Arc2D.PIE);
		
		arc.setArcByCenter( sx , sy , range,startAngle,angleSize, Arc2D.PIE);
	}
	public Arc2D.Double getArc(){
		return arc;
	}
	public boolean isPointInRange(double x, double y) {
		double rangeTolerance=.1;
		Rectangle2D.Double rect = new Rectangle2D.Double(x-rangeTolerance,y-rangeTolerance,2*rangeTolerance,2*rangeTolerance);
		return arc.intersects(rect);
//		return  arc.contains(x,y);
	}

}
