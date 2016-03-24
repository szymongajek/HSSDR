package hyperGraphs;

import java.awt.Point;

import util.Logger;

public class RelationHE extends HyperEdge {
	// wierzcholek source
	private Node source;
	// wierzcholek source
	private Node target;

	//dla relacji miedzypietrowych
	Point twoFloorRelStartPoint;
	Point twoFloorRelEndPoint;
	
	public RelationHE() {
	}

	public RelationHE(ObjectHE parent, Node source, Node target, String relKind){
		this.setSource(source);
		this.setTarget(target);
		this.setParentEdge(parent);
		this.setAttribute(HLH.KIND, relKind);
        
		source.addRelation(this);
        target.addRelation(this);
        parent.addChildElement(this);
	}
	
	/**
	 * dziwny kontruktow wywolujacy kopiujacy ale sam nim nie bedacy
	 * @param rel
	 * @param src
	 * @param tgt
	 */
	RelationHE(RelationHE rel, Node src, Node tgt) {
		super(rel);
		source = src;
		src.addRelation(this);
		target = tgt;
		tgt.addRelation(this);
	}

	public Node getSource() {
		return source;
	}

	void setSource(Node source) {
		this.source = source;
	}

	public Node getTarget() {
		return target;
	}

	void setTarget(Node target) {
		this.target = target;
	}

	private void removeConnections() {
		source.removeConnection(this);
		target.removeConnection(this);
	}

	void remove() {
		getParentEdge().removeRelation(this);
		removeConnections();
	}

	public boolean isLinkingNodes(Node node1, Node node2) {
		if (node1 == target && node2 == source) {
			return true;
		} else if (node1 == source && node2 == target) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * zwraca wezel koncowy relacji inny niz node
	 * jezeli node nie nalezy do rel zwraca null
	 * @param node
	 * @return
	 */
	public Node getIncidentNodeOtherThan(Node node){
		if (target == node ) {
			return source;
		}else if ( source== node ){
			return target;
		}else{
			Logger.LOGGER.error("node not connected to relation");
			return null;
		}
		
	}
	
	public void reconnectNodes(Node oldNode, Node newNode) {
		if (oldNode == target ) {
			target = newNode;
		}else if ( oldNode == source ){
			source = newNode;
		}else{
			throw new RuntimeException("removing node not connected to relation");
		}
		
		newNode.addRelation(this);
		oldNode.removeConnection(this);
		
	}

	public void setTwoFloorRelHookPoints(Point twoFloorRelStartPoint, Point twoFloorRelEndPoint) {
		this.twoFloorRelStartPoint = twoFloorRelStartPoint;
		this.twoFloorRelEndPoint = twoFloorRelEndPoint;
	}


	public Point getInterFloorRelationHookPointforNode(Node node){
		if (node == target ) {
			return twoFloorRelEndPoint;
		}else if ( node == source ){
			return twoFloorRelStartPoint;
		}else{
			throw new RuntimeException("checking node not connected to relation");
		}
	}

}
