package mmk.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXmlFile {

	public static void main(String[] args) {
		
		File xmlFile = new File("xml\\" + "asd.xml");
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("Parameters");
			doc.appendChild(rootElement);
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			StreamResult sResult = new StreamResult(xmlFile);

			Element childElement = doc.createElement("Parameter");
			rootElement.appendChild(childElement);
			
			Attr attrName = doc.createAttribute("name");
			attrName.setValue("id");
			childElement.setAttributeNode(attrName);
			
			Attr attrType = doc.createAttribute("type");
			attrType.setValue("string");
			childElement.setAttributeNode(attrType);
			
			Attr attrValue = doc.createAttribute("value");
			attrValue.setValue("00001");
			childElement.setAttributeNode(attrValue);
			
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, sResult);
		}
		catch (ParserConfigurationException | TransformerException pce) {
			System.out.println("error: " + pce.getMessage());
		}
		
		System.out.println("Completed.");
		
	}

}
