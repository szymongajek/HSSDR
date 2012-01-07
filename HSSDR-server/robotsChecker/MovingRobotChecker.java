package robotsChecker;

import java.util.HashSet;
import hyperGraphs.*;

public class MovingRobotChecker
{
	  
	HashSet<ObjectHE> rooms = new HashSet<ObjectHE>();
	 

	private GraphMessage[] check(HLH graph) {
		AccessibilityChecker.findRooms(graph.getRootEdge(),rooms);
		String err="";
		
		//sprawdzanie rozmiarow pokoi
		for (ObjectHE r : rooms)
			if (r.getNodes().size()==4){
//				 prostokatne
				 int rectX = r.getWallLen(0);
				 int rectY = r.getWallLen(1);
				 
				err=Robot.checkRect(rectX,rectY);
				if (!err.equals("")){
					GraphMessage m = new GraphMessage();
					m.setMessage(err);
					return new GraphMessage[] { m };
				}
			}else if (r.getNodes().size()==6){
//				 ksztalt L
				/*
				 *  ______
				 *  |     |
				 *  |     |
				 * d|     |
				 *  |_____|
				 *  |  b  |____________
				 *  |		|a		 |	
				 *  |________|________|
				 *         c
				 */
				 int Dpos = largestWallNr(r, true);
				 int Cpos = largestWallNr(r, false);
				 int Bpos=-1, Apos=-1;
				 
				 if (Cpos==Dpos-1){
					 Apos= Cpos-1;
					  Bpos=Dpos+1;
					 if (Apos==-1) Apos=5;
					 if (Bpos==6) Bpos=0;
//					err="a:"+ r.getWallLen(Apos)+"c"+r.getWallLen(Cpos)+ "d"+r.getWallLen(Dpos)+"b"+ r.getWallLen(Bpos)  ;
				 }else if (Dpos==Cpos-1){
					 Apos= Cpos+1;
					  Bpos=Dpos-1;
					 if (Bpos==-1) Bpos=5;
					 if (Apos==6) Apos=0;
//					 err="b"+ r.getWallLen(Bpos)+ "d"+r.getWallLen(Dpos) +"c"+r.getWallLen(Cpos)+ "a:"+ r.getWallLen(Apos) ;
				 }else if (Cpos==5 &&Dpos==0){
					 Apos= Cpos-1;
					  Bpos=Dpos+1;
					 if (Apos==-1) Apos=5;
					 if (Bpos==6) Bpos=0;
//					err="a:"+ r.getWallLen(Apos)+"c"+r.getWallLen(Cpos)+ "d"+r.getWallLen(Dpos)+"b"+ r.getWallLen(Bpos)  ;
				 }else if (Dpos==5 &&Cpos==0){
					 Apos= Cpos+1;
					  Bpos=Dpos-1;
					 if (Bpos==-1) Bpos=5;
					 if (Apos==6) Apos=0;
//					 err="b"+ r.getWallLen(Bpos)+ "d"+r.getWallLen(Dpos) +"c"+r.getWallLen(Cpos)+ "a:"+ r.getWallLen(Apos) ;
				 }
				 
	 
				err=Robot.checkLtype(r.getWallLen(Apos),r.getWallLen(Bpos),r.getWallLen(Cpos),r.getWallLen(Dpos))  ;
				
				if (!err.equals("")){
					GraphMessage m = new GraphMessage();
					m.setMessage(err);
					return new GraphMessage[] { m };
				}
			} else if (r.getNodes().size()==8){
//				 ksztalt T lub U lub Z
				//TODO inne ksztalty pokoji
			} 
				
//		sprawdzanie mozliwosci skretu w drzwi
		for (ObjectHE r : rooms){
				int doors_size;
				int dist_to_wall;
				
				for (Node n : r.getNodes()) {
								
					if (n.hasDoors()){
						doors_size=LayoutUtils.calcDoorsSize(n, 0);
						
						String dir =n.getAttribute(HLH.DIRECTION);
						String dir_to_search=LayoutUtils.oppositeDirection(dir);
						
						double [] doors_mid=  LayoutUtils.calcDoorsMiddlePoint(n, 0);
						
						dist_to_wall=LayoutUtils.getDistNearestWallOfDirContCoord(r, dir_to_search, doors_mid);
//						err=dist_to_wall+"  size:"+ doors_size  ;
						err=Robot.checkDoorsTurntype(dist_to_wall, doors_size)  ;
						
						if (!err.equals("")){
							GraphMessage m = new GraphMessage();
							m.setMessage(err);
							return new GraphMessage[] { m };
						}
						
						//TODO for other doors
						
						
					}//if (n.hasDoors()		
				}//for (Node n
		}
		
		return new GraphMessage[] { };
	} 

	 

	public static GraphMessage[] checkStructure(HLH graph)
	{
		MovingRobotChecker ac = new MovingRobotChecker();
		
		
		return ac.check(graph);
	}

	public static int  largestWallNr(ObjectHE r, boolean vertical){
		if (vertical){
			 if (r.getWallDir(0). equals("E")
					 || r.getWallDir(0). equals("W")){
				return  posOfLargestNr(r.getWallLen(0), r.getWallLen(2), r.getWallLen(4))*2;
			 }else{
				 return  posOfLargestNr(r.getWallLen(1), r.getWallLen(3), r.getWallLen(5))*2+1;
			 }
			
		}else{
			 if (r.getWallDir(0). equals("E")
					 || r.getWallDir(0). equals("W")){
				 return  posOfLargestNr(r.getWallLen(1), r.getWallLen(3), r.getWallLen(5))*2+1;
			 }else{
				 return  posOfLargestNr(r.getWallLen(0), r.getWallLen(2), r.getWallLen(4))*2;
			 }
		}

	}
	
	public static int  posOfLargestNr(int x, int y, int z){
		if (x>y && x>z) return 0;
		if (y>x && y>z) return 1;
		if (z>x && z>y) return 2;
		throw new RuntimeException("wejscie do metody to nie l-sciana");
	}
		 
}
