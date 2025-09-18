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
import java.util.HashMap;
import java.util.Map;
import com.quizmaster.model.ScoreRecord;
import com.quizmaster.model.Student;
import com.quizmaster.util.DatabaseConnection;

public class TopScore {

	public Map<Student,ScoreRecord> getTopScore(){
		ScoreRecord scoreRecord = new ScoreRecord();
		Student student=new Student();
		Map<Student, ScoreRecord> map=new HashMap<Student, ScoreRecord>();
		Connection con = null;
		try {
			
			con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from score,student_registration where total_score=(select MAX(total_score) from score) and student_registration.id=score.student_id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) { // true, if return false

				scoreRecord.setId(rs.getInt("id"));
				scoreRecord.setStudentId(rs.getInt("student_id"));
				scoreRecord.setTotalScore(rs.getInt("total_score"));
				scoreRecord.setGrade(rs.getString("grade"));
				student.setId(rs.getInt("id"));
				student.setFirstName(rs.getString("firstname"));
				student.setLastName(rs.getString("lastname"));
				map.put(student, scoreRecord);
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return map;
		
	}


	public void viewTopScore() {
		
		 Map<Student,ScoreRecord> topScore=getTopScore();
		 
		 for (Map.Entry<Student, ScoreRecord> entry : topScore.entrySet()) {
			Student key = entry.getKey();
			ScoreRecord val = entry.getValue();
			
			System.out.println("Name of the topper Student: "+key.getFirstName()+" "+key.getLastName()+"\nScore: "+val.getTotalScore()+"\nGrade: "+val.getGrade());
			
		}
		 
	}
}
