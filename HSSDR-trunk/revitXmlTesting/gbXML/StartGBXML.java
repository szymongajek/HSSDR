package revitXmlTesting.gbXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import folf.Parser;
import folf.Result;
import folf.Suite;
@Deprecated
public class StartGBXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		String input_file="e:\\Project2.xml";
		
		
		//wczytaj dane z pliku
		GBXMLParser parser = new GBXMLParser();
		parser.parseFile(input_file);
		
		List<Space> spaces= parser.spaces;
		for (Space sp : spaces) {
			System.out.println(sp);
		}
	 
		
	}

}
