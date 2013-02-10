package hyperGraphs;


import java.util.Hashtable;
import java.util.Iterator;
import java.util.ArrayList;

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
     * warunek - pod ta HE sa tylko liœcie  
     * alg:
     * 1. znajdz nody EXT dla tej hiperkrawedzi i ozn jako extHE
     * 2. dla wszystkich grup  el extHE rozniacych sie tylko prefiksem przed pierwsza kropka stworz jeden wezel 
     * 3. etypkieta tego wezla to etykieta z ktowych powstal  po pierwszej kropce
     * 4. dla kazdej grupy jesli z wezlow z tej grupy wychodza relacje to zlej je w jedna, idaca do nowego wezla dla grupy
     * 5. usun   child elements
     * 6. dodaj nowo stworzone wezly do tej HE
     */
    public void contentSuppression(){
//    	warunek - pod ta HE sa tylko liœcie  
    	
    	for (HyperEdge child:childElements ){
    		if (child instanceof ObjectHE  && ((ObjectHE)child).getChildElements().size()>0);
//    			throw new RuntimeException("proba usuniecia wnetrza hiperkrawedzi nie bedacej na dole hierarchii");
    	}
    	
//		1. znajdz nody EXT dla tej hiperkrawedzi i ozn jako extHE
    	
    	ArrayList <Node> extHE=findExternalNodes();
    	
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
    		  n.setObjectEdge(this);
    		  n.setAttributesFrom(vect.get(0));
    		  copyDoorsFromGroupTo(vect,n);
    		  n.setAttribute(HLH.LABEL, vect.get(0).getLabelSuffix() );
    		  // polozenie wezla i dlugosc sciany
    		  int len_sum=0;
    		  int middleX=0, middleY=0;
    		  for (Node joiningNode:vect){
    			  len_sum+=Integer.parseInt(joiningNode.getAttribute(HLH.LEN));
    			  middleX+=joiningNode.getMiddleX();
    			  middleY+=joiningNode.getMiddleY();
    		  }
    		  n.setAttribute(HLH.LEN, String.valueOf(len_sum));
    		  n.setMiddleX(middleX/vect.size());
    		  n.setMiddleY(middleY/vect.size());
 
    		  newNodes.add(n);
    	  } 
    	  
//        * 4. dla kazdej grupy jesli z wezlow z tej grupy wychodza relacje to zlej je w jedna, idaca do nowego wezla dla grupy
    	  
    	  for (Node newNode: newNodes){
    		  ArrayList<Node> oldNodes = nodesGroups.get(newNode.getAttribute(HLH.LABEL));
    		  if (oldNodes.get(0).getRelations().size()==0){
    			  // ta grupa wezlow wychodzi na zew calego grafu i nie tworzymy relacji
    			  // sprawdzamy czy tak jest dla wszystkich
    			  for (Node n : oldNodes){
    				  if (n.getRelations().size()!=0)
    					  throw new RuntimeException("w grupie wezlow zlaczanej w jeden sa wezly z rel i bez.");
    			  }
    		  }else{
    			  //  wezly z tej grupy wchodza do jakis relacji
    			  // przy podziale bianrnym drzewa kazdy z nich musi wchodzic do dokl jednej rel
    			  // sprawdzamy czy tak jest dla wszystkich
    			  for (Node n : oldNodes){
    				  if (n.getRelations().size()!=1)
    					  throw new RuntimeException("w grupie wezlow zlaczanej w jeden sa wezly z rel i bez.");
    			  }
    			  // znajdujemy wezel do ktorego wchodza relacje wezlow z tej grupy
    			  RelationHE relation1 =oldNodes.get(0).getRelations().get(0);
    			  Node theNode;
    			  if (relation1.getSource()==oldNodes.get(0))
    				  theNode=relation1.getTarget();
    			  else 
    				  theNode=relation1.getSource();
    			  // sprawdzamy czy dla wszystkich nodow z grupy ich relacje wchodza do znalezionego wezla
    			  for (Node n : oldNodes){
    				  RelationHE rel =n.getRelations().get(0);
    				  if ( !( (rel.getSource()==n && rel.getTarget()==theNode)||
    						  (rel.getSource()==theNode && rel.getTarget()==n) ))
    					  throw new RuntimeException("blad w wezlach zrodlowych i docelowych relacji");
    			  }
    			  //stworz nowa relacje, podepniej od wezlow
//    			 skpoiuj atrybuty i ocjca z wczesniej zapamietanej relaji z pierwszego wezla relation1
    			  RelationHE newRelation = new RelationHE(relation1,newNode,theNode); 
//    			  podepnij nowa relacje do parent edge, na tym poziomi gdzie poprzednia mial parentEdge
//    			  uwaga, parent edge starej relacji nie musi byc rowne this. parentEgde, moze byc gdzies wyzej w strukturze, np. poziom pierwszego podzialu
    			  relation1.getParentEdge().getChildElements().add(newRelation);
    			  
    			  // odepnij relajcje z grupy od ich wezlow i usun 
    			  for (Node n : oldNodes){
    				  RelationHE rel =n.getRelations().get(0);
    				  rel.remove();
    			  }
    		  }
    	  }
    	  
//        * 5. usun   child elements
//    	  ustal nowe polozenie tej hipergrawedzi
    	  int middleX=0,middleY=0, objChildren=0;
    	  for (HyperEdge child:childElements ){
      		if (child instanceof ObjectHE){
      			middleX+=child.getMiddleX();
      			middleY+=child.getMiddleY();
      			objChildren++;
      		}
    	  }
    	  this.setMiddleX(middleX/objChildren);
    	  this.setMiddleY(middleY/objChildren);
    	  
      				
    	 this.childElements= new ArrayList<HyperEdge>();
  
//        * 6. dodaj nowo stworzone wezly do tej HE
    		 
    	this.nodes=newNodes;
    		 
    		 
    }

    private void copyDoorsFromGroupTo(ArrayList<Node> group, Node newNode) {
    	for (Node joiningNode:group){
    		newNode.addAllDoorsFrom(joiningNode);
		  }
		
	}

	/*
     * znajduje wezly zewnetrzne dla tej HE
     * zakladamy ze ta HE ma dzieci, wiec nie ma sama wezlow
     * wezly zew to takie wezly podpiete do zagniezdzonych w niej objHE, ktore
     * - nie maja podpietych relacji, sa zew dla calego grafu
     * - maja podpieta chociarz jedna relacje nie bedaca dzieckiem bierzacej HE
     */
    private ArrayList<Node> findExternalNodes() {
    	ArrayList <Node> extHE=new ArrayList<Node>();
    	ArrayList <Node> nodesToCheck=new ArrayList<Node>();
    	
    	for (HyperEdge child:childElements ){
    		if (child instanceof ObjectHE ){
    			nodesToCheck.addAll(((ObjectHE)child).getNodes());
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

	public void addNode(Node n)
    {
        nodes.add(n);
    }

    public ArrayList <Node> getNodesWithAttr(String property, String value)
    {
        ArrayList <Node> ret = new ArrayList<Node>();
        for(int i = 0; i < nodes.size(); i++)
            if(((Node)nodes.get(i)).getAttribute(property).equals(value))
                ret.add((Node)nodes.get(i));

        return ret;
    }

    public ArrayList <HyperEdge> getChildElements()
    {
        return childElements;
    }

   public  void setChildElements(ArrayList <HyperEdge> childElements)
    {
        this.childElements = childElements;
        for(Iterator iter = childElements.iterator(); iter.hasNext();)
        {
            HyperEdge he = (HyperEdge)iter.next();
            if(he instanceof ObjectHE)
                ((ObjectHE)he).setLevel(getLevel() + 1);
        }

    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public ArrayList<Node> getNodes()
    {
        return nodes;
    }
    
    /*
     * zwraca kierunek i-tej sciany z hiperkrawedzi - N S W E
     */
    public String  getWallDir(int number){
    	return nodes.get(number).getAttribute(HLH.DIRECTION);
    }
    /*
     * zwraca dlugosc i-tej sciany z hiperkrawedzi
     */
    public int  getWallLen(int number){
    	return Integer.parseInt(nodes.get(number).getAttribute(HLH.LEN));
    }
    
    /*
     * zwraca wezel o atrybucie HLH.WALL_NR równym podanemu parametrowi
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
		
		for(Node n : getNodes()){
			for (RelationHE rel: n.getRelations()){
				ObjectHE tested =rel.getConnectionNodeOtherThan(n).getObjectEdge(); 
				if (tested== other)
						return rel.getAttribute(HLH.KIND); 
			}
		}
		return null;
	}

	public boolean hasDoors(DoorsAttributes doors) {
		for(Node n : getNodes()){
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
		
		for (int i = 0; i < this.getNodes().size(); i++) {
			Node n = this.getNodes().get(i);
			
			if (n.getRelations().size()!=0)
				throw new RuntimeException("proba usuniecia wezla z relacjami!");
			
		}
		
		this.nodes=new ArrayList<Node>();
		
	}
	
	public boolean hasDoors(){
		for (Node n : getNodes()) {
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
