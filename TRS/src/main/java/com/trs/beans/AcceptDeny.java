package com.trs.beans;

public class AcceptDeny {
	
	private int formId;
	private String acceptDeny;
	private String accType;
	private String reason;
	private int addReim;
	
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
	
	
	
	public AcceptDeny(int formId, String acceptDeny, String accType, String reason, int addReim) {
		super();
		this.formId = formId;
		this.acceptDeny = acceptDeny;
		this.accType = accType;
		this.reason = reason;
		this.addReim = addReim;
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
	public int getAddReim() {
		return addReim;
	}
	public void setAddReim(int addReim) {
		this.addReim = addReim;
	}
	@Override
	public String toString() {
		return "AcceptDeny [formId=" + formId + ", acceptDeny=" + acceptDeny + ", accType=" + accType + ", reason="
				+ reason + ", addReim=" + addReim + "]";
	}

	
	
	
	

}
