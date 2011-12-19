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

	
    private void checkLayout()
    {
    	// roboty
        //GraphMessage meesage[] = CheckerPlugin.checkStructure(graph);
        //messageDisplayer.displayMessageAndHighlight(meesage);
        
        // testy
        
        Vocabulary voc;
		Structure rs;
		
		Object [] tab = graph.createStructureAndVocabulary();
		
		rs= (Structure)tab[0];
		voc =(Vocabulary)tab[1];
		
		System.out.println(rs.toString());
		
		ArrayList<String> testFiles = testChooser.getTestFilesList(); 
		
        Parser p = new Parser();
        String message="";
        ArrayList<String> roomsToHighlight = new ArrayList<String>();
        for (String fname : testFiles) {
        	fname=TestChooser.TEST_DIR+"/"+fname;
            System.out.println("Processing " + fname + "...");
            message+="Result of: \""+ fname +"\":"+"\n";
            Suite su = p.suiteFromFile(fname, voc);
            if (su == null) {
                System.out.println("syntax or file error!");
                message+="syntax or file error!  ";
                String msg = p.getErrorMessages();
                if (msg != null){
                	 System.out.print(msg);
                	 message+=msg+"\n";
                }
                System.out.println();
                continue;
            }
            Result[] res = su.getCompleteResults(rs, 99);
            for (int i = 0; i < res.length; ++i) {
                System.out.print("#" + (i+1) + ": ");
                message+="#" + (i+1) + ": ";
                res[i].printResult();
                message+=res[i].getResult();
                
                for (int k = 0; ; ++k) {
                    Map<String, Object> qvars =  res[i].getQVarsState(k);
                    if (qvars == null)
                        break;
                    for (String name : qvars.keySet())
                    	roomsToHighlight.add( qvars.get(name).toString());
                }
            }
            System.out.println();
            message+="\n";
        }
       messageDisplayer.displayMessageAndHighlight(message,roomsToHighlight);
        
    }
    
    public ObjectHE getGraph(){
    	return graph.getRootEdge();
    }

	

	

}
