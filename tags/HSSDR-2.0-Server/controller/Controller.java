package controller;

import folf.Parser;
import folf.Result;
import folf.Structure;
import folf.Suite;
import folf.Vocabulary;
import hyperGraphs.HLH;
import hyperGraphs.ObjectHE;
import hyperGraphs.HGSensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rectangularBoard.Path;
import sensors.Sensor;

public class Controller
{
    private HLH graph;
    private MessageDisplayer messageDisplayer;
    private HyperGraphBrowser HGBrowser;
    private TestChooser testChooser;
  
    public Controller()
    {
        graph = new HLH();
    }

    public void init(MessageDisplayer messageDisplayer, HyperGraphBrowser HGBrowser,TestChooser testChooser)
    {
        this.messageDisplayer = messageDisplayer;
        this.HGBrowser = HGBrowser;
        this.testChooser=testChooser;
    }

    public void startGraph(Path rootPath, float gridToMeters, int sensorRange)
    {
    	
        graph.init(rootPath.createObjectHE(null,0),gridToMeters,sensorRange );
        HGBrowser.setGraph(graph);
        checkLayout();
    }

    public void developArea(Path developedPath, ArrayList newObjects, int level)
    {
        ObjectHE developedEdge;
        if(graph.getRootEdge().getAttribute(HLH.LABEL).equals(developedPath.toString()))
            developedEdge = graph.getRootEdge();
        else
            developedEdge = graph.findObjectHEWithLabel(developedPath.toString());
        if(newObjects.size() != 2)
        {
            throw new RuntimeException(" TODO podzial na wiecej niz dwa ");
        } else
        {
            ObjectHE firstHE = ((Path)newObjects.get(0)).createObjectHE(developedEdge,level + 1);
            ObjectHE secondHE = ((Path)newObjects.get(1)).createObjectHE(developedEdge,level + 1);
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
    
    public void clearGraph()
    {
        graph = new HLH();
        HGBrowser.clear();
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

		System.out.println("Processing " + fname + "...");
		resultMessage.append("Result of: \"" + fname + "\":" + "\n");
		Suite su = parser.suiteFromFile(fname, vocabulary);
		if (su == null) {
			System.out.println("syntax or file error!");
			resultMessage.append("syntax or file error!  ");
			String msg = parser.getErrorMessages();
			if (msg != null) {
				System.out.print(msg);
				resultMessage.append( msg + "\n");
			}
			System.out.println();
			ret[0] = resultMessage.toString();
			ret[1] = roomsToHighlight;
			return ret;
		}
		Result[] res = su.getCompleteResults(structure, 99);
		for (int i = 0; i < res.length; ++i) {
			System.out.print("#" + (i + 1) + ": ");
			resultMessage.append( "#" + (i + 1) + ": ");
			res[i].printResult();
			resultMessage.append(res[i].getResult());

			for (int k = 0;; ++k) {
				Map<String, Object> qvars = res[i].getQVarsState(k);
				if (qvars == null)
					break;
				for (String name : qvars.keySet())
					roomsToHighlight.add(qvars.get(name).toString());
			}
		}
		System.out.println();
		resultMessage.append("\n");

		ret[0] = resultMessage.toString();
		ret[1] = roomsToHighlight;
		return ret;
	}
	
    private void checkLayout()
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
		
		System.out.println(structure.toString());
		
		List<String> testFiles = testChooser.getTestFilesList(); 
		
         
        ArrayList<String> roomsToHighlight = new ArrayList<String>();
        StringBuffer resultMessage = new StringBuffer();
        
        for (String fname : testFiles) {
        	fname=testChooser.getTestFilesDirectory()+"/"+fname;
            
        	Object [] parsingRes =  checkLayout(structure, vocabulary, fname);
        	resultMessage.append((String)parsingRes[0]);
        	roomsToHighlight.addAll((ArrayList<String>)parsingRes[1]);
            
           
        }
       messageDisplayer.displayMessageAndHighlight(resultMessage.toString(),roomsToHighlight);
    }
    
    public ObjectHE getGraph(){
    	return graph.getRootEdge();
    }

	

	

}
