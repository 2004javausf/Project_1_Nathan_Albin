package com.trs.beans;

public class TRS_User {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private double balance;
	private String accType;
	
	public TRS_User(String username, String password, String firstName, String lastName, double balance,
			String accType) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.accType = accType;
	}

	public TRS_User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public TRS_User() {
		super();
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
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	@Override
	public String toString() {
		return "TRS_User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", balance=" + balance + ", accType=" + accType + "]";
	}
	
	

}
