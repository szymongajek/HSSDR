package revitXmlTesting;

import hyperGraphs.HLH.ROOM_TYPES;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AreaTypeMapping {
	
	
	private static  HashMap<String, ROOM_TYPES> mapping ;
	
	public static ROOM_TYPES  getType(String name){
		name=extractName(name);
		if (mapping==null)
			AreaTypeMapping.init();
		 
		if (mapping.get(name)==null)
			return ROOM_TYPES.Space;
		else 
			return mapping.get(name);
	}

	public static String extractName(String name){
		 Pattern pattern = 
	            Pattern.compile("[0-9]");
		   Matcher matcher = 
	            pattern.matcher(name);
		   if (matcher.find()){
			   name = name.substring(0, matcher.start());
			   name = name.trim();   
		   }
		   
		return name;
	}
	private static void init() {
		mapping = new HashMap<String, ROOM_TYPES>();
		
		mapping.put("Computer Lab", ROOM_TYPES.Office);
		mapping.put("Conference", ROOM_TYPES.Office);
		mapping.put("Office", ROOM_TYPES.Office);
		
		mapping.put("Women", ROOM_TYPES.Bathroom);
		mapping.put("Men", ROOM_TYPES.Bathroom);
		mapping.put("Toilet", ROOM_TYPES.Bathroom);
		mapping.put("Bathroom", ROOM_TYPES.Bathroom);
		
		mapping.put("Corridor", ROOM_TYPES.Corridor);
		mapping.put("Lounge", ROOM_TYPES.Corridor);
		mapping.put("Lobby", ROOM_TYPES.Corridor);
		mapping.put("Vest.", ROOM_TYPES.Corridor);
		
		mapping.put("Stair", ROOM_TYPES.Staircase);
		mapping.put("Staircase", ROOM_TYPES.Staircase);
		
		mapping.put("Kitchen", ROOM_TYPES.Kitchen);
		
		mapping.put("Bedroom", ROOM_TYPES.Bedroom);
		
		mapping.put("Garage", ROOM_TYPES.Garage);
		
		mapping.put("Living_Room", ROOM_TYPES.Living_Room);
		
		mapping.put("Space", ROOM_TYPES.Space);
	}
}
