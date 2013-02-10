package rectangularBoard;

import hyperGraphs.HLH;
import hyperGraphs.LayoutUtils;
import hyperGraphs.Node;
import hyperGraphs.ObjectHE;
import hyperGraphs.DoorsAttributes;

 
import java.awt.geom.Line2D;
import java.util.Stack;
import java.util.ArrayList;

import editor.Doors;
import editor.ObjectPainter;
import sensors.Sensor;
public class Path {
	
	
	public static final int  LINE_SOLID=0;
	public static final int LINE_DASHED=1;
	
	private ArrayList<Integer> pathX  ;
	private ArrayList<Integer> pathY  ;
	
	private ArrayList<Integer> lineKind  ;
	
	private ArrayList<Path> nestedPaths = new ArrayList<Path>();
	// dane o drzwiach naniesionych na Path
	public  ArrayList<Doors> doors = new ArrayList<Doors>  ();
	
	//	 dane o czujnikach dodanych do  Path
	public  ArrayList<Sensor> sensors = new ArrayList<Sensor>  ();
	 
	private String userLabel="";
	private int roomType=0;
	
	public static final int ROOM_TYPE_EMPTY_SPACE=9;
	
	private int gridMeteres=1;
	
	//wymiary planszy na ktorej rysowany jest Path
	protected int maxX;
	protected int maxY;
	protected int [][] interior=null;
	private int interiorCount;
	
	
	public Path(int maxX, int maxY) {
		 pathX = new ArrayList<Integer>();
		 pathY = new ArrayList<Integer>();
		 lineKind = new ArrayList<Integer>();
		 
		 this.maxX = maxX;
		 this.maxY = maxY;
		 
		 nestedPaths = new ArrayList<Path>();
	}
	/**
	 * copies the points and line kind, does not copy nested paths
	 * @param source
	 */
	public Path(Path source){
		this(source.maxX,source.maxY);
		
		// first point 
		this.simpleAdd(source.getX(0), source.getY(0), source.getLineKind(0));
		// lines
		for (int i = 1; i < source.size(); i++) {
			this.simpleAdd(source.getX(i), source.getY(i), source.getLineKind(i));
		}
		
	}
	
	// dodaje nowy punkt, jeœli le¿y na jednej linii z poprzednim, przed³u¿a poprzedni¹ œcianê
	public void add(int i, int j,int line_kind){
		
		if (size()>=2 && isInLineXWithPrev(i,j)){
			pathX.remove(size()-1);
			pathX.add(i);	
		}else if (size()>=2 && isInLineYWithPrev(i,j)){
			pathY.remove(size()-1);
			pathY.add(j);	
		}else {
			simpleAdd(i, j,line_kind);
		}
 	
	}
	
	
	
	public Doors addDoors(int x1, int y1, int x2, int y2) {
		
		Doors d = new Doors(x1,y1,x2,y2);
		d.wallNr= findWallNrForDoors(d);
		this.doors.add(d);
		return d;
	}
	public void addNestedPath(Path p) {
		nestedPaths.add(p);
	}
	public Sensor addSensor(int sx, int sy, int dx, int dy) {
		
		Sensor s = new Sensor(sx,sy,dx,dy);
		this.sensors.add(s);
		return s;
	}
	
	private void calculateAreaValue() {
		int count=0;

		for (int i = 0 ; i < interior.length  ; i++ )  
			for (int   j=0;   j < interior[0].length;   j++)  
				if (interior[i][j]==Interior.ROOM) 
					count++;
				 
		 
		this.interiorCount=count;
	}	
	
	//sprawdza czy ostatni punkt i pierwszy nie tworz¹ œciany
	public void checkEnd(){
		//spawdz czy pierwsza sciana i ostatnia sa w tej samej linii
		// zakladamy ze sa conajmniej 4 pkty
		if (size()<4)
			throw new RuntimeException("zakladamy ze sa conajmniej 4 pkty");
		
		int y0 = pathY.get(0);
		int x0 = pathX.get(0);
		
		int yn = pathY.get(size()-1);
		int xn = pathX.get(size()-1);
		
		int y1 = pathY.get(1);
		int x1 = pathX.get(1);
		
		int yn1 = pathY.get(size()-2);
		int xn1 = pathX.get(size()-2);

		if (x1==xn1 || y1==yn1)	{
			pathX.set( 0,xn1 );
			pathY.set( 0, yn1 );
			
			pathX.remove(pathX.size()-1);
			pathY.remove(pathY.size()-1);
			lineKind.remove(lineKind.size()-1);
			
		}
		
		
		
		
	}
	
	public boolean contains (int i, int j){
		// sprawdza czy podany punkt nalezy do punktów œcie¿ki, lub czy nale¿y do linii ³¹cz¹cej punkty tej œcie¿ki
		
		//punkty œcie¿ki
		int shapeX=0,shapeY=0;
		for (int k = 0; k <size(); k++) {
			shapeX= getX(k);
			if (shapeX==i){
				shapeY=getY(k);
				if (shapeY==j){
					return true;
				}
			}
			
		} 
		
		//linie laczace
		int X1=0,Y1=0;
		int X2=0,Y2=0;
		for (int k = 0; k <size()-1; k++) { // k-1 linii
			X1= getX(k);
			X2= getX(k+1);
			Y1= getY(k);
			Y2= getY(k+1);
			if (X1==i && X2 == i ){// linia pionowa
				if (Y1>Y2){
				 if (Y1>j && j>Y2 )
					 return true;
				}else{
					if (Y1<j && j<Y2)
						return true;
				}
			}
			
			if (Y1==j && Y2 == j ){//pozioma
				if (X1>X2){
				 if (X1>i && i>X2 )
					 return true;
				}else{
					if (X1<i && i<X2)
						return true;
				}
			}
			
		} 
		
		return false;
	}
	public boolean containsCoord (double i, double j){
		// sprawdza czy punkt o wspolrzednych lezy w scezce
		 
		//linie laczace
		for (int k = 0; k <size()-1; k++) { // k-1 linii
			if (containsCoord(i, j, k))
				return true;
		} 
		
		return false;
	}
	
	
	public boolean containsCoord (double i, double j, int wallNr){
		// sprawdza czy punkt o wspolrzednych lezy w scezce na scianie nr wallNr
		 
		//linie laczace
		double X1=0,Y1=0;
		double X2=0,Y2=0;
		 
		
			X1= getX(wallNr);
			X2= getX(wallNr+1);
			Y1= getY(wallNr);
			Y2= getY(wallNr+1);
			if (X1==i && X2 == i ){// linia pionowa
				if (Y1>Y2){
				 if (Y1>j && j>Y2 )
					 return true;
				}else{
					if (Y1<j && j<Y2)
						return true;
				}
			}
			
			if (Y1==j && Y2 == j ){//pozioma
				if (X1>X2){
				 if (X1>i && i>X2 )
					 return true;
				}else{
					if (X1<i && i<X2)
						return true;
				}
			}

		return false;
	}
	
	public boolean containsDoorsAt( int x1, int y1, int x2, int y2 ) {
		for (Doors d : this.doors) {
			if (d.areAtPoint(x1, y1, x2, y2) ) 
				return true;
		}
		return false;
	}
	
	public boolean containsTwoPointsInOrOnBorder (int x1, int y1,int x2, int y2 ){
		// sprawdza czy podane dwa punkty naleza do punktów œcie¿ki, lub czy nale¿a do linii ³¹cz¹cej punkty tej œcie¿ki, lub czy sa w interior
//		samo sprawdzanie interior nie wystrczy bo interior oznacza kratki. np dla kwadratu 2x2 interior ma 4 punkty, a obwodka 8
		return (contains(x1, y1) || hasPointInInterior(x1, y1))&&
		(contains(x2, y2) || hasPointInInterior(x2, y2));
	}
	
	public void createInterior() {
		// zaczynajaca od 0,0 zaznacza polaczone ze soba pola
//		jesli na polu jest ukosna linia to nie dodawaj go
//		zwroc tablice z wszystkimi nie zaznaczonymi polami postaci: 2 w interior, 0 - poza
		
		int [][] tab =  new int[maxX][maxY];
		// init with 0
		
//		0 - niesprawdzone
 
//		2 - polaczone z 0 0
//		3 - za linia, lub pod linia
		
		Stack<Field> toCheck = new Stack<Field>();
		toCheck.push(new Field(0,0));
		tab[0][0]=2;
		
		Field f, nextN, nextE, nextS,nextW;
		while (!toCheck.empty()){
			f =toCheck.pop();
 
			nextN = f.getN();
			nextE = f.getE();
			nextS = f.getS();
			nextW = f.getW();
			
			Field[] tmp =  {nextN,nextE,nextS,nextW};
			
			for (Field next:tmp) {
				if (fieldInBounds(next))
					if (tab[next.x][next.y]==0){// 			czy stan == 0, 
						if (!pathCrossesLine(f.cellMiddleX() , f.cellMiddleY() , 
											next.cellMiddleX() , next.cellMiddleY() )){  //				czy jest dostep
							toCheck.push(next); //					ustaw na 2 i wrzuc
							tab[next.x][next.y]=2;
						}else{
							tab[next.x][next.y]=3;//					ustaw na 3
						}
					}	
			}
			
		}
		
		this.interior =  new int[maxX][maxY];
//		wszystko co POLACZONE Z 0,0 jest poza interior
		for (int i =0; i<maxX; i++)
			for (int j=0; j<maxY; j++)
				if (tab[i][j]==2)
					this.interior[i][j]=Interior.OUTSIDE;
				else
					this.interior[i][j]=Interior.ROOM;
		
		return;
	}
	
	public ObjectHE createObjectHE(ObjectHE parentEdge, int level) {
		
		ObjectHE he= new ObjectHE();
		
		// suma wszystkich wspolrzednych z interior - do srodka ciezkosci
		int sum_x=0, sum_y=0;
		// maksymalnie wysuniete wsp 
		int max_S=0, max_N=maxY, max_W=maxX, max_E=0;

		for (int i = 0 ; i < interior.length  ; i++ )  
			for (int   j=0;   j < interior[0].length;   j++) {
				if (interior[i][j]==Interior.ROOM){
					sum_x+=i;
					sum_y+=j;
					if (i<max_W) max_W=i;
					if (i>max_E) max_E=i;
					if (j<max_N) max_N=j;
					if (j>max_S) max_S=j;
				}
		}
		//srodek ciezkosci
		int avg_x = sum_x/interiorCount;
		int avg_y= sum_y/interiorCount;
		
		// wymiary prostokata
		int sizeX = max_E - max_W+1;
		int sizeY = max_S - max_N+1;
		
		double scale = (1-level*0.3);
		
		sizeX*= scale;
		sizeY*= scale;
		
  
		he.setSizeX( sizeX );
		he.setSizeY(  sizeY );
		he.setMiddleX( avg_x );
		he.setMiddleY( avg_y );
		
		he.setAttribute(HLH.LABEL, toString());
		he.setAttribute(HLH.USER_LABEL, getUserLabel());
		he.setAttribute(HLH.ROOM_TYPE_LABEL, HLH.ROOM_TYPES.values()[ getRoomType()].toString());
		he.setAttribute(HLH.AREA, String.valueOf(getAreaValue()));
		he.setInterior( getInterior());
	 	
		int Ncurrx=avg_x - sizeX/2;
		int Ncurry=avg_y - sizeY/2;
		int Ecurrx=avg_x + sizeX/2;
		int Ecurry=avg_y - sizeY/2;
		int Scurrx=avg_x + sizeX/2;
		int Scurry=avg_y + sizeY/2;
		int Wcurrx=avg_x - sizeX/2;
		int Wcurry=avg_y + sizeY/2;
		
		int px=0,py=0;
		
//		przesuniecie pottrzebne aby numerowac zgodnie z zasadami 
		int ct= this.findMostLeftUpperLineNr(); 
		cyclicMoveWalls(ct);
		
		
		
		for (int wallNr = 0; wallNr <= size()-2; wallNr++) {
			Node n = new Node();
			int len = getLineLen(wallNr)* gridMeteres;
			String dir=getLineDir(wallNr);
			n.setAttribute(HLH.LEN, String.valueOf(len));
			n.setAttribute(HLH.DIRECTION,dir);
			if (getLineKind(wallNr+1)== Path.LINE_SOLID){
				n.setAttribute(HLH.KIND,HLH.KIND_ADJ);
			}else if (getLineKind(wallNr+1)==Path.LINE_DASHED ){
				if (HLH.isDashedLineMeansVisible()){
					n.setAttribute(HLH.KIND,HLH.KIND_VIS);
				}else {
					n.setAttribute(HLH.KIND,HLH.KIND_ACC);
				}
				
			}
			n.setAttribute(HLH.COORD,LayoutUtils.getLineString(getX(wallNr),getY(wallNr),getX(wallNr+1),getY(wallNr+1)));
			n.setAttribute(HLH.WALL_NR,String.valueOf(wallNr));
					
			if (parentEdge!=null)
				n.setEmbFuncNode(findEmbMappingNode(parentEdge, dir, wallNr));
			
			String label = String.valueOf(wallNr+1);
			label=label+getNodeLabelFromParent(parentEdge,n);		
			n.setAttribute(HLH.LABEL,label);
			n.setObjectEdge(he);
			he.addNode(n);

			
			if (parentEdge!=null && n.getEmbFuncNode()!=null){ // nie jest root edge i nie jest nowo powstala sciana 
				
				// przekopiuj wszystkie drzwi do nowego wezla z tego, ktory on zastepuje
				
				if (n.getEmbFuncNode().hasDoors()){//jezeli sa drzwi
					
					Node emb = n.getEmbFuncNode();
				 	// znajdz wszystkie drzwi ktore leza w obrebie odcinka sciany repr przez ten wezel
					
					for (int k=0; k<emb.numberOfDoors(); k++){
						if (doorsAreOnWall(emb.getCOORD_X1(k),  emb.getCOORD_Y1(k), emb.getCOORD_X2(k), emb.getCOORD_Y2(k),wallNr)){
							DoorsAttributes moved = emb.getDoors().get(k);
							if (moved.getNode1()!=emb && moved.getNode2()!=emb) 
								throw new RuntimeException();
							if (moved.getNode1()==emb){
								moved.setNode1(n);
							}
							if (moved.getNode2()==emb){
								moved.setNode2(n);
							}
							
							n.addDoorsAttributes(moved );
							
							this.addDoors(emb.getCOORD_X1(k),  emb.getCOORD_Y1(k), emb.getCOORD_X2(k), emb.getCOORD_Y2(k));
//							TODO: uwaga! przy usuwaniu drzwi to spowoduje blad, w PATH.doors dzwi beda dodane dwa razy, raz podczas klikniecia i pozniej 
//							dodatkowo podczas dzielenia krawedzi
							
						}
						
					}
					 
				}
				
				
				// utworz relacje dla nowego wezla
				parentEdge.createEmbeddingRelationTo(n);
				
				
			}
			
			len*=scale;
			
			if (dir.equals("N")){
				px= (Ncurrx +  len/2 );
				py=Ncurry -ObjectPainter.NODE_DIST_FROM_HE_IN_SQUARES;
				
				Ncurrx+= len;
			}else if (dir.equals("S")){
				px=(Scurrx - len/2);
				py=Scurry +ObjectPainter.NODE_DIST_FROM_HE_IN_SQUARES;
				
				Scurrx-= len;
			}else if (dir.equals("E")){
				px=Ecurrx +ObjectPainter.NODE_DIST_FROM_HE_IN_SQUARES;
				py=(Ecurry + len/2);
				
				Ecurry+= len;
			}else if (dir.equals("W")){
				px=Wcurrx -ObjectPainter.NODE_DIST_FROM_HE_IN_SQUARES;
				py=(Wcurry - len/2);
				
				Wcurry-= len;
			}
 
			n.setMiddleX( px );
			n.setMiddleY( py );
		}
		
		return he;
	}
	void cyclicMoveWalls(int translate){
		if (getX(0)!=getX(size()-1) || getY(0)!=getY(size()-1))
			throw new RuntimeException("proba operacji cyklicznej na nie zapetlonej linii");
		
		// przesuwa sciany w Path, tak aby sciana nr translate byla pierwsza w wektorze
		ArrayList<Integer> newpathX= new ArrayList<Integer>()  ;
		ArrayList<Integer> newpathY  = new ArrayList<Integer>();
		ArrayList<Integer> newLineKind  = new ArrayList<Integer>();
		
		//pierwszy pkt
		newpathX.add(getX(translate));
		newpathY.add(getY(translate));
		newLineKind.add(getLineKind(0));//niewazne co
		
		//linie		
		for (int i = translate+1; i < size(); i++) {
			newpathX.add(getX(i));
			newpathY.add(getY(i));
			newLineKind.add(getLineKind(i));
		}
		for (int i = 1; i < translate+1; i++) {
			newpathX.add(getX(i));
			newpathY.add(getY(i));
			newLineKind.add(getLineKind(i));
		}
		this.pathX=newpathX;
		this.pathY=newpathY;
		this.lineKind=newLineKind;
		
	}
	private  boolean doorsAreOnWall(int doorsX1,int doorsY1,int doorsX2,int doorsY2, int wallNr ) {
		if ( containsCoord(doorsX1, doorsY1, wallNr)
				&& containsCoord(doorsX2, doorsY2, wallNr) )
			return true;
		
		return false;
	}
	
	public boolean fieldInBounds(Field f){
		if ((f.x<0) || (f.x>=maxX)) return false;
		if ((f.y<0) || (f.y>=maxY)) return false;
		return true;
	}
	 
	private Node findEmbMappingNode(ObjectHE parentEdge, String dir, int wallNr){
  	
		ArrayList <Node> nodes  = parentEdge.getNodes();
		
		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			if (n.getAttribute(HLH.DIRECTION).equals(dir)){
				int [] tab = LayoutUtils.getWallCoord(n);
				
				Path test= new Path( this.maxX, this.maxY);
				test.simpleAdd(tab[0],tab[1],Path.LINE_SOLID);
				test.simpleAdd(tab[2],tab[3], Path.LINE_SOLID);
				test.simpleAdd(tab[0],tab[1], Path.LINE_SOLID);
				
				if (test.contains(getX(wallNr), getY(wallNr)) && test.contains(getX(wallNr+1), getY(wallNr+1)))
					return n;
			}
		}
		return null;
	}
	
	private int findMostLeftUpperLineNr() {
		// znajduje punkt w sciezce  max wysuniety w kier N i W
		
		int maxN=maxY, maxW=maxX;
		int best=0;
		
		for (int i = 0; i < size()-1; i++) {
			if (getY(i) < maxN  || (getY(i) == maxN && getX(i)<maxW )){
				best = i;
				maxN=getY(i);
				maxW=getX(i);
			} 
		}
		if (getLineDir(best).equals("N")) return best;
		else if (best+3<size() && getLineDir(best+1).equals("N")) return best+1;
		else if (best-1>0 && getLineDir(best-1).equals("N")) return best+1;
		else throw new RuntimeException("blad podcas numerowania wierzcholkow");
	}
	
	public Path findPathByLabel(String hlhLabel){
		/*
		 * wyszukuje sciezke o odpowiedniej etykiecie, sprawdza ta sciezke, a nastepnie jej dzieci
		 */
		if (toString().equals(hlhLabel)) return this;
		ArrayList <Path>nested = getNestedPaths();
		int i=0;
		while ( i<nested.size() ){
			Path found = nested.get(i).findPathByLabel(hlhLabel);
			if (found!= null)
				return found; 
			i++;
		}
		return null;
		
	}
	public Path findPathByUserLabel(String hlhLabel){
		/*
		 * wyszukuje sciezke o odpowiedniej etykiecie, sprawdza ta sciezke, a nastepnie jej dzieci
		 */
		if (getLabel().equals(hlhLabel)) return this;
		ArrayList <Path>nested = getNestedPaths();
		int i=0;
		while ( i<nested.size() ){
			Path found = nested.get(i).findPathByUserLabel(hlhLabel);
			if (found!= null)
				return found; 
			i++;
		}
		return null;
		
	}
	private int findWallNrForDoors(Doors d) {
		//znajduje nr sciany na ktorej leza drzwi
		 
		double testPointX = ((double)d.x1+(double)d.x2)/2;
		double testPointY = ((double)d.y1+(double)d.y2)/2;
			
		for (int k = 0; k <size()-1; k++) { // k-1 linii
			if (containsCoord(testPointX, testPointY, k))
				return k;
		} 
		 
		return -1;
	}
	
	public double getAreaValue() {
		return  interiorCount*gridMeteres;
	}
  
	public int getGridMeteres() {
		return gridMeteres;
	}
	 
	public int[][] getInterior() {
		return interior;
	}
	public String getLabel(){
		
		if (userLabel.length()>0)
			return userLabel;
	 
		return this.toString();
	}

	public int getLastLineSize(){
 		if (size()<2) return -2;
		int x1 = pathX.get(size()-2) ;
		int x2 = pathX.get(size()-1);
		int y1 = pathY.get(size()-2) ;
		int y2 = pathY.get(size()-1);
		
		return Path.getLineSize(x1, x2, y1, y2);
	
	}
	public static int getLineSize(int x1,int x2, int y1, int y2){
		if (x1 == x2){
			return Math.abs(y1-y2);
		}else {
			return Math.abs(x1-x2);
		}
		
	}

	public String getLineDir(int i){
//		 zwraca orientacje  i-tego odcinka w sciezce, 
		int X1=0,Y1=0;
		int X2=0,Y2=0;
		
		X1= getX(i);
		X2= getX(i+1);
		Y1= getY(i);
		Y2= getY(i+1);
		
		int nextNE = getInterior()[X1][Y1-1];
		int nextNW = getInterior()[X1-1][Y1-1];
		int nextSE = getInterior()[X1][Y1];
		int nextSW = getInterior()[X1-1][Y1];
		
		if (X1==X2) {// pionowe
			if (Y1<Y2){// linia w kier S
				if (nextSE==Interior.ROOM) 
					return "W";
				 else if (nextSW==Interior.ROOM) 
					return "E";
			} else{// linia w kier N
				if (nextNE==Interior.ROOM ) 
					return "W";
				 else if (nextNW==Interior.ROOM) 
					return "E";
			}
		}else {// poziome
			if (X1<X2) { // linia w kier E
				if (nextNE==Interior.ROOM ) 
					return "S";
				 else if (nextSE==Interior.ROOM) 
					return "N";
			}else{// linia w kier W
				if (nextNW==Interior.ROOM) 
					return "S";
				 else if (nextSW==Interior.ROOM) 
					return "N";
			}
			
		}
		throw new RuntimeException("blad przy ustalaniu kerunku sciany ");
		 
	}
	 
	public int  getLineLen(int i){
		// zwraca dlugosc i-tego odcinka w sciezce, od punktu i do i+1 
		 
		int x1= getX(i);
		int x2= getX(i+1);
		int y1= getY(i);
		int y2= getY(i+1);
		
		return Path.getLineSize(x1, x2, y1, y2);
	}
	public Path getLowestPathContains(int x1, int y1,int x2, int y2 ){
		// jezeli dwa pkty lezA na sciezce zwraca ta sciezke lub jej najnizsze dziecko zwierajace dwa  pkty
		Path lowest =null;
		
		if (this.containsTwoPointsInOrOnBorder(x1, y1, x2, y2) ){
			lowest=this;
		}else {
			return null;
		}
		
		ArrayList <Path>nested = lowest.getNestedPaths();
		for (int k = 0; k < nested.size(); k++) {
			if (nested.get(k).containsTwoPointsInOrOnBorder(x1, y1, x2, y2) )
				lowest=nested.get(k).getLowestPathContains(x1, y1, x2, y2) ;
		}
		return lowest;
	}
	public Path[] getLowestTwoPathsContains(int x1, int y1,int x2, int y2 ){
		// wyszukuje najnizsze w hierarchii sciezki zwierajace dwa pkty - moze zwrocic 0, 1 lub 2
		
		if (!this.containsTwoPointsInOrOnBorder(x1, y1, x2, y2) )
			return new Path[2];
		
		ArrayList <Path>nested = this.getNestedPaths();
		if (nested.size()==0){ // jest tylko jedna scizka zawieracjaca w hierarchii i wlasnie doszlismy do jej najnizszego dziecka
			Path [] ret = new Path[2];
			ret[0]=this;
			ret[1]=null;
			return ret;
		}
		int found =0;
		int [] foundNumbers= new int[2];
		for (int k = 0; k < nested.size(); k++) {
			if (nested.get(k).containsTwoPointsInOrOnBorder(x1, y1, x2, y2)){
				foundNumbers[found]=k;
				found++;
			}
		}
		if (found!=1 && found!=2 )
			throw new RuntimeException("found!=1 && found!=2");
		else 
			if (found ==1){ // szukamy dalej tak samo, 
				return nested.get(foundNumbers[0]).getLowestTwoPathsContains(x1, y1, x2, y2);
			}else { //found ==2 znalezlismy path w ktorym lezy styk dwoch obszarow, a do niego naleza dwa pkty
				Path [] ret = new Path[2];
				ret[0]=nested.get(foundNumbers[0]).getLowestPathContains(x1, y1, x2, y2);
				ret[1]=nested.get(foundNumbers[1]).getLowestPathContains(x1, y1, x2, y2);
				return ret;
			}
	}
	
	public int getMaxX() {
		return maxX;
	}
	
	public int getMaxY() {
		return maxY;
	}

	public ArrayList<Path> getNestedPaths() {
		return nestedPaths;
	}

	private String getNodeLabelFromParent(ObjectHE parentEdge, Node newNode) {
		
		if (parentEdge==null) //rootEdge
			return "";
		
		Node sub = newNode.getEmbFuncNode();
		if (sub==null)//nowo powstala sciana
			return "";
		
		return "."+sub.getAttribute(HLH.LABEL);
		
	}
	
	public int getRoomType() {
		return roomType;
	}
	public String getUserLabel() {
		return userLabel;
	}
	public int getX(int i){
		
		return pathX.get(i);
	}
	public int getY(int i){
		
		return pathY.get(i);
	}
	/**
	 * return line kind between point i-1 and i
	 * for 0 line kind is meaningless, point daes not have line kind
	 * @param i
	 * @return
	 */
	public int getLineKind(int i){
		
		return lineKind.get(i);
	}
	
	public boolean hasPointInInterior(int i, int j){
		 
		return interior[i][j]==Interior.ROOM;
		
	}
	
	public  boolean isClockwise(){
		if (getX(0)!=getX(size()-1) || getY(0)!=getY(size()-1))
			throw new RuntimeException("proba operacji cyklicznej na nie zapetlonej linii");
		
		int i =0;
		
		int X1=0,Y1=0;
		int X2=0,Y2=0;
		
		X1= getX(i);
		X2= getX(i+1);
		Y1= getY(i);
		Y2= getY(i+1);
		
		int nextNE = getInterior()[X1][Y1-1];
		int nextNW = getInterior()[X1-1][Y1-1];
		int nextSE = getInterior()[X1][Y1];
		int nextSW = getInterior()[X1-1][Y1];
		
		if (X1==X2) {// pionowe
			if (Y1<Y2){// linia w kier S
				if (nextSE==Interior.ROOM) 
					return false;
				 else if (nextSW==Interior.ROOM) 
					return true;
			} else{// linia w kier N
				if (nextNE==Interior.ROOM ) 
					return true;
				 else if (nextNW==Interior.ROOM) 
					return false;
			}
		}else {// poziome
			if (X1<X2) { // linia w kier E
				if (nextNE==Interior.ROOM ) 
					return false;
				 else if (nextSE==Interior.ROOM) 
					return true;
			}else{// linia w kier W
				if (nextNW==Interior.ROOM) 
					return true;
				 else if (nextSW==Interior.ROOM) 
					return false;
			}
			
		}
		throw new RuntimeException("blad przy ustalaniu kierunku path ");
		 
	}
	
	private boolean isInLineXWithPrev(int i, int j) {
		
		if ( pathY.get(size()-2)==pathY.get(size()-1) && pathY.get(size()-1) ==j){
			return true;
		}
		return false;
	}
	private boolean isInLineYWithPrev(int i, int j) {
		
		if ( pathX.get(size()-2)==pathX.get(size()-1) && pathX.get(size()-1) ==i){
			return true;
		}
		return false;
	}
	void makeclockwise(){
		// jesli potrzeba ustawia Path w kolejnoœci zgodnie z ruchem wsk zegara
		
		if (!isClockwise()){
			ArrayList<Integer> newpathX= new ArrayList<Integer>()  ;
			ArrayList<Integer> newpathY  = new ArrayList<Integer>();
			ArrayList<Integer> newLineKind  = new ArrayList<Integer>();
			
			//dodaj pkt startowy
			newpathX.add(getX( size()-1));
			newpathY.add(getY( size()-1));
			newLineKind.add(0);//niewazne co 
			
			//dodaj linie
			for (int i = size()-2; i >= 0 ; i--) {
				newpathX.add(getX(i));
				newpathY.add(getY(i));
				newLineKind.add(getLineKind(i+1));
			}
			 
			this.pathX=newpathX;
			this.pathY=newpathY;
			this.lineKind=newLineKind;
		}
	}
	public void makeValid() {
		
		createInterior();
		calculateAreaValue();
		
		makeclockwise();
		
//		przesuniecie pottrzebne aby numerowac zgodnie z zasadami 
		int ct= this.findMostLeftUpperLineNr(); 
		cyclicMoveWalls(ct);
 
	}
	/**
	 * sprawdza czy ktorykolwiek odcinek skladowy PAth krzyzuje sie z podanym odcinkiem
	 * @param x1
	 * @param y1
	 * @param x2 
	 * @param y2
	 * @return
	 */
	public boolean pathCrossesLine(float x1, float y1, float x2, float y2){
		Line2D.Float line = new Line2D.Float(x1,y1,x2,y2);
		
		float X1=0,Y1=0;
		float X2=0,Y2=0;
		
		for (int k = 0; k <size()-1; k++) { // k-1 linii
			X1= getX(k);
			Y1= getY(k);
			X2= getX(k+1);
			Y2= getY(k+1);
			
			if ( line.intersectsLine(X1, Y1, X2, Y2) )
				return true;
		}
		
		
		return false;
	}
	/*
	 * usuwa ostatni punkt ze sciezki, zostawia co najnmiej leda linie. zwraca inf czy cos usunal
	 */
	public  boolean removeLast(){
		
		if (size()>2 ){
			pathX.remove(pathX.size()-1);
			pathY.remove(pathY.size()-1);
			lineKind.remove(lineKind.size()-1);
			
			return true;
		}
		
		return false;
 	
	}
	public void removeNestedPaths() {
		this.nestedPaths= new ArrayList<Path>();
		
	}
	public void setGridMeteres(int gridMeteres) {
		this.gridMeteres = gridMeteres;
	}
	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}
	public void simpleAdd(int i, int j, int line_kind){
		
		pathX.add(i);
		pathY.add(j);
		lineKind.add(line_kind);
		 
	}
	public void simpleAddField(int index, Field f, int line_kind){
		
		pathX.add(index,f.x);
		pathY.add(index,f.y);
		lineKind.add(index,line_kind);
		
	}
	public int  size(){
		if (pathX.size()!=pathY.size() || pathX.size()!=lineKind.size())
			throw new RuntimeException("niekreslony size!!");
		return pathX.size();
		
	}
	public Path [] split(  Path editedPath){
		// motoda przyjmuje jako argument sciezke dzielaca i tworzy dwie sciezki zamkniete, utworzone przez ta sciezke i linie laczaca pkty  
		int k;
		Field first = new Field(editedPath.getX(0),editedPath.getY(0));
		Field second = new Field(editedPath.getX(editedPath.size()-1),editedPath.getY(editedPath.size()-1));
		
		
		
		//szukamy przeciecia 1
		
//		punkty œcie¿ki
	 
		int interSect1=-1;
		int interSect2=-1;
		Path readyForDivision=new Path( this);// sciezka z ewentualnym podzialem lini prostej na dwie, gdy punkt przeciecia wypada w srodku lini
		
		for (k = 0; k <readyForDivision.size(); k++) {
			if (readyForDivision.getX(k)==first.x){
				if (readyForDivision.getY(k)==first.y){
					interSect1=k;
				}
			}
			
		}
		
		if (interSect1==-1){ // nie przecina w punktcie sciezki
			
//			szukamy w ktorej lini laczacej pkty sciezki nalezy first
			int X1=0,Y1=0;
			int X2=0,Y2=0;
			for ( k = 0; k <readyForDivision.size()-1; k++) { // k-1 linii
				X1= readyForDivision.getX(k);
				X2= readyForDivision.getX(k+1);
				Y1= readyForDivision.getY(k);
				Y2= readyForDivision.getY(k+1);
				if (X1==first.x && X2 == first.x ){// linia pionowa
					if (Y1>Y2){
					 if (Y1>first.y && first.y>Y2 ){
							//dodajemy do sciezki first
						    readyForDivision.simpleAddField( k+1,first,readyForDivision.getLineKind(k+1));
					 		interSect1=k+1;
					 		break;
					 }
					}else{
						if (Y1<first.y && first.y<Y2){
							//dodajemy do sciezki first
							 readyForDivision.simpleAddField( k+1,first,readyForDivision.getLineKind(k+1));
					 		interSect1=k+1;
					 		break;
						}
					}
				}
				
				if (Y1==first.y && Y2 == first.y ){//pozioma
					if (X1>X2){
					 if (X1>first.x && first.x>X2 ){
//							dodajemy do sciezki first
						 readyForDivision.simpleAddField( k+1,first,readyForDivision.getLineKind(k+1));
					 		interSect1=k+1;
					 		break;
					 }
					}else{
						if (X1<first.x && first.x<X2){
//							dodajemy do sciezki first
							 readyForDivision.simpleAddField( k+1,first,readyForDivision.getLineKind(k+1));
					 		interSect1=k+1;
					 		break;
						}
					}
				}
			} 
			
			
		}
		
		for (k = 0; k <readyForDivision.size(); k++) {
			if (readyForDivision.getX(k)==second.x){
				if (readyForDivision.getY(k)==second.y){
					interSect2=k;
				}
			}
			
		}
		
		if (interSect2==-1){ // nie przecina w punktcie sciezki
 // nie przecina w punktcie sciezki
			
//			szukamy w ktorej lini laczacej pkty sciezki nalezy first
			int X1=0,Y1=0;
			int X2=0,Y2=0;
			for ( k = 0; k <readyForDivision.size()-1; k++) { // k-1 linii
				X1= readyForDivision.getX(k);
				X2= readyForDivision.getX(k+1);
				Y1= readyForDivision.getY(k);
				Y2= readyForDivision.getY(k+1);
				if (X1==second.x && X2 == second.x ){// linia pionowa
					if (Y1>Y2){
					 if (Y1>second.y && second.y>Y2 ){
//						dodajemy do sciezki second
						 readyForDivision.simpleAddField( k+1,second,readyForDivision.getLineKind(k+1));
					 		interSect2=k+1;
					 		if (interSect2<=interSect1)//jak dodajemy elent w vectorze przed interS1, to jedo indeks sie zkiewsza i trzeba uaktualnic go
					 			interSect1++;
					 			
					 		break;
					 }
					}else{
						if (Y1<second.y && second.y<Y2){
//							dodajemy do sciezki second
							 readyForDivision.simpleAddField( k+1,second,readyForDivision.getLineKind(k+1));	
					 		interSect2=k+1;
					 		if (interSect2<=interSect1)//jak dodajemy elent w vectorze przed interS1, to jedo indeks sie zkiewsza i trzeba uaktualnic go
					 			interSect1++;
					 		break;
						}
					}
				}else if (Y1==second.y && Y2 == second.y ){//pozioma
					if (X1>X2){
					 if (X1>second.x && second.x>X2 ){
//							dodajemy do sciezki second
						 readyForDivision.simpleAddField( k+1,second,readyForDivision.getLineKind(k+1));
					 		interSect2=k+1;
					 		if (interSect2<=interSect1)//jak dodajemy elent w vectorze przed interS1, to jedo indeks sie zkiewsza i trzeba uaktualnic go
					 			interSect1++;
					 		break;
					 }
					}else{
						if (X1<second.x && second.x<X2){
//							dodajemy do sciezki second
							 readyForDivision.simpleAddField( k+1,second,readyForDivision.getLineKind(k+1));
					 		interSect2=k+1;
					 		if (interSect2<=interSect1)//jak dodajemy elent w vectorze przed interS1, to jedo indeks sie zkiewsza i trzeba uaktualnic go
					 			interSect1++;
					 		break;
						}
					}
				}
				
			} 
		}
		
		
		if (interSect1==-1 || interSect2==-1)
			throw new RuntimeException("nie znaleziono pktow przeciecia");
		
		// tworzymy sciezke od intersect1 do intersect2, po edited i od intersect2 do intersect1 po readyForDivision idac do przodu 
		Path retInc= new Path(editedPath);//dodajemy do œcieki na pocz¹tku linie dzielaca

		//		 tworzymy sciezke od intersect1 do intersect2, po edited i od intersect2 do intersect1 po readyForDivision idac do tylu
		Path retDec= new Path(editedPath);	// dodajemy do œcie¿ek na pocz¹tku linie dzielaca
		
		if (interSect1<interSect2){
//			 pierwsza sciezka nie przechodz porzez pkt pocz 
			for (int i = interSect2-1; i>=interSect1   ; i--) {
				retDec.simpleAdd(readyForDivision.getX(i), readyForDivision.getY(i),readyForDivision.getLineKind(i+1));
			}
//			 druda sciezka  przechodz  
//			od intersect2 do konca
			for (int i = interSect2+1; i < readyForDivision.size() ; i++) {
				retInc.simpleAdd(readyForDivision.getX(i), readyForDivision.getY(i),readyForDivision.getLineKind(i));
			}
//			od konca do interecest1
			for (int i = 1; i <= interSect1 ; i++) {
				retInc.simpleAdd(readyForDivision.getX(i), readyForDivision.getY(i),readyForDivision.getLineKind(i));
			}
			
		}else{
			
//			 pierwsza sciezka nie przechodz porzez pkt pocz 
			for (int i = interSect2+1; i<=interSect1   ; i++) {
				retInc.simpleAdd(readyForDivision.getX(i), readyForDivision.getY(i),readyForDivision.getLineKind(i));
			}
//			 druda sciezka  przechodz  
//			od intersect2 do konca
			for (int i = interSect2-1; i >= 0 ; i--) {
				retDec.simpleAdd(readyForDivision.getX(i), readyForDivision.getY(i),readyForDivision.getLineKind(i+1));
			}
//			od konca do interecest1
			for (int i = readyForDivision.size()-2; i >= interSect1 ; i--) {
				retDec.simpleAdd(readyForDivision.getX(i), readyForDivision.getY(i),readyForDivision.getLineKind(i+1));
			}
		}
		 
		
		
		return new Path[]{retInc,retDec};
	}
	
	public String toString(){
		String ret="";
		ret+=getX(0)+","+getY(0);
		
		for (int i = 1; i < this.size(); i++) {
			ret+=( (getLineKind(i)==LINE_SOLID)?"__":"_ _" )+getX(i)+","+getY(i);
		}
		return ret;
	}
	
	public boolean isFinished(){
		return interior!=null;
	}
	
}
