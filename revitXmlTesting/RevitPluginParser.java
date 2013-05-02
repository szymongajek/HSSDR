package revitXmlTesting;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

 
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
 

import rectangularBoard.Path;


public class RevitPluginParser extends DOMParser{

	 
	List<Room> rooms = new ArrayList<Room>();
	List<Door> doors= new ArrayList<Door>();

	
	/**
	 * parse one rooms and its points
	 */
	@Override
	Path parseDocumentWithMainOutline() {
		NodeList nl = dom.getElementsByTagName("room");
		if(nl != null && nl.getLength() > 0) {
			 
			Element el = (Element)nl.item(0);
			Room r = getRoom(el);
			if (r==null){
				 System.out.print("Uwaga!!Blad parsowania obrysu dla pokoju id:");
				 System.out.println (Integer.parseInt(el.getAttribute("id")));
				 return null;
			}
			return r.path;
			 
		}
		return null;
	}
	
	@Override
	void parseDocument(){
		//get the root elememt
		//Element docEle = dom. getDocumentElement();
		
		Hashtable<Integer,Room> roomsMap = new Hashtable<Integer, Room>();
		
		NodeList nl = dom.getElementsByTagName("room");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				Element el = (Element)nl.item(i);
				Room r = getRoom(el);
				if (r==null){
					 System.out.print("Uwaga!!Blad parsowania XML dla pokoju id:");
					 System.out.println (Integer.parseInt(el.getAttribute("id")));
					 continue;
				}
				rooms.add(r);
				roomsMap.put(r.getId(), r);
			}
		}
		
		nl = dom.getElementsByTagName("door");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				Element el = (Element)nl.item(i);
				int id = Integer.parseInt(el.getAttribute("id"));
				Room from;
				if (el.getAttribute("fromRoom").length()!=0){
					int fromRoom = Integer.parseInt(el.getAttribute("fromRoom"));
					from = roomsMap.get(fromRoom);
				}else 
					from=null;
				
				Room to;
				if (el.getAttribute("toRoom").length()!=0){
					int toRoom = Integer.parseInt(el.getAttribute("toRoom"));
					to = roomsMap.get(toRoom);
				}else 
					to=null;
				
				BoundingBox bbox =  getBoundingBox(el);
				
				 if (bbox==null ){
					 System.out.println("Blad parsowania XML dla drzwi id:"+id);
				 }else {
					 Door d = new Door(id, from, to, bbox);
						doors.add(d);
						if (from!=null)
							from.addDoors(d);
						if (to!=null)
							to.addDoors(d);
				 }
			}
		}
	}

	private Room getRoom(Element empEl) {
		
		String name = empEl.getAttribute("name");
		int id = Integer.parseInt(empEl.getAttribute("id"));
		 BoundingBox bbox =  getBoundingBox(empEl);
		 //getPolygonPoints(empEl);
		 Path p = getPolygonAsPath(empEl);
		 //System.out.println(p);
		 if (bbox==null || p==null){
			 return null;
		 }
		 p.setUserLabel(name);
		return new Room(id,name,bbox, p);
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content 
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is name I will return John  
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private String getPolygonPoints(Element ele ) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName("polygon_point");
		
		float px = 0;
		float py=0;
		int rpx = 0;
		int rpy = 0;
		for(int i=0; i<nl.getLength(); i++){
			Element el = (Element) nl.item(i);
			float x = Float.parseFloat(el.getAttribute("x"));
			float y = Float.parseFloat(el.getAttribute("y"));
			int rx = transfCoordX(x);
			int ry = transfCoordY(y);
			if (i>0)System.out.println("Poly diff r"+(i-1)+"->"+i+": " +(rx-rpx)+", "+(ry-rpy));
			//System.out.println("Poly diff"+(i-1)+"->"+i+": " +(x-px)+", "+(y-py));
			px=x;
			py=y;
			rpx=rx;
			rpy=ry;
		}
	 
		return textVal;
	}
	private Path getPolygonAsPath(Element ele ) {
		
		NodeList nl = ele.getElementsByTagName("polygon_point");
		
		Path path = new Path(MAX_COORD, MAX_COORD);
		if (nl.getLength()==0) return null;
		
		for(int i=0; i<nl.getLength(); i++){
			Element el = (Element) nl.item(i);
			float x = Float.parseFloat(el.getAttribute("x"));
			float y = Float.parseFloat(el.getAttribute("y"));
			int rx = transfCoordX(x)  ;
			int ry = transfCoordY(y) ;
			path.add(rx, ry,Path.LINE_SOLID);
		}
		
		Element el = (Element) nl.item(0);
		float x = Float.parseFloat(el.getAttribute("x"));
		float y = Float.parseFloat(el.getAttribute("y"));
		int rx = transfCoordX(x)  ;
		int ry = transfCoordY(y) ;
		path.add(rx, ry,Path.LINE_SOLID);
		
		path.makeValid();
	 
		return path;
	}
	
	 
	/**
	 * Calls getTextValue and returns XMin and rest as bbox
	 * @param ele
	 * @param tagName
	 * @return
	 */ 
	private BoundingBox getBoundingBox(Element ele) {
		//in production application you would catch the exception
		if (ele.getAttribute("minX").length()==0 ) return null;
		float minX=Float.parseFloat(ele.getAttribute("minX"));
		float minY=Float.parseFloat(ele.getAttribute("minY"));
		float minZ=Float.parseFloat(ele.getAttribute("minZ"));
		float maxX=Float.parseFloat(ele.getAttribute("maxX"));
		float maxY=Float.parseFloat(ele.getAttribute("maxY"));
		float maxZ=Float.parseFloat(ele.getAttribute("maxZ"));
		
		
		return new BoundingBox(
				transfCoordX(minX),
				transfCoordY(minY),
				transfCoordZ(minZ),
				transfCoordX(maxX),
				transfCoordY(maxY),
				transfCoordZ(maxZ));
	}


}
