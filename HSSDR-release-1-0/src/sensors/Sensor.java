package sensors;

import hyperGraphs.HLH;

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
	
	
	
	
	public Sensor(int sx, int sy, int dx, int dy) {
		this.sx = sx;
		this.sy = sy;
		this.dx = dx;
		this.dy = dy;
	}
	
	 
	private Arc2D.Double createArc(){
		Arc2D.Double arc;
		
		int startAngle = calcArcAngleStart();
		 
//		System.out.println("xdif: "+xdif+" ydif: "+ydif+" tang: "+tang+" radAng"+radAng+" startAngle: "+startAngle);
		
		arc=new Arc2D.Double(Arc2D.PIE);
		
		arc.setArcByCenter( sx , sy , HLH.sensorRange,startAngle, HLH.SENSOR_ANGLE_SIZE, Arc2D.PIE);
		return arc;
	}


	/**
	 * for needs of setArcByCenter method calculates starting angle of arc. arc is constructued with use of starting angle+angle range,
	 * not arc middle+range/2 in both sides
	 * @return
	 */
	public int calcArcAngleStart() {
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
		
		startAngle-= HLH.SENSOR_ANGLE_SIZE/2;
		return startAngle;
	}
	public boolean isPointInRange(double x, double y) {
		Rectangle2D.Double rect = new Rectangle2D.Double(x-HLH.SENSOR_CALC_RANGE_TOLERANCE,y-HLH.SENSOR_CALC_RANGE_TOLERANCE,2*HLH.SENSOR_CALC_RANGE_TOLERANCE,2*HLH.SENSOR_CALC_RANGE_TOLERANCE);
		return createArc().intersects(rect);
//		return  arc.contains(x,y);
	}

}
