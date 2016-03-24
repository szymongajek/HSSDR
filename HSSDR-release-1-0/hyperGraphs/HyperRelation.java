package hyperGraphs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class HyperRelation extends RelationHE {
	
	// Incidence  matrics -  Macierz  incydencji
	private ArrayList<Node> incidents = new ArrayList<Node>(); 
	
	// dla kolejnych wezlow w realcji punkt zaczenienia relacji w obszarze
	private ArrayList<Point> hyperRelHookPointSequence = new ArrayList<Point>();
	

	public HyperRelation(ObjectHE parent, Node source, Node target,
			String relKind) {
		incidents.add(source);
		incidents.add(target);
		this.setParentEdge(parent);
		this.setAttribute(HLH.KIND, relKind);

		source.addRelation(this);
		target.addRelation(this);
		parent.addChildElement(this);
	}

	public HyperRelation(ObjectHE parent, Node[] elements,
			String relKind) {
		
		this.setParentEdge(parent);
		this.setAttribute(HLH.KIND, relKind);

		for (Node node : elements) {
			incidents.add(node);
			node.addRelation(this);
		}
		
		parent.addChildElement(this);
		
	}

	public void addNodeToRelation(Node newNode) {
		incidents.add(newNode);

		newNode.addRelation(this);
	}

	void remove() {
		getParentEdge().removeRelation(this);
		incidents.clear();
	}

	public Node getFirstNode() {
		return incidents.get(0);
	}

	public Node getSecondNode() {
		return incidents.get(1);
	}

	public List<Node> getIncidentNodes() {
		return incidents;
	}

	/**
	 * check if this hyper rel contains in its connected node a node from both
	 * of given floors
	 * 
	 * @param floor1
	 * @param floor2
	 * @return
	 */
	public boolean containsNodeFromFloor(int floor) {
		for (Node node : incidents) {
			if (node.getFloor() == floor) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLinkingNodes(Node node1, Node node2) {

		return (incidents.contains(node1) && incidents.contains(node2));

	}
	
	@Override
	public void reconnectNodes(Node oldNode, Node newNode) {
		for (int i = 0; i < incidents.size(); i++) {
			if (incidents.get(i)== oldNode){
				incidents.set(i, newNode);
			}
		}
		
		newNode.addRelation(this);
		oldNode.removeConnection(this);
		
	}
	
	@Override
	public Point getInterFloorRelationHookPointforNode(Node node){
		
		for (int i = 0; i < incidents.size(); i++) {
			if (incidents.get(i)== node){
				return hyperRelHookPointSequence.get(i);
			}
		}
		
		throw new RuntimeException("checking node not connected to relation");
	}

	public void setHyperRelHookPointSequence(ArrayList<Point> multiFloorRelHookPointSequence) {
		this.hyperRelHookPointSequence=multiFloorRelHookPointSequence;
	}
	
	
}
