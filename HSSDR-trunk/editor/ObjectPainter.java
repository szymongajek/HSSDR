package editor;
 
import hyperGraphs.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ObjectPainter   {
	public static final int AREA_EMPTY=0;
	public static final int AREA_OBJECTHE_CENTER=1;
	public static final int AREA_RELATIONHE=2;
	public static final int AREA_NODE=3;
	public static final int AREA_OBJECTHE_RESIZE=4;
	
	public static final int MIN_HE_SIZE=1;
	
	public static final int NODE_DIST_FROM_HE_IN_SQUARES=2; 
	
	public static   MovableComponent selected =null;
	
	public static boolean debug_mode=false;
	
	public static int getAreaKind(int x, int y, ObjectHE edge){
		
		RelationHE rel  = checkRelationsForPoint(x, y, edge);
		if (rel!=null)
			return AREA_RELATIONHE;
		
		Node node = checkNodesForPoint(x, y, edge);
		if (node!=null)
			return AREA_NODE;
 
		ObjectHE objHE = checkObjectHEForPoint(x, y, edge);
		if (objHE!=null){
			
			if (whatRegionInObjectHE(objHE,x, y )==0)//edge
				return AREA_OBJECTHE_RESIZE;
			else //center
				return AREA_OBJECTHE_CENTER;
		}
			
			
	
		
		return AREA_EMPTY; 
	}
	 
	public static MovableComponent getObjAt(int x, int y, ObjectHE edge){
		RelationHE rel  = checkRelationsForPoint(x, y, edge);
		if (rel!=null) return  rel;
		
		Node node = checkNodesForPoint(x, y, edge);
		if (node!=null)
			return  node;
		
		ObjectHE objHE = checkObjectHEForPoint(x, y, edge);
		if (objHE!=null)
			return objHE;
	 
		return null;
	}
	public static void selectObject(int x, int y, ObjectHE edge){
		 selected=getObjAt(x, y, edge);
	}
	public static void releaseObject(int x, int y, ObjectHE edge){
		selected=null;
		 
	}
	public static boolean isSelected(){
		return selected!=null;
		 
	}
	public static void dragObject(int x, int y, ObjectHE edge, boolean isRightClick){
		int xcoord=x/HyperGraphEditor.calcXY(1);
		int ycoord=y/HyperGraphEditor.calcXY(1);
		 
		if (selected instanceof ObjectHE){
			ObjectHE selected = (ObjectHE)ObjectPainter.selected;
			
//			if (whatRegionInObjectHE(selected,x, y )==0){
			if(!isRightClick){
				//resize objHE
				int rectPX = HyperGraphEditor.calcXY(  selected.getMiddleX());
				int rectPY = HyperGraphEditor.calcXY(  selected.getMiddleY());
			 
				int old_sizex =  selected.getSizeX() ;
				int old_sizey =    selected.getSizeY() ;
				
				int newSizeX=((-rectPX+x)/HyperGraphEditor.calcXY(1));
				int newSizeY=((-rectPY+y)/HyperGraphEditor.calcXY(1));
				
				if (newSizeX<MIN_HE_SIZE)
					newSizeX=MIN_HE_SIZE;
				if (newSizeY<MIN_HE_SIZE)
					newSizeY=MIN_HE_SIZE;
				
				selected.setSizeX(newSizeX*2);
				selected.setSizeY(newSizeY*2);
				
				int transX = (selected.getSizeX()-old_sizex)/2 ;
				int transY = (selected.getSizeY()-old_sizey)/2 ;
				
				for (int i = 0; i < selected.getNodes().size(); i++) {// move nodes
					  Node n = selected.getNodes().get(i);
					  if (n.getAttribute("DIR").equals("E")){
						  selected.getNodes().get(i).translateBy(transX, 0);
					  }else if (n.getAttribute("DIR").equals("S")){
						  selected.getNodes().get(i).translateBy(0, transY);
					  }else if (n.getAttribute("DIR").equals("W")){
						  selected.getNodes().get(i).translateBy(-transX, 0);
					  }else if (n.getAttribute("DIR").equals("N")){
						  selected.getNodes().get(i).translateBy(0, -transY);
					  }
					   
				}
				
			}else {//move objHE
				
				
				int transX = xcoord - selected.getMiddleX();
				int transY = ycoord - selected.getMiddleY();
				
				for (int i = 0; i < selected.getNodes().size(); i++) {// move nodes
					  selected.getNodes().get(i).translateBy(transX, transY); 
	  		
				} 
				selected.moveTo(xcoord, ycoord);// move objectHE
			}
			
		}else {
			selected.moveTo(xcoord, ycoord); //move rel, node
		}
		
	}

	 
	public static  Node checkNodesForPoint(int x, int y, ObjectHE edge  ){
		Node ret = isPointInNodesOfHE(x, y, edge);
		if (ret!=null)
			return ret;
 
		for (int i = 0; i < edge.getChildElements().size(); i++) {
			HyperEdge  e =  edge.getChildElements().get(i);
			if (e instanceof ObjectHE){
				ret = checkNodesForPoint(x, y, (ObjectHE)e);
				if (ret!=null)
					return ret;
				
				}
			}
		return null;
	}	
	private static Node isPointInNodesOfHE(int x, int y, ObjectHE objectHE) {
		for (int i = 0; i < objectHE.getNodes().size(); i++) {
			if (isPointInNode(x, y, objectHE.getNodes().get(i)))
					return objectHE.getNodes().get(i);
					
		} 
		return null;
		
	}
	
	public static  RelationHE checkRelationsForPoint(int x, int y, ObjectHE edge  ){
		RelationHE rel;
		for (int i = 0; i < edge.getChildElements().size(); i++) {
			HyperEdge  e =  edge.getChildElements().get(i);
			if (e instanceof RelationHE){
				if (isPointInRelationHE(x, y, (RelationHE)e))
					return (RelationHE)e;
				}
			}
  
		for (int i = 0; i < edge.getChildElements().size(); i++) {
			HyperEdge  e =  edge.getChildElements().get(i);
			if (e instanceof ObjectHE){
				rel = checkRelationsForPoint(x, y, (ObjectHE)e);
				if (rel!=null)
					return rel;
				}
			}
		return null;
	}	
	 
	
	public static  ObjectHE checkObjectHEForPoint(int x, int y, ObjectHE edge  ){
		 
		for (int i = 0; i < edge.getChildElements().size(); i++) {
			HyperEdge  e =  edge.getChildElements().get(i);
			if (e instanceof ObjectHE){
				ObjectHE ret = checkObjectHEForPoint(x, y, (ObjectHE)e);
				if (ret!=null)
					return ret;
				}
			}
 
		if (isPointInObjectHE(x, y, edge)){
			return edge;
		}
		return null;
	}	
	 
	
	
	 
	private static boolean isPointInRelationHE(int x, int y, RelationHE rel) {
		 
		if (rel.getMiddleX()==0 && rel.getMiddleY() == 0)
			throw new RuntimeException("nie ustawiona pos realcji");
			 
			
		int rectSX = HyperGraphEditor.calcXY( rel.getSizeX()) ;
		int rectSY = HyperGraphEditor.calcXY(  rel.getSizeY());
		int rectPX = HyperGraphEditor.calcXY(  rel.getMiddleX());
		int rectPY = HyperGraphEditor.calcXY(  rel.getMiddleY());
	 
		
		int Xmin=rectPX - rectSX/2;
		int Ymin=rectPY - rectSY/2;
		int Xmax=rectPX + rectSX/2;
		int Ymax=rectPY + rectSY/2;
		
		 return (x >=Xmin)&&(x<=Xmax)&&(y>=Ymin)&&(y<=Ymax); 
		
		 
	}

	private static boolean isPointInObjectHE(int x, int y, ObjectHE edge) {
		
		int rectSX =  HyperGraphEditor.calcXY( edge.getSizeX()) ;
		int rectSY =  HyperGraphEditor.calcXY( edge.getSizeY());
		int rectPX =  HyperGraphEditor.calcXY(  edge.getMiddleX());
		int rectPY = HyperGraphEditor.calcXY(  edge.getMiddleY());
	 
		
		int Xmin=rectPX - rectSX/2;
		int Ymin=rectPY - rectSY/2;
		int Xmax=rectPX + rectSX/2;
		int Ymax=rectPY + rectSY/2;
		
		 return (x >=Xmin)&&(x<=Xmax)&&(y>=Ymin)&&(y<=Ymax); 
		
	}
	
	private static int whatRegionInObjectHE1(ObjectHE edge, int x, int y) {
		int center_padding = 10;
		int on_edge=0;
		int in_center=1;
		
//		int rectSX =  HyperGraphEditor.calcXY( edge.getSizeX()) ;
//		int rectSY =  HyperGraphEditor.calcXY( edge.getSizeY());
//		int rectPX =  HyperGraphEditor.calcXY(  edge.getMiddleX());
		int rectPY = HyperGraphEditor.calcXY(  edge.getMiddleY());
	 
//		
//		int Xmin=rectPX - rectSX/2;
//		int Ymin=rectPY - rectSY/2;
//		int Xmax=rectPX + rectSX/2;
//		int Ymax=rectPY + rectSY/2;
		
		 if (   (y<rectPY)){
			 
			 return on_edge;
		 }else 
			 return in_center;
		  
		
	}
	private static int whatRegionInObjectHE (ObjectHE edge, int x, int y) {
		int center_padding = 10;
		int on_edge=0;
		int in_center=1;
		
		int rectSX =  HyperGraphEditor.calcXY( edge.getSizeX()) ;
		int rectSY =  HyperGraphEditor.calcXY( edge.getSizeY());
		int rectPX =  HyperGraphEditor.calcXY(  edge.getMiddleX());
		int rectPY = HyperGraphEditor.calcXY(  edge.getMiddleY());
	 
		
		int Xmin=rectPX - rectSX/2;
		int Ymin=rectPY - rectSY/2;
		int Xmax=rectPX + rectSX/2;
		int Ymax=rectPY + rectSY/2;
		
		 if (  (x>=Xmax-center_padding)&&(y>=Ymax-center_padding)){
			 
			 return on_edge;
		 }else 
			 return in_center;
		  
		
	}
	private static boolean isPointInNode(int x, int y, Node n) {
		int posX =HyperGraphEditor.calcXY(  n.getMiddleX());
		int posY =HyperGraphEditor.calcXY( n.getMiddleY());
		
		return  (Point2D.distance (x,y,posX,posY)< 10 );
	}

	
	public static  void paintHyperEdge(Graphics2D g2D, HyperEdge edge, boolean drawObjects, boolean drawRelations ){
 
		if (edge instanceof ObjectHE){
			if (drawObjects)
				paintObjectHE(g2D, (ObjectHE)edge);
			paintChildElements(g2D, (ObjectHE)edge, drawObjects, drawRelations );
		}else if (edge instanceof RelationHE){
			if (drawRelations)
				paintRelationHE(g2D, (RelationHE)edge);
		}
 
	}	
	public static  void paintChildElements(Graphics2D g2D, ObjectHE edge, boolean drawObjects, boolean drawRelations ){
		
		for (int i = 0; i < edge.getChildElements().size(); i++) {
			HyperEdge  e =  edge.getChildElements().get(i);
			// nie rysujemy obszarow empty
			if (HLH.ROOM_TYPES.Empty.toString().equals(e.getAttribute(HLH.ROOM_TYPE_LABEL))){
				continue;
			}
			paintHyperEdge(g2D, e,drawObjects,drawRelations);
		}
	}
	public static void paintRelationHE(Graphics2D g2d, RelationHE rel) {
	 
		Node source  = rel.getSource();
		Node target = rel.getTarget();
		
		int sourceX =  source.getMiddleX();
		int sourceY =  source.getMiddleY(); 
		
		int targetX =  target.getMiddleX();
		int targetY = target.getMiddleY();
 
		// jezeli nie jest ustaione jeszce to obliczmy polozenie elipsy i ustawiamy param w MobableComp tego elementu
		if (rel.getMiddleX()==0 && rel.getMiddleY() == 0){
		 
			rel.setSizeX(2);
			rel.setSizeY(1);

			rel.setMiddleX(((sourceX + targetX)/2)+1+1);//+20 zeby sie nie zlewaly w pionie, ja nody sa w jedej lini
			rel.setMiddleY( (sourceY + targetY)/2   + 2);
		} 
		
		
		
		ObjectHE firstObj = source.getObjectEdge();
		ObjectHE secObj = target.getObjectEdge();
		// nie rysujemy relacji do obszarow empty
		if ((HLH.ROOM_TYPES.Empty.toString().equals(firstObj
				.getAttribute(HLH.ROOM_TYPE_LABEL)))
				|| (HLH.ROOM_TYPES.Empty.toString().equals(secObj
						.getAttribute(HLH.ROOM_TYPE_LABEL)))) {
			return;
		}
		
		int elSX = HyperGraphEditor.metersToPixels * rel.getSizeX() ;
		int elSY = HyperGraphEditor.metersToPixels * rel.getSizeY();
		int elPX = HyperGraphEditor.metersToPixels * rel.getMiddleX();
		int elPY = HyperGraphEditor.metersToPixels * rel.getMiddleY();
		
		sourceX *=  HyperGraphEditor.metersToPixels ;
		sourceY *=  HyperGraphEditor.metersToPixels ; 
		targetX *=  HyperGraphEditor.metersToPixels ;
		targetY *= HyperGraphEditor.metersToPixels ;
		
		g2d.drawLine(sourceX, sourceY, elPX, elPY);
		g2d.drawLine(targetX, targetY,  elPX,  elPY);
		g2d.setColor(Color.white);
		g2d.fillOval(elPX-elSX/2, elPY-elSY/2, elSX, elSY);
		g2d.setColor(Color.black);
		g2d.drawOval(elPX-elSX/2, elPY-elSY/2, elSX, elSY);
		
		String kind =rel.getAttribute(HLH.KIND);
 
		g2d.drawString(kind, elPX-5 , elPY );
	}
	
	
	
	public static  void paintObjectHE(Graphics2D g2D, ObjectHE edge ){
		
		int rectSX = HyperGraphEditor.metersToPixels * edge.getSizeX() ;
		int rectSY = HyperGraphEditor.metersToPixels * edge.getSizeY();
		int rectPX = HyperGraphEditor.metersToPixels * edge.getMiddleX();
		int rectPY = HyperGraphEditor.metersToPixels * edge.getMiddleY();
		
		if (edge.getLevel()==0)
			g2D.setColor(Color.red);
		else 
			g2D.setColor(Color.black);
	
		drawNodes(g2D, edge.getNodes(),rectPX,rectPY);
		
		int rX0=rectPX - rectSX/2;
		int rY0=rectPY - rectSY/2;
		
		g2D.setColor(Color.white);
		g2D.fillRect(rX0, rY0, rectSX , rectSY);
		
		if (edge.getLevel()==0)
			g2D.setColor(Color.red);
		else 
			g2D.setColor(Color.black);
		
		g2D.drawRect(rX0, rY0, rectSX , rectSY);
		
		String label = edge.getAttribute(HLH.USER_LABEL);

		if (!"".equals(label)) {

			int labx = rX0 + 2, laby = rY0- 4;

			Font prev = g2D.getFont();
			Font font = new Font("Arial", Font.BOLD, 16);
			g2D.setFont(font);

			drawString(g2D, label, labx, laby);
			g2D.setFont(prev);
		}

	}

	private static  void drawString(Graphics2D g2D, String text, int x, int y) {
		for (String line : text.split("\\\\n"))
			g2D.drawString(line, x, y += g2D.getFontMetrics().getHeight());
	} 

	
	public static  void drawNodes(Graphics2D g2D, ArrayList<Node> nodes, int edgMiddleX,int edgMiddleY){
		
		for (int i = 0; i < nodes.size(); i++) {
			int posX =HyperGraphEditor.metersToPixels * nodes.get(i).getMiddleX();
			int posY =HyperGraphEditor.metersToPixels * nodes.get(i).getMiddleY(); 
			
			g2D.fillOval(posX-4, posY-4, 8, 8);
			
			//etykiety i linie laczace
			String dir=	nodes.get(i).getAttribute(HLH.DIRECTION);
			if (dir.equals("N")){
				g2D.drawLine(posX, posY, posX, edgMiddleY);
				g2D.drawString(nodes.get(i).getAttribute(HLH.LABEL), posX , posY-6 );
			}else if (dir.equals("S")){
				g2D.drawLine(posX, posY, posX, edgMiddleY);
				g2D.drawString(nodes.get(i).getAttribute(HLH.LABEL), posX , posY+22 );
			}else if (dir.equals("E")){
				g2D.drawLine(posX, posY, edgMiddleX, posY);
				g2D.drawString(nodes.get(i).getAttribute(HLH.LABEL), posX+16 , posY+10 );
			}else if (dir.equals("W")){
				g2D.drawLine(posX, posY, edgMiddleX, posY);
				g2D.drawString(nodes.get(i).getAttribute(HLH.LABEL), posX-16 , posY+14 );
			}
			
			
			 //ilosc realcji - debug
			if (debug_mode)
				if (dir.equals("N")){
					 
					g2D.drawString("r:"+String.valueOf(nodes.get(i).getRelations().size()), posX , posY-18 );
				}else if (dir.equals("S")){
					 
					g2D.drawString("r:"+String.valueOf(nodes.get(i).getRelations().size()), posX , posY+32 );
				}else if (dir.equals("E")){
					 
					g2D.drawString("r:"+String.valueOf(nodes.get(i).getRelations().size()), posX+16 , posY+20 );
				}else if (dir.equals("W")){
					 
					g2D.drawString("r:"+String.valueOf(nodes.get(i).getRelations().size()), posX-16 , posY+24 );
				}
			
			//drzwi - debug?
			if (nodes.get(i).hasDoors())
				g2D.drawRect(posX, posY, 12, 12);
			if (nodes.get(i).numberOfDoors()>1) {
				g2D.drawRect(posX, posY, 18, 18);
				g2D.drawRect(posX, posY, 12, 12);
				g2D.drawString(String.valueOf("x"+nodes.get(i).numberOfDoors()), posX+21, posY+11);
			}
	
		}
		
	}
	public static  void paintGraph(ObjectHE rootEdge, Graphics2D g2D){
		paintObjectHE(g2D, rootEdge);
		paintChildElements(g2D, rootEdge, true, false);
		paintChildElements(g2D, rootEdge, false, true);
	}
	 
	
	 
	 
}
