package com.trs.beans;

public class AcceptDeny {
	
	private int formId;
	private String acceptDeny;
	private String accType;
	private String reason;
	public AcceptDeny() {
		super();
	}
	public AcceptDeny(int formId, String acceptDeny, String accType, String reason) {
		super();
		this.formId = formId;
		this.acceptDeny = acceptDeny;
		this.accType = accType;
		this.reason = reason;
	}
	public int getFormId() {
		return formId;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	public String getAcceptDeny() {
		return acceptDeny;
	}
	public void setAcceptDeny(String acceptDeny) {
		this.acceptDeny = acceptDeny;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "AcceptDeny [formId=" + formId + ", acceptDeny=" + acceptDeny + ", accType=" + accType + ", reason="
				+ reason + "]";
	}
	
	

}
