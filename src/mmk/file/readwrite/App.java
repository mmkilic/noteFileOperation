package mmk.file.readwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
	
	public static void main(String[] args) throws IOException {
		
		List<Person> people = readFile("readwrite/people.txt");
		
		writeFile(people, "readwrite/peopleOut.txt");
		writeFile(new Person("Meryem", 32, 172, 58), "readwrite/peopleOut.txt");
	}
	
	private static List<Person> readFile(String file) throws IOException{
		List<Person> people = new ArrayList<Person>();
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
		String line = reader.readLine();
		
		while (line != null) {
			String[] lineItems = line.split(":");
			
			Person p = new Person(lineItems[0], 
								Integer.valueOf(lineItems[1]), 
								Integer.valueOf(lineItems[2]), 
								Integer.valueOf(lineItems[3]));
			people.add(p);
			
			line = reader.readLine();
		}
		reader.close();
		
		return people;
	}
	
	private static void writeFile(List<Person> people, String file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));){
			
			for (Person person : people) {
				writer.write(person.personInfo() + "\n");
			}
			writer.flush();
		}
		catch (Exception e) {
			System.out.println("Error: Writing");
		}
		
		System.out.println("All Writing is completed");
	}
	
	private static void writeFile(Person person, String file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file), true))){
			writer.append(person.personInfo() + "\n");
			writer.flush();
		}
		catch (Exception e) {
			System.out.println("Error: Writing");
		}
		
		System.out.println(person.getName() + " has been written.");
	}
}
