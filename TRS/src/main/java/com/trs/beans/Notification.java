package com.trs.beans;

public class Notification {
	
	private String username;
	private int formId;
	private double changeAmt;
	private double newBal;
	private String status;
	public Notification() {
		super();
	}
	public Notification(String username, int formId, double changeAmt, double newBal, String status) {
		super();
		this.username = username;
		this.formId = formId;
		this.changeAmt = changeAmt;
		this.newBal = newBal;
		this.status = status;
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
	public double getChangeAmt() {
		return changeAmt;
	}
	public void setChangeAmt(double changeAmt) {
		this.changeAmt = changeAmt;
	}
	public double getNewBal() {
		return newBal;
	}
	public void setNewBal(double newBal) {
		this.newBal = newBal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Notification [username=" + username + ", formId=" + formId + ", changeAmt=" + changeAmt + ", newBal="
				+ newBal + ", status=" + status + "]";
	}
	
	

}
