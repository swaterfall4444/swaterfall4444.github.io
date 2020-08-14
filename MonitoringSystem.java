/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;

import java.io.IOException; 
import java.util.Scanner;



/**
 *
 * @author Shannon Waterfall
 */
public class MonitoringSystem {

    /**
     * @param args 
     * @throws java.io.FileNotFoundException
     */
     

     
     

			
    
    public static void main(String[] args) throws IOException {
        //variables
        Scanner scnr = new Scanner(System.in);
        int userInput1 = 0;
        int userInput2 = 1;
        int userInput3 = 0;
        int userInput4 = 0;
                        
        //Initial output 
        while (userInput1 < 3) {            
            Menus.mainMenu();               //call menu method for main menu
            userInput1 = scnr.nextInt();    //obtain user input for main menu            
            
            if (userInput1 == 1) {         //output and information for animals
                Menus.getMenu(1);
                userInput2 = scnr.nextInt();
                while (userInput2 != 0) {       //0 is return option
                	if (userInput2 == 1) {
                    	Add.addAnimal();
                    	Menus.getMenu(1);
                        userInput2 = scnr.nextInt();
                        }
                	else {
                		getInformation.getInfoAnimal(userInput2);
                		userInput3 = scnr.nextInt();
                		if (userInput3 == 1) {
                			Menus.editMenu(1);
                			userInput4 = scnr.nextInt();
                			
                			if(userInput4 <= 4 && userInput4 > 0) {
                				edit.editAnimal(userInput2, userInput4);
                				}
                			}
                		else {
                			Menus.getMenu(1);
                			userInput2 = scnr.nextInt();
                			}
                		}
                	}
                }
            
            
            if (userInput1 == 2) {         //output and information for animals
            	Menus.getMenu(2);
            	userInput2 = scnr.nextInt();
            	while (userInput2 != 0) {       //0 is return option
            		if (userInput2 == 1) {
            			Add.addHabitat();
            			Menus.getMenu(2);
            			userInput2 = scnr.nextInt();
            			}
            		else {
            			getInformation.getInfoHabitat(userInput2);
            			userInput3 = scnr.nextInt();
            			if (userInput3 == 1) {
            				Menus.editMenu(2);
            				userInput4 = scnr.nextInt();
            				
            				if(userInput4 <= 4 && userInput4 > 0) {
            					edit.editHabitat(userInput2, userInput4);
            					}
            				}
            			else {
            				Menus.getMenu(2);
            				userInput2 = scnr.nextInt();
            				}
            			}
            		}
            	}
            }
        scnr.close();
        }
    }




