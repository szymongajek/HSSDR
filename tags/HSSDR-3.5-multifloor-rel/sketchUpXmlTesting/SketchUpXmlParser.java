package sketchUpXmlTesting;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.AbstractDOMParser;

public class SketchUpXmlParser extends AbstractDOMParser {

	ArrayList<SUObject> suObjectsList;

	public ArrayList<SUObject> getSuObjectsList() {
		return suObjectsList;
	}

	@Override
	public void parseDocument() {

		suObjectsList = new ArrayList<SUObject>();
		ArrayList<String> columnNames = new ArrayList<String>();

		NodeList nl = dom.getElementsByTagName("element");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				NodeList attributes = el.getElementsByTagName("attr");

				if (i == 0) {
					for (int j = 0; j < attributes.getLength(); j++) {
						Element attribute = (Element) attributes.item(j);
						String attrValue = attribute.getTextContent();
						columnNames.add(attrValue);

					}
				} else if (i!=nl.getLength()-1) { // do not parse last row - it contains totals
					SUObject obj = new SUObject();
					for (int j = 0; j < attributes.getLength(); j++) {
						Element attribute = (Element) attributes.item(j);
						String attrValue = attribute.getTextContent();
						obj.setProperty(columnNames.get(j), attrValue);
					}
					

					suObjectsList.add(obj);
				}

			}
		}

	}

}
