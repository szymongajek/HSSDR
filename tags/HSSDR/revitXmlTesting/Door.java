package revitXmlTesting;

public class Door {
	int id;
	Room fromRoom;
	Room toRoom;
	BoundingBox bbox;
	
	public Door(int id, Room fromRoom,Room toRoom, BoundingBox bbox){
		this.id=  id;
		this.fromRoom=  fromRoom;
		this.toRoom=  toRoom;
		this.bbox=  bbox;
	}
	public String toString(){
		String ret= "Door{id:"+id;
		ret+= fromRoom!=null?" form:"+fromRoom.getId():"";
		ret+= toRoom!=null?  " to:"+toRoom.getId():"";
		return ret+"}";
	}

}
