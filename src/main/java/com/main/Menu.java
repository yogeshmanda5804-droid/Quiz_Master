package com.main;

import java.util.Scanner;

import com.usermanagement.StudentRegistration;
import com.usermanagement.Studentlogin;

public class Menu {
	static Scanner scanner = new Scanner(System.in);
static boolean exit =false;
	public static void main(String[] args) throws Exception {
		scanner = new Scanner(System.in);
		Student_menu student_menu=new Student_menu(scanner);
		
		while (!exit) {
			System.out.println("Welcome to Quiz Based Application");
			System.out.println("1.Student Menu");
			System.out.println("2.Admin menu");
			System.out.println("3.Exit");
			
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
			case 1:  student_menu.showstudenmenu();
            case 2: break;
            case 3: exit =true ; System.out.println("Thank You!!");break;
			default: System.out.println("Invalid Choice Try Again");break;
			}
		
		}
		
	
		
	}

}
