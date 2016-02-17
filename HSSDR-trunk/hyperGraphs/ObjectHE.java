package hyperGraphs;


import java.util.Hashtable;
import java.util.ArrayList;


import util.Logger;

public class ObjectHE extends HyperEdge  
{
//	elementy zagniezdzone w hiperkrawedzi: relacje i komponenty(ObjectHE)
	private ArrayList <HyperEdge> childElements;
//	wierzcholki przyjegle do hiperkrawedzi 
    private ArrayList <Node> nodes;
//    poziom zagniezdzenia w drzewie : rootEdge 0, jej dzieci 1 itd..
    private int level;
     

    public ObjectHE()
    {
        childElements = new ArrayList<HyperEdge> ();
        nodes = new ArrayList<Node>();
        level = 0;
  
    }
    
    /*
     * procedura usuwajaca zawartosc hiperkrawedzi - cofniecie podzialu
     * warunek - pod ta HE sa tylko li�cie  
     * alg:
     * 1. znajdz nody EXT dla tej hiperkrawedzi i ozn jako extHE
     * 2. dla wszystkich grup  el extHE rozniacych sie tylko prefiksem przed pierwsza kropka stworz jeden wezel 
     * 3. etypkieta tego wezla to etykieta z ktowych powstal  po pierwszej kropce
     * 4. dla kazdej grupy jesli z wezlow z tej grupy wychodza relacje to:
     * - te, ktre maja ten sam target zlej w jedna, idaca do nowego wezla dla grupy
     * - te ktore maja inny target przepnij do nowego
     * 5. usun   child elements
     * 6. dodaj nowo stworzone wezly do tej HE
     */
    public boolean contentSuppression(){
//    	warunek - pod ta HE sa tylko liscie  
    	
		if (!isLeafMinusOneLevel()){
			Logger.LOGGER.error("proba usuniecia wnetrza hiperkrawedzi nie bedacej na dole hierarchi");
    		return false;
		}
    	
		Logger.LOGGER.debug("contentSuppression");
		
//		1. znajdz nody EXT dla tej hiperkrawedzi i ozn jako extHE
    	ArrayList <Node> extHE=findChildrensExternalNodes();
    	
// 		2. dla wszystkich grup  el extHE rozniacych sie tylko prefiksem przed pierwsza kropka stworz jeden wezel
//		 3. etypkieta tego wezla to etykieta z ktowych powstal  po pierwszej kropce
    //      mapa grup wezlow
    	
    	   Hashtable<String,ArrayList<Node>> nodesGroups= new Hashtable<String, ArrayList<Node>>();
    	   for (Node node:extHE ){
    		   if (!nodesGroups.containsKey(node.getLabelSuffix()))
    			   nodesGroups.put(node.getLabelSuffix(),  new ArrayList<Node>( ) );
    		   nodesGroups.get(node.getLabelSuffix()).add(node);
    	   }
    	   
    	  ArrayList<Node> newNodes = new ArrayList<Node>(); 
    	  for (ArrayList<Node> vect: nodesGroups.values()){
    		  Node n = new Node();
    		  n.setFloor(this.getFloor());
    		  n.setObjectEdge(this);
    		  n.setAttributesFrom(vect.get(0));
    		  n.setDirection(vect.get(0).getDirection());
    		  copyDoorsFromGroupTo(vect,n);
    		  n.setAttribute(HLH.LABEL, vect.get(0).getLabelSuffix() );
    		  n.calcMergingNodeLEN(vect);
    		  n.calcAndSetPositionSingle(this);
    		 
    		  newNodes.add(n);
    	  } 
    	  
    	  
//        * 4. dla kazdej grupy jesli z wezlow z tej grupy wychodza relacje to przepinanie
    	  for (Node newNode: newNodes){
    		  ArrayList<Node> oldNodesGroup = nodesGroups.get(newNode.getAttribute(HLH.LABEL));
    		  Logger.LOGGER.debug("przepinanie, ilosc wezlow w grupie:"+oldNodesGroup.size());
    		  if (oldNodesGroup.get(0).getRelations().size()==0){
    			  // ta grupa wezlow wychodzi na zew calego grafu i nie tworzymy relacji
    			  // sprawdzamy czy tak jest dla wszystkich
    			  for (Node n : oldNodesGroup){
    				  if (n.getRelations().size()!=0){
    					  Logger.LOGGER.error("w grupie wezlow zlaczanej w jeden sa wezly z rel i bez.");
    					  return false;
    				  }
    			  }
    			  Logger.LOGGER.debug("nic do przepiecia");
    			  
    		  }else{
    			  //grupowanie relacji po wezle do ktorego ida
    			  //lista relacji idacych do wspolnego wezla
    			  Hashtable<String,ArrayList<RelationHE>> oldRelationsGroupsByTarget= new Hashtable<String,ArrayList<RelationHE>>();
    			  //lista wezlow z ktorych wychodza relacja idace do wspolnego wezla
    			  Hashtable<String,ArrayList<Node>> oldNodesGroup_NodesMap= new Hashtable<String,ArrayList<Node>>();
    			  //wezel do ktorego idzie ta grupa relacji
    			  Hashtable<String,Node> oldNodesGroup_OtherNodesMap= new Hashtable<String,Node>();
    			  
    			  for (Node n : oldNodesGroup){
    				  for(RelationHE rel: n.getRelations()){
    					  Node otherNode = rel.getIncidentNodeOtherThan(n);
    					  
    					  if (!oldRelationsGroupsByTarget.containsKey(otherNode.toString())){
    						  oldRelationsGroupsByTarget.put(otherNode.toString(), new ArrayList<RelationHE>());
    					  }
    					  oldRelationsGroupsByTarget.get(otherNode.toString()).add(rel);
    					  
    					  if (!oldNodesGroup_NodesMap.containsKey(otherNode.toString())){
    						  oldNodesGroup_NodesMap.put(otherNode.toString(), new ArrayList<Node>());
    					  }
    					  oldNodesGroup_NodesMap.get(otherNode.toString()).add(n);
    					  
    					  oldNodesGroup_OtherNodesMap.put(otherNode.toString(), otherNode);
    				  }
    			  }
    			  for ( String otherNodeLabel: oldRelationsGroupsByTarget.keySet()){
    				  ArrayList<RelationHE> relationsGroupList = oldRelationsGroupsByTarget.get(otherNodeLabel);
    				  ArrayList<Node> relationsGroupList_nodesToDelete =oldNodesGroup_NodesMap.get(otherNodeLabel);
    				  Node relationsGroupList_targetNode  =oldNodesGroup_OtherNodesMap.get(otherNodeLabel);
    				  
    				  if (relationsGroupList.size()>1){
//    					  maja ten sam target zlej w jedna, idaca do nowego wezla dla grupy
//    					  przepinamy pierwsza
    					  Logger.LOGGER.debug("relacje maja ten sam target zlej w jedna, idaca do nowego wezla dla grupy");
    					  Logger.LOGGER.debug("ilosc relacji w grupie:"+relationsGroupList.size()+" wezel docelowy:"+otherNodeLabel);
    					  RelationHE toReconnect = relationsGroupList.get(0);
    					  Node toReconnectNode = relationsGroupList_nodesToDelete.get(0);
    					  toReconnect.reconnectNodes(toReconnectNode, newNode);
//    					  reszte usuwamy
    					  for (int i = 1; i < relationsGroupList.size(); i++) {
    						  RelationHE toDelete = relationsGroupList.get(i);
    						  toDelete.remove();
    					  }
    					  
    				  }else{
//   					  te ktore maja inny target przepnij do nowego
    					  RelationHE toReconnect = relationsGroupList.get(0);
    					  Logger.LOGGER.debug("relacje maja inny target przepnij do nowego");
    					  Logger.LOGGER.debug("relacja do przepiecia:"+toReconnect+" wezel docelowy:"+otherNodeLabel);
    					  Node toReconnectNode = relationsGroupList_nodesToDelete.get(0);
    					  toReconnect.reconnectNodes(toReconnectNode, newNode);
    				  }
    			  }
    		  }
    	  }
    	  
    	  //4.5 dla polaczen pionowych, stworz nowy wezel i przepnij do niego relacje z usuwanych
    	  ArrayList <Node> vertNodes=findChildrensVerticallNodes();
    	  
    	  Node vert = new Node();
    	  vert.setFloor(this.getFloor());
    	  vert.setObjectEdge(this);
    	  vert.setDirection(HLH.DIRECTION_VERTICAL);
    	  vert.setAttributesFrom(vertNodes.get(0));
    	  vert.calcAndSetPositionVERT(this);
    	  vert.setAttribute(HLH.LABEL, vertNodes.get(0).getLabelSuffix());
    	  newNodes.add(vert);
  		
    	  //przepiecie
    	  
    	  for (Node  prevNode: vertNodes){
    		  ArrayList<RelationHE> relationsToReconnect = new ArrayList<RelationHE>() ;
    		  relationsToReconnect.addAll(prevNode.getRelations())  ;
    		  
    		  for (RelationHE relationHE : relationsToReconnect) {
  				relationHE.reconnectNodes(prevNode, vert);
  			}
    	  }
		  
//        * 5. usun   child elements
    	 this.childElements= new ArrayList<HyperEdge>();
  
//        * 6. dodaj nowo stworzone wezly do tej HE
    	this.nodes=newNodes;
    		 
    	return true;
    		 
    }

    private void copyDoorsFromGroupTo(ArrayList<Node> group, Node newNode) {
    	for (Node joiningNode:group){
    		newNode.addAllDoorsFrom(joiningNode);
		  }
		
	}
	/*
     * sprawdza czy HE ma dzieci i te dzieci sa liscmi. warunek konieczny dla ususwania
     */
    public boolean isLeafMinusOneLevel() {
    	boolean hasAtLeastOneChild =false;
    	
    	for (HyperEdge child:childElements ){
    		if (child instanceof ObjectHE ){
    			hasAtLeastOneChild=true;
    			if (((ObjectHE) child).hasChildElements()){
    				return false;
    			}
    		}
    	}
    	
		return hasAtLeastOneChild;
	}
	/*
     * znajduje wezly zewnetrzne dla tej HE
     * zakladamy ze ta HE ma dzieci, wiec nie ma sama wezlow
     * wezly zew to takie wezly podpiete do zagniezdzonych w niej objHE, ktore
     * - nie maja podpietych relacji, sa zew dla calego grafu
     * - maja podpieta chociarz jedna relacje nie bedaca dzieckiem bierzacej HE
     */
    private ArrayList<Node> findChildrensExternalNodes() {
    	ArrayList <Node> extHE=new ArrayList<Node>();
    	ArrayList <Node> nodesToCheck=new ArrayList<Node>();
    	
    	for (HyperEdge child:childElements ){
    		if (child instanceof ObjectHE ){
    			nodesToCheck.addAll(((ObjectHE)child).getWallNodes());
    		}
    	}
    	
    	for (Node node: nodesToCheck){
    		if (node.getRelations().size()==0)
    			extHE.add(node);
    		else {
    			for (RelationHE rel:node.getRelations() )
    				if (!this.containsRelation(rel)){
    					extHE.add(node);
    					break;
    				}    			
    		}
    	}
    	
		return extHE;
	}
    
	/*
     * znajduje wezly pionowe dla dzieci typu ObjectHE tej HE
     */
    private ArrayList<Node> findChildrensVerticallNodes() {
    	ArrayList <Node> vertical=new ArrayList<Node>();
    	
    	for (HyperEdge child:childElements ){
    		if (child instanceof ObjectHE ){
    			vertical.add(((ObjectHE)child).getVerticalNode());
    		}
    	}
    	
		return vertical;
	}

	public void addNode(Node n)
    {
        nodes.add(n);
    }

//    public ArrayList <Node> getNodesWithAttr(String property, String value)
//    {
//        ArrayList <Node> ret = new ArrayList<Node>();
//        for(int i = 0; i < nodes.size(); i++)
//            if(((Node)nodes.get(i)).getAttribute(property).equals(value))
//                ret.add((Node)nodes.get(i));
//
//        return ret;
//    }

    public ArrayList <HyperEdge> getChildElements()
    {
        return childElements;
    }

   public  void setChildElements(ArrayList <HyperEdge> childElements)
    {
        this.childElements = childElements;
        for(HyperEdge he :  childElements) 
        {
            if(he instanceof ObjectHE)
                ((ObjectHE)he).setLevel(getLevel() + 1);
        }

    }
   
   public boolean   hasChildElements()
   {
       return childElements.size()>0;
   }

   
   public  void addChildElement(   HyperEdge egde)
   {
       this.childElements.add(egde);

   }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public ArrayList<Node> getAllNodes()
    {
        return nodes;
    }
    
    public ArrayList<Node> getWallNodes()
    {
    	ArrayList<Node> walls= new ArrayList<Node>();
    	for(Node node: nodes){
    		if (!node.isVerticalConnections()){
    			walls.add(node);
    		}
    	}
        return walls;
    }
    
    public Node getVerticalNode()
    {
    	for(Node node: nodes){
    		if (HLH.DIRECTION_VERTICAL.equals(node.getDirection())){
    			return node;
    		}
    	}
        return null;
    }
    
    /*
     * zwraca kierunek i-tej sciany z hiperkrawedzi - N S W E
     */
    public String  getWallDir(int number){
    	return nodes.get(number).getDirection();
    }
    /*
     * zwraca dlugosc i-tej sciany z hiperkrawedzi
     */
    public int  getWallLen(int number){
    	return Integer.parseInt(nodes.get(number).getAttribute(HLH.LEN));
    }
    
    /*
     * zwraca wezel o atrybucie HLH.WALL_NR r�wnym podanemu parametrowi
     */
    public Node  getNodeNumbered(String number){
    	 
          for(int i = 0; i < nodes.size(); i++)
              if((nodes.get(i)).getAttribute(HLH.WALL_NR ).equals( number))
                  return  nodes.get(i);

          return null;
    }
    
    public String toString (){
    	return getAttribute(HLH.USER_LABEL).equals("")?getAttribute(HLH.LABEL):""+getAttribute(HLH.USER_LABEL);
    }
    public String getMapLabel (){
    	return getAttribute(HLH.LABEL);
    }
    

	public void createEmbeddingRelationTo(Node n) {

		//znajdz relacje w ktorych wystepuje ta krawedz
		ObjectHE parent = this.getParentEdge();
		
		if (parent==null) // poziom 1 - nic nie przepinamy
			return;
		Node emb=n.getEmbFuncNode();
		
		ArrayList <RelationHE> embRels= emb.getRelations();
		for (int i = 0; i < embRels.size(); i++) {
			 
			RelationHE rel = embRels.get(i);
			RelationHE newRel;
	//				dla kazdej w ktorej jedna strona jest embNode z podanego wezla dodaj relacje do podanego wezla
			if (rel.getSource()==emb){
				if (! LayoutUtils.nodesHaveCommonLineSegment(n,rel.getTarget()))
					continue;
				newRel = new RelationHE(rel,n,rel.getTarget());
				
			}else { // (rel.getTarget()==emb){
				if (! LayoutUtils.nodesHaveCommonLineSegment(rel.getSource(),n))
					continue;
				newRel = new RelationHE(rel,rel.getSource(),n);
				
			}
			if (n.hasDoors())
				newRel.setAttribute(HLH.KIND, HLH.KIND_ACC);
			else 
				newRel.setAttribute(HLH.KIND, HLH.KIND_ADJ);
//		nowa relacja jest na tym samym poziomie hierarchii co jej poprzedniczka
			rel.getParentEdge().getChildElements().add(newRel);
		} 
	}

	public void removeConnectedRelations() {
		// 	usuwa relacje polaczone z tym obiektem
		if (this.getParentEdge()==null) // poziom 1 - nic nie usuwamy
			return;
		
		for (int i = 0; i < nodes.size(); i++) {
			ArrayList <RelationHE> rels =  nodes.get(i).getRelations();
			ArrayList <RelationHE> toRemove = new ArrayList<RelationHE>();
			for (int j = 0; j < rels.size(); j++) {
				toRemove.add(rels.get(j));
			}
 
			for (int j = 0; j < toRemove.size(); j++) {
				RelationHE removing = toRemove.get(j);
				removing.remove();
			}
			
			
			
		}
		
	}
	public String connectedWith(ObjectHE other){
		
		for(Node node1 : getAllNodes())
			for(Node node2 : other.getAllNodes())
				for (RelationHE rel: node1.getRelations()){
					if (rel.isLinkingNodes(node1, node2))
							return rel.getAttribute(HLH.KIND); 
		}
		return null;
	}

	public boolean hasDoors(DoorsAttributes doors) {
		for(Node n : getWallNodes()){
			if (n.hasDoors(doors))
				return true;
		}
		return false;
	}
	
	void removeRelation(RelationHE relationHE) {
		this.childElements.remove(relationHE);
		
	}
	boolean containsRelation(RelationHE relationHE) {
		return this.childElements.contains(relationHE);
	}

	public void clearNodes() {
		// usuna Nody podpiete do tego obiektu
		// jesli Nody sa w jakies relacji rzuca wyjatek
		
		for (int i = 0; i < this.getAllNodes().size(); i++) {
			Node n = this.getAllNodes().get(i);
			
			if (n.getRelations().size()!=0)
				throw new RuntimeException("proba usuniecia wezla z relacjami!");
			
		}
		
		this.nodes=new ArrayList<Node>();
		
	}
	
	public boolean hasDoors(){
		for (Node n : getWallNodes()) {
			if (n.hasDoors())
				return true;
		
		}
		return false;
	}
	 

	// do celow obliczania sciezki zapis punktow wewnetrznych pokoju
	private int[][] interior;
	public void setInterior(int[][] interior) {
		this.interior = interior;
		
	}
	public int[][] getInterior(  ) {
		return interior;
		
	}
/*
 * czy jestg lisciem w strukturze drzewa - 
 */
	public boolean isRoom(){
		return this.getChildElements().size()==0;
	}
	
}
