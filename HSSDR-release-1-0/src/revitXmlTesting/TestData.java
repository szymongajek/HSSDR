package revitXmlTesting;


import java.util.List;

import util.Logger;

import folf.AbstractFunction;
import folf.CachingFunction;
import folf.Structure;
import folf.SymmetricTabularRelation;
import folf.TabularFunction;
import folf.TabularRelation;
import folf.UnaryTabularRelation;
import folf.Vocabulary;


public class TestData {
	
	Vocabulary vocabulary;
	Structure structure;
	
	 public TestData(List<Room> rooms, List<Door> doors ){
		 	structure = new Structure();
	    	vocabulary = new Vocabulary();
	    	vocabulary.allowNumbers();
	    	vocabulary.allowStrings();
	        
	    	
	    // relacje adjacency accesibility
//	    	SymmetricTabularRelation adjacency = new SymmetricTabularRelation();
//	    	structure.addRelation("adjacent", adjacency);
	    	SymmetricTabularRelation accesibility = new SymmetricTabularRelation();
	    	structure.addRelation("accessible", accesibility);
	    	UnaryTabularRelation isRoom = new UnaryTabularRelation();
	    	structure.addRelation("Rooms", isRoom);
	    	
	    	for (Room r : rooms){
	    		structure.addDomainObject(r);
	    		isRoom.add(r);
	        }
	    	
	    	//vocabulary.addRelation("adjacent", 2);
	    	vocabulary.addRelation("accessible", 2);
	    	vocabulary.addRelation("Rooms", 1);
	    	
	    	
	    	 
    		for (Door d : doors){ 
        		 if (d.fromRoom!=null & d.toRoom!=null)
        			accesibility.add(d.fromRoom, d.toRoom);
       		}
	    	
	   // funkcja type - typ pomieszczenia
 
	    	vocabulary.addFunction("type", 1);
	    	TabularFunction type = new TabularFunction(1);
	        structure.addFunction("type", type);
	    	
	        for (Room r : rooms){
	        	type.add(new Room[]{r},AreaTypeMapping.getType(r.name).toString());
	        }

	        // doors
	        UnaryTabularRelation isDoors = new UnaryTabularRelation();
	    	structure.addRelation("Doors", isDoors);
	    	vocabulary.addRelation("Doors", 1);
	    	
	    	for (Door d : doors){ 
	    		structure.addDomainObject(d);
	    		isDoors.add(d);
	        }
//	    	 zbi�r drzwi prowadz�cych na zewn�trz ExternalDoors
//	    	#ExternalDoors(d) <=> Door(d) and ( forall x, y in Room:  
//	    	# doorsInRoom(d, x) and doorsInRoom(d, y) => x = y );

	    	 UnaryTabularRelation externalDoors = new UnaryTabularRelation();
	     	structure.addRelation("ExternalDoors", externalDoors);
	     	vocabulary.addRelation("ExternalDoors", 1);
	     	
	     	for (Door d : doors){ 
	     		if (d.fromRoom==null || d.toRoom==null) externalDoors.add(d);     			
	         }
	        
	        //doors in a room (door, room )
	    	TabularRelation doorsInRoom = new TabularRelation(2);
	      	structure.addRelation("doorsInRoom", doorsInRoom);
	      	vocabulary.addRelation("doorsInRoom", 2);
	      	
	      	for (Door d : doors){ 
	      		if (d.fromRoom!=null) 
	      			doorsInRoom.add(d, d.fromRoom);
	      		if ( d.toRoom!=null)  
	      			doorsInRoom.add(d, d.toRoom);
    			
	      	}
	    	 
	        // distance Doors x Doors -> liczby rzecz, 
	        //ograniczona do drzwi nale��cych do tego samego pokoju
	    	structure.addFunction("doorsDist", new CachingFunction( new DoorToDoorRevit()));
	      	vocabulary.addFunction("doorsDist", 2);

	      	// powierzchnia pokoju
//	      	vocabulary.addFunction("area", 1);
//	    	TabularFunction area = new TabularFunction(1);
//	        structure.addFunction("area", area);
//	        for (ObjectHE he : getAllRooms()){
//	        	area.add(new ObjectHE[]{he},Double.valueOf(he.getAttribute(HLH.AREA)));
//	        }
	        
	    	if (!vocabulary.isCompatible(structure))
	    		throw new RuntimeException("!vocabulary.isCompatible(structure)");
	 }
	 static class DoorToDoorRevit extends AbstractFunction
	    {
	        public DoorToDoorRevit()
	        {
	            super(2);
	        }  

	        public Double value(List<Object> args)
	        {
	            if (args.size() != 2)
	                throw new IllegalArgumentException("bad arity");
	            PathCalculatorRevit calc = new PathCalculatorRevit((Door)args.get(0),(Door)args.get(1) );
	            double d =  calc.distanceBetweenDoors();
	            Logger.LOGGER.debug("calc dist:"+d);
	            return d;
	        }
	    }

	public Structure getStructure() {
		return structure;
	}
	public Vocabulary getVocabulary() {
		return vocabulary;
	}
}
