package monitoringsystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Add {
	static Scanner scnr = new Scanner(System.in);
	static String animal, name, age, health, feeding;
	static String habitat, temperature, food, cleanliness;
	static int answer;
	
	
	//Add new animal to the end of the Animals.txt file
	public static void addAnimal() throws IOException{
		try {
			FileWriter writer = new FileWriter("animals.txt", true);
			System.out.println("What kind of Animal are you adding? ");
			animal = scnr.nextLine();
			writer.append("Details on " + animal + "\n");
			writer.append("Animal - " + animal + "\n");
			
			System.out.println("What is the animal's name? " );
			name = scnr.nextLine();
			writer.append("Name: " + name + "\n");
			
			System.out.println("How old is the animal? " );
			age = Integer.toString(scnr.nextInt());
			writer.append("Age: " + age + "\n");
			
			System.out.println("Are there any health concerns for " + name + "?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			
			answer = scnr.nextInt();
			if (answer ==2) {
				writer.append("Health concerns: None \n");
				}
			else {
				System.out.println("Please enter the health concerns: ");
				scnr.nextLine();
				health = scnr.nextLine();
				writer.append("*****Health Concerns: " + health + "\n");
				}
					
			System.out.println("Is there a feeding schedule for " + name + "?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			
			answer = scnr.nextInt();
			if (answer ==2) {
				writer.append("*****Feeding schedule: None on record \n");
				}
			else {
				System.out.println("Please enter the feeding schedule: ");
				scnr.nextLine();
				feeding = scnr.nextLine();
				writer.append("Feeding schedule: " + feeding + "\n");
				}
			writer.append("\n");
				
			writer.close();
			}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			}
		} 
	
	//Add a new habitat to the end of the habitats.txt file
	public static void addHabitat() throws IOException{
		try {
			FileWriter writer = new FileWriter("habitats.txt", true);
			System.out.println("What kind of Habitat are you adding? ");
			habitat = scnr.nextLine();
			writer.append("Details on " + habitat + "\n");
						
			System.out.println("What kind of animals live in the " + habitat  + "?" );
			name = scnr.nextLine();
			writer.append("Habitat - " + name + "\n");
			
			System.out.println("What is the temperature of the habitat?" );
			temperature = scnr.nextLine();
			writer.append("Temperature: " + temperature + "\n");
			
			System.out.println("Are there any issues with the food source?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			
			answer = scnr.nextInt();
			if (answer ==2) {
				System.out.println("Please enter the food source: ");
				scnr.nextLine();
				food = scnr.nextLine();
				writer.append("Food source:" + food +"\n");
				}
			else {
				System.out.println("Please enter the issues with the food source: ");
				scnr.nextLine();
				health = scnr.nextLine();
				writer.append("*****Food source: " + food + "\n");
				}
			
			
			System.out.println("Is there any issue with the cleanliness for the " + habitat + "?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			answer = scnr.nextInt();
			if (answer ==2) {
				writer.append("Cleanliness: Passed \n");
				}
			else {
				System.out.println("Please enter what needs to be cleaned: ");
				scnr.nextLine();
				cleanliness = scnr.nextLine();
				writer.append("*****Cleanliness: " + cleanliness + "\n");
				}
			writer.append("\n");
				
			writer.close();
			
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		
		}scnr.close();
	}
	
}
