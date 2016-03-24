package hyperGraphs;

import java.util.Properties;
import java.util.ArrayList;


public class Node extends MovableComponent {
	

	// atrubuty
	private Properties attributes;
	// atrybut kierunek
	private String direction;
	// relacje do ktorych podlaczony jest wierzcholek
	private ArrayList<RelationHE> relations = new ArrayList<RelationHE>();
	// hiper kr do ktorej podloczony jest node
	private ObjectHE objectEdge;

	private ArrayList<DoorsAttributes> doors = new ArrayList<DoorsAttributes>();

	private int floor;

	public Node() {
		attributes = new Properties();
	}

	public void setAttributesFrom(Node n) {
		attributes = new Properties(n.attributes);
	}

	public String getAttribute(String str) {
		return attributes.getProperty(str);
	}

	public void setAttribute(String atr, String value) {
		attributes.setProperty(atr, value);
	}

	public boolean isEastOrWest() {
		if (this.getDirection().equals("E") || this.getDirection().equals("W"))
			return true;
		else
			return false;

	}

	/**
	 * czy Node to sciana (w odroznieniu od polaczen pionowych)
	 * @return
	 */
	public boolean isVerticalConnections() {
		return this.getDirection().equals(HLH.DIRECTION_VERTICAL);
	}

	public String toString() {
		return getAttribute(HLH.LABEL) + " " + getAttribute(HLH.COORD);
	}

	/*
	 * dla celow usuwania wnetrza HE zwraca etykiete wezla nastepujaca po
	 * pierwszej kropce jesli nie ma kropki rzuca wyjatek
	 */
	public String getLabelSuffix() {
		String label = this.getAttribute(HLH.LABEL);
		int i = label.indexOf(".");
		if (i == -1)
			throw new RuntimeException("etykieta nie zawiera kropki");
		return label.substring(i + 1);

	}

	// tymczasowo ustawiany wierzcholek do celow obliczania osadzenia
	// reprezentuje wierzcholek krawedzi ktora zostala podzielona na
	// repezentowana przez this krawedz
	private Node embFuncNode;
	public static final int NODE_DIST_FROM_HE_IN_SQUARES=2;

	public Node getEmbFuncNode() {
		return embFuncNode;
	}

	public void setEmbFuncNode(Node embFuncNode) {
		this.embFuncNode = embFuncNode;
	}

	public void addRelation(RelationHE rel) {
		this.relations.add(rel);
	}

	public ArrayList<RelationHE> getRelations() {
		return relations;
	}

	public void removeConnection(RelationHE relationHE) {
		relations.remove(relationHE);

	}

	public void setObjectEdge(ObjectHE he) {
		this.objectEdge = he;

	}

	public ObjectHE getObjectEdge() {
		return objectEdge;
	}

	public boolean hasDoors() {
		return (!doors.isEmpty());
	}

	public int numberOfDoors() {
		return doors.size();
	}

	public void addDoorsAttributes(DoorsAttributes da) {
		doors.add(da);
	}

	public int getCOORD_X1(int doorsInNodeNr) {
		return doors.get(doorsInNodeNr).getCOORD_X1();
	}

	public int getCOORD_X2(int doorsInNodeNr) {
		return doors.get(doorsInNodeNr).getCOORD_X2();
	}

	public int getCOORD_Y1(int doorsInNodeNr) {
		return doors.get(doorsInNodeNr).getCOORD_Y1();
	}

	public int getCOORD_Y2(int doorsInNodeNr) {
		return doors.get(doorsInNodeNr).getCOORD_Y2();
	}

	public void addAllDoorsFrom(Node another) {

		this.doors.addAll(another.getDoors());

	}

	public ArrayList<DoorsAttributes> getDoors() {
		return doors;
	}

	public boolean hasDoors(DoorsAttributes other) {
		for (DoorsAttributes da : doors) {
			if (da.equals(other))
				return true;

		}

		return false;
	}

	public void removeDoors(DoorsAttributes other) {
		doors.remove(other);

	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((doors == null) ? 0 : doors.hashCode());
		result = prime * result + floor;
		result = prime * result
				+ ((objectEdge == null) ? 0 : objectEdge.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (doors == null) {
			if (other.doors != null)
				return false;
		} else if (!doors.equals(other.doors))
			return false;
		if (floor != other.floor)
			return false;
		if (objectEdge == null) {
			if (other.objectEdge != null)
				return false;
		} else if (!objectEdge.equals(other.objectEdge))
			return false;
		return true;
	}

	/**
	 * obliczas dlugisc sciany nowego wezla na podstawie tych z ktorych zlaczania powstaje
	 * @param prevNodes
	 */
	void calcMergingNodeLEN(ArrayList<Node> prevNodes) {
		 
		  int len_sum=0;
		  for (Node joiningNode:prevNodes){
			  len_sum+=Integer.parseInt(joiningNode.getAttribute(HLH.LEN));
		  }
		  
		  this.setAttribute(HLH.LEN, String.valueOf(len_sum));
		  
	}


	/**
	 * oblicza i ustawia polozenie wezla V. korzysta z polozenia HE  motherEdge
	 * @param motherEdge
	 */
	public void calcAndSetPositionVERT(ObjectHE motherEdge) {
		int sizeX = motherEdge.getSizeX();
		int sizeY = motherEdge.getSizeY();
		int avg_x = motherEdge.getMiddleX();
		int avg_y = motherEdge.getMiddleY();
		int px = 0, py = 0;
		px = avg_x + sizeX / 2 + Node.NODE_DIST_FROM_HE_IN_SQUARES
				/ 2;
		py = avg_y - sizeY / 2 - Node.NODE_DIST_FROM_HE_IN_SQUARES
				/ 2;

		this.setMiddleX(px);
		this.setMiddleY(py);
	}
	
	/**
	 * oblicza i ustawia polozenie wezla NSWE. korzysta z polozenia HE  motherEdge
	 * @param motherEdge
	 */
	public void calcAndSetPositionSingle(ObjectHE motherEdge ) {

		int sizeX = motherEdge.getSizeX();
		int sizeY = motherEdge.getSizeY();
		int avg_x = motherEdge.getMiddleX();
		int avg_y = motherEdge.getMiddleY();

		int Ncurrx = avg_x - sizeX / 2;
		int Ncurry = avg_y - sizeY / 2;
		int Ecurrx = avg_x + sizeX / 2;
		int Ecurry = avg_y - sizeY / 2;
		int Scurrx = avg_x + sizeX / 2;
		int Scurry = avg_y + sizeY / 2;
		int Wcurrx = avg_x - sizeX / 2;
		int Wcurry = avg_y + sizeY / 2;

		int px = 0, py = 0;
		int len = Integer.parseInt(this.getAttribute(HLH.LEN));

		String dir = this.getDirection();

		if (dir.equals("N")) {
			px = (Ncurrx + len / 2);
			py = Ncurry - Node.NODE_DIST_FROM_HE_IN_SQUARES;

			Ncurrx += len;
		} else if (dir.equals("S")) {
			px = (Scurrx - len / 2);
			py = Scurry + Node.NODE_DIST_FROM_HE_IN_SQUARES;

			Scurrx -= len;
		} else if (dir.equals("E")) {
			px = Ecurrx + Node.NODE_DIST_FROM_HE_IN_SQUARES;
			py = (Ecurry + len / 2);

			Ecurry += len;
		} else if (dir.equals("W")) {
			px = Wcurrx - Node.NODE_DIST_FROM_HE_IN_SQUARES;
			py = (Wcurry - len / 2);

			Wcurry -= len;
		}
		
		this.setMiddleX(px);
		this.setMiddleY(py);
	}
	
	/**
	 * oblicza i ustawia polozenie grupy wezlow NSWE. 
	 * bierze pod uwage dlugosci scian, i przesuwa kolejne wezly o tej samej orentacji zeby nie byly w jednym miejscu
	 * korzysta z polozenia HE  motherEdge
	 * @param motherEdge
	 */
	public static void calcAndSetPositionGroup( ArrayList<Node> group, ObjectHE motherEdge ) {

		int sizeX = motherEdge.getSizeX();
		int sizeY = motherEdge.getSizeY();
		int avg_x = motherEdge.getMiddleX();
		int avg_y = motherEdge.getMiddleY();

		int Ncurrx = avg_x - sizeX / 2;
		int Ncurry = avg_y - sizeY / 2;
		int Ecurrx = avg_x + sizeX / 2;
		int Ecurry = avg_y - sizeY / 2;
		int Scurrx = avg_x + sizeX / 2;
		int Scurry = avg_y + sizeY / 2;
		int Wcurrx = avg_x - sizeX / 2;
		int Wcurry = avg_y + sizeY / 2;

		int px = 0, py = 0;
		
		for (Node node:group){
			int len = Integer.parseInt(node.getAttribute(HLH.LEN));

			String dir = node.getDirection();

			if (dir.equals("N")) {
				px = (Ncurrx + len / 2);
				py = Ncurry - Node.NODE_DIST_FROM_HE_IN_SQUARES;

				Ncurrx += len;
			} else if (dir.equals("S")) {
				px = (Scurrx - len / 2);
				py = Scurry + Node.NODE_DIST_FROM_HE_IN_SQUARES;

				Scurrx -= len;
			} else if (dir.equals("E")) {
				px = Ecurrx + Node.NODE_DIST_FROM_HE_IN_SQUARES;
				py = (Ecurry + len / 2);

				Ecurry += len;
			} else if (dir.equals("W")) {
				px = Wcurrx - Node.NODE_DIST_FROM_HE_IN_SQUARES;
				py = (Wcurry - len / 2);

				Wcurry -= len;
			}
			
			node.setMiddleX(px);
			node.setMiddleY(py);
		}
		
	}
}
