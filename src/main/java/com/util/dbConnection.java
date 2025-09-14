package com.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class dbConnection {

	public static Connection getdbconnection() throws IOException {
		String dbUrl = null;
		String dbusername = null;
		String dbpassword = null;
		String drivername = null;
		Properties prop = new Properties();
		try {

			// Added by yogesh to get db creditials and get db connection
			prop.load(dbConnection.class.getClassLoader().getResourceAsStream("config.properties"));

			drivername = prop.getProperty("db.driver");
			dbUrl = prop.getProperty("db.url");
			dbusername = prop.getProperty("db.username");
			dbpassword = prop.getProperty("db.password");

		} catch (Exception e) {

			e.printStackTrace();
		}

		Connection con = null;
		try {
			Class.forName(drivername);
			con = DriverManager.getConnection(dbUrl, dbusername, dbpassword);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return con;

	}

}
