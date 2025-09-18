package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 public static Connection getConnection() throws Exception {
	        String url = "jdbc:mysql://localhost:3306/Testingdb";
	        String user = "root";
	        String pass = "root22";
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        return DriverManager.getConnection(url, user, pass);
	    }

}
