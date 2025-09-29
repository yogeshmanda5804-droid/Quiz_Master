package com.quizmaster.quations;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnections {
	
	public Connection getDBConnection () throws IOException, SQLException {
		
		String DB_Driver = null;
		String DB_URL = null;
		String DB_Username = null;
		String DB_Password = null;
		Connection con = null;
		
		FileInputStream fis = new FileInputStream("D:\\Vel Java Full Stack\\Class Projects\\QuizeMaster\\config.properties");
		Properties p = new Properties();		
		p.load(fis);
		
		DB_Driver = p.getProperty("drivername");
		DB_URL = p.getProperty("db_url");
		DB_Username = p.getProperty("db_username");
		DB_Password = p.getProperty("db_password");
		
		
		try {
			Class.forName(DB_Driver);
			
			con = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return con;
		
		
	}
	

}
