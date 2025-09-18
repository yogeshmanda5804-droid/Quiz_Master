/*
 * User Story 7.2: Display Quiz Summary
As a student I want to see a summary after completing the quiz So that I can
review my attempt
Acceptance Criteria:
Summary includes: Total Questions, Correct Answers, Wrong Answers, Score,Grade
 */
package com.quizmaster.score;

import java.util.ArrayList;
import java.util.List;

import com.quizmaster.model.Question;

public class DisplayResultSummary {
	
	public void takeQuiz() {
	    List<Question> questions = questionD.getRandomQuestions(10);
	    int score = 0;
	    List<String> summary = new ArrayList<>();

	    for (int i = 0; i < questions.size(); i++) {
	        Question q = questions.get(i);
	        System.out.println("\nQ" + (i + 1) + ": " + q.getQuestionText());
	        System.out.println("1. " + q.getOption1());
	        System.out.println("2. " + q.getOption2());
	        System.out.println("3. " + q.getOption3());
	        System.out.println("4. " + q.getOption4());
	        
	        int ans = readAnswer();
	        boolean correct = (ans == q.getCorrectOption());
	        if (correct) score++;

	        String chosen = switch (ans) {
	            case 1 -> q.getOption1();
	            case 2 -> q.getOption2();
	            case 3 -> q.getOption3();
	            case 4 -> q.getOption4();
	            default -> "Invalid";
	        };

	        String correctAns = switch (q.getCorrectOption()) {
	            case 1 -> q.getOption1();
	            case 2 -> q.getOption2();
	            case 3 -> q.getOption3();
	            case 4 -> q.getOption4();
	            default -> "Unknown";
	        };

	        summary.add("Q" + (i + 1) + ": " + q.getQuestionText() +
	                    "\n  Your Answer: " + chosen +
	                    "\n  Correct Answer: " + correctAns +
	                    "\n  Result: " + (correct ? "Correct" : "Wrong"));
	    }

	    String grade = DisplayGrade.computeGrade(score);
	    String feedback =DisplayGrade.feedbackForGrade(grade);

	    System.out.println("\n=== Quiz Summary ===");
	    for (String line : summary) {
	        System.out.println(line + "\n");
	    }
	    System.out.println("Final Score: " + score + "/10");
	    System.out.println("Grade: " + grade);
	    System.out.println("Feedback: " + feedback);

	  
	}


}
