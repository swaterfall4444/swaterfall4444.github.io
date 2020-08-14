package monitoringsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class edit {
	static BufferedReader inputStream = null;
	static int j, i, answer;
	static String oldInfo, newInfo, line, newInput;
	static Scanner input = new Scanner(System.in);
	
	
	//Get previous value for the animal's information that is being updated
	public static String oldAnimal(int userInput2, int userInput3) throws IOException {
		int search = userInput2 - 1;
		j = 0;
		//open file to read from
		try {
			inputStream = new BufferedReader(new FileReader("animals.txt"));
			Scanner scnr = new Scanner(inputStream);
        
			while (scnr.hasNextLine()) {
				line = scnr.nextLine();
      	  
				if(line.length() >= 10 && line.charAt(0)== 'A') {
					j++;
					if (j == search) {
                    
						//output relevant information
						for (i = 0; i <= 3; ++i) {
							line = scnr.nextLine();
                        
							if (i == userInput3) {
								 
									 oldInfo = line;
							}
							}
						}
                    
                    
					}

				}
			
		} catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
		}
		return oldInfo;
		}
	
	
	//Get previous value for the habitat's information that is being updated
	public static String oldHabitat(int userInput2, int userInput3) throws IOException {
		int search = userInput2-1;
		j = 0;
		//open file to read from
		try {
			inputStream = new BufferedReader(new FileReader("habitats.txt"));
			Scanner scnr = new Scanner(inputStream);
        
			while (scnr.hasNextLine()) {
				line = scnr.nextLine();
      	  
				if(line.length() >= 5 && line.charAt(0)== 'H') {
					j++;
					if (j == search) {
                    
						//output relevant information
						for (i = 0; i <= 3; ++i) {
							if (i != userInput3) {
								line = scnr.nextLine();
							}else {
								
									 oldInfo = line;
									 }
							}
						}
					}
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
				}
		return oldInfo;
		}
	
	
	public static void editAnimal(int userInput2, int userInput4) throws IOException {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("animals.txt"));
			String oldContent = "";
			String newContent = "";
			
			line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
				}
			
			if (userInput4 == 1) {
				oldInfo = edit.oldAnimal(userInput2, 0);
				System.out.println(oldInfo);
				System.out.println("What is the animal's new name? \n");
				newInput = input.nextLine();
				newInfo = "Name: " + newInput;
				}
			
			else if (userInput4 == 2) {
				oldInfo = edit.oldAnimal(userInput2, 1);
				System.out.println(oldInfo);
				System.out.println("What is the animal's new age? \n");
				newInput = Integer.toString(input.nextInt());
				newInfo = "Age: " + newInput;
				}
			
			else if (userInput4 == 3) {	
				oldInfo = edit.oldAnimal(userInput2, 2);
				System.out.println(oldInfo);
				System.out.println("Are there any new health concerns? \n");
				System.out.println("1. Yes");
				System.out.println("2. No");
				
				answer = input.nextInt();
				if (answer == 1) {
					input.nextLine();
					System.out.println("Please enter all health concerns including pre-existing concerns: \n");
					newInput = input.nextLine();
					newInfo = "*****Health concerns: " + newInput;
				}
				else if (answer == 2) {
					input.nextLine();
					newInfo = "Health concerns: None";
				}
			}
			
			else if (userInput4 == 4){					
					oldInfo = edit.oldAnimal(userInput2, 3);
					System.out.println(oldInfo);
					System.out.println("Is there a new feeding schedule for this animal? Select no if there is not a feeding schedule at this time. \n");
					System.out.println("1. Yes");
					System.out.println("2. No");
					
					answer = input.nextInt();
					if (answer == 1) {
						input.nextLine();
						System.out.println("Please enter the feeding schedule: \n");
						newInput = input.nextLine();
						newInfo = "Feeding schedule: " + newInput;
					}
					else if (answer == 2) {
						input.nextLine();
						newInfo = "*****Feeding schedule: None on Record";
					}
				}
			
			//replace the old information with the new information and write the new information to the text file
			newContent = oldContent.replace(oldInfo, newInfo);
			FileWriter writer = new FileWriter("animals.txt");
			writer.write(newContent);
			writer.close();
			reader.close();
			
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			}
		}
	
	
	public static void editHabitat(int userInput2, int userInput4) throws IOException {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("habitats.txt"));
			String oldContent = "";
			String newContent = "";
			
			line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
				}
			
			if (userInput4 == 1) {				
			oldInfo = edit.oldHabitat(userInput2, 0);
			System.out.println(oldInfo);
			System.out.println("What animals live in this habitat? \n");
			newInput = input.nextLine();
			newInfo = "Habitat - " + newInput;
			}
			
			
			else if (userInput4 == 2) {				
				oldInfo = edit.oldHabitat(userInput2, 1);
				System.out.println(oldInfo);
				System.out.println("What is the Temperature in the habitat? \n");
				newInput = input.nextLine();
				newInfo = "Temperature: " + newInput;				
				}
			
			else if (userInput4 == 3) {				
				oldInfo = edit.oldHabitat(userInput2, 2);
				System.out.println(oldInfo);
				System.out.println("Are there any new Feeding Source concerns? \n");
				System.out.println("1. Yes");
				System.out.println("2. No");
				
				answer = input.nextInt();
				if (answer == 1) {
					input.nextLine();
					System.out.println("Please enter Food source concerns: \n");
					newInput = input.nextLine();
					newInfo = "*****Food source: " + newInput;
				}
				else if (answer == 2) {
					input.nextLine();
					System.out.println("Please enter the food source: ");
					newInput = input.nextLine();
					newInfo = "Food source: " + newInput;
				}
			}
			
			else if (userInput4 == 4){					
					oldInfo = edit.oldHabitat(userInput2, 3);
					System.out.println(oldInfo);
					System.out.println("Are there any cleanliness issues? \n");
					System.out.println("1. Yes");
					System.out.println("2. No");
					
					answer = input.nextInt();
					if (answer == 1) {
						input.nextLine();
						System.out.println("Please enter the cleanliness issues:  \n");
						newInput = input.nextLine();
						newInfo = "*****Cleanliness: " + newInput;
					}
					else if (answer == 2) {
						input.nextLine();
						newInfo = "Cleanliness: Passed";
					}
				}
			
			newContent = oldContent.replace(oldInfo, newInfo);
			FileWriter writer = new FileWriter("habitats.txt");
			writer.write(newContent);
			writer.close();
			reader.close();
			
		}
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      }
		}
	}
	
	
	