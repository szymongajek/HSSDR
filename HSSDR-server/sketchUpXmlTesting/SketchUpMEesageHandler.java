package sketchUpXmlTesting;

import hyperGraphs.DoorsAttributes;
import hyperGraphs.HGSensor;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import folf.Structure;
import folf.SymmetricTabularRelation;
import folf.TabularFunction;
import folf.TabularRelation;
import folf.UnaryTabularRelation;
import folf.Vocabulary;

import server.MessageHandler;

public class SketchUpMEesageHandler implements MessageHandler {

	private static List<SUObject> getRooms(List<SUObject> list ){
		ArrayList<SUObject> rooms = new ArrayList<SUObject>();
		for (SUObject suObject : list) {
			if (suObject.isRoom()) {
				rooms.add(suObject);
			}
		}
		
		return rooms;
	}
	
	@Override
	public String handle(String s) {

		SketchUpXmlParser parser = new SketchUpXmlParser();

		parser.parseString(s);
		List<SUObject> list = parser.getSuObjectsList();

		 String ret = "";
		 for (SUObject suObject : list) {
		 ret += suObject;
		 }
		 System.out.println("objects"+ret);

		 String ret2 = "";
		 for (SUObject suObject : getRooms(list)) {
			 ret2 += suObject;
		 }
		 System.out.println("room: "+ret);

		 
		Object [] tab =  createStructureAndVocabulary(list);
		
		Structure structure= (Structure)tab[0];
		Vocabulary vocabulary =(Vocabulary)tab[1];

		SketchUpTestChooser testChooser = new SketchUpTestChooser();
		List<String> testFiles = testChooser.getTestFilesList();

		ArrayList<String> roomsToHighlight = new ArrayList<String>();
		StringBuffer resultMessage = new StringBuffer();

		for (String fname : testFiles) {
			fname = testChooser.getTestFilesDirectory() + "/" + fname;

			Object[] parsingRes = Controller.checkLayout(structure, vocabulary,
					fname);
			resultMessage.append((String) parsingRes[0]).append("\n");
			roomsToHighlight.addAll((ArrayList<String>) parsingRes[1]);

		}
		resultMessage.append("Rooms:").append("\n");

		for (String roomName : roomsToHighlight) {
			resultMessage.append(roomName).append("\n");
		}

		return resultMessage.toString();
	}
	
	 public Object []  createStructureAndVocabulary (List<SUObject> list){
	    	
	    	Structure structure = new Structure();
	    	Vocabulary vocabulary = new Vocabulary();
	    	vocabulary.allowNumbers();
	    	vocabulary.allowStrings();
	        
	    	
	    // relacje adjacency accesibility
	    	SymmetricTabularRelation adjacency = new SymmetricTabularRelation();
	    	structure.addRelation("adjacent", adjacency);
	    	SymmetricTabularRelation accesibility = new SymmetricTabularRelation();
	    	structure.addRelation("accessible", accesibility);
	    	UnaryTabularRelation isRoom = new UnaryTabularRelation();
	    	structure.addRelation("Rooms", isRoom);
	    	
	    	for (SUObject room : getRooms(list)){
	    		structure.addDomainObject(room);
	    		isRoom.add(room);
	        }
	    	
	    	vocabulary.addRelation("adjacent", 2);
	    	vocabulary.addRelation("accessible", 2);
	    	vocabulary.addRelation("Rooms", 1);
	    	
	     // here not adding:
//			accesibility.add(he1, he2);
//			adjacency.add(he1,he2);
	    	
	   // funkcja type - typ pomieszczenia

	    	vocabulary.addFunction("type", 1);
	    	TabularFunction type = new TabularFunction(1);
	        structure.addFunction("type", type);
	    	
	        for (SUObject room : getRooms(list)){
	        	type.add(new SUObject[]{room},room.getType());
	        }

	        // doors
	        UnaryTabularRelation isDoors = new UnaryTabularRelation();
	    	structure.addRelation("Doors", isDoors);
	    	vocabulary.addRelation("Doors", 1);
	    	
	    	 // here not adding:
//    		structure.addDomainObject(da);
//    		isDoors.add(da);
	    	
//	    	 zbiór drzwi prowadz¹cych na zewn¹trz ExternalDoors
//	    	#ExternalDoors(d) <=> Door(d) and ( forall x, y in Room:  
//	    	# doorsInRoom(d, x) and doorsInRoom(d, y) => x = y );

	    	 UnaryTabularRelation externalDoors = new UnaryTabularRelation();
	     	structure.addRelation("ExternalDoors", externalDoors);
	     	vocabulary.addRelation("ExternalDoors", 1);
	    
	     	 // here not adding:
// 			externalDoors.add(da);     			
	        
	        //doors in a room (door, room )
	    	TabularRelation doorsInRoom = new TabularRelation(2);
	      	structure.addRelation("doorsInRoom", doorsInRoom);
	      	vocabulary.addRelation("doorsInRoom", 2);
			
	      	 // here not adding:
//	      	doorsInRoom.add(doors, room);
	        
	        // distance Doors x Doors -> liczby rzecz, 
	        //ograniczona do drzwi nale¿¹cych do tego samego pokoju
	      	 // here not adding:
//	    	structure.addFunction("doorsDist", new CachingFunction( new DoorToDoor()));
//	      	vocabulary.addFunction("doorsDist", 2);

	      	// powierzchnia pokoju
	      	vocabulary.addFunction("area", 1);
	    	TabularFunction area = new TabularFunction(1);
	        structure.addFunction("area", area);
	    	
	        for (SUObject room : getRooms(list)){
	        	area.add(new SUObject[]{room},Double.valueOf(room.getArea()));
	        }
	        
	        // sensors
	        UnaryTabularRelation isSensor = new UnaryTabularRelation();
	    	structure.addRelation("Sensors", isSensor);
	    	vocabulary.addRelation("Sensors", 1);
	    	
	    	 // here not adding:
//    		structure.addDomainObject(s);
//    		isSensor.add(s);
	    	
	    	 //sensor in a room (sensor, room )
	    	TabularRelation sensorInRoom = new TabularRelation(2);
	      	structure.addRelation("sensorInRoom", sensorInRoom);
	      	vocabulary.addRelation("sensorInRoom", 2);
	      	
	      	 // here not adding:
//    		sensorInRoom.add(s, s.parentEdge);
	        
	        //sprawdza czy dzwi sa obserwowane przez jakis czujnik
//	    	structure.addFunction("isDoorSurveilled", new CachingFunction( new ISDoorSurveilled()));
//	      	vocabulary.addFunction("isDoorSurveilled", 1);
	      	
	      	UnaryTabularRelation surveilledDoors = new UnaryTabularRelation();
	    	structure.addRelation("surveilledDoors", surveilledDoors);
	    	vocabulary.addRelation("surveilledDoors", 1);
	    	
	    	 // here not adding:
//	     	surveilledDoors.add(da);     			
	      	
//	      sprawdza czy mozna przemknac pomiedzy drzwiami w tym samym pokoju bez wchodzenia w zasieg czujnika
	    	 // here not adding:
//	    	structure.addRelation("isPassageWatched", new CachingRelation( new IsPassageWatched()));
//	      	vocabulary.addRelation("isPassageWatched", 2);
	        
	    	if (!vocabulary.isCompatible(structure))
	    		throw new RuntimeException("!vocabulary.isCompatible(structure)");
	    	
	    	
	        return new Object []{structure,vocabulary};
	        }

}
