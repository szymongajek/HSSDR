package editor;

import hyperGraphs.HLH;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import rectangularBoard.Path;
import sensors.Sensor;
 
 class Arrow{
		  Point start, end;	
		 
		 boolean thick;
		 
	 public Arrow(Point tempArrStart, Point tempArrEnd, boolean thick) {
		super();
		this.start = tempArrStart;
		this.end = tempArrEnd;
		this.thick = thick;
	}


 }
public class FloorsEditor extends JPanel { 
	
	 
	private int sizeX=1000, sizeY=500; // pocztakowy rozmiar planszy (bez zoomowania)
	 
	
	private ArrayList<LayoutEditor> layoutEditorsList;
	
	private Arrow tempArrow;	
	
	private ArrayList<Arrow> arrows = new ArrayList<Arrow>();
	
	
	public FloorsEditor(ArrayList<LayoutEditor> layoutEditorsList){
		this.layoutEditorsList = layoutEditorsList;
	}
	
	public void reset(ArrayList<LayoutEditor> layoutEditorsList){
		this.layoutEditorsList = layoutEditorsList;
		arrows = new ArrayList<Arrow>();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		
		
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2D.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2D.setColor(Color.white);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

		int layoutscount = layoutEditorsList.size();

		int perLayoutPixels = sizeY / layoutscount;

		int xcorrection = 320;
		int ycorrection = perLayoutPixels * 10 / 3;

		AffineTransform saved = g2D.getTransform();
		
		g2D.translate(200, 20.3);
		g2D.scale(.7, .3);
		g2D.shear(-0.5, 0);

		for (int i = 0; i < layoutscount; i++) {
			layoutEditorsList.get(i).paintMe(g);
			g.translate(xcorrection, ycorrection);
		}
		
		g2D.setTransform(saved);
	 
		drawTempArrow(g2D);
		drawArrows(g2D);
		
	}
	
	
	final static BasicStroke arrow_stroke = new BasicStroke(1.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	private void drawArrows(Graphics2D g2D) {
 
		g2D.setColor(Color.BLACK);
		g2D.setStroke(arrow_stroke);
		for (Arrow arr : arrows) {
			g2D.draw(createTwoDirArrowShapeNice(arr ));
			
		}
	
		
	}
	
	private void drawTempArrow(Graphics2D g2d) {
		if (tempArrow==null){
			return;
		}
		g2d.setColor(Color.BLACK);
//		g2d.draw(createArrowBase(tempArrStart, tempArrEnd ));
//		g2d.draw(createArrowBase(tempArrStart, tempArrEnd ));
//		g2d.draw(createArrowBase(tempArrStart, tempArrEnd ));
	
		g2d.draw(createTwoDirArrowShapeNice(tempArrow ));
		
	}

	public void initLayout(int sizeX,int sizeY ){
		
		this.sizeX=sizeX;
		this.sizeY=sizeY;
 
		this.setPreferredSize(new Dimension(sizeX,sizeY));
		this.setSize(sizeX, sizeY);
		
		repaint();
	}

	public void addArrow(int arr1x, int arr1y, int a2x, int a2y, boolean isthick) {
		arrows.add(new Arrow(new Point(arr1x, arr1y), new Point(a2x, a2y), isthick));
		
	}

	public void setTemporaryArrow(int arr1x, int arr1y, int a2x, int a2y) {
		 
		 tempArrow= new Arrow(new Point(arr1x, arr1y), new Point(a2x, a2y), false) ;
		
	}

	public void removeTempArrow() {
		tempArrow=null;
	}
	
	int HOR_SIZE=3;
 	int VER_SIZE=3;
 	
	 public static Shape createArrowBase(Point fromPt, Point toPt) {
	        
		 	
//		 Point shapeStart = new Point(fromPt.);
//		 Point shapeEnd = new Point(toPt);
//		 
		 	Polygon arrowPolygon = new Polygon();
	        arrowPolygon.addPoint(-6,3);
	        arrowPolygon.addPoint(6,3);
	        arrowPolygon.addPoint( 6,-3);
	        arrowPolygon.addPoint(-6,-3);


	        Point midPoint = midpoint(fromPt, toPt);

	        double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

	        AffineTransform transform = new AffineTransform();
	        transform.translate(midPoint.x, midPoint.y);
	        double ptDistance = fromPt.distance(toPt);
	        double scale = ptDistance / 12.0; // 12 because it's the length of the arrow polygon.
	        transform.rotate(rotate);

	        transform.scale(scale, 1);
	        
	        return transform.createTransformedShape(arrowPolygon);
	    }
	
	
	 public static Shape createTwoDirArrowShapeNice( Arrow arrow) {
		 
		 Point fromPt= arrow.start; Point toPt = arrow.end;
		 
	        Polygon arrowPolygon = new Polygon();
	        arrowPolygon.addPoint(-5,1);
	        arrowPolygon.addPoint(5,1);
	        arrowPolygon.addPoint(5,3);
	        arrowPolygon.addPoint(6,0);
	        arrowPolygon.addPoint(5,-3);
	        arrowPolygon.addPoint(5,-1);
	        arrowPolygon.addPoint(-5,-1);
	        arrowPolygon.addPoint(-5,-3);
	        arrowPolygon.addPoint(-6,0);
	        arrowPolygon.addPoint(-5,3);


	        Point midPoint = midpoint(fromPt, toPt);

	        double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

	        AffineTransform transform = new AffineTransform();
	        transform.translate(midPoint.x, midPoint.y);
	        double ptDistance = fromPt.distance(toPt);
	        double scale = ptDistance / 12.0; // 12 because it's the length of the arrow polygon.
	        transform.rotate(rotate);

	        int size;
	        if (arrow.thick){
	        	size = 6;
	        }else {
	        	size = 2;
	        }
	        transform.scale(scale, size);
	       
	        return transform.createTransformedShape(arrowPolygon);
	    }

	
	  public static Shape createOneDirArrowShape(Point fromPt, Point toPt) {
	        Polygon arrowPolygon = new Polygon();
	        arrowPolygon.addPoint(-6,1);
	        arrowPolygon.addPoint(3,1);
	        arrowPolygon.addPoint(3,3);
	        arrowPolygon.addPoint(6,0);
	        arrowPolygon.addPoint(3,-3);
	        arrowPolygon.addPoint(3,-1);
	        arrowPolygon.addPoint(-6,-1);


	        Point midPoint = midpoint(fromPt, toPt);

	        double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

	        AffineTransform transform = new AffineTransform();
	        transform.translate(midPoint.x, midPoint.y);
	        double ptDistance = fromPt.distance(toPt);
	        double scale = ptDistance / 12.0; // 12 because it's the length of the arrow polygon.
	        transform.scale(scale, scale);
	        transform.rotate(rotate);

	        return transform.createTransformedShape(arrowPolygon);
	    }

	    private static Point midpoint(Point p1, Point p2) {
	        return new Point((int)((p1.x + p2.x)/2.0), 
	                         (int)((p1.y + p2.y)/2.0));
	    }
	    

	
}
