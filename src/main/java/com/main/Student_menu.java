package com.main;

import java.util.Scanner;

import com.usermanagement.StudentRegistration;
import com.usermanagement.Studentlogin;

public class Student_menu {
	Scanner scanner;
public Student_menu(Scanner scanner) {
		this.scanner = scanner;
	}
	public void showstudenmenu() throws Exception {
		
			StudentRegistration studentRegistration=new StudentRegistration(scanner);
			Studentlogin studentlogin=new Studentlogin();

			boolean exit= false;
			while (!exit) {
				
				System.out.println("\n=== Student Menu ===");
			    System.out.println("1. Register");
			    System.out.println("2. Login");
			    System.out.println("3. Attempt Quiz");
			    System.out.println("4. View My Score");
			    System.out.println("5. Exit to Main Menu");
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
				case 2:	studentlogin.login();break;
				case 3:	break;
				case 4:break;
				case 5:exit=true;	break;
					

				default:System.out.println("Invalid Choice Try Again");
					break;
				}
				
			}
		
	
		
	}
	
}
