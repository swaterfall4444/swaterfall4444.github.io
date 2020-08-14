/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Shannon
 */
public class Menus {
   
     public static void mainMenu() {
        System.out.println("Make a selection");
        System.out.println("1. Animals");
        System.out.println("2. Habitats");
        System.out.println("3. Exit");
        System.out.println();
        }
     
     public static void getMenu(int userInput1) throws IOException{
    	 //initialize variables
    	 BufferedReader inputStream = null;
    	 String line = "";
    	 int i = 0;
    	 //determine which file to read from
    	 if (userInput1 == 1) {
    		 System.out.println("Select an animal");
    		 System.out.println("(Press zero to return to the main menu)");
    		 //open file to read from
    		 try {
    			 inputStream = new BufferedReader(new FileReader("animals.txt"));
    			 Scanner scnr = new Scanner(inputStream);
    			 line = scnr.nextLine();
    			 i = 2;
    			 System.out.println("1. Add a New Animal");
    			 //obtain menu Options
    			 while (scnr.hasNextLine()) {
    				 if (line == null) {
    					 scnr.nextLine();
    					 }
    				 if (line.length() > 3 && line.charAt(0) == 'D') {
    					 System.out.println(i + ". " + line.substring(11, line.length()));
    					 ++i;
    					 }
    				 line = scnr.nextLine();
    				 }
    			 scnr.close();
    			 } 
    		 finally {
    			 if (inputStream !=null) {
    				 inputStream.close();
    				 }
    			 }
    		 }
    	 
    	 else if (userInput1 == 2) {
    		 System.out.println("Select a habitat");
    		 System.out.println("(Press zero to return to the main menu)");
    		 //open file to read from
    		 try {
    			 inputStream = new BufferedReader(new FileReader("habitats.txt"));
    			 Scanner scnr = new Scanner(inputStream).useDelimiter("D");
    			 line = scnr.nextLine();
    			 i = 2;
    			 System.out.println("1. Add a New Habitat");
    			 while (scnr.hasNextLine()) {
    				 if (line.length() > 3 && line.charAt(0) == 'D') {
    					 System.out.println(i + ". " + line.substring(11, line.length()));
    					 ++i;
    					 }
    				 line = scnr.nextLine();
    				 }
    			 scnr.close();
    			 }
    		 finally {                
    			 if (inputStream !=null) {
    				 inputStream.close();
    				 }
    			 }
    		 }
    	 }
    
    public static void editMenu(int userInput2) {
    	
    	if (userInput2 == 1) {
    		System.out.println("1. Edit Name");
    		System.out.println("2. Edit Age");
    		System.out.println("3. Edit Health concerns");
    		System.out.println("4. Edit Feeding Schedule");
    		}
    	
    	else if (userInput2 == 2) {
    		System.out.println("1. Edit Habitat animals");
    		System.out.println("2. Edit the Habitat Temperature");
    		System.out.println("3. Edit the Habitat Food source");
    		System.out.println("4. Edit the Habitat Cleanliness");
    		}
    	}
    }
