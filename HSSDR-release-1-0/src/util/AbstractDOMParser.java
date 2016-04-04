package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import rectangularBoard.Path;

public abstract class AbstractDOMParser {

	public int SCALE = 1;
	public int MAX_COORD =100;
	public int XOFFSET=25;
	public int YOFFSET=25;
	public int ZOFFSET=25;
	
	
	public boolean sym_reflection_y=false;
	public int sym_reflection_y_transl=40;
	
	public Document dom;
	  
	
	public void parseString(String content ){
//		parse the xml and get the dom object
		parseXmlString(content);
		
		//get each element and  create object
		parseDocument();
	}
	
	public void parseFile(String fileName ){
//		parse the xml file and get the dom object
		parseXmlFile(fileName);
		
		//get each element and  create object
		parseDocument();
	}
	
	public void parseFile(File file ){
//		parse the xml file and get the dom object
		parseXmlFile(file);
		
		//get each element and  create object
		parseDocument();
	}
	
	public Path parseFileWithMainOutline(String fileName ){
//		parse the xml file and get the dom object
		parseXmlFile(fileName);
		
		//get each element and  create object
		return parseDocumentWithMainOutline();
	}
	public Path parseFileWithMainOutline(File file){
//		parse the xml file and get the dom object
		parseXmlFile(file);
		
		//get each element and  create object
		return parseDocumentWithMainOutline();
	}
	
	private void parseXmlFile(String fileName){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(fileName );
		}catch(ParserConfigurationException pce) {
			Logger.LOGGER.error("", pce); 
		}catch(SAXException se) {
			Logger.LOGGER.error("", se); 
		}catch(IOException ioe) {
			Logger.LOGGER.error("", ioe); 
		}
	}
	
	private void parseXmlFile(File file){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(file );
		}catch(ParserConfigurationException pce) {
			Logger.LOGGER.error("", pce); 
		}catch(SAXException se) {
			Logger.LOGGER.error("", se); 
		}catch(IOException ioe) {
			Logger.LOGGER.error("", ioe); 
		}
	}
	
	private void parseXmlString(String content){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			dom = db.parse( new ByteArrayInputStream(content.getBytes() ) );
		}catch(ParserConfigurationException pce) {
			Logger.LOGGER.error("", pce); 
		}catch(SAXException se) {
			Logger.LOGGER.error("", se); 
		}catch(IOException ioe) {
			Logger.LOGGER.error("", ioe); 
		}
	}
	
	
	public abstract void parseDocument();
	
	public Path parseDocumentWithMainOutline(){
		return null;
	}
	
	
	public int transfCoordX(float x){
		int ret= Math.round(x*SCALE)+XOFFSET;
		if ( (ret<1)||(ret>=MAX_COORD-1) )throw new RuntimeException("Room coordinates out of bounds:"+ret);
		return ret;
	}
	public int transfCoordY(float x){
		int ret= Math.round(x*SCALE)+YOFFSET;
		if (sym_reflection_y){
			ret*=-1;
			ret+=sym_reflection_y_transl;
		}
		if ( (ret<1)||(ret>=MAX_COORD-1) )throw new RuntimeException("Room coordinates out of bounds:"+ret);
		return ret;
	}
	
	public int transfCoordZ(float x){
		int ret= Math.round(x*SCALE)+ZOFFSET;
		if ( (ret<1)||(ret>=MAX_COORD-1) )throw new RuntimeException("Room coordinates out of bounds:"+ret);
		return ret;
	}
	
	public int transfLen(float x){
		int ret= Math.round(x*SCALE);
		return ret;
	}


}
