package editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import rectangularBoard.Path;
import sensors.Sensor;
 

enum Mode {
	EMPTY, //		 0 - draw niezaczety ksztalt
	DRAWING_OUTLINE,// 1 - draw zaczety ksztalt
	OUTLINE_FINISHED,// 2 - po  narysowaniu rootShape , niezaznaczony developedPath
	AREA_SELECTED,// 25 - po  narysowaniu rootShape , zaznaczony developedPath
	DIVIDING_AREA,//	3 - po  narysowaniu rootShape, w trakcie rysowania dzialacej krawedzi editedPath
	ZOOM,// 10 - zoom
	ADD_DOORS,// 20  - dodawanie drzwi
	SENSOR_ADDING; //dodanie pkt 1 czujnika i oczekiwanie na wybranie pkt 2 
}
public class LayoutEditor extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2972602188639346016L;

	private int sizeX=500, sizeY=500; // pocztakowy rozmiar planszy (bez zoomowania)
	 
	private double zoomedTo; // skala powiekszenia
	private double zoomSpeed=10.0/100.0; // szybkoœæ  powiekszenia
	private int gridSize=20; // co ile px siatka
	private float gridMeteres=0;
	
	private boolean initialized = false;
	
	// wyswietlanie rysowanej dlugosci
	private int showLineMeteres=-1;
	private int showLineX=0;
	private int showLineY=0;
	
	private boolean showLabels=true;
	
	
	private int markedGrigX =0, markedGrigY=0;
	
	private Point markedDoor1 = new Point(), markedDoor2= new Point();
	private boolean possibleDoorsToShow=false;
	
	
	private Path rootPath = new Path(sizeX/gridSize, sizeY/gridSize );
	 
	//path ktory reprezenetuje hioer krawedz ktora dzielimy
	private  Path developedPath= null; 
	
	 
	//path jest pod myszka i jest podswietlany
	private  Path highlitedPath= null; 
	
//	path zaznaczane na czerwono jako psujace testy
	private  ArrayList<Path> alertedPaths= new ArrayList<Path>(); 
 
	//na biezaco rysowany podzial
	private Path editedPath = new Path(sizeX/gridSize, sizeY/gridSize );
	
	// obszar podswietlony dla celów usuwania zaznaczany w drzewku divisionTree
	private Path divisionPath=null;
	
	//w trakcie dodawania czujnika
	private Sensor addingSensor = null;

	//parametry do linii 
	final static float dash1[] = { 5.0f, 5.0f};
	final static BasicStroke dashed_stroke = new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f, dash1, 2.5f);
	final static BasicStroke normal_stroke = new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	final static BasicStroke rootPath_stroke = new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	final static BasicStroke grid_stroke = new BasicStroke(1.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	    
	public Mode prevDravingMode= Mode.EMPTY;
	public Mode mode=Mode.EMPTY;
	
	
	private boolean tranformApplied;

	public LayoutEditor(){
		  
		 sizeX=this.getWidth();
		 sizeY=this.getHeight();
		 zoomedTo=1;
		
	}

	public void initLayout(int sizeX,int sizeY, int gridSize, float meteres){
		
		this.initialized=true;
		
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.gridSize =gridSize;
		this.gridMeteres=meteres;
 
		this.setPreferredSize(new Dimension(sizeX,sizeY));
		this.setSize(sizeX, sizeY);
		
		repaint();
	}
	
	public void paintMe(Graphics g ){
		
		Graphics2D g2D = (Graphics2D)g;
		if (initialized){
//			System.out.println("scale"+this.getWidth()+  "   "+(int)(sizeX*zoomedTo) );
			
			g2D.setColor(Color.white);
			g2D.fillRect(0, 0, this.getWidth() , this.getHeight() );
			g2D.scale(zoomedTo, zoomedTo);
			
//	 		interior highlited Path
			colorPathInterior(highlitedPath,new Color(151,204,204), g2D);
 
//	 		interior developedPath
			colorPathInterior(developedPath,Color.yellow//Color.orange
			, g2D);
			
//			alerted Paths
			for (Path p :alertedPaths){
				colorPathInterior(p,new Color(255,169,129), g2D);
			}
 			
//	 		interior test1
// 			colorPathInterior(test1,Color.blue, g2D);
//	 		interior test2
// 			colorPathInterior(test2,Color.green, g2D);
  
 			
//	 		interior divisionPath Path
 			colorPathInterior(divisionPath,Color.ORANGE , g2D);
 
 			
//			 siatka
			g2D.setColor(new Color(120,150,223));
			g2D.setStroke(grid_stroke);
			for (int i = gridSize; i <sizeX; i=i+gridSize) {
				g2D.drawLine (i, 0, i, sizeY);
			}
			for (int i = gridSize; i <sizeY; i=i+gridSize) {
				g2D.drawLine(0, i, sizeX, i);
			}
			
//			pomieszczenia typu empty - bez siatki
			paintEmptySpaces(g2D, rootPath);

//			path
	 		paintPathTree(g2D, rootPath,  Color.BLACK, false);
	 		
//	 		root path na koniec zeby byla pogrubiona
	 		paintPath(g2D, rootPath,  Color.BLACK, true);
	 		
//	 		biezaco rysowana linia
 			paintPath(g2D, editedPath,  Color.BLACK, false);
 			
// 		 	 linia podzialu przy undo division
 		 	colorDivisionLine(Color.RED , g2D,null);
 			
 			paintDoorsFromPaths(g2D, rootPath, 0);
 			
 			paintSensorsFromPaths(g2D, rootPath, 0);
			 
//			zaznaczony kwadrat
			g2D.setColor(Color.blue);
	 		g2D.fillOval(markedGrigX * gridSize-5, markedGrigY * gridSize-5, 10, 10);
	 		
//			marked Door
	 		if (possibleDoorsToShow){
	 			//czyscimy
	 			g2D.setColor(Color.white);
	 			g2D.setStroke(rootPath_stroke);
		 		g2D.drawLine(calcXY( markedDoor1.x), calcXY(markedDoor1.y),
		 				calcXY(markedDoor2.x), calcXY(markedDoor2.y)  ); 
	 			//odtwarzamy siatke
		 		g2D.setColor(new Color(120,150,223));
	 			g2D.setStroke(grid_stroke);
		 		g2D.drawLine(calcXY( markedDoor1.x), calcXY(markedDoor1.y),
		 				calcXY(markedDoor2.x), calcXY(markedDoor2.y)  );
		 		//rysujemy drzwi
		 		g2D.setColor(Color.black);
		 		if ( markedDoor1.x == markedDoor2.x){// pionowe
		 			g2D.drawRect((int)(calcXY( markedDoor1.x)-gridSize*0.5 ), 
			 				(int)(calcXY(markedDoor1.y)+gridSize*0.20),
			 				(int)(gridSize) , (int)(gridSize*0.6) );
		 		}else {
		 			g2D.drawRect((int)(calcXY( markedDoor1.x)+gridSize*0.20), 
			 				(int)(calcXY(markedDoor1.y)-gridSize*0.5),
			 				(int)(gridSize*0.6) , (int)(gridSize) );
		 		}
		 		
		 		
	 		}
		
	 		
	 	 // wyswietlanie dlugosci linii
	 		if (showLineMeteres!=-1){
	 			 g2D.setColor(Color.blue);
	 			 g2D.drawString(String.valueOf(showLineMeteres*gridMeteres)+" m", showLineX, showLineY);
	 		}
	 		
	 		 // w tarakcie dodawania czujnika
	 		if (addingSensor!=null){
	 			 g2D.setColor(Color.gray);
	 			 g2D.drawLine(calcXY(addingSensor.sx), calcXY(addingSensor.sy), calcXY(addingSensor.dx), calcXY(addingSensor.dy));
	 		}
	 	
	 		
		}else {
			g2D.setColor(Color.gray);
			g2D.fillRect(2, 2, this.getWidth()-2, this.getHeight()-2);
		}
	
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.white);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

		if (tranformApplied) {
			g2D.translate(180.0, 20.3);
			g2D.scale(.7, .3);
			g2D.shear(-0.5, 0);

			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}

		paintMe(g);

	}
	
 
	private void colorDivisionLine(Color color, Graphics2D g2D, BasicStroke stroke ){
		/* :
		 * do poprawienia, nie dziala dla przypadkow gdy sciezka tworzy sie od polowy lini dzielacej, potem przeskakuje do poczatku 
		 * i dalej znowu dociera do polowy. od prawidlowego konca do prawidlowego poczatku rysuje sie lini po przekatnej 
		 */
//		if (divisionPath!=null){
//			if (divisionPath.getNestedPaths().size()==2){
//				Path p1 = divisionPath.getNestedPaths().get(0);
//				Path p2 = divisionPath.getNestedPaths().get(1);
//								
//				Path divLine = new Path(Path.LINE_SOLID, p1.getMaxX(),p1.getMaxY());
//				
//				for (int i = 0; i < p2.size(); i++) {
//					if ( p1.contains(p2.getX(i), p2.getY(i)))
//						divLine.simpleAdd(p2.getX(i), p2.getY(i));
//				}
//				
//				for (int i = 0; i < divLine.size()-1; i++) {
//					if ( divLine.getX(i)==divLine.getX(i+1) && divLine.getY(i)==divLine.getY(i+1)  ){
//						divLine.cyclicMoveWalls(i);
//						break;
//					}
//				}
//				if ( divLine.getX(0)==divLine.getX(divLine.size()-1) && divLine.getY(0)==divLine.getY(divLine.size()-1)  ) 
//					divLine.removeLast();
//				 
//				
//				paintPath(g2D, divLine, color, stroke);
//				 
//			}else{
//				throw new RuntimeException("podzial na wiecej niz dwoje dzieci");
//			}
//		}
	}
	// zaznacza w edytorze sciezke divisionPath, w celu cofniecia podzialu. jesli probujemy zaznaczyc lisc w drzewie sciezek nie zaznaczamy
	public void setDivisionPath(Path divisionPath) {
		if (divisionPath==null || divisionPath.getNestedPaths().size()==0)
			this.divisionPath=null;
		else
			this.divisionPath = divisionPath;
	}
	
	private void colorPathInterior(Path p, Color color, Graphics2D g2D ){
			if (p!=null){
				g2D.setColor(color);
				int [][] interior = p.getInterior();
				for (int i = 0 ; i < interior.length  ; i++ )  
					for (int   j=0;   j < interior[0].length;   j++) {
 					if (p.hasPointInInterior(i, j)){
 						g2D.fillRect(i*gridSize, j*gridSize,  gridSize ,  gridSize );
 					}
				}
			}
	}
	
	 Path findPathByLabel(String hlhLabel){
  
		return rootPath.findPathByLabel(hlhLabel);
	}
	 
	private void paintDoorsFromPaths(Graphics2D g2D, Path toDraw, int level) {
		ArrayList <Path> nested = toDraw.getNestedPaths();
		
		if (nested.size()>0){//			paint children - rysujemy tylko te ktore nie maja dzieci
			for (int i = 0; i < nested.size(); i++) {
				paintDoorsFromPaths(g2D, nested.get(i), level+1);
			}
		}else {//			 paint toDraw
			paintPathDoors(g2D, toDraw, level);
		} 
	}
	private void paintPathDoors(Graphics2D g2d, Path toDraw, int level) {
		for (Doors door : toDraw.doors) {
			paintDoors(g2d, door  );
		}
	}

	private void paintDoors(Graphics2D g2D, Doors d) {
		//		czyscimy
			g2D.setColor(Color.white);
			g2D.setStroke(rootPath_stroke);
 		g2D.drawLine(calcXY( d.x1), calcXY(d.y1),
 				calcXY(d.x2), calcXY(d.y2)  ); 
			//odtwarzamy siatke
 		g2D.setColor(new Color(120,150,223));
			g2D.setStroke(grid_stroke);
 		g2D.drawLine(calcXY( d.x1), calcXY(d.y1),
 				calcXY(d.x2), calcXY(d.y2)  );
 		//rysujemy drzwi
 		g2D.setColor(Color.black);
 		if ( d.x1 == d.x2){// pionowe
 			g2D.drawRect((int)(calcXY( d.x1)-gridSize*0.5 ), 
	 				(int)(calcXY(d.y1)+gridSize*0.20),
	 				(int)(gridSize) , (int)(gridSize*0.6) );
 		}else {
 			g2D.drawRect((int)(calcXY( d.x1)+gridSize*0.20), 
	 				(int)(calcXY(d.y1)-gridSize*0.5),
	 				(int)(gridSize*0.6) , (int)(gridSize) );
 		}
	}
	
	private void paintSensorsFromPaths(Graphics2D g2D, Path toDraw, int level) {
		ArrayList <Path> nested = toDraw.getNestedPaths();
		
		if (nested.size()>0){//			paint children - rysujemy tylko te ktore nie maja dzieci
			for (int i = 0; i < nested.size(); i++) {
				paintSensorsFromPaths(g2D, nested.get(i), level+1);
			}
		}else {//			 paint toDraw
			paintPathSensors(g2D, toDraw, level);
		} 
	}
	private void paintPathSensors(Graphics2D g2d, Path toDraw, int level) {
		for (Sensor sensor : toDraw.sensors) {
			paintSensor(g2d, sensor);
		}
	}

	private void paintSensor(Graphics2D g2D, Sensor s) {
		
		Arc2D.Double  arc= new Arc2D.Double();
		
		arc.setArcByCenter(calcXY(s.getArc().getCenterX()), calcXY(s.getArc().getCenterY()), calcXY(Sensor.range), s.getArc().getAngleStart(), s.getArc().getAngleExtent(), s.getArc().getArcType());
		
		Color rgb=Color.gray;
		int alfa =100;
		Color c = new Color(rgb.getRed(),rgb.getGreen(),rgb.getBlue(),alfa );
		
		g2D.setColor(c);
		g2D.fill(arc);
	}

	private void paintEmptySpaces(Graphics2D g2D, Path toDraw  ) {
		ArrayList <Path> nested = toDraw.getNestedPaths();
		
		if (nested.size()>0){//			paint children - rysujemy tylko te ktore nie maja dzieci
			for (int i = 0; i < nested.size(); i++) {
				paintEmptySpaces(g2D, nested.get(i) );
			}
		}else { 
			if (toDraw.getRoomType()==Path.ROOM_TYPE_EMPTY_SPACE){
				colorPathInterior(toDraw,Color.white, g2D);
			}
		} 
	}
	
	private void paintPathTree(Graphics2D g2D, Path toDraw,  Color color, boolean isRootPath ) {
		ArrayList <Path> nested = toDraw.getNestedPaths();
		
		if (nested.size()>0){//			paint children - rysujemy tylko te ktore nie maja dzieci
			for (int i = 0; i < nested.size(); i++) {
				paintPathTree(g2D, nested.get(i),color,isRootPath);
			}
		}else {//			 paint toDraw
			paintPath(g2D, toDraw, color,isRootPath);
		} 
	}
	private void paintPath(Graphics2D g2D, Path toDraw, Color color,  boolean isRootPath  ) {
		if (toDraw.size()>1){
 			g2D.setColor(color);
 			
 			int x1,x2,y1,y2;
 			for (int i = 0; i < toDraw.size()-1; i++) {
 				x1= toDraw.getX(i) ;
 				y1= toDraw.getY(i) ;
 				
 				x2= toDraw.getX(i+1) ;
 				y2= toDraw.getY(i+1) ;
 				
 				if (isRootPath){
 					g2D.setStroke(rootPath_stroke);
 				}else if (toDraw.getLineKind(i+1)==Path.LINE_SOLID){
					g2D.setStroke(normal_stroke);
				}else if (toDraw.getLineKind(i+1)==Path.LINE_DASHED){
	 				g2D.setStroke(dashed_stroke); 
				}
 				Font font = null;
 				g2D.setFont(font);
 				g2D.drawLine(calcXY(x1), calcXY(y1), calcXY(x2), calcXY(y2)); 
			}///for	
 			
 			String label = toDraw.getUserLabel();
 			
 			if (showLabels && toDraw.isFinished() && !"".equals(label) ){
 				
 				int labx=calcXY(toDraw.getX(0))+calcXY(1)/2+2, 
 				laby=calcXY(toDraw.getY(0))+calcXY(1)-4;
 				
 				Font font = new Font("Arial", Font.BOLD, 16);
 				g2D.setFont(font);
 				
 				drawString(g2D,label,labx ,laby  );
 			}
 		}//(toDraw.size()>1)
	}
		
	private void drawString(Graphics2D g2D, String text, int x, int y) {
        for (String line : text.split("\\\\n"))
        	g2D.drawString(line, x, y += g2D.getFontMetrics().getHeight());
    }
	
	public Sensor addSensor(int sensor1x, int sensor1y, int x, int y) {
		 return this.developedPath.addSensor(calcIJ(sensor1x), calcIJ(sensor1y), calcIJ(x), calcIJ(y));
	}
	
	/*
	 * funkcja zaznacza odznacza developed path
	 * jesli klikniete jest wczesniej zaznaczony odznacza go
	 * jesli klikniety inny lub nic nie bylo zaznaczone zaznacza
	 * zwraca true jesli po wykonaniu jest cos zaznaczone
	 * 	 */
	public boolean selectDevelopedPath(int x, int y) {
		
		int i,j=0;
		 
		i=calcIJ(x);
		j=calcIJ(y);
		Path previous = developedPath;
		if (i<rootPath.getMaxX() && j<rootPath.getMaxY()){
			developedPath=findDevelopedPath(i, j);
			if (previous == developedPath)
				developedPath=null;
			if (developedPath!=null)
				System.out.println(developedPath);
			repaint();
		}
		
		
		return developedPath != null;
	}
	public void clearDevelopedPath( ) {
		
		developedPath = null;
	}
	public void clearEditedPath( ) {
		
		editedPath =new Path(sizeX/gridSize, sizeY/gridSize );
	}
	public void highlightPath(int x, int y) {
		int i,j=0;
		 
		i=calcIJ(x);
		j=calcIJ(y);
		if (i<rootPath.getMaxX() && j<rootPath.getMaxY()){
			highlitedPath=findDevelopedPath(i, j);
			repaint();
		}
		
	}
 
	/*
	 * funkcja na podstawie aktualnej pozycji kursora znajduje path ktore zawiera zaznaczony kwadrat i  jest najnizej w hierarchii 
	 */
	public Path findDevelopedPath(int i, int j) {
		
		Path lowestContaining=null;
		
		if (rootPath.hasPointInInterior(i, j)){
			lowestContaining=rootPath;
		}else return null;

		while (true){
			ArrayList <Path>nested = lowestContaining.getNestedPaths();
			if (nested.size()==0)
				break;
			for (int k = 0; k < nested.size(); k++) {
				if (nested.get(k).hasPointInInterior(i, j))
					lowestContaining=nested.get(k);
			}
			  
		}
		return lowestContaining;
	}
	
	public void clear( ){
		
		this.initialized=true;
		
		 
		this.setPreferredSize(new Dimension(sizeX,sizeY));
		this.setSize(sizeX, sizeY);
		
		 zoomedTo=1;

		rootPath = new Path(sizeX/gridSize, sizeY/gridSize );
	 
		editedPath = new Path(sizeX/gridSize, sizeY/gridSize );
		
		developedPath=null;
		highlitedPath=null;
		
		markedGrigX =0;
		markedGrigY=0;
	 
		
		prevDravingMode=Mode.EMPTY;
		mode=Mode.EMPTY;
		
		repaint();
	}
	
	
	public void developNestedPaths() {
		// majac edited dzielimy developed i tworzymy dwie nowe zamkniete path ktore zapamietujemy jako dzieci pierwszej
		 
		Path [] tab = developedPath.split(editedPath);
 		
		tab[0].makeValid();
		tab[1].makeValid();
		
		developedPath.addNestedPath(tab[0]);
		developedPath.addNestedPath(tab[1]);	
	}
	 
	
	public void beginRootPath(int x, int y){
		rootPath = new Path(sizeX/gridSize, sizeY/gridSize );
		 
 
		int i,j=0;
		 
		 
		i=calcIJ(x);
		j=calcIJ(y);
		
		rootPath.add(i,j,Path.LINE_SOLID);
		 
		
		
	}
	
	public boolean startNewPath(int x, int y, int kind){
		int i,j=0;
		 
		i=calcIJ(x);
		j=calcIJ(y);
		 
		if (isInDevelopedPath(i, j)  ){
			editedPath =  new Path(sizeX/gridSize, sizeY/gridSize );
			editedPath.add(i,j,kind);
			 
			return true;
		}
 
		return false;
	}
	
	public int continueNewPath(int x, int y, int kind){
		 
		int i,j=0;
		 
		i=calcIJ(x);
		j=calcIJ(y);
		
		markEditedWhileDrawing(i, j);
		
		i=markedGrigX;
		j=markedGrigY;
		
		int lastX=  editedPath.getX(editedPath.size()-1) ;
		int lastY =  editedPath.getY(editedPath.size()-1)  ;
		if (i!=lastX || j!= lastY){
			if (isInDevelopedPath(i, j) ){
				editedPath.add(i,j,kind);
				repaint();
				return -1;
			 
			}else {
				editedPath.add(i,j,kind);
				repaint();
			} 
		}
		return editedPath.getLastLineSize();
	}
	public int continueRootPath(int x, int y){
		 
		int i,j=0;
		 
		i=calcIJ(x);
		j=calcIJ(y);
		
		markWhileDrawing(i, j);
		
		i=markedGrigX;
		j=markedGrigY;
		
		
		int lastX=  rootPath.getX(rootPath.size()-1) ;
		int lastY =  rootPath.getY(rootPath.size()-1)  ;
		if (i!=lastX || j!= lastY){
			if (rootPath.size()>1){// spr czy sie nie petli
				if (rootPath.contains(i, j)){
					int firstX=  rootPath.getX(0) ;
					int firstY =  rootPath.getY(0) ;
					if  (i==firstX && j== firstY){// powrot do poczatku
						rootPath.add(i,j,Path.LINE_SOLID);
						rootPath.checkEnd();
						rootPath.makeValid();
						repaint();
						return -1;
					}else {
						
					}
					
				}else {
					rootPath.add(i,j,Path.LINE_SOLID);
					repaint();
				}
			}else {//drugi el zawsze dodaj
				rootPath.add(i,j,Path.LINE_SOLID);
				repaint();
			}
			
		}
		return rootPath.getLastLineSize();
	}
	
	public Point removeLastLineFromRoot() {
		if (rootPath.removeLast()){
			int i = rootPath.getX(rootPath.size()-1);
			int j = rootPath.getY(rootPath.size()-1);
			markedGrigX = i;
			markedGrigY = j;
			repaint();
			
			return new Point(calcXY(i),calcXY(j));			
		} 
		return null;
	}
	 
	public Point removeLastLineFromEdited() {
		if (editedPath.removeLast()){
			int i = editedPath.getX(editedPath.size()-1);
			int j = editedPath.getY(editedPath.size()-1);
			markedGrigX = i;
			markedGrigY = j;
			repaint();
			
			return new Point(calcXY(i),calcXY(j));			
		} 
		return null;
	}
	
	private boolean isInDevelopedPath(int i , int j){
 
			if (developedPath.contains(i, j))
				return true;
		return false;
	}
	public boolean isInAllowedArea(int x, int y){
		// TODO wprowadzic sensowne max wsp do rysowania z uwzgl zooma
		return (( calcIJ(x)>0)&& ( calcIJ(y)>0) &&( calcIJ(x)+1<(sizeX/gridSize)) &&	 (calcIJ(y)+1<(sizeY/gridSize)) );
	}
	 
	public void markGrid(int x, int y){
		int i,j=0;
		 
		i=calcIJ(x);
		j=calcIJ(y);
//		System.out.println(i+"___"+j);
		
		markedGrigX = i;
		markedGrigY = j;
		repaint();
	}
	public Path [] markDoors(int x, int y) {
		
		if (!isInAllowedArea(x, y))
			return new Path [2];
		
		int [] ret = selectEdge(x, y);
		
		int x1= ret[0];
		int y1 =ret[1];
		int x2 =ret[2];
		int y2= ret[3];
		
		Path [] found = rootPath.getLowestTwoPathsContains(x1, y1, x2, y2);
		
		Path first = found[0];
		Path second = found[1];
		
		if (first!= null && first.containsCoord(((double)x1+(double)x2)/2, ((double)y1+(double)y2)/2)){ // odcinek lezy w jakies path i czy lezy na jej liniach
			possibleDoorsToShow=true;
			markedDoor1.x=x1;
			markedDoor1.y=y1;
			markedDoor2.x=x2;
			markedDoor2.y=y2;
			
//			test1=first;
//			test2=second;
 
			return found;
		}else {
			possibleDoorsToShow=false;
			markedDoor1 = new Point();
			markedDoor2 = new Point();
			
//			test1=null;
//			test2=null;
			return new Path [2];
		}
	}
	public Object [] putDoors(int x, int y) {
		Object [] ret = new Object [5];
		Path [] found =markDoors(x, y);
		Path first = found[0];
		Path second = found[1];
		if (first==null ) return null; // nie kliknieto na mozliwe miejsce
		
		if (first.containsDoorsAt(markedDoor1.x,markedDoor1.y,markedDoor2.x,markedDoor2.y) ) return null; 
		
		Doors d1 = first.addDoors(markedDoor1.x,markedDoor1.y,markedDoor2.x,markedDoor2.y);
		int firstWall =d1.wallNr;
		
		ret[0]=first.toString();
		ret[1]=String.valueOf(firstWall);
		
		if (second!=null && !second.containsDoorsAt(markedDoor1.x,markedDoor1.y,markedDoor2.x,markedDoor2.y)){
			Doors d2 = second.addDoors(markedDoor1.x,markedDoor1.y,markedDoor2.x,markedDoor2.y);
			int secondWall=d2.wallNr;
			ret[2]=second.toString();
			ret[3]=String.valueOf(secondWall);
		}
		
		ret[4]=d1;
		
		return ret; 
	}
	public int[] selectEdge(int x, int y) {
		int gs = (int)(gridSize *zoomedTo);
		
		int x1,x2,y1,y2;
		
		int xgridDist=0;
		if ((x%gs) < gs/2 ){
			xgridDist=x%gs;
		}else {
			xgridDist=gs-x%gs;
		}
		int ygridDist=0;
		if ((y%gs) < gs/2 ){
			ygridDist=y%gs;
		}else {
			ygridDist=gs-y%gs;
		}
		
		boolean horizontal;
		
		if (xgridDist>ygridDist)
			horizontal=true; 
		else 
			horizontal=false; 
 
		if (horizontal){
			y1=calcIJ(y); 
			y2=calcIJ(y);
			if ((x%gs) < gs/2 ){
				x1=calcIJ(x);
				x2=calcIJ(x)+1;
			}else {
				x1=calcIJ(x)-1;
				x2=calcIJ(x);
			}
		}else{
			x1=calcIJ(x);
			x2=calcIJ(x);
			if ((y%gs) < gs/2 ){
				y1=calcIJ(y);
				y2=calcIJ(y)+1;
			}else {
				y1=calcIJ(y)-1;
				y2=calcIJ(y);
			}
		}
		
		int [] ret = new int[4];
		ret[0]=x1;
		ret[1]=y1;
		ret[2]=x2;
		ret[3]=y2;
		return ret;
		 
	}
	public void markWhileDrawing(int i, int j){
		int lastX= ((Integer)rootPath.getX(rootPath.size()-1)).intValue();
		int lastY = ((Integer)rootPath.getY(rootPath.size()-1)).intValue();
		
		int distX = Math.abs(lastX - i);
		int distY = Math.abs(lastY - j);
		 
		if (distX<=1 && distY <=1){// czy lezy o 1 od ostatniego
			if(lastX==i || lastY==j ){// czy nie lezy po przekatenej
				markedGrigX = i;
				markedGrigY = j;
				repaint();
			}
		}

	}
	public void markEditedWhileDrawing(int i, int j){
		 
		int lastX = editedPath.getX(editedPath.size()-1);
		int lastY = editedPath.getY(editedPath.size()-1);
		
		int distX = Math.abs(lastX - i);
		int distY = Math.abs(lastY - j);
		 
		if (distX<=1 && distY <=1)// czy lezy o 1 od ostatniego
			if(lastX==i || lastY==j )// czy nie lezy po przekatenej
				 if ( (editedPath.size()>1) || //jest juz pierwszy odcinek
						 developedPath.hasPointInInterior(i, j) ){  //nie idziemy na zew  
					markedGrigX = i;
					markedGrigY = j;
					repaint();
				}
	}
	
	private int calcIJ(int x){
		int i =0;
		int gs = (int)(gridSize *zoomedTo);
		if ((x%gs) < gs/2 ){
			i=(x - x%gs)/gs;
		}else {
			i=(x - x%gs)/gs + 1;
		}
		return i;
	}
	 
	
	private int calcXY(int i){
		return   i * gridSize ;
		
	}
	private double calcXY(double i){
		return   i * gridSize ;
		
	}
	
	public void setShowLineLen(int len, int x, int y){
		this.showLineMeteres = len;
		this.showLineX=x;
		this.showLineY=y;
		
	}
	
	public void hideLineLen(){
		this.showLineMeteres = -1;
 
		
	}
	 
 
	
	 
    public void updateSize(  ) 
    {   
    	 setSize( (int)(sizeX* zoomedTo), (int)(sizeY* zoomedTo) ); 
    	 setPreferredSize(new Dimension((int)(sizeX* zoomedTo), (int)(sizeY* zoomedTo) )); 
    	  
 		repaint();
    }       
    
	 /** 
     * This method increments the zoom factor with 
     * the zoom percentage, to create the zoom in effect  
     */ 
    public void zoomIn() 
    {  System.out.println("++++++++++++++++++"+this.getWidth()+  "   "+this.getHeight() );
    	zoomedTo += zoomSpeed; 
 
    	updateSize( );
    }             
     
    /** 
     * This method decrements the zoom factor with the  
     * zoom percentage, to create the zoom out effect  
     */ 
    public void zoomOut() 
    { System.out.println("-----------"+this.getWidth()+  "   "+this.getHeight() );
    	zoomedTo -= zoomSpeed; 
         
    	if(zoomedTo < zoomSpeed) 
        { 
    		zoomedTo  = zoomSpeed; 
        }
 
    	updateSize( );
    } 
     
    /** 
     * This method returns the currently 
     * zoomed percentage 
     *  
     * @return 
     */ 
    public int getZoomedPerc() 
    
    { 
        return (int)(zoomedTo * 100);  
    }

	public int getSizeX() {
		return sizeX;
	}


	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}


	public int getSizeY() {
		return sizeY;
	}


	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
 
	public int getGridSize() {
		return gridSize;
	}
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}

	public Path getRootPath() {
		return rootPath;
	}
	public void setRootPath(Path p) {
		this.rootPath = p;
	}
 
	public Path getDevelopedPath() {
		return developedPath;
	}

	public void setDevelopedPath(Path developedPath) {
		this.developedPath = developedPath;
	}

	public Path getDivisionPath() {
		return divisionPath;
	}

	public void deletgeSubPathsFrom(String label) {
		 Path base = rootPath.findPathByLabel(label);
		 
		 base.removeNestedPaths();
		
	}
	
	public void resetEditedPath(){
		editedPath=new Path(sizeX/gridSize, sizeY/gridSize );;
	}

	public void hihglightRooms(ArrayList<String> roomsToHighlight) {
		for (String s: roomsToHighlight){
			Path p = rootPath.findPathByUserLabel(s);
			alertedPaths.add(p);
		}
		
	}
	public void clearHihglightedRooms( ) {
		 
		alertedPaths.clear();
	}

	public Sensor getAddingSensor() {
		return addingSensor;
	}

	public void setAddingSensor(int sx, int sy, int dx, int dy) {
		this.addingSensor = new Sensor(calcIJ(sx),calcIJ(sy),calcIJ(dx),calcIJ(dy)  );
	}
	public void setAddingSensor(Sensor addingSensor) {
		this.addingSensor = addingSensor;
	}

	public boolean isShowLabels() {
		return showLabels;
	}

	public void setShowLabels(boolean showLabels) {
		this.showLabels = showLabels;
	}

	public void applyTransform() {
		 tranformApplied= !tranformApplied;
		
	}

	
}
