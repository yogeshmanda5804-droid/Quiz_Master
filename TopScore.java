/*
 * User Story 5.2: View Top Scorer
As a student/admin I want to view the highest scoring student So that I can
identify top performance
Acceptance Criteria:
Query to fetch max score
Display student name(s) with top score
 */
package com.quizmaster.score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.quizmaster.model.ScoreRecord;
import com.quizmaster.model.Student;
import com.quizmaster.util.DatabaseConnection;

public class TopScore {

	public ScoreRecord getTopScore(){
		ScoreRecord scoreRecord = new ScoreRecord();
		Connection con = null;
		try {
			
			con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from score where total_score=(select MAX(total_score) from score");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) { // true, if return false

				scoreRecord.setId(rs.getInt("id"));
				scoreRecord.setStudentId(rs.getInt("student_id"));
				scoreRecord.setTotalScore(rs.getInt("total_score"));
				scoreRecord.setGrade(rs.getString("grade"));
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return scoreRecord;
		
	}


	public void viewTopScore() {
		
		ScoreRecord record=getTopScore();
		Student s=new Student();
		
		System.out.println(s.getFirstName());
	}
}
