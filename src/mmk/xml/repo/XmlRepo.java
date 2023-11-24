package mmk.xml.repo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlRepo {
	private File xmlFile;
	private Document doc;
	private Element rootElement;
	private Transformer transformer;
	private StreamResult sResult;
	
	public XmlRepo(String urlFileName) {
		xmlFile = new File(urlFileName);
		
		if (xmlFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(xmlFile);
				rootElement = doc.getDocumentElement();
				
				TransformerFactory tFactory = TransformerFactory.newInstance();
				transformer = tFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				
				sResult = new StreamResult(xmlFile);
			}
			catch (ParserConfigurationException pce) {
				System.out.println(pce.getMessage());
			}
			catch (TransformerConfigurationException tce) {
				System.out.println(tce.getMessage());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.newDocument();
				rootElement = doc.createElement("Parameters");
				doc.appendChild(rootElement);
				
				TransformerFactory tFactory = TransformerFactory.newInstance();
				transformer = tFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        //transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
		        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
				
				sResult = new StreamResult(xmlFile);
				DOMSource source = new DOMSource(doc);
				
				transformer.transform(source, sResult);
			}
			catch (ParserConfigurationException pce) {
				//...
			}
			catch (TransformerConfigurationException tce) {
				//...
			}
			catch (Exception e) {
				//...
			}
		}
	}
	
	public boolean addParam(XmlParameter<?> parameter) {
		try {
			
			Element childElement = doc.createElement("Parameter");
			rootElement.appendChild(childElement);
			
			Attr attrName = doc.createAttribute("name");
			attrName.setValue(parameter.getName());
			childElement.setAttributeNode(attrName);
			
			Attr attrType = doc.createAttribute("type");
			attrType.setValue(parameter.getType());
			childElement.setAttributeNode(attrType);
			
			Attr attrValue = doc.createAttribute("value");
			attrValue.setValue(parameter.getValeu().toString());
			childElement.setAttributeNode(attrValue);
			
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, sResult);
			
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public boolean changeParam(XmlParameter<?> parameter) {
		try {
			NodeList nList = doc.getElementsByTagName("Parameter");
			
			for (int i = 0; i <nList.getLength(); i++) {
				Element e = (Element)nList.item(i);
				if (e.getAttribute("name").equalsIgnoreCase(parameter.getName()) &&
						e.getAttribute("type").equalsIgnoreCase(parameter.getType())) {
					
					e.setAttribute("value", parameter.getValeu().toString());
					DOMSource source = new DOMSource(doc);
					transformer.transform(source, sResult);
					return true;
				}
			}		
			return false;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public List<XmlParameter<?>> readParam(){
		List<XmlParameter<?>> parameters = new ArrayList<>();
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Parameter");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			Element param = (Element) node;
			
			try {
				parameters.add(getParameter(param));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return parameters;
	}
	
	private XmlParameter<?> getParameter(Element element) throws Exception{
		XmlParameter<?> parameter = null;
		String typeOfParameter = element.getAttribute("type").trim().toLowerCase(Locale.ENGLISH);

		if (typeOfParameter.equals("real_number"))
			parameter = new XmlParameter<Double>(element.getAttribute("name"),
					element.getAttribute("type"), Double.valueOf(element.getAttribute("value")));
		else if (typeOfParameter.equals("string"))
			parameter = new XmlParameter<String>(element.getAttribute("name"),
					element.getAttribute("type"), (element.getAttribute("value")));
		else if (typeOfParameter.equals("integer"))
			parameter = new XmlParameter<Integer>(element.getAttribute("name"),
					element.getAttribute("type"), Integer.valueOf(element.getAttribute("value")));
		else if (typeOfParameter.equals("yes_no"))
			parameter = new XmlParameter<Boolean>(element.getAttribute("name"),
					element.getAttribute("type"), Boolean.valueOf(element.getAttribute("value")));
		
		return parameter;
	}
	
}
