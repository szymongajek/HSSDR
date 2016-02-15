package revitXmlTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.Logger;

import folf.Parser;
import folf.Result;
import folf.Suite;

public class Start {
	public static String test_file="test_files/_evac_route.7.ind.test";
	public static String input_file="Q:\\HSSDR\\HSSDR revit plugin\\revit xml\\adv sample proj.xml";
	public static boolean DISPLAY_ROOMS = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//wczytaj dane z pliku
		RevitPluginParser parser = new RevitPluginParser();
		parser.parseFile(input_file);
		
		List<Room> rooms= parser.rooms;
		List<Door> doors= parser.doors;
		if (DISPLAY_ROOMS){
			for (Room r : rooms) {
//			 	if (! (r.name.contains(" 102") ||r.name.contains(" 123") ||  r.name.contains(" 107") ||  r.name.contains(" 131") ||  r.name.contains("234"))) continue;
				r.print();
				if (r.path!= null){
					 RoomPainter rpainter = new RoomPainter(r,parser.MAX_COORD);
				}
			}
//			for (Door d : doors) {
//				System.out.println(d);
//				 
//			}
		}
	
		
		TestData testData = new TestData(rooms, doors);
		 
		//wykonaj test
        Parser p = new Parser();
        String message="";
        ArrayList<String> roomsToHighlight = new ArrayList<String>();
        
        Suite su = p.suiteFromFile(test_file, testData.getVocabulary());
            if (su == null) {
                System.out.println("syntax or file error!");
                message+="syntax or file error!  ";
                String msg = p.getErrorMessages();
                if (msg != null){
                	 System.out.print(msg);
                	 message+=msg+"\n";
                }
                System.out.println(message);
            }
            Result[] res = su.getCompleteResults(testData.getStructure(), 99);
            for (int i = 0; i < res.length; ++i) {
    			String msg ="#" + (i + 1) + ": ";
    			Logger.LOGGER.debug(msg);
    			message+=msg;

    			msg=res[i].isTrue()?"Success ":"Failure ";
    			msg+=res[i].getMessageInfo();
    			Logger.LOGGER.debug(msg);
    			message+=msg+" \n";

    			msg=res[i].getQVarsInfo();
    			if (msg.length()>0){
    				Logger.LOGGER.debug(msg);
        			message+=msg+" \n";
    			}

    			if (!res[i].isTrue()){
    				for (int k = 0;; ++k) {
    					Map<String, Object> qvars = res[i].getQVarsState(k);
    					if (qvars == null)
    						break;
    					for (String name : qvars.keySet())
    						roomsToHighlight.add(qvars.get(name).toString());
    				}
    			}
    			
    		}
            message+="\n";
            System.out.println("Number of rooms:"+rooms.size());
            System.out.println("Number of doors:"+doors.size());
            System.out.println(message);
            System.out.println("Objects:"+roomsToHighlight);
             
 
	}

}
/*
 * 
 *  
 *  
 *  


Objects:[Room{id:215097, Women 211}, 
Room{id:215167, Caferteria 224}, 
Room{id:215138, Computer Lab 222}, 

Room{id:216463, Administration 322}, 


Room{id:214989, Men 210},
Room{id:217813, Computer Lab 209}, 
Room{id:217812, Drafting 208}, 


Room{id:177330, Instruction 108}, 
Room{id:177326, Men 110}, 
 Room{id:177328, Women 109}]

 *  
 *  
 */
