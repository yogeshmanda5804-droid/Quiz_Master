package com.quizmaster.quations;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;





public class ScoreCalculations {

	String answer;
	int correctAns=0;
	int wrongAns=0;
	
	public void candidateScore () throws IOException, SQLException {
		
		 QuestionsDisplay qd = new QuestionsDisplay();
		 
		Set<QuationClass> set = qd.getRandomQuations();
		
		Scanner scn = new Scanner(System.in);
		

		int id =0;
		
		for(QuationClass q : set) {
			     	 
        	 id++;
        	 
			System.out.println("Quation "+id+" : "+q.getQuation());
			System.out.println("A : "+q.getOptA());
			System.out.println("B : "+q.getOptB());
			System.out.println("C : "+q.getOptC());
			System.out.println("D : "+q.getOptD());
			System.out.println();
			
			
			do {
			System.out.println("Answer : ");
     		answer = scn.next().trim().toLowerCase();
     		
     		if(!answer.matches("[abcd]")) {
     			System.out.println("Invalid input! Please enter only a, b, c, d");
     		}
			
			}while(!answer.matches("[abcd]"));
     		
 			System.out.println();
			
 			
 			if((answer).equals(q.getAns())) {
 				
 				correctAns++;
 				
 			}else {
 				wrongAns++;
 			}
		
	     }
		
		System.out.println("Your Score : "+correctAns);
		
		
		scn.close();
		
	}
	
	public void getGrade() {
		
		if(correctAns>8) {
			System.out.println("Your grade : A");
			System.out.println("Excellent");
		}else if(correctAns>5) {
			System.out.println("Your grade : B");
			System.out.println("Good");
		}else{
			System.out.println("Needs Improvement");
		}
		
	}
	

	
	
	
}


