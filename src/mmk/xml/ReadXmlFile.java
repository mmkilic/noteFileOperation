package mmk.xml;

import java.io.IOException;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXmlFile {

	public static void main(String[] args) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse("xml//asd.xml");
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Parameter");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				Element param = (Element) node;
				System.out.println(setParameters(param));
			}
		} 
		catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static String setParameters(Element element) throws Exception{
		String result = "";
		String typeOfParameter = element.getAttribute("type").trim().toLowerCase(Locale.ENGLISH);

		if (typeOfParameter.equals("real_number"))
			result = element.getAttribute("name") + " - " + element.getAttribute("type") 
					+ " - " + Double.valueOf(element.getAttribute("value"));
		else if (typeOfParameter.equals("string"))
			result = element.getAttribute("name") + " - " + element.getAttribute("type") 
			+ " - " + element.getAttribute("value");
		else if (typeOfParameter.equals("integer"))
			result = element.getAttribute("name") + " - " + element.getAttribute("type") 
			+ " - " + Integer.valueOf(element.getAttribute("value"));
		else if (typeOfParameter.equals("yes_no"))
			result = element.getAttribute("name") + " - " + element.getAttribute("type") 
			+ " - " + Boolean.valueOf(element.getAttribute("value"));
		
		return result;
	}

}
