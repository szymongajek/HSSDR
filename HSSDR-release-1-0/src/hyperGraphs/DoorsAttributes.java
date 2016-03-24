package hyperGraphs;

  public class DoorsAttributes {
	
//	   drzwi to przerwa w scianie pomiedzy pktami (x1,y1) i (x2,y2)
	private int  COORD_X1;
	private int  COORD_Y1;
	private int  COORD_X2;
	private int  COORD_Y2;
	private Node node1, node2;
	
	
	
	   DoorsAttributes (int x1, int y1, int x2, int y2, Node node1,Node node2 ){
		this.COORD_X1=x1;
		this.COORD_Y1=y1;
		this.COORD_X2=x2;
		this.COORD_Y2=y2;
		this.node1=node1;
		this.node2=node2;
	}
	
	public int getCOORD_X1() {
		return COORD_X1;
	}
	public void setCOORD_X1(int coord_x1) {
		COORD_X1 = coord_x1;
	}
	public int getCOORD_X2() {
		return COORD_X2;
	}
	public void setCOORD_X2(int coord_x2) {
		COORD_X2 = coord_x2;
	}
	public int getCOORD_Y1() {
		return COORD_Y1;
	}
	public void setCOORD_Y1(int coord_y1) {
		COORD_Y1 = coord_y1;
	}
	public int getCOORD_Y2() {
		return COORD_Y2;
	}
	public void setCOORD_Y2(int coord_y2) {
		COORD_Y2 = coord_y2;
	}
	
	public int getMiddleX(){
		return (getCOORD_X1()+getCOORD_X2() )/2;
	}
	public int getMiddleY(){
		return ( getCOORD_Y1()+  getCOORD_Y2() )/2;
	}
	
	public String toString(){
		String ret="[]"+COORD_X1+","+COORD_X2+"<->"+COORD_Y1+","+COORD_Y2 ;

//		ret+=" r1:"+this.getNode1().getObjectEdge();
//		if(this.getNode2()!= null  ) ret+=" r2:"+this.getNode2().getObjectEdge();
		
		return ret;
	}
 
	 @Override
	public boolean equals(Object other){
		 if (! (other instanceof DoorsAttributes))
			 return false;
		 DoorsAttributes da = (DoorsAttributes)other;
		return  ( this.COORD_X1==da.COORD_X1 ) && ( this.COORD_X2==da.COORD_X2 ) &&
		( this.COORD_Y1==da.COORD_Y1 ) && ( this.COORD_Y2==da.COORD_Y2 ) ;
	}

	public Node getNode1() {
		return node1;
	}

	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	public Node getNode2() {
		return node2;
	}

	public void setNode2(Node node2) {
		this.node2 = node2;
	}
	
	public boolean belongsToRoom(ObjectHE room) {
		if(this.getNode1().getObjectEdge()==room) return true;
		if(this.getNode2()!= null && this.getNode2().getObjectEdge()==room) return true;
		return false;
	}
	public static ObjectHE findCommonRoom(DoorsAttributes d1, DoorsAttributes d2){
		ObjectHE room11= d1.getNode1().getObjectEdge();
		if (d2.belongsToRoom(room11))
			return  room11;
		
		if (d1.getNode2()!=null){
			ObjectHE room12= d1.getNode2().getObjectEdge();
			 if (d2.belongsToRoom(room12))
					return  room12;
		}
		
		throw new RuntimeException("podane pary drzwi nie maja wspolnejgo pokoju");
	}
	
	public boolean externalDoors(){
		return this.getNode2()==null;
	}
	 
}
