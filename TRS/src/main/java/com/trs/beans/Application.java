package com.trs.beans;

public class Application {

	private int formId;
	private String username;
	private String eventType;
	private double eventCost;
	private String gradeType;
	private String startDate;
	private double rAmt;
	private double rAdditional;
	private String grade;
	private String status;
	private int numAccepted;
	
	
	
	public Application() {
		super();
	}



	public Application(int formId, String username, String eventType, double eventCost, String gradeType,
			String startDate, double rAmt, double rAdditional, String grade, String status) {
		super();
		this.formId = formId;
		this.username = username;
		this.eventType = eventType;
		this.eventCost = eventCost;
		this.gradeType = gradeType;
		this.startDate = startDate;
		this.rAmt = rAmt;
		this.rAdditional = rAdditional;
		this.grade = grade;
		this.status = status;
	}



	public Application(int formId, String username, String eventType, double eventCost, String gradeType,
			String startDate, double rAmt, double rAdditional, String grade, String status, int numAccepted) {
		super();
		this.formId = formId;
		this.username = username;
		this.eventType = eventType;
		this.eventCost = eventCost;
		this.gradeType = gradeType;
		this.startDate = startDate;
		this.rAmt = rAmt;
		this.rAdditional = rAdditional;
		this.grade = grade;
		this.status = status;
		this.numAccepted = numAccepted;
	}



	public int getFormId() {
		return formId;
	}



	public void setFormId(int formId) {
		this.formId = formId;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEventType() {
		return eventType;
	}



	public void setEventType(String eventType) {
		this.eventType = eventType;
	}



	public double getEventCost() {
		return eventCost;
	}



	public void setEventCost(double eventCost) {
		this.eventCost = eventCost;
	}



	public String getGradeType() {
		return gradeType;
	}



	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public double getrAmt() {
		return rAmt;
	}



	public void setrAmt(double rAmt) {
		this.rAmt = rAmt;
	}



	public double getrAdditional() {
		return rAdditional;
	}



	public void setrAdditional(double rAdditional) {
		this.rAdditional = rAdditional;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}


	
	

	public int getNumAccepted() {
		return numAccepted;
	}



	public void setNumAccepted(int numAccepted) {
		this.numAccepted = numAccepted;
	}



	@Override
	public String toString() {
		return "Application [formId=" + formId + ", username=" + username + ", eventType=" + eventType + ", eventCost="
				+ eventCost + ", gradeType=" + gradeType + ", startDate=" + startDate + ", rAmt=" + rAmt
				+ ", rAdditional=" + rAdditional + ", grade=" + grade + ", status=" + status + ", numAccepted="
				+ numAccepted + "]";
	}


	

	
	
}
