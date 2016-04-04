package revitXmlTesting;

import java.util.ArrayList;

import rectangularBoard.Path;
import util.Logger;


public class Room {

	int id;
	String name;
	BoundingBox bbox;
	Path path;
	ArrayList<Door> doors;
	
	public Room(int id,	String name, BoundingBox bbox, Path path ){
		this.id=id;
		this.name=name;
		this.bbox=bbox;
		this.path= path;
		doors=new ArrayList<Door>();
	}
	
	public void addDoors(Door d){
		doors.add(d);
	}
	public ArrayList<Door> getDoors (){
		return doors ;
	}
	public void print(){
		Logger.LOGGER.debug("Room, id="+id+" name="+name +" "+bbox);
		if (path!=null)
			Logger.LOGGER.debug("	path:"+path);
	}
	public int getId(){
		return id;
	}
	
	public String toString(){
		return "Room{id:"+id+", "+name+"}";
	}
}
