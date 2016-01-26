package controller;

import folf.Parser;
import folf.Result;
import folf.Structure;
import folf.Suite;
import folf.Vocabulary;
import hyperGraphs.HLH;
import hyperGraphs.ObjectHE;
import hyperGraphs.HGSensor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import rectangularBoard.Path;
import sensors.Sensor;
import util.Logger;

public class Controller
{
    private HLH graph;
    private MessageDisplayer messageDisplayer;
    private HyperGraphBrowser HGBrowser;
    private TestChooser testChooser;
  
    public Controller()
    {
    }

    public void initController(MessageDisplayer messageDisplayer, HyperGraphBrowser HGBrowser,TestChooser testChooser)
    {
        this.messageDisplayer = messageDisplayer;
        this.HGBrowser = HGBrowser;
        this.testChooser=testChooser;
    }
    
    public void clearGraph()
    {
        graph = null;
        HGBrowser.clear();
    }
    
    public void initGraph(float gridToMeters, int sensorRange, int floorCount )
    {
    	graph = new HLH(gridToMeters, sensorRange, floorCount) ;
        HGBrowser.setGraph(graph);
        setHGBrowserCurrentFloor(0);
    }
    public void setHGBrowserCurrentFloor(int floorNr){
    	HGBrowser.setCurrentFloor(floorNr);
    }

    public void startOutline(Path rootPath, float gridToMeters, int sensorRange, int floorNr)
    {
        graph.createRootEdge(rootPath.createObjectHE(null,0, floorNr),gridToMeters,sensorRange, floorNr );
        checkLayout();
    }
    
    public void startOutline(Path rootPath, float gridToMeters, int sensorRange )
    {
    	startOutline(  rootPath,   gridToMeters,   sensorRange, 0);
    }

    public void developArea(Path developedPath, ArrayList newObjects, int level)
    {
        ObjectHE developedEdge;
        developedEdge = graph.findObjectHEWithLabel(developedPath.toString());
        if(newObjects.size() != 2)
        {
            throw new RuntimeException(" TODO podzial na wiecej niz dwa ");
        } else
        {
            ObjectHE firstHE = ((Path)newObjects.get(0)).createObjectHE(developedEdge,level + 1, developedEdge.getFloor());
            ObjectHE secondHE = ((Path)newObjects.get(1)).createObjectHE(developedEdge,level + 1, developedEdge.getFloor());
            graph.developEgde(developedEdge, firstHE, secondHE);
            checkLayout();
            return;
        }
    }
    
    public void putDoors(String firstLabel, String firstNodeNr, String secondLabel, String secondNodeNr, int dx1, int dy1, int dx2, int dy2) {
    	 graph.putDoors( firstLabel,  firstNodeNr,  secondLabel,  secondNodeNr,  dx1,  dy1,  dx2,  dy2);
		 checkLayout();
	}
    public void removeDoors( int dx1, int dy1, int dx2, int dy2){
    	graph.removeDoors(dx1, dy1, dx2, dy2) ;
    	checkLayout();
    }

    public void addSensor(Sensor s, Path developedPath) {
    	ObjectHE developedEdge = graph.findObjectHEWithLabel(developedPath.toString());
		graph.addSensor(developedEdge, new HGSensor(s.sx,  s.sy,  s.dx, s.dy));
		checkLayout();
	}
    
    public void setRoomUserLabel(String roomString, String user_label)
    {
    	 graph.setRoomUserLabel( roomString,  user_label);
    	 checkLayout();
    }
    public void setRoomType(String roomString, String user_label)
    {
    	 graph.setRoomType( roomString,  user_label);
    	 checkLayout();
    }
    
    public void deleteDivision(String label) {
    	graph.contentSuppression(label);
		 checkLayout();
	}

    private static final Parser parser = new Parser();
    
	public static Object[] checkLayout(Structure structure, Vocabulary vocabulary,
			String fname) {

		StringBuffer resultMessage = new StringBuffer();
		ArrayList<String> roomsToHighlight = new ArrayList<String>();
		Object[] ret = new Object[2];

		Logger.LOGGER.debug("Processing " + fname + "...");
		resultMessage.append("Result of: \"" + fname + "\":" + "\n");
		
			
		Result[] res={};
		Exception folfException = null;
		try {
			Suite su = parser.suiteFromFile(fname, vocabulary);
			if (su != null) {
				res = su.getCompleteResults(structure, 99);
			}else{
				Logger.LOGGER.error("syntax or file error!");
				resultMessage.append("syntax or file error!  ");
				String msg = parser.getErrorMessages();
				Logger.LOGGER.error(msg);
				resultMessage.append( msg + "\n");
			}
			
		}catch (UnsupportedOperationException e ) {
			folfException=e;
		}catch (IllegalArgumentException e ) {
			folfException=e;
		}catch (NoSuchElementException e ) {
			folfException=e;
		}catch (RuntimeException e ) {
			folfException=e;
		}
		
		if (folfException!=null){
			Logger.LOGGER.error("FOLF exception",folfException);
			resultMessage.append("Error during formula evaluation" + "\n");
			resultMessage.append(folfException.getMessage()+ "\n");
			resultMessage.append(folfException+ "\n");
		}
		
		for (int i = 0; i < res.length; ++i) {
			String msg ="#" + (i + 1) + ": ";
			Logger.LOGGER.debug(msg);
			resultMessage.append(msg);

			msg=res[i].isTrue()?"Success ":"Failure ";
			msg+=res[i].getMessageInfo();
			Logger.LOGGER.debug(msg);
			resultMessage.append(msg+" \n");

			msg=res[i].getQVarsInfo();
			if (msg.length()>0){
				Logger.LOGGER.debug(msg);
				resultMessage.append(msg+" \n");
			}
			
			if (!res[i].isTrue()){ // kolorowanie tylko w przypadku failure
				for (int k = 0;; ++k) {
					Map<String, Object> qvars = res[i].getQVarsState(k);
					if (qvars == null)
						break;
					for (String name : qvars.keySet())
						roomsToHighlight.add(qvars.get(name).toString());
				}
			}
			
		}
		
		ret[0] = resultMessage.toString();
		ret[1] = roomsToHighlight;
		return ret;
		
	}
	
    public void checkLayout()
    {
    	// roboty
        //GraphMessage meesage[] = CheckerPlugin.checkStructure(graph);
        //messageDisplayer.displayMessageAndHighlight(meesage);
        
        // testy
        
        Vocabulary vocabulary;
		Structure structure;
		
		Object [] tab = graph.createStructureAndVocabulary();
		
		structure= (Structure)tab[0];
		vocabulary =(Vocabulary)tab[1];
		
		List<String> testFiles = testChooser.getTestFilesList(); 
		
         
        ArrayList<String> roomsToHighlight = new ArrayList<String>();
        StringBuffer resultMessage = new StringBuffer();
        
        for (String fname : testFiles) {
        	fname=testChooser.getTestFilesDirectory()+"/"+fname;
            
        	Object [] parsingRes =  checkLayout(structure, vocabulary, fname);
        	resultMessage.append((String)parsingRes[0]);
        	roomsToHighlight.addAll((ArrayList<String>)parsingRes[1]);
            
           
        }
        // wyswietlanie struktury po testach - w celu zobaczenia zawartosci Caching*
        Logger.LOGGER.debug(structure.toString());
        
       messageDisplayer.displayMessageAndHighlight(resultMessage.toString(),roomsToHighlight);
    }
    
    public HLH getGraph(){
    	return graph;
    }

   
    public void addMultiFloorRealtion(ArrayList<Path > elements, ArrayList<Point> multiFloorRelHookPointSequence, String relKind ){
    	Logger.LOGGER.debug(" adding multi floor relation of type: "+ relKind+" for:");
    	
    	
    	ObjectHE[] edges= new ObjectHE[elements.size()]; 
    	
    	for (int i = 0; i < elements.size(); i++) {
    		Logger.LOGGER.debug(" edge "+i+": "+ elements.get(i));
    		edges[i] = graph.findObjectHEWithLabel(elements.get(i).toString());
		}
    	
    	graph.addMultiFloorRealtion(edges, multiFloorRelHookPointSequence, relKind);
	}
    
	public void addTwoFloorRealtion(Path upperArea, Path lowerdArea,
			String relationKind, Point startPoint, Point endPoint) {
		Logger.LOGGER.debug(" adding relation for: " + upperArea + " -> "
				+ lowerdArea + " of type:" + relationKind);

		ObjectHE upperEdge = graph.findObjectHEWithLabel(upperArea.toString());
		ObjectHE lowerEdge = graph.findObjectHEWithLabel(lowerdArea.toString());

		graph.addTwoFloorRealtion(upperEdge, lowerEdge, relationKind, startPoint, endPoint);
		checkLayout();
	}


}
