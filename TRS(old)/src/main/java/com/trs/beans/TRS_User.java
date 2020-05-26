package com.trs.beans;

public class TRS_User {
	
	private String username;
	private String password;
	private String FirstName;
	private String LastName;
	private int balance;
	private String acc_type;
	
	
	
	public TRS_User() {
		super();
	}



	public TRS_User(String username, String password, String firstName, String lastName, int balance, String acc_type) {
		super();
		this.username = username;
		this.password = password;
		FirstName = firstName;
		LastName = lastName;
		this.balance = balance;
		this.acc_type = acc_type;
	}



	public TRS_User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstName() {
		return FirstName;
	}



	public void setFirstName(String firstName) {
		FirstName = firstName;
	}



	public String getLastName() {
		return LastName;
	}



	public void setLastName(String lastName) {
		LastName = lastName;
	}



	public int getBalance() {
		return balance;
	}



	public void setBalance(int balance) {
		this.balance = balance;
	}



	public String getAcc_type() {
		return acc_type;
	}



	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}



	@Override
	public String toString() {
		return "TRS_User [username=" + username + ", password=" + password + ", FirstName=" + FirstName + ", LastName="
				+ LastName + ", balance=" + balance + ", acc_type=" + acc_type + "]";
	}




	
	

}
