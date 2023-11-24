package mmk.file;

import java.io.File;
import java.util.HashMap;

public class ReadFiles {

	public static void main(String[] args) {
		File folder = new File("files");
		File[] listOfFiles = folder.listFiles();
		
		HashMap<String, File> files = new HashMap<>();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	files.put(file.getName(), file);
		        System.out.println(file.getName());
		    }
		}
		
		System.out.println("---------------------------------------");
		System.out.println("HasMap: " + files.size());
		System.out.println("---------------------------------------");
		
		String[] list = folder.list();
		for (String file : list) {
		    if (file.contains(".xml")) {
		        System.out.println(file);
		    }
		}
	}

}
