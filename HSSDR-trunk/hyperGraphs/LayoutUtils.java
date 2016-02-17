package hyperGraphs;

import java.util.ArrayList;

import util.Logger;

public class LayoutUtils {
	// operacje na grafie charakterystyczne dla rozkladu pomieszczen i nie nalezace do def grafu
	
	/**
	 * zwarca wspolrzedne pocz i konca sciany reprezentowanej przez Node,postaci x1+":"+y1+":"+x2+":"+y2;
	 */
	public static int [] getWallCoord(Node n){
		
		return getWallCoord(n.getAttribute(HLH.COORD));
    }
	
	public static int [] getWallCoord(String lineString){
		int [] ret = new int[4];
		
		String [] tab = lineString.split(":");
		for (int i = 0; i < tab.length; i++) {
			ret[i]=Integer.parseInt(tab[i]);
		}
		return ret;
    }
	
	public static String  getLineString(int x1,int y1,int x2,int y2){
		// zwraca string pozwalajacy identyfikowac te same krawedzie 

		return x1+":"+y1+":"+x2+":"+y2;
	}
	
	public static boolean  areNodesForSameWall(Node n1, Node n2){
		// sprawdza czy Nody repreyentuja ta sama sciane na podstawie inf w atr COORD
		// moga to byc te same wszpolrzedne, albo zamienione punkty pocz z koncowym
		
		int [] t1= getWallCoord(n1);
		int [] t2= getWallCoord(n2);
		
		return  ((t1[0]==t2[2])&&(t1[1]==t2[3])&&(t1[2]==t2[0])&&(t1[3]==t2[1]))
				|| ((t1[0]==t2[0])&&(t1[1]==t2[1])&&(t1[2]==t2[2])&&(t1[3]==t2[3]));
		
	}
	/**
	 * sprawdza czy odcinki sciany reprezentowane przez wezly maja wspolny odcinek, wtedy miedzy nimi powinna byc relacja 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static boolean  nodesHaveCommonLineSegment(Node n1, Node n2){
		
		int [] t1= getWallCoord(n1);
		int [] t2= getWallCoord(n2);
		
		int 
		ax1=t1[0],
		ay1=t1[1], 
		ax2=t1[2],
		ay2=t1[3],
		bx1=t2[0],
		by1=t2[1], 
		bx2=t2[2],
		by2=t2[3];

		if (n1.isEastOrWest() && n2.isEastOrWest()){ // both vertical
			if (! ((ax1==bx1) &&
					(ax2==bx2)))
				return false;
			
			// zamina odcinkow typu 15->9 na 9->15
			if (ay1>ay2){
				int tmp=ay1;
				ay1=ay2;
				ay2=tmp;
			}
			if (by1>by2){
				int tmp=by1;
				by1=by2;
				by2=tmp;
			}
			
			if (ay1<=by1){ // odc a wyzej
				return ay2>by1;
			}else {
				return by2>ay1;
			}
		}else if (!n1.isEastOrWest() && !n2.isEastOrWest()){ // both horizontal
			if (! ((ay1==by1) &&
					(ay2==by2)))
				return false;
			
//			 zamina odcinkow typu 15->9 na 9->15
			if (ax1>ax2){
				int tmp=ax1;
				ax1=ax2;
				ax2=tmp;
			}
			if (bx1>bx2){
				int tmp=bx1;
				bx1=bx2;
				bx2=tmp;
			}
			if (ax1<=bx1) // odc bardziel na lewo
				return ax2>bx1;
			else 
				return bx2>ax1;
		}else
			return false;
	}

	public static int  calcDoorsSize(Node n, int doorsInNodeNr){
  	
		int x1 = n.getCOORD_X1(doorsInNodeNr) ;
		int y1  = n.getCOORD_Y1(doorsInNodeNr) ;
		int x2  = n.getCOORD_X2(doorsInNodeNr) ;
		int y2 = n.getCOORD_Y2(doorsInNodeNr) ;
 
		if (x1==x2)
			return Math.abs(y1-y2);
		else 
			return Math.abs(x1-x2);
	}
	public static double[] calcDoorsMiddlePoint(Node n, int doorsInNodeNr) {
		int x1 = n.getCOORD_X1(doorsInNodeNr) ;
		int y1  = n.getCOORD_Y1(doorsInNodeNr) ;
		int x2  = n.getCOORD_X2(doorsInNodeNr) ;
		int y2 = n.getCOORD_Y2(doorsInNodeNr) ;
		double [] ret = new double[2];
	 
		 ret[0]=( (double)(x1+x2) )/2;
		 ret[1]=( (double)(y1+y2) )/2;
		 return ret;
	}
	//w danej krawedzi szuka sciany o kierunku okr w dir, ktora jest na tej saqmej lini co pkt coord, zwraca odleglosc do pierwszej znalezionej
	public static int getDistNearestWallOfDirContCoord(ObjectHE hEdge,String dir, double[] coord) {
		
		for (Node n: hEdge.getWallNodes())
			if (n.getDirection().equals(dir)){
				double cx=coord[0];
				double cy=coord[1];
				int[] wall = getWallCoord(n);
				
				if (dir.equals("N") || dir.equals("S")){
					
					int wx1=wall[0], wx2 = wall[2];
					if (wx1>wx2){
						 if ( wx1>cx && cx>wx2   ){
							 double dist =cy - wall[1];
							 return Math.abs( (int)dist  ); 
						 }
					}else{
						 if ( wx1<cx && cx<wx2){
							 double dist =cy - wall[1];
							 return Math.abs( (int)dist  ); 
						 }
					}
				}else {// E W
					int wy1=wall[1], wy2 = wall[3];
					
					if (wy1>wy2){
						 if (wy1>cy && cy>wy2 ){
							 double dist =cx - wall[0];
							 return Math.abs( (int)dist  );
						 }
					}else{
						if (wy1<cy && cy<wy2){
							double dist =cx - wall[0];
							 return Math.abs( (int)dist  );
						}
					}
				}
				
			}		
		return 0;
	}

	public static String oppositeDirection(String dir) {
		if (dir.equals("N")) return "S";
		if (dir.equals("S")) return "N";
		if (dir.equals("W")) return "E";
		if (dir.equals("E")) return "W";
		return null;
	}

	
	
	/**
	 * oblicza COORD string dla wezla powstalego ze zlaczenia wezlow w vect
	 * zalozenie ze sa na jednej linii
	 * zalozenie ze jest ich dwa z uwagi na podzial binarny
	 * @param vect
	 * @return
	 */
	public static String calculateMergingNodesCoord(ArrayList<Node> vect) {
		
		if (vect.size()>2){
			Logger.LOGGER.error("niebslugiwana liczba arg dla calculateMergingNodesCoord");
		}else if (vect.size()==1){
			//zwraca co przyszlo
			return vect.get(0).getAttribute(HLH.COORD);
		}
		Node n1 = vect.get(0);
		Node n2 = vect.get(1);
		int [] t1 = LayoutUtils.getWallCoord(n1);
		int [] t2 = LayoutUtils.getWallCoord(n2);
		
		int 
		ax1=t1[0],
		ay1=t1[1], 
		ax2=t1[2],
		ay2=t1[3],
		bx1=t2[0],
		by1=t2[1], 
		bx2=t2[2],
		by2=t2[3];

		int resx1=0, resy1=0, resx2=0, resy2=0;
		
		if (n1.isEastOrWest() && n2.isEastOrWest()){ // both vertical
			if (! ((ax1==bx1) && (ax2==bx2))){
				Logger.LOGGER.error("wezly nie leza na linii");
				return null;
			}
			resx1 = ax1;
			resx2 = ax1;
			
			resy1=LayoutUtils.minValue(ay1,ay2,by1,by2);
			resy2=LayoutUtils.maxValue(ay1,ay2,by1,by2);
				
		}else if (!n1.isEastOrWest() && !n2.isEastOrWest()){ // both horizontal
			if (! ((ay1==by1) &&	(ay2==by2))){
				Logger.LOGGER.error("wezly nie leza na linii");
				return null;
			}
			resy1=ay1;
			resy2=ay2;
		 
			resx1=LayoutUtils.minValue(ax1,ax2,bx1,bx2);
			resx2=LayoutUtils.maxValue(ax1,ax2,bx1,bx2);
			
		}else{
			Logger.LOGGER.error("wezly prostopadle");
			return null;
		}
		
		return LayoutUtils.getLineString(resx1, resy1, resx2, resy2);
	}
	
	/**
	 * oblicza minimum z podanego ciagu int
	 * @param params
	 * @return
	 */
	public static  int minValue(int ... params){
		
		int ret = Integer.MAX_VALUE;
		
		for(int x:params){
			if (x<ret){
				ret =x;
			}
		}
		
		return ret;
	}
	
	/**
	 * oblicza maksimum z podanego ciagu int
	 * @param params
	 * @return
	 */
	public static int maxValue(int ... params){
		
		int ret = Integer.MIN_VALUE;
		
		for(int x:params){
			if (x>ret){
				ret =x;
			}
		}
		
		return ret;
	}
	
}
