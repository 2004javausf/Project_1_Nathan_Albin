package com.trs.beans;

public class Grade {
	
	private String username;
	private int formId;
	private String grade;
	public Grade() {
		super();
	}
	public Grade(String username, int formId, String grade) {
		super();
		this.username = username;
		this.formId = formId;
		this.grade = grade;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getFormId() {
		return formId;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Grade [username=" + username + ", formId=" + formId + ", grade=" + grade + "]";
	}
	
	
	

}
