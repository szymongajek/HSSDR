
package hyperGraphs;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

import util.Logger;

import folf.*;

public class HLH
{
	
	//	etykieta wezla (np 1.2.4), dla hiperkrawedzi repr obiekty-ciag wsp scian uzywany
	//	do odnajdywania hiperkrawedzi w grafie na podstawie objectHEMap, 
	public static final String LABEL="LABEL";
//	dla  hiperkrawedzi nazwa podana przez uzytkownika
	public static final String USER_LABEL="USER_LABEL";
//	dla  hiperkrawedzi powierzchnia pomieszczenia
	public static final String ROOM_TYPE_LABEL="ROOM_TYPE_LABEL";
//	dla  hiperkrawedzi powierzchnia pomieszczenia
	public static final String AREA="AREA";
	//	dla wezlow dlugosc sciany repr przez wezel
	public static final String LEN="LEN";
	//	 orienatajca sciany w wezlach: N S W E
	public static final String DIRECTION="DIR";
	@Deprecated
	public static final String DIRECTION_FLOOR="F";
//	  
	@Deprecated
	public static final String DIRECTION_CEILING="C";
	
	public static final String DIRECTION_VERTICAL="V";
	//	 dla wezlow wspolrzedne koncow sciany: dostepne przez LayoutUtils.getWallCoord(node)
	public static final String COORD="COORD";
//	 dla wezlow numer kolejnej sciany jaka reprezentuje wezel
	public static final String WALL_NR="WALL_NR";

	//	 dla wezlow wspolrzedne drzwi x1, drzwi to przerwa w scianie pomiedzy pktami (x1,y1) i (x2,y2)
	// atrybuty DOORS_COOR_X1 DOORS_COOR_Y1 DOORS_COOR_X2 DOORS_COOR_Y2 przeniesione do DoorsAttributes
  
	//	dla relacji rodzaj  : ADJ - sasiedztwo, ACC - dostepnosc
	public static final String KIND="KIND";
//	 dostepnosc
	public static final String KIND_ACC="ACC";
//	 sasiedztwo
	public static final String KIND_ADJ="ADJ";
//	 widocznosc
	public static final String KIND_VIS="VIS";
	/**
	 * gdy ustawione przerywana linia oznacza widocznosc, o dostepnosci decyduja drzwi
	 */
	private static boolean dashedLineMeansVisible=true;
	
	//	dla hiperkrawedzi obiektowych typ pomieszczenia reprezentwanego przez ta krawedz
	public enum ROOM_TYPES{
		Space,Kitchen,Corridor,Staircase,Bathroom,Living_Room,Garage,Bedroom, Office, Living_Area, Communication_Area, Technical_Area,Empty
		
	}
	
	//najwyzej polozona w hierarchii hiperkrawejdz 
    private ObjectHE rootEdgeGroundFloor;
    private ObjectHE rootEdgeGraph;
    private ObjectHE[] rootEdges;
    private Hashtable<String,ObjectHE> objectHEMap;
    private Hashtable<String,DoorsAttributes> doorsMap;
    private ArrayList<HGSensor> sensors;
    
    float gridToMeters;
    
    int floorCount=0;

    public HLH(float gridToMeters, int sensorRange, int floorCount)
    {
        objectHEMap = new Hashtable<String,ObjectHE>();
        doorsMap = new Hashtable<String,DoorsAttributes>();
        sensors = new ArrayList<HGSensor>();
        this.floorCount=floorCount;
        rootEdges = new ObjectHE[floorCount];
        rootEdgeGraph = new ObjectHE();
        
        rootEdgeGraph.setSizeX( 500 );
        rootEdgeGraph.setSizeY(  500 );
		rootEdgeGraph.setMiddleX( 300 );
		rootEdgeGraph.setMiddleY( 300 );
		
		rootEdgeGraph.setAttribute(HLH.LABEL, toString());
		rootEdgeGraph.setAttribute(HLH.USER_LABEL,"whole building");
		rootEdgeGraph.setAttribute(HLH.AREA, "0");
		rootEdgeGraph.setLevel(-1);
        
    }
    
    public void createRootEdge(ObjectHE rootEdge, float gridToMeters, int sensorRange, int floorNr)
    {
        rootEdge.setLevel(0);
        if (floorNr==0) this.rootEdgeGroundFloor = rootEdge;
        rootEdges[floorNr]= rootEdge;
        rootEdgeGraph.addChildElement(rootEdge);
        rootEdge.setParentEdge(rootEdgeGraph);
        objectHEMap.put(rootEdge.getAttribute(HLH.LABEL), rootEdge);
        this.gridToMeters=gridToMeters;
        HGSensor.range=sensorRange;
    }

    @Deprecated
    public ObjectHE getRootEdgeGroundFloor()
    {
        return rootEdgeGroundFloor;
    }
    
    public ObjectHE getGraphRootEdge( )
    {
        return  rootEdgeGraph;
    }
    
    public ObjectHE getFloorRootEdge(int floorNr)
    {
        return rootEdges[floorNr];
    }
    
    class DoorToDoor extends AbstractFunction
    {
        public DoorToDoor()
        {
            super(2);
        }  

        public Double value(List<Object> args)
        {
            if (args.size() != 2)
                throw new IllegalArgumentException("bad arity");
            PathCalulator calc = new PathCalulator((DoorsAttributes)args.get(0),(DoorsAttributes)args.get(1) );
            double d =  calc.distanceBetweenDoors();
             System.out.println("calc dist:"+d);
            return d;
        }
    }
    class ISDoorSurveilled extends AbstractFunction
    {
        public ISDoorSurveilled()
        {
            super(1);
        }  

        public Boolean value(List<Object> args)
        {
            if (args.size() != 1)
                throw new IllegalArgumentException("bad arity");
            return SensorTests.isDoorWatched((DoorsAttributes)args.get(0), HLH.this  );
        }
    }
    class IsPassageWatched extends AbstractRelation
    {
        public IsPassageWatched()
        {
            super(2);
        }  

        public boolean value(List<Object> args)
        {
            if (args.size() != 2)
                throw new IllegalArgumentException("bad arity");
            return SensorTests.isPassageWatched((DoorsAttributes)args.get(0),(DoorsAttributes)args.get(1),HLH.this  );
        }
    }
    
    class IsDirectChild extends AbstractRelation
    {
        public IsDirectChild()
        {
            super(2);
        }  

        public boolean value(List<Object> args)
        {
            if (args.size() != 2)
                throw new IllegalArgumentException("bad arity");
            HyperEdge area1 = (HyperEdge)args.get(0);
            HyperEdge area2 = (HyperEdge)args.get(1);
            
            return area1.getParentEdge().equals(area2);
        }
    }
    
    class IsInDescendants extends AbstractRelation
    {
        public IsInDescendants()
        {
            super(2);
        }  

        public boolean value(List<Object> args)
        {
            if (args.size() != 2)
                throw new IllegalArgumentException("bad arity");
            HyperEdge area1 = (HyperEdge)args.get(0);
            HyperEdge area2 = (HyperEdge)args.get(1);
            
            return area1.isInDescendantsOf(area2);
        }
    }
    public Object []  createStructureAndVocabulary (){
    	
    	Structure structure = new Structure();
    	Vocabulary vocabulary = new Vocabulary();
    	vocabulary.allowNumbers();
    	vocabulary.allowStrings();
        
    	
    //  pokoje oraz strefy
    	UnaryTabularRelation isRoom = new UnaryTabularRelation();
    	structure.addRelation("Rooms", isRoom);
    	vocabulary.addRelation("Rooms", 1);
    	
    	UnaryTabularRelation isArea = new UnaryTabularRelation();
    	structure.addRelation("Areas", isArea);
    	vocabulary.addRelation("Areas", 1);
    	
    	for (ObjectHE he : getAllRooms()){
    		structure.addDomainObject(he);
    		isRoom.add(he);
        }
    	
//    	wszystkie hiperkrawedzie obiektowe - obszary logiczne z dziecmi, oraz zwykle pokoje
    	for (ObjectHE he: getAllObjectHE()){
    		structure.addDomainObject(he);
    		isArea.add(he);
        }
    	
    	// relacje adjacency accesibility
    	SymmetricTabularRelation adjacency = new SymmetricTabularRelation();
    	structure.addRelation("adjacent", adjacency);
    	SymmetricTabularRelation accesibility = new SymmetricTabularRelation();
    	structure.addRelation("accessible", accesibility);
    	
    	vocabulary.addRelation("adjacent", 2);
    	vocabulary.addRelation("accessible", 2);
    	
    	for (ObjectHE he1 : getAllRooms())
    		for (ObjectHE he2 : getAllRooms()) 
        		if (he1 != he2){
        				String connection = he1.connectedWith(he2);
        				if (connection!= null && connection.equals(HLH.KIND_ACC)){
        					accesibility.add(he1, he2);
        				}else if (connection!= null && connection.equals(HLH.KIND_ADJ)){
        					adjacency.add(he1,he2);
        			}
        		}
    	
//      sprawdzanie hierarchi dziedziczenia dla hiperkrawedzi - czy x jest bezposrednim dzieckiem y
    	structure.addRelation("isDirectChild", new CachingRelation( new IsDirectChild()));
      	vocabulary.addRelation("isDirectChild", 2);
      	
//      sprawdzanie hierarchi dziedziczenia dla hiperkrawedzi - czy x jest potomkiem y
    	structure.addRelation("isInDescendants", new CachingRelation( new IsInDescendants()));
      	vocabulary.addRelation("isInDescendants", 2);
    	
   // funkcja type - typ pomieszczenia

    	vocabulary.addFunction("type", 1);
    	TabularFunction type = new TabularFunction(1);
        structure.addFunction("type", type);
    	
        for (ObjectHE he : getAllObjectHE()){
        	type.add(new ObjectHE[]{he},he.getAttribute(HLH.ROOM_TYPE_LABEL));
        }

        // doors
        UnaryTabularRelation isDoors = new UnaryTabularRelation();
    	structure.addRelation("Doors", isDoors);
    	vocabulary.addRelation("Doors", 1);
    	
    	for (DoorsAttributes da : getAllDoors()){
    		structure.addDomainObject(da);
    		isDoors.add(da);
        }
    	
//    	 zbiór drzwi prowadz¹cych na zewn¹trz ExternalDoors
//    	#ExternalDoors(d) <=> Door(d) and ( forall x, y in Room:  
//    	# doorsInRoom(d, x) and doorsInRoom(d, y) => x = y );

    	 UnaryTabularRelation externalDoors = new UnaryTabularRelation();
     	structure.addRelation("ExternalDoors", externalDoors);
     	vocabulary.addRelation("ExternalDoors", 1);
     	
     	for (DoorsAttributes da : getAllDoors()){
     		if (da.externalDoors()){
     			externalDoors.add(da);     			
     		}
         }
        
        //doors in a room (door, room )
    	TabularRelation doorsInRoom = new TabularRelation(2);
      	structure.addRelation("doorsInRoom", doorsInRoom);
      	vocabulary.addRelation("doorsInRoom", 2);
      	
    	for (ObjectHE room : getAllObjectHE())
    		for (DoorsAttributes doors : getAllDoors())
    			if (room.hasDoors(doors) ){
    				doorsInRoom.add(doors, room);
    			}
        
        // distance Doors x Doors -> liczby rzecz, 
        //ograniczona do drzwi nale¿¹cych do tego samego pokoju
    	structure.addFunction("doorsDist", new CachingFunction( new DoorToDoor()));
      	vocabulary.addFunction("doorsDist", 2);

      	// powierzchnia pokoju
      	vocabulary.addFunction("area", 1);
    	TabularFunction area = new TabularFunction(1);
        structure.addFunction("area", area);
    	
        for (ObjectHE he : getAllRooms()){
        	area.add(new ObjectHE[]{he},Double.valueOf(he.getAttribute(HLH.AREA)));
        }
        
        // sensors
        UnaryTabularRelation isSensor = new UnaryTabularRelation();
    	structure.addRelation("Sensors", isSensor);
    	vocabulary.addRelation("Sensors", 1);
    	
    	for (HGSensor s : getSensors()){
    		structure.addDomainObject(s);
    		isSensor.add(s);
        }
    	
    	 //sensor in a room (sensor, room )
    	TabularRelation sensorInRoom = new TabularRelation(2);
      	structure.addRelation("sensorInRoom", sensorInRoom);
      	vocabulary.addRelation("sensorInRoom", 2);
      	
      	for (HGSensor s : getSensors()) 
    		sensorInRoom.add(s, s.parentEdge);
        
        //sprawdza czy dzwi sa obserwowane przez jakis czujnik
//    	structure.addFunction("isDoorSurveilled", new CachingFunction( new ISDoorSurveilled()));
//      	vocabulary.addFunction("isDoorSurveilled", 1);
      	
      	UnaryTabularRelation surveilledDoors = new UnaryTabularRelation();
    	structure.addRelation("surveilledDoors", surveilledDoors);
    	vocabulary.addRelation("surveilledDoors", 1);
    	
    	for (DoorsAttributes da : getAllDoors()){
     		if (SensorTests.isDoorWatched(da,this)){
     			surveilledDoors.add(da);     			
     		}
         }
      	
//      sprawdza czy mozna przemknac pomiedzy drzwiami w tym samym pokoju bez wchodzenia w zasieg czujnika
    	structure.addRelation("isPassageWatched", new CachingRelation( new IsPassageWatched()));
      	vocabulary.addRelation("isPassageWatched", 2);
    	
    	
        
    	if (!vocabulary.isCompatible(structure))
    		throw new RuntimeException("!vocabulary.isCompatible(structure)");
    	
    	
        return new Object []{structure,vocabulary};
        }
    /*
     *dodaja go grafu dwie nowe krawedzie: first i secod
     * tworzy i dodaje do grafu relacje dla wspolnych scian first i second
     * czysci wierzcholki i relacje developed
     */
    //TODO przy visibility przy dzieleniu he ktora ma juz jakies relacje i sa one zmieniane, vis jest updatowaana zamiast dost.
    public void  developEgde(ObjectHE developed, ObjectHE first, ObjectHE second){
    	
    	if (developed.getChildElements().size()!=0)
    		throw new RuntimeException("proba dzielenia niepustej hiperkrawdzi");
    	
        ArrayList<HyperEdge> children = new ArrayList<HyperEdge>();
        children.add(first);
        first.setParentEdge(developed);
        children.add(second);
        second.setParentEdge(developed);
        objectHEMap.put(first.getAttribute(HLH.LABEL), first);
        objectHEMap.put(second.getAttribute(HLH.LABEL), second);
        
        ArrayList<Node> nodes1 = first.getWallNodes();
        ArrayList<Node> nodes2 = second.getWallNodes();
        
        for(int i = 0; i < nodes1.size(); i++)
        {
            for(int j = 0; j < nodes2.size(); j++)
                if(LayoutUtils.areNodesForSameWall(nodes1.get(i), nodes2.get(j)))
                {
                    Node n1 = (Node)nodes1.get(i);
                    Node n2 = (Node)nodes2.get(j);
                    RelationHE rel = new RelationHE();
                    if(!n1.getAttribute(HLH.KIND).equals(n2.getAttribute(HLH.KIND)))
                        throw new RuntimeException("rozne rodzaje linii dla jednej sciany"
                        		+",n1:"+n1+" n2:"+n2);
                    rel.setAttribute(HLH.KIND, n1.getAttribute(HLH.KIND));
                    rel.setSource(n1);
                    rel.setTarget(n2);
                    rel.setParentEdge(developed);
                    n1.addRelation(rel);
                    n2.addRelation(rel);
                    children.add(rel);
                    if (HLH.isDashedLineMeansVisible() && n1.getAttribute(HLH.KIND).equals(HLH.KIND_VIS)){
                    	// oprocz visibility dodaj adjecncy
                    	 RelationHE relAdj = new RelationHE();
                          
                    	 relAdj.setAttribute(HLH.KIND, HLH.KIND_ADJ);
                    	 relAdj.setSource(n1);
                    	 relAdj.setTarget(n2);
                    	 relAdj.setParentEdge(developed);
                         n1.addRelation(relAdj);
                         n2.addRelation(relAdj);
                         children.add(relAdj);
                    }
                }

        }

        developed.setChildElements(children);
         
        developed.removeConnectedRelations();
        
    	developed.clearNodes();
    }

    public void putDoors(String firstLabel, String firstNodeNr, String secondLabel, String secondNodeNr, int dx1, int dy1, int dx2, int dy2) {
    	ObjectHE first = this.findObjectHEWithLabel(firstLabel);
		 Node firstNode = first.getNodeNumbered(firstNodeNr);
		 DoorsAttributes newDoors;
		
 
		 if (secondLabel!=null){
			 ObjectHE second = this.findObjectHEWithLabel(secondLabel);
			 Node secondNode = second.getNodeNumbered(secondNodeNr);
	  
			 //  pomiedzy node 1 i 2 znalez rel ktora laczy kraw o znanych tu labelch i wstaic jej dost  i drzwi
			 ArrayList<RelationHE> rels = firstNode.getRelations();
			 
			 RelationHE found = null, next = null;
			 
			 for (int i = 0; i < rels.size(); i++) {
				 next= rels.get(i);
				 if ((next.getSource()==firstNode && next.getTarget()==secondNode)||(next.getSource()==secondNode && next.getTarget()==firstNode)){
					 if (HLH.isDashedLineMeansVisible()){
						 if (!next.getAttribute(HLH.KIND).equals(HLH.KIND_VIS)){
							 found=next;
							 break;
						 }
					 }else {
						 found=next;
						 break;
					 }
				 }	
			}//for
			
			 found.setAttribute(HLH.KIND, HLH.KIND_ACC);
			 
			 newDoors=  new DoorsAttributes(dx1, dy1, dx2, dy2,firstNode,secondNode);
			 firstNode.addDoorsAttributes(newDoors);
			 secondNode.addDoorsAttributes(newDoors);
			 
		 } else {
			 newDoors=  new DoorsAttributes(dx1, dy1, dx2, dy2,firstNode,null);
			 firstNode.addDoorsAttributes(newDoors);
		 }
		
		 doorsMap.put(newDoors.toString(),newDoors);
		 
    }
    
    public void removeDoors( int dx1, int dy1, int dx2, int dy2) {
    	
    	DoorsAttributes tmp = new DoorsAttributes(dx1, dy1, dx2, dy2,null,null);
    	String doorsId = tmp.toString();
    	
    	DoorsAttributes toRemove = doorsMap.get(doorsId);
		 Node firstNode =toRemove.getNode1();
		 Node secondNode =toRemove.getNode2();
    	
    	if (toRemove.externalDoors()){
//        	usun z wezlow
    		 firstNode.removeDoors(toRemove);
    	}else {
        	// zmien relacje pomiedzy wezlami

    		 
//    		  pomiedzy node 1 i 2 znalez rel ktora laczy kraw o znanych tu labelch i wstaic jej dost  i drzwi
			 ArrayList<RelationHE> rels = firstNode.getRelations();
			 
			 RelationHE found = null, next = null;
			 
			 for (int i = 0; i < rels.size(); i++) {
				 next= rels.get(i);
				 if ((next.getSource()==firstNode && next.getTarget()==secondNode)||(next.getSource()==secondNode && next.getTarget()==firstNode)){
					 found=next;
					 break;
				 }	
			}//for
			
			 found.setAttribute(HLH.KIND, HLH.KIND_ADJ);
			 
//        	usun z wezlow
			 firstNode.removeDoors(toRemove);
			 secondNode.removeDoors(toRemove);
    		
    	}
    	
//    	 usun z doorsMap
    	doorsMap. remove(doorsId);
    }

    public ObjectHE findObjectHEWithLabel(String string)
    {
        return  objectHEMap.get(string);
    }
    public Collection<ObjectHE> getAllObjectHE(){
    	return objectHEMap.values();
    }
    public Collection<ObjectHE> getAllRooms(){
    	ArrayList<ObjectHE> rooms = new ArrayList<ObjectHE>();
    	for (ObjectHE he: getAllObjectHE()){
    		if (he.isRoom()) rooms.add(he);
    	}
    	return rooms;
    }
    public Collection<DoorsAttributes> getAllDoors(){
    	return doorsMap.values();
    }
    
    
    public void removeFromMap(ObjectHE he)
    {
        objectHEMap.remove(he.getAttribute(HLH.LABEL));
    }
 
	public   void contentSuppression(String label) {

		ObjectHE toSuppress = findObjectHEWithLabel(label);
		if ( toSuppress !=null){
//			usun z mapy hiperkrawedzi dzieci oproznianiej HE 
			for (HyperEdge child:toSuppress.getChildElements() ){
        		if (child instanceof ObjectHE  ){
        			removeFromMap((ObjectHE)child);
        		}
			}
			toSuppress.contentSuppression();
		}
	}

	public void setRoomUserLabel(String roomString, String user_label) {
		findObjectHEWithLabel(roomString).setAttribute(HLH.USER_LABEL, user_label);
		
	}
	public void setRoomType(String roomString, String room_type_label) {
		findObjectHEWithLabel(roomString).setAttribute(HLH.ROOM_TYPE_LABEL, room_type_label);
		
	}

	public void addSensor(ObjectHE parentEdge, HGSensor sensor) {
		sensor.parentEdge=parentEdge;
		sensors.add(sensor);
	}

	public ArrayList<HGSensor> getSensors() {
		return sensors;
	}
	
	public static boolean isDashedLineMeansVisible() {
		return HLH.dashedLineMeansVisible;
	}

	public static void setDashedLineMeansVisible(boolean dashedLineMeansVisible) {
		HLH.dashedLineMeansVisible = dashedLineMeansVisible;
	}
	
	public RelationHE addTwoFloorRealtion(ObjectHE upper, ObjectHE lower, String relKind, Point startPoint, Point endPoint ){
		
		Node source = upper.getVerticalNode();
		Node target = lower.getVerticalNode();
		
		 RelationHE newRel = new RelationHE(rootEdgeGraph, source, target, relKind);
		 
		 newRel.setTwoFloorRelHookPoints(startPoint, endPoint);
		
		 return newRel;
	}
	
	public RelationHE addMultiFloorRealtion(ObjectHE[] elements, ArrayList<Point> multiFloorRelHookPointSequence, String relKind ){
		
		Node[] nodes = new Node[elements.length];
		
		for (int i = 0; i < elements.length; i++) {
			nodes[i]=elements[i].getVerticalNode();
		}
		
		HyperRelation newRel =  new HyperRelation(rootEdgeGraph, nodes, relKind);
		newRel.setHyperRelHookPointSequence(multiFloorRelHookPointSequence);
		
		return newRel;
	}
	
}