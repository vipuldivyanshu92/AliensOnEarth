package com.AliensOnEarth.main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.gnostice.pdfone.PdfDocument;
import com.gnostice.pdfone.PdfWriter;

import menu.Menu;
import menu.MenuCallback;

import javaConsole.JavaConsole;

public class MainConsole {
		
	private static Boolean fContinue = true;
	private static Scanner scanner;
	private static JavaConsole console;
	public static ArrayList<String> values;
	private static Menu menuExport;
	private static Menu mainMenu;
	 
	// this is the main function
	public static void main(String[] args) {

		// values holds the registration rows details list
		values = new ArrayList<String>();
		
		// creating java console
		console = new JavaConsole();
		// creating input scanner 
	    scanner = new Scanner(System.in);
	    
	    // create main menu console
	    createMainMenu();
	   
	    // create Export menu
	    createExportMenu();
	   
		// loop till Continue flag set false
	    while(fContinue)
		{
			console.clear();
			System.out.println("Please choose an option.");
			mainMenu.show();
		}
		
	    // exit system
		System.exit(0);
	    
	}
		
	/**
	 * @brief create main menu
	 */
	private static void createMainMenu() {
		
		 	mainMenu = new Menu(console);
		    
		    mainMenu.add("Start Registeration", new MenuCallback() { public void Invoke() { registerHandler(); } });
		     
		    mainMenu.add("Exit", new MenuCallback() { public void Invoke() { ExitHandler(); } });
		    
	}

	/**
	 * @brief create export menu
	 */
	private static void createExportMenu() {
		 menuExport = new Menu(console);
			
			menuExport.add(Utils.menuExportString[0],new MenuCallback() { public void Invoke() { 
				registerHandler();
			}
			});
			
			menuExport.add(Utils.menuExportString[1],new MenuCallback() { public void Invoke() { 
				exportTxt(values);
			}
			});
			menuExport.add(Utils.menuExportString[2],new MenuCallback() { public void Invoke() { 
				exportPdf(values);
			}
			});
			menuExport.add(Utils.menuExportString[3],new MenuCallback() { public void Invoke() { 
				ExitHandler();
			}
			});
	}

	/**
	 * @brief Handle Menu item1 choice
	 */
	public static void registerHandler(){
			System.out.println("Welcome to the Alien Registeration.\n Entered the asked details as they are asked...\n");
			while(fContinue)
			{
			// put in loof for all the elements

			String row="";
				for(int i=0;i<Utils.entries.length;i++)
				{
					System.out.println(Utils.entries[i]+" :");
					row += scanner.nextLine();
					row +="\t";
				}
				
			values.add(row);
			System.out.println(row);
			
			menuExport.show();
			// show menu to export in txt or pdf
			
//			int escape = scanner.nextInt();
//			if(escape == 0)
//				ExitHandler();
			}
			
	}
	
	/**
	 * @brief Export to text file
	 */
	public static void exportTxt(ArrayList<String> values){
		try{
		PrintWriter writer = new PrintWriter("AlienOnEarth.txt", "UTF-8");
		
		for (int i = 0; i<values.size();i++){

				writer.println(values.get(i));				
			}
		writer.close();
		}
		catch(Exception e){
		}
		mainMenu.show();
	}
	
	/**
	 * @brief Saving to pdf file
	 */
	public static void exportPdf(ArrayList<String> values){
		try{
			//create a writer instance
			PdfWriter writer = PdfWriter.fileWriter("AlienOnEarth.pdf");
			// Create a PdfDocument instance with the PdfWriter
			PdfDocument d = new PdfDocument(writer);
			
			int x=10,y=10;
			
			for (int i =0; i<values.size();i++)
			{
			d.writeText(values.get(i), 
					    x,   // x-coordinate 
					    y);	// y-coordinate
			y+=10;
			}
			// Write document to file
			d.write();
			// Close all I/O streams associated with the PDF writer
			writer.dispose();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		mainMenu.show();
	}	
	
	/**
	 * @brief Handle Menu exit
	 */
	public static void ExitHandler(){
			fContinue = false;
			System.out.println("Thank you for using our registeration Service.\n Press enter to exit");
			scanner.nextLine();
			System.exit(0);
			
	}
	
}
