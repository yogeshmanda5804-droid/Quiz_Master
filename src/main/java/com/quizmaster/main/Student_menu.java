package com.quizmaster.main;

import java.util.Scanner;

import com.usermanagement.StudentRegistration;
import com.usermanagement.Studentlogin;
import com.util.Consolehelper;

public class Student_menu {
	Scanner scanner;
	boolean isloggedin ;
	//String string;
	//public static boolean flag;
	
	public void showstudenmenu() throws Exception {
		
		  Studentlogin studentlogin = new Studentlogin(scanner, this);  // pass 'this' to login
		    StudentRegistration studentRegistration = new StudentRegistration(scanner);

			boolean exit= false;
			while (!exit) {
				
				Consolehelper.println("\n=== Student Menu ===");
			    System.out.println("1. Register");
			    System.out.println("2. Login");
			    System.out.println("3. Attempt Quiz");
			    System.out.println("4. View My Score");
			    System.out.println("5. Display Summery");
			    System.out.println("6. Exit to Main Menu");
			    System.out.print("Enter your choice: ");
				String input =scanner.next();
				int choice = -1;
				if(input.matches("\\d+")) {
					choice= Integer.parseInt(input);
				}
				else {
					System.out.println("Invalid input! Please enter a number.");
		            continue; 
				}  
			
				switch (choice) {
				case 1:	studentRegistration.getsudentinfo();break;
				case 2:	studentlogin.login(); System.out.println(isloggedin); break;
				case 3:	break;
				case 4:break;
				case 5:	break;
				case 6:exit=true;	break;	

				default:System.out.println("Invalid Choice Try Again");
					break;
				}
				
			}
		
	
		
	}
	


	public void setIsloggedin(boolean isloggedin) {
		this.isloggedin = isloggedin;
	}


public Student_menu(Scanner scanner) {
		this.scanner = scanner;
	}




	
}
