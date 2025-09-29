package com.quizmaster.quations;

public class QuationClass {

	private String quation;
	private String optA;
	private String optB;
	private String optC;
	private String optD;
	private String ans;
	
	
	
	public QuationClass(String quation, String optA, String optB, String optC, String optD, String ans) {
		super();
		this.quation = quation;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.ans=ans;
	}
	
	public String getQuation() {
		return quation;
	}
	public void setQuation(String quation) {
		this.quation = quation;
	}
	public String getOptA() {
		return optA;
	}
	public void setOptA(String optA) {
		this.optA = optA;
	}
	public String getOptB() {
		return optB;
	}
	public void setOptB(String optB) {
		this.optB = optB;
	}
	public String getOptC() {
		return optC;
	}
	public void setOptC(String optC) {
		this.optC = optC;
	}
	public String getOptD() {
		return optD;
	}
	public void setOptD(String optD) {
		this.optD = optD;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}
	
	
	
	
	
}
