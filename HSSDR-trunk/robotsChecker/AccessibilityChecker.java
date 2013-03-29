package robotsChecker;

import java.util.HashSet;
import java.util.ArrayList;

import hyperGraphs.*;

public class AccessibilityChecker
{
	HashSet<ObjectHE> rooms = new HashSet<ObjectHE>();
	HashSet<ObjectHE> eliminated_rooms = new HashSet<ObjectHE>();

	static void  findRooms(ObjectHE hyperedge,HashSet<ObjectHE> roomsSet)
	{
		ArrayList<HyperEdge> children = hyperedge.getChildElements();
		if (! children.isEmpty()) {
			// this is an area with rooms/smaller areas inside
			for (HyperEdge h : children)
				if (h instanceof ObjectHE)
					AccessibilityChecker.findRooms((ObjectHE) h,roomsSet);
		} else {
			// this is a room
			roomsSet.add(hyperedge);
		}
	}

	boolean hasOutsideDoor(ObjectHE room)
	{
		for (Node n : room.getNodes()) {
			// check if n represents an outside wall (no adj/acc relations)
			// with a door (door position attributes present)
			if (n.getRelations().isEmpty() && n.hasDoors())
				return true;
		}
		return false;
	}

	void eliminateAccessibleRooms(ObjectHE room)
	{
		Node n2;
		boolean fromSrcToTgt;
		for (Node n : room.getNodes()) {
			for (RelationHE r : n.getRelations()) {
				if (r.getAttribute(HLH.KIND) != HLH.KIND_ACC)
					continue;
				if (r.getSource() == n) {
					n2 = r.getTarget();
				} else if (r.getTarget() == n) {
					n2 = r.getSource();
				} else {
					throw new RuntimeException("Inconsistency detected");
				}
 
				ObjectHE room2 = n2.getObjectEdge(); 
				
				
				if (rooms.contains(room2)) {
					rooms.remove(room2);
					eliminated_rooms.add(room2);
					eliminateAccessibleRooms(room2);
				}
			}
		}
	}

	GraphMessage[] check(HLH graph)
	{
		AccessibilityChecker.findRooms(graph.getRootEdgeGroundFloor(),rooms);
		// check for existence of rooms with doors leading outside
		for (ObjectHE r : rooms)
			if (hasOutsideDoor(r))
				eliminated_rooms.add(r);
		rooms.removeAll(eliminated_rooms);
		// found any?
		if (eliminated_rooms.isEmpty()) {
			GraphMessage m = new GraphMessage();
			m.setMessage("No doors leading outside found.");
			return new GraphMessage[] { m };
		}
		// follow accessibility edges and discard all connected rooms
		HashSet<ObjectHE> tmp = new HashSet<ObjectHE>(eliminated_rooms);
		for (ObjectHE r : tmp)
			eliminateAccessibleRooms(r);
		// discarded all?
		if (rooms.isEmpty())
			return new GraphMessage[] { };
		// report leftovers
		GraphMessage m = new GraphMessage();
		m.setMessage("Inaccessible room(s) found");
		m.setObjects(new ArrayList<MovableComponent>(rooms));
		return new GraphMessage[] { m };
	}

	public static GraphMessage[] checkStructure(HLH graph)
	{
		AccessibilityChecker ac = new AccessibilityChecker();
		return ac.check(graph);
	}
}
