package com.usermanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.dbConnection;

public class StudentRegistration {
	Scanner scanner = null;
	PreparedStatement ps;
	Studentdetails studentdetails;
	String Email;
	Connection con;

	public void getsudentinfo() {

		try {

			System.out.println("Enter first name");
			String firstname = scanner.next();

			System.out.println("Enter Last name");
			String Lastname = scanner.next();
			System.out.println("Enter City");
			String city = scanner.next();
			System.out.println("Enter Email-id ");
			Email = scanner.next();
			while (!validateemail(Email)) {
				System.out.println("Invalid email format");
				System.out.println("re-Enter Email-id ");
				Email = scanner.next();
			}
			;
			while (checkduplicate(Email, "email")) {

				System.out.println("Duplicaate Email ID ");
				System.out.println("re-Enter Email-id ");
				Email = scanner.next();
				while (!validateemail(Email)) {
					System.out.println("Invalid email format");
					System.out.println("re-Enter Email-id ");
					Email = scanner.next();
				}
				;
			}
			;

			System.out.println("Enter Username");
			String Username = scanner.next();

			while (checkduplicate(Username, "username")) {
				System.out.println("Duplicaate Username");
				System.out.println("re-Enter Username");
				Username = scanner.next();
			}
			;

			System.out.println("Enter password");
			String password = scanner.next();
			studentdetails = new Studentdetails(firstname, Lastname, city, Email, Username, password);
			con = dbConnection.getdbconnection();
			ps = con.prepareStatement(
					"insert into student_registration(firstname,lastname,city,email,username,password)values(?,?,?,?,?,?)");
			ps.setString(1, studentdetails.getFirstname());
			ps.setString(2, studentdetails.getLastname());
			ps.setString(3, studentdetails.getCity());
			ps.setString(4, studentdetails.getEmail());
			ps.setString(5, studentdetails.getUsername());
			ps.setString(6, studentdetails.getPassword());
			int result = ps.executeUpdate();
			System.out.println("\"Student Registration successfully!" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public StudentRegistration(Scanner scanner) {
		// super();
		this.scanner = scanner;
	}

	public boolean validateemail(String Email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(Email);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkduplicate(String value, String Field) throws Exception {
		boolean exists = false;

		try {
			con = dbConnection.getdbconnection();
			Statement stmtStatement = con.createStatement();
			ResultSet rs = stmtStatement.executeQuery("select " + Field + " from student_registration;");
			while (rs.next()) {
				if (value.equals(rs.getString(Field))) {
					exists = true;
					break;
				} else {
					exists = false;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return exists;

	}
}
