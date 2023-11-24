 package mmk.xml.repo;

import java.io.IOException;

public class XmlApp {

	public static void main(String[] args) throws IOException {
		
		saveXml("xml\\asd2.xml");
		readXml("xml\\asd2.xml");
		
	}
	
	private static void saveXml(String file) {
		XmlParameter<String> xp1 = new XmlParameter<>("name", "STRING", "M.KILIC");
		XmlParameter<String> xp2 = new XmlParameter<>("machineType", "STRING", "workstation");
		XmlParameter<Double> xp3 = new XmlParameter<>("length", "REAL_NUMBER", 200.5);
		XmlParameter<Integer> xp4 = new XmlParameter<>("qty", "INTEGER", 5);
		XmlParameter<Boolean> xp5 = new XmlParameter<>("completed", "YES_NO", true);
		
		XmlRepo xmlr = new XmlRepo(file);

		xmlr.addParam(xp1);
		xmlr.addParam(xp2);
		xmlr.addParam(xp3);
		xmlr.addParam(xp4);
		xmlr.addParam(xp5);
		
		System.out.println("done");	
	}
	
	private static void readXml(String file) {
		XmlRepo xmlr = new XmlRepo(file);
		
		xmlr.readParam().stream().forEach(p -> System.out.println(p.toString()));
	}

}
