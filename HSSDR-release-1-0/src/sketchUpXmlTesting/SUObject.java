package sketchUpXmlTesting;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Properties;

import util.Logger;

public class SUObject {
	
	public final String AREA = "AREA";
	public final String NAME = "DEFINITION NAME";
	
	public final String TYPE = "TYPE";
	public final String LENX = "LENX";
	public final String LENY = "LENY";
	
	Properties props = new Properties();
	
	
	public void setProperty (String key, String value){
		props.setProperty(key, value);
	}
	
	public String getStringProperty (String key){
		return props.getProperty(key);
	}
	
	public int getIntProperty (String key){
		return Integer.parseInt(props.getProperty(key));
	}
	
	public String toString(){
		String ret;
//		ret= props.toString();
		 ret=getName();
		
		return ret;
	}
	
	public String getName(){
		return props.getProperty("ROOMNAME");
	}
	public String getArea() {
		Logger.LOGGER.debug("area: "+Double.valueOf(props.getProperty("AREA")));
		return props.getProperty("AREA");
	}

	public String getType() {
		return props.getProperty("TYPE");
	}

	public String getLenX() {
		return props.getProperty("LENX");
	}

	public String getLenY() {
		return props.getProperty("LENY");

	}
	public boolean isRoom(){
		return getType().length()>0;
	}
	
	
}
