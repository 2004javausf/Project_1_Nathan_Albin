package com.trs.beans;

public class ReAmtChange {
	
	private int formId;
	private double changeAmt;
	public ReAmtChange() {
		super();
	}
	public ReAmtChange(int formId, double changeAmt) {
		super();
		this.formId = formId;
		this.changeAmt = changeAmt;
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
	@Override
	public String toString() {
		return "ReAmtChange [formId=" + formId + ", changeAmt=" + changeAmt + "]";
	}
	
	

}
