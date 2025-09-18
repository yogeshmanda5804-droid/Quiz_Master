package com.quizmaster.main;

import java.util.Scanner;

import com.util.Consolehelper;


public class Admin_menu {
	Scanner scanner;

	public void showsdminnmenu() throws Exception {

		boolean exit = false;
		while (!exit) {

			Consolehelper.println("\n=== Admin Menu ===");
			System.out.println("1. Add New Question");
			System.out.println("2. Edit Question");
			System.out.println("3. Delete Question");
			System.out.println("4. View All Student Scores");
			System.out.println("5. Search Student Score by ID");
			System.out.println("6. View Top Scorer");
			System.out.println("7. Exit to Main Menu");
			System.out.print("Enter your choice: ");
			String input = scanner.next();
			int choice = -1;
			if (input.matches("\\d+")) {
				choice = Integer.parseInt(input);
			} else {
				System.out.println("Invalid input! Please enter a number.");
				continue;
			}

			switch (choice) {
			case 1:
				;
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				
				exit = true;
				break;

			default:
				System.out.println("Invalid Choice Try Again");
				break;
			}

		}

	}

	public Admin_menu(Scanner scanner) {

		this.scanner = scanner;
	}
}
