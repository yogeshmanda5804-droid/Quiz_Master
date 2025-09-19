package com.quizmaster.model;

public class ScoreRecord {
    private int id;
    private int studentId;
    private int totalScore;
    private String grade;

    public ScoreRecord() {}

    

    public ScoreRecord(int id, int studentId, int totalScore, String grade) {
		this.id = id;
		this.studentId = studentId;
		this.totalScore = totalScore;
		this.grade = grade;
	}



	// getters/setters
    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    public int getStudentId(){ return studentId; }
    public void setStudentId(int s){ studentId = s; }
    public int getTotalScore(){ return totalScore; }
    public void setTotalScore(int s){ totalScore = s; }
    public String getGrade(){ return grade; }
    public void setGrade(String g){ grade = g; }



	@Override
	public String toString() {
		return "ScoreRecord [id=" + id + ", studentId=" + studentId + ", totalScore=" + totalScore + ", grade=" + grade
				+ "]";
	}
    
    
}
