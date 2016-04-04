package revitXmlTesting.gbXML;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import rectangularBoard.Path;
import revitXmlTesting.Point;
import util.AbstractDOMParser;
import util.Logger;
@Deprecated
public class GBXMLParser extends AbstractDOMParser{

	
	List<Space> spaces = new ArrayList<Space>();
	
	
	public void parseDocument(){

		//get the root elememt
		//Element docEle = dom. getDocumentElement();
		
		Hashtable<Integer,Space> spacesMap = new Hashtable<Integer, Space>();
		
		NodeList nl = dom.getElementsByTagName("Space");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				Element el = (Element)nl.item(i);
				Space s = getSpace(el);
				if (s==null){
					Logger.LOGGER.debug("Uwaga!!Blad parsowania XML dla space id:");
					Logger.LOGGER.debug (Integer.parseInt(el.getAttribute("id")));
					 continue;
				}
				spaces.add(s);
				spacesMap.put(s.getCadId(), s);
			}
		}
		
		 
		
		
		
	}

	private Space getSpace(Element empEl) {
		String id = empEl.getAttribute("id");
		
		NodeList nl = empEl.getElementsByTagName("PlanarGeometry");
		
		Element pg = (Element)nl.item(0);
		
		nl = pg.getElementsByTagName("PolyLoop");
		
		Element ploop = (Element)nl.item(0);
		
		nl = empEl.getElementsByTagName("CartesianPoint");
		ArrayList<Point> planarGeom= new ArrayList<Point>();
		
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				Element el = (Element)nl.item(i);
				NodeList coord = el.getChildNodes();
				String x =coord.item(0).getTextContent();
				String y =coord.item(1).getTextContent();
				String z =coord.item(2).getTextContent();
				double xx = Double.parseDouble(x);
				double yy = Double.parseDouble(y);
				double zz = Double.parseDouble(z);
				
				planarGeom.add(new Point(xx,yy,zz));
			}
		}
		
		Space sp = new Space();
		sp.setPlanarGeom(planarGeom);
		sp.setId(id);
		return sp;
	}

	 
	
}
