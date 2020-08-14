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
public class getInformation {
	static BufferedReader inputStream = null;
	static String line = "";
    static int i, n, j;    
	static boolean hasStar = false;

	
    public static void getInfoAnimal(int userInput2) throws IOException{
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
            	
                	//Line is found- Begin output
                	System.out.println("Displaying Details on " + line.substring(9, line.length()));
                	System.out.println();
                       
                       //output following information
                       for (i = 0; i <4; ++i) {
                           line = scnr.nextLine();
                           
                           //determine if dialog box is needed
                           if(line.charAt(0) == '*') {
                               hasStar = true;
                           }
                           //Formatting for Dialog box
                           if (hasStar) {
                               System.out.print("*");
                               for(n = 0; n < line.length()-3; ++n) {	
                                   System.out.print('*');
                               }
                               System.out.println("*");
                               System.out.println("* " + line.substring(5,line.length()) + " *");
                               System.out.print("*");
                               for(n = 0; n < line.length()-3; ++n) {
                                   System.out.print('*');
                               }
                               System.out.println("*");
                               //end having the hasStar = true: this will set the next line at false
                               hasStar = false;
                           }
                           else {
                        	   System.out.println(line);
                        	   }
                           System.out.println();
                           }
                       }
        		  }
        	  }
          }
	finally {
		if (inputStream != null) {
			inputStream.close();
			}
		} 
	System.out.println("Press 1 to edit this animal");
	} 
    
    
    public static void getInfoHabitat(int userInput2) throws IOException {
    	
    	int search = userInput2 - 1;
    	j = 0;
    	//open file to read from
        try {
            inputStream = new BufferedReader(new FileReader("habitats.txt"));
            Scanner scnr = new Scanner(inputStream);
            
            while (scnr.hasNextLine()) {
          	  line = scnr.nextLine();
          	  
          	  if(line.length() >= 10 && line.charAt(0)== 'H') {
          		  j++;
          		  if (j == search) {
                        //line is found - begin output
                        System.out.println("Displaying Details on " + line.substring(10, line.length()));
                        System.out.println();
                        //output relevant information
                        for (i = 0; i < 3; ++i) {
                            line = scnr.nextLine();
                            
                            //determine if dialog box is needed
                            if(line.charAt(0) == '*') {
                                hasStar = true;
                            }
                            
                            //formatting for dialog box
                            if (hasStar) {
                                System.out.print("*");                                
                                for(n = 0; n < line.length()-3; ++n) {	
                                    System.out.print('*');
                                }
                                System.out.println("*");
                                System.out.println("* " + line.substring(5,line.length()) + " *");
                                System.out.print("*");
                                for(n = 0; n < line.length()-3; ++n) {
                                    System.out.print('*');
                                }
                                System.out.println("*");
                                //end having the hasStar = true: this will set the next line at false
                                hasStar = false;
                            }//close if hasStar loop
                            
                            else {
                                System.out.println(line);
                            }
                            System.out.println();
                        }                                        
                    }                                           
                }                                              
            }                                                 
        }                                                    
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        } System.out.println("Press 1 to edit this habitat");
    }
}					


			
			

				

