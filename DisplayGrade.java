/*
 * User Story 5.1: Display Grade with Message

As a student I want to see a message based on my score So that I can get
feedback on my performance
Acceptance Criteria:
If score ≥ 8: Grade A – "Excellent"
If score ≥ 5: Grade B – "Good"
If score < 5: Grade C – "Needs Improvement"
 */
package com.quizmaster.score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.quizmaster.model.ScoreRecord;
import com.quizmaster.util.DatabaseConnection;

public class DisplayGrade {

	public static ScoreRecord getScoreByStudentId(int studentId) {
		
		ScoreRecord scoreRecord = new ScoreRecord();
		Connection con = null;
		try {
			
			con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from score where student_id=?");
			ps.setInt(1, studentId);
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

	public static void displayGrade() {

		System.out.println("Enter Student ID: ");
		Scanner scanner = new Scanner(System.in);
		int StudID = scanner.nextInt();

		ScoreRecord scoreRecord = getScoreByStudentId(StudID);
		int score = scoreRecord.getTotalScore();

		computeGrade(score);

		scanner.close();
	}

	private static void computeGrade(int score) {
		System.out.println("Your are score is:" + score);
		if (score >= 8) {
			System.out.println("Grade A – \"Excellent\"");
			;
		} else if (score >= 5) {
			System.out.println("Grade B – \"Good\"");
		} else {
			System.out.println("Grade C – \"Needs Improvement\"");
		}
	}

}
