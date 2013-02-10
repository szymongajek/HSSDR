package hyperGraphs;
 
public class RelationHE extends HyperEdge
{
	//wierzcholek source
    private Node source;
//  wierzcholek source
    private Node target;

    public RelationHE()
    {
    }
    RelationHE(RelationHE rel,Node src, Node tgt)
    {
    	super(rel);
    	source=src;
    	src.addRelation(this);
    	target=tgt;
    	tgt.addRelation(this);
    }

    public Node getSource()
    {
        return source;
    }

    void setSource(Node source)
    {
        this.source = source;
    }

    public Node getTarget()
    {
        return target;
    }

    void setTarget(Node target)
    {
        this.target = target;
    }
    
    private void removeConnections(){
    	source.removeConnection(this);
    	target.removeConnection(this);
    }
    
    void remove(){
    	getParentEdge().removeRelation(this);
		removeConnections();
    }
    
    public Node getConnectionNodeOtherThan(Node n){
    	if (n==target) return source;
    	if (n==source) return target;
    	throw new IllegalArgumentException();
    }

}
