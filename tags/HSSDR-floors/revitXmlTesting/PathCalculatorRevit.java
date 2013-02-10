package revitXmlTesting;

import hyperGraphs.PathCalulator;

public class PathCalculatorRevit  extends PathCalulator{

	public PathCalculatorRevit(Door d1, Door d2) {
		Room room11= d1.fromRoom;
		Room room12= d1.toRoom;
		
		Room room;
		
		if ((d2.fromRoom==room11)||(d2.toRoom==room11))
			room=room11;
		else if ((d2.fromRoom==room12)||(d2.toRoom==room12))
			room=room12;
		else 
			throw new RuntimeException("drzwi nie naleza do tego samego pokoju");
		
		System.out.println("calc dist dla pokoju:"+room.name);
		
		this.room_interior = room.path.getInterior();
		
		int dx=d1.bbox.minX;
		int dy=d1.bbox.minY;
		int dmx=d1.bbox.maxX;
		int dmy=d1.bbox.maxY;
		
		if (findNeighbourWithinInterior(dx, dy)!=null) {
			door1x=findNeighbourWithinInterior(dx, dy)[0];
			door1y=findNeighbourWithinInterior(dx, dy)[1];
			
		}else if (findNeighbourWithinInterior(dmx, dmy)!=null) {
			door1x=findNeighbourWithinInterior(dmx, dmy)[0];
			door1y=findNeighbourWithinInterior(dmx, dmy)[1];
			
		}else if (findNeighbourWithinInterior(dx, dmy)!=null) {
			door1x=findNeighbourWithinInterior(dx, dmy)[0];
			door1y=findNeighbourWithinInterior(dx, dmy)[1];
			
		}else if (findNeighbourWithinInterior(dmx, dy)!=null) {
			door1x=findNeighbourWithinInterior(dmx, dy)[0];
			door1y=findNeighbourWithinInterior(dmx, dy)[1];
			
		}else
			throw new RuntimeException("drzwi "+d1.id+" leza poza wnetrzem pokoju"+room.name);
		
		dx=d2.bbox.minX;
		dy=d2.bbox.minY;
		dmx=d2.bbox.maxX;
		dmy=d2.bbox.maxY; 
		
		if (findNeighbourWithinInterior(dx, dy)!=null) {
			door2x=findNeighbourWithinInterior(dx, dy)[0];
			door2y=findNeighbourWithinInterior(dx, dy)[1];
			
		}else if (findNeighbourWithinInterior(dmx, dmy)!=null) {
			door2x=findNeighbourWithinInterior(dmx, dmy)[0];
			door2y=findNeighbourWithinInterior(dmx, dmy)[1];
			
		}else if (findNeighbourWithinInterior(dx, dmy)!=null) {
			door2x=findNeighbourWithinInterior(dx, dmy)[0];
			door2y=findNeighbourWithinInterior(dx, dmy)[1];
			
		}else if (findNeighbourWithinInterior(dmx, dy)!=null) {
			door2x=findNeighbourWithinInterior(dmx, dy)[0];
			door2y=findNeighbourWithinInterior(dmx, dy)[1];
			
		}else
			throw new RuntimeException("drzwi "+d2.id+" leza poza wnetrzem pokoju"+room.name);
		
	}
	
	private int[] findNeighbourWithinInterior(int dx, int dy){
		if (room_interior[dx][dy]==2){
			return new int[]{dx,dy };
		}
		if (room_interior[dx-1][dy]==2){
			return  new int[]{dx-1,dy  };
		}
		if (room_interior[dx][dy-1]==2){
			return new int[]{dx,dy-1  };
		}
		if (room_interior[dx-1][dy-1]==2){
			return new int[]{dx-1,dy-1 };
		}
		return null;
	}

}
