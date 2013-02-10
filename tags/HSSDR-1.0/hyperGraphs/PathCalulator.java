package hyperGraphs;

import java.awt.Point;
import java.util.LinkedList;

import rectangularBoard.Interior;

public class PathCalulator {
	
	protected int [] [] room_interior;
	protected int door1x, door1y, door2x,door2y;
	CompMap cmap;
	
	protected PathCalulator(){
		
	}
	public PathCalulator ( DoorsAttributes d1, DoorsAttributes d2){
		
		ObjectHE room=DoorsAttributes.findCommonRoom(d1, d2);

		this.room_interior = room.getInterior();
		
		door1x = d1.getMiddleX();
		door1y = d1.getMiddleY();
		
		door2x = d2.getMiddleX();
		door2y = d2.getMiddleY();
		 
	}
	public PathCalulator ( DoorsAttributes d1, DoorsAttributes d2, int [] [] interior){

		this.room_interior = interior;
		
		door1x = d1.getMiddleX();
		door1y = d1.getMiddleY();
		
		door2x = d2.getMiddleX();
		door2y = d2.getMiddleY();
		 
	}

	public   double distanceBetweenDoors( ){
		
		if ((door1x==door2x)&&(door1y==door2y))
			return 0;
		
		cmap= new CompMap(room_interior);
		cmap.markPathBetween(door1x , door1y,door2x , door2y );
//		cmap.write( );
		
		return cmap.calculatePathBetween(door1x , door1y,door2x , door2y );
	}
	public boolean doorsAccessible( ){
		
		if ((door1x==door2x)&&(door1y==door2y))
			return true;
		
		cmap= new CompMap(room_interior);
		cmap.markAccessibleFrom(door1x, door1y);
		 
//		cmap.write( );
//		System.out.println("acc fr:"+door1x+" "+ door1y+" to: "+door2x+" "+  door2y);
		return cmap.isOpponentAccessible(door2x , door2y );
	}
}

  class CompMap {
	static int ACM_UNCHECKED=-1;
    static int ACM_ACCESS=-2;
    static int ACM_DETECTEDWALL=-3;
    static int ACM_PATH=-4;
    static int ACM_WALKED_PATH=-5;
    
    int accCount=0;
    int distSum=0;
    
	  int [][] array;
	  
	  public int sizeX, sizeY;
	  
	  class Field {
			
			int x;
			int y;
			int kind;
			Field(int x, int y){
				this.x=x; 
				this.y=y;
			}
			boolean equals(Field f2){
				return (x==f2.x && y==f2.y);
			}
			Field getN  (){
				 return  new Field(x, y - 1) ;
			  }
		     Field getE (){
				 return  new Field(x + 1, y);
			  }
		     Field getS (){
				 return  new Field(x, y + 1);
			  }
		     Field getW (){
				 return  new Field(x - 1, y) ;
			  }
		}
	 /*
	  * tworzy compmape z interior z Path
	  */
	  CompMap(int [] []  room_interior ){
		  sizeX = room_interior.length;
		  sizeY= room_interior[1].length;
		  
		  init(sizeX, sizeY, ACM_UNCHECKED);
		  
		  for (int i = 0; i < sizeX; i++)  
	    		for (int j = 0; j < sizeY; j++)  
	    			if (room_interior[i][j]==Interior.ROOM)
	    				array[i][j]=ACM_UNCHECKED;
	    			else 
	    				array[i][j]=ACM_DETECTEDWALL;
		  
	  }

	  public double calculatePathBetween(int x1, int y1, int x2, int y2) {
			 // na podstawie ACM_PATH
		  Field end=new Field(x1,y1);
		  Field cur=new Field(x2,y2);
		  
		  double dist_line_2=2;
		  double dist_diagonal_2=1.41;
		  
		  double dist=0;
		  
		  while (!cur.equals(end)){
			  
			  if (cur.getN().equals(end) || cur.getE().equals(end) || cur.getS().equals(end) || cur.getW().equals(end) ){
				  dist++;
				  break;
			  }else {
				  if (getField(cur.getN())==ACM_PATH){
					  Field next = cur.getN();
					  if (getField(next.getN())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getN();
						  dist+=dist_line_2;
						  continue;
					  }
					  if (getField(next.getE())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getE();
						  dist+=dist_diagonal_2;
						  continue;
					  }
					  if (getField(next.getW())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getW();
						  dist+=dist_diagonal_2;
						  continue;
					  }
				  }
				  if (getField(cur.getE())==ACM_PATH){
					  Field next = cur.getE();
					  if (getField(next.getN())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getN();
						  dist+=dist_diagonal_2;
						  continue;
					  }
					  if (getField(next.getS())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getS();
						  dist+=dist_diagonal_2;
						  continue;
					  }
					  if (getField(next.getE())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getE();
						  dist+=dist_line_2;
						  continue;
					  }
				  }
				  if (getField(cur.getS())==ACM_PATH){
					  Field next = cur.getS();
					  if (getField(next.getS())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getS();
						  dist+=dist_line_2;
						  continue;
					  }
					  if (getField(next.getE())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getE();
						  dist+=dist_diagonal_2;
						  continue;
					  }
					  if (getField(next.getW())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getW();
						  dist+=dist_diagonal_2;
						  continue;
					  }
				  }
				  if (getField(cur.getW())==ACM_PATH){
					  Field next = cur.getW();
					  if (getField(next.getW())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getW();
						  dist+=dist_line_2;
						  continue;
					  }
					  if (getField(next.getS())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getS();
						  dist+=dist_diagonal_2;
						  continue;
					  }
					  if (getField(next.getN())==ACM_PATH){
						  setField(cur, ACM_WALKED_PATH);
						  setField(next, ACM_WALKED_PATH);
						  cur= next.getN();
						  dist+=dist_diagonal_2;
						  continue;
					  }
				  }
				  
			  }

			  
			  
		  }
		  
		  
		  return dist;
		}

	void markPathBetween(int x1, int y1, int x2, int y2){
		  
		  calcDist(x1,y1);
		  setField(x1, y1, 0);
		  int curx=x2,cury=y2;
		  int i=0;
//			this.write( );
		  while (curx!=x1 || cury!=y1){
			  
			  int nearest=Integer.MAX_VALUE;
			  int nextx=0, nexty=0;
			  if (i%2==0){
				  if (freeN(curx, cury) && getFieldN(curx, cury)<nearest){
					  nextx=curx;
					  nexty=cury-1;
					  nearest=getFieldN(curx, cury );
				  }
				  if (freeE(curx, cury) && getFieldE(curx, cury)<nearest){
					  nextx=curx+1;
					  nexty=cury;
					  nearest=getFieldE(curx , cury);
				  }
				  if (freeS(curx, cury) && getFieldS(curx, cury)<nearest){
					  nextx=curx;
					  nexty=cury+1;
					  nearest=getFieldS(curx, cury );
				  }
				  if (freeW(curx, cury) && getFieldW(curx, cury)<nearest){
					  nextx=curx-1;
					  nexty=cury;
					  nearest=getFieldW(curx , cury);
				  }
			  }else {// zamieniona kolejnosc - bedzie chodzil po skosie
				  if (freeW(curx, cury) && getFieldW(curx, cury)<nearest){
					  nextx=curx-1;
					  nexty=cury;
					  nearest=getFieldW(curx , cury);
				  }
				  if (freeS(curx, cury) && getFieldS(curx, cury)<nearest){
					  nextx=curx;
					  nexty=cury+1;
					  nearest=getFieldS(curx, cury );
				  }
				  if (freeE(curx, cury) && getFieldE(curx, cury)<nearest){
					  nextx=curx+1;
					  nexty=cury;
					  nearest=getFieldE(curx , cury);
				  }
				  if (freeN(curx, cury) && getFieldN(curx, cury)<nearest){
					  nextx=curx;
					  nexty=cury-1;
					  nearest=getFieldN(curx, cury );
				  }
			  }
			  
			  
			  array[curx][cury] = ACM_PATH;
			  curx=nextx;
			  cury=nexty;
			  i++;
		  }
		  
		  setField(x1, y1, ACM_PATH);
		  setField(x2, y2, ACM_PATH);
	  }
	 
	   
	 private  CompMap (CompMap cmap ){
		  sizeX=cmap.sizeX;
		  sizeY=cmap.sizeY;
		  array = new int [sizeX][ sizeY];
	    	
	      for (int i = 0; i < sizeX; i++)  
	    		for (int j = 0; j < sizeY; j++)  
	    			array[i][j]=cmap.array[i][j];
				 
		  
	  }
	  void init (int width, int height, int value){
		  sizeX=width;
		  sizeY=height;
		  array = new int [sizeX][ sizeY];
	    	
	      for (int i = 0; i < sizeX; i++)  
	    		for (int j = 0; j < sizeY; j++)  
	    			array[i][j]=value;
				 
	  }
	    void write( ) {
			
				for (int j = 0; j < sizeY; j++) { 
					for (int i = 0; i < sizeX; i++)  
					if (getField(i,j)==CompMap.ACM_UNCHECKED)
						System.out.print(" ? ");
					else if (getField(i,j)==CompMap.ACM_ACCESS)
						System.out.print("   ");
					else if (getField(i,j)==CompMap.ACM_DETECTEDWALL)
						System.out.print(" X ");
					else if (getField(i,j)==CompMap.ACM_PATH)
						System.out.print(" # ");
					else if (getField(i,j)==CompMap.ACM_WALKED_PATH)
						System.out.print(" * ");
					else if (getField(i,j)>-1){
						System.out.print(" ");
						System.out.print( getField(i,j)+"");
						if (getField(i,j)<10) 
							System.out.print(" ");
					} 
					System.out.println(); 
				}
		}
		

	  
	  void setField (int x, int y, int value){
		  array[x][y]=value;
	  }
	  
	  int getField (int x, int y ){
		 return  array[x][y] ;
	  }

	  int nearWall(){
		  int min_dist= Integer.MAX_VALUE;
		  
		  for (int i = 1; i < sizeX-1; i++)  
	    		for (int j = 1; j < sizeY-1; j++)  
	    			if ( (array[i][j]>=0) &&
	    					(array[i][j]<min_dist) &&
	    				(array[i+1][j]==ACM_DETECTEDWALL||
	    				array[i][j+1]==ACM_DETECTEDWALL ||
	    				array[i-1][j]==ACM_DETECTEDWALL||
	    				array[i][j-1]==ACM_DETECTEDWALL))
	    				min_dist=array[i][j];
		  
		  return min_dist;
	    				
		  
	  }
	  
	  void  createDistMapFrom(int x, int y){
		  calcDist(x, y );
		  
	    	return ;
	    }
	  void calcRealAccesibility(int sx, int sy ){
	    	 
		  LinkedList <Point> stack_prev = new LinkedList<Point>();
		  LinkedList <Point> stack_next = new LinkedList<Point>();
		  LinkedList <Point> stack ;
		  
		  int field_count=0;
		  int dist=0;
		  
		  stack_prev.addFirst(new Point(sx,sy));
		  		  
		  int count=0;
		  while (!stack_prev.isEmpty() ){
			  
			  while (!stack_prev.isEmpty()){
				  Point p = stack_prev.removeFirst();
				  int x=p.x;
				  int y=p.y;
				  
				  if ( array[x][y-1]==ACM_UNCHECKED  )   // NORTH
			    		if (isWall(x,y-1) ){ 
			    			array[x][y-1]=ACM_DETECTEDWALL;
				    	}else{
				    		array[x][y-1]=count;
				    		field_count++;
				    		dist+=count;
				    		stack_next.addLast(new Point(x, y-1) );
				    	}
			    	if ( array[x+1][y]==ACM_UNCHECKED  )   // EAST
			    		if (isWall(x+1,y) ){ 
			    			array[x+1][y]=ACM_DETECTEDWALL;
				    	}else{
				    		array[x+1][y]=count;
				    		field_count++;
				    		dist+=count;
				    		stack_next.addLast(new Point(x+1, y  ));
				    	}
			    	if ( array[x][y+1]==ACM_UNCHECKED  )   // SOUTH
			    		if (isWall(x,y+1) ){ 
			    			array[x][y+1]=ACM_DETECTEDWALL;
				    	}else{
				    		array[x][y+1]=count;
				    		field_count++;
				    		dist+=count;
				    		stack_next.addLast(new Point(x, y+1  ));
				    	}
			    	if ( array[x-1][y]==ACM_UNCHECKED  )   // WEST
			    		if (isWall(x-1,y) ){ 
			    			array[x-1][y]=ACM_DETECTEDWALL;
				    	}else{
				    		array[x-1][y]=count;
				    		field_count++;
				    		dist+=count;
				    		stack_next.addLast(new Point(x-1, y) );
				    	}
			  }
			  
			  stack= stack_prev;
			  stack_prev=stack_next;
			  stack_next=stack;
			  
			  count++;
		  }
		  
		  	this.distSum=dist;
		  	this.accCount=field_count;
	    	return;
	    }
	  
	  void calcDist(int sx, int sy ){
	    	 
		  LinkedList <Point> stack_prev = new LinkedList<Point>();
		  LinkedList <Point> stack_next = new LinkedList<Point>();
		  LinkedList <Point> stack ;
		  
		  int field_count=0;
		  int dist=0;
		  
		  stack_prev.addFirst(new Point(sx,sy));
		  		  
		  int count=0;
		  while (!stack_prev.isEmpty() ){
			  
			  while (!stack_prev.isEmpty()){
				  Point p = stack_prev.removeFirst();
				  int x=p.x;
				  int y=p.y;
				  
				  if ( array[x][y-1]==ACM_UNCHECKED  )   // NORTH
			    		if (isWall(x,y-1) ){ 
			    			array[x][y-1]=ACM_DETECTEDWALL;
				    	}else{
				    		array[x][y-1]=count;
				    		field_count++;
				    		dist+=count;
				    		stack_next.addLast(new Point(x, y-1) );
				    	}
			    	if ( array[x+1][y]==ACM_UNCHECKED  )   // EAST
			    		if (isWall(x+1,y) ){ 
			    			array[x+1][y]=ACM_DETECTEDWALL;
				    	}else{
				    		array[x+1][y]=count;
				    		field_count++;
				    		dist+=count;
				    		stack_next.addLast(new Point(x+1, y  ));
				    	}
			    	if ( array[x][y+1]==ACM_UNCHECKED  )   // SOUTH
			    		if (isWall(x,y+1) ){ 
			    			array[x][y+1]=ACM_DETECTEDWALL;
				    	}else{
				    		array[x][y+1]=count;
				    		field_count++;
				    		dist+=count;
				    		stack_next.addLast(new Point(x, y+1  ));
				    	}
			    	if ( array[x-1][y]==ACM_UNCHECKED  )   // WEST
			    		if (isWall(x-1,y) ){ 
			    			array[x-1][y]=ACM_DETECTEDWALL;
				    	}else{
				    		array[x-1][y]=count;
				    		field_count++;
				    		dist+=count;
				    		stack_next.addLast(new Point(x-1, y) );
				    	}
			  }
			  
			  stack= stack_prev;
			  stack_prev=stack_next;
			  stack_next=stack;
			  
			  count++;
		  }
		  
		  	this.distSum=dist;
		  	this.accCount=field_count;
	    	return;
	    }
	  
	   void markAccessibleFrom(int x, int y ){
	    	 
	    	if ( array[x][y-1]==ACM_UNCHECKED  )   // NORTH
	    		if (isWall(x,y-1) ){ 
	    			array[x][y-1]=ACM_DETECTEDWALL;
		    	}else{
		    		array[x][y-1]=ACM_ACCESS;
	    			markAccessibleFrom(x, y-1 );
		    	}
	    	if ( array[x+1][y]==ACM_UNCHECKED  )   // EAST
	    		if (isWall(x+1,y) ){ 
	    			array[x+1][y]=ACM_DETECTEDWALL;
		    	}else{
		    		array[x+1][y]=ACM_ACCESS;
	    			markAccessibleFrom(x+1, y );
		    	}
	    	if ( array[x][y+1]==ACM_UNCHECKED  )   // SOUTH
	    		if (isWall(x,y+1) ){ 
	    			array[x][y+1]=ACM_DETECTEDWALL;
		    	}else{
		    		array[x][y+1]=ACM_ACCESS;
	    			markAccessibleFrom(x, y+1 );
		    	}
	    	if ( array[x-1][y]==ACM_UNCHECKED  )   // WEST
	    		if (isWall(x-1,y) ){ 
	    			array[x-1][y]=ACM_DETECTEDWALL;
		    	}else{
		    		array[x-1][y]=ACM_ACCESS;
	    			markAccessibleFrom(x-1, y );
		    	}
	    	return;
	    }
	     boolean isOpponentAccessible(int opx, int opy){
	    	return  ( array[opx-1][opy]==ACM_ACCESS || array[opx+1][opy]==ACM_ACCESS ||
	    			array[opx][opy-1]==ACM_ACCESS || array[opx][opy+1]==ACM_ACCESS);
	    			
	     }
	     
	     boolean isWall(int i, int j){
	    	 return getField(i, j)==ACM_DETECTEDWALL;
	     }
	     int getFieldN  (int i, int j){
			 return  getField(i, j - 1) ;
		  }
	     int getFieldE (int i, int j){
			 return  getField(i + 1, j);
		  }
	     int getFieldS (int i, int j){
			 return  getField(i, j + 1);
		  }
	     int getFieldW (int i, int j){
			 return  getField(i - 1, j) ;
		  }
	     int getField (Field f){
			 return  getField(f.x , f.y) ;
		  }
	     void setField (Field f, int value){
			   setField(f.x , f.y,value) ;
		  }
	     
	   
	      boolean freeN(int i, int j){
	     	return  getField(i, j - 1) !=ACM_DETECTEDWALL && getField(i, j - 1) !=ACM_PATH;
	     }
	      boolean freeE(int i, int j){
	     	return  getField(i + 1, j)!=ACM_DETECTEDWALL &&  getField(i + 1, j)!=ACM_PATH;
	     }
	      boolean freeS(int i, int j){
	     	return getField(i, j + 1)!=ACM_DETECTEDWALL && getField(i, j + 1)!=ACM_PATH;
	     }
	      boolean freeW(int i, int j){
	     	return getField(i - 1, j)!=ACM_DETECTEDWALL && getField(i - 1, j)!=ACM_PATH;
	     }
}

