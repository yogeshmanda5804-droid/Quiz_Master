package com.quizmaster.quations;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class QuestionsDisplay {
	
	int id;
	String quation;
	String optA;
	String optB;
	String optC;
	String optD;
	String ans;
	
	public List<QuationClass> getQuations () throws IOException, SQLException {
		
		
		DatabaseConnections db = new DatabaseConnections();
		
		Connection con = db.getDBConnection();
		
		PreparedStatement ps = con.prepareStatement("select * from quations_table");
		
		ResultSet rs = ps.executeQuery();
		
		List <QuationClass> list = new LinkedList <QuationClass>();
		
		QuationClass qc = null;
		
		
		while(rs.next()) {
			
			quation =rs.getString(2);
			optA = rs.getString(3);
			optB = rs.getString(4);
			optC = rs.getString(5);
			optD = rs.getString(6);
			ans =rs.getString(7);
			
			qc = new QuationClass(quation, optA, optB, optC, optD, ans);
			
			list.add(qc);
					
			}
		
		return list;
		
	}
	
	public Set<QuationClass> getRandomQuations () throws IOException, SQLException {
		
        QuestionsDisplay qd = new QuestionsDisplay();
		
		List<QuationClass> list =qd.getQuations();
		
		Set<QuationClass> set = new HashSet<QuationClass>(list);
		
		return set;
	}
	

	
	
	
	
	



	
}
