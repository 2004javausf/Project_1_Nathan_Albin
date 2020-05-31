package com.trs.beans;

public class Denial {
	
	private int formId;
	private String bencoReason;
	private String deptheadReason;
	private String dirsupReason;
	public Denial() {
		super();
	}
	public Denial(int formId, String bencoReason, String deptheadReason, String dirsupReason) {
		super();
		this.formId = formId;
		this.bencoReason = bencoReason;
		this.deptheadReason = deptheadReason;
		this.dirsupReason = dirsupReason;
	}
	public int getFormId() {
		return formId;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	public String getBencoReason() {
		return bencoReason;
	}
	public void setBencoReason(String bencoReason) {
		this.bencoReason = bencoReason;
	}
	public String getDeptheadReason() {
		return deptheadReason;
	}
	public void setDeptheadReason(String deptheadReason) {
		this.deptheadReason = deptheadReason;
	}
	public String getDirsupReason() {
		return dirsupReason;
	}
	public void setDirsupReason(String dirsupReason) {
		this.dirsupReason = dirsupReason;
	}
	@Override
	public String toString() {
		return "Denial [formId=" + formId + ", bencoReason=" + bencoReason + ", deptheadReason=" + deptheadReason
				+ ", dirsupReason=" + dirsupReason + "]";
	}

	
	

}
