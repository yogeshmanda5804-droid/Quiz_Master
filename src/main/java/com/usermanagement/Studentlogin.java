package com.usermanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.quizmaster.main.Student_menu;
import com.util.Consolehelper;
import com.util.dbConnection;

public class Studentlogin {
	Scanner scanner = null;
	Connection con;
	String Username;
	String password;
	String name;
	Student_menu studentMenu;

	public void login() throws Exception {

		System.out.println("Enter Username");
		Username = scanner.next();

		System.out.println("Enter Password");
		password = scanner.next();

		if (checkLogin(Username, password)) {
			
			Consolehelper.println(" \u2705 Login successful!");
			System.out.println("Hello " + name);
			studentMenu.setIsloggedin(true);

		} else {
			System.out.println(" Invalid username or password.");
			studentMenu.setIsloggedin(false);

		}

	}

	public Studentlogin(Scanner scanner, Student_menu studentMenu) {
		this.scanner = scanner;
		this.studentMenu = studentMenu;
	}

	public boolean checkLogin(String username, String pass) throws Exception {
		boolean exists = false;

		try {
			con = dbConnection.getdbconnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM student_registration WHERE username = ? AND password = ?;");
			ps.setString(1, username);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.name = rs.getString("firstname");
				exists = true;
			} else {
				exists = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return exists;

	}

}
