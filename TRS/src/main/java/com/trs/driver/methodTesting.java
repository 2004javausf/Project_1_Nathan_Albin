package com.trs.driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.trs.dao.AcceptDenyDAOImpl;
import com.trs.dao.ApplicationDAOImpl;
import com.trs.util.ConnFactory;

public class methodTesting {

	public static ConnFactory cf = ConnFactory.getInstance();
	
	public static void main(String[] args) {
		//System.out.println(ApplicationDAOImpl.getActiveApplications());
		//AcceptDenyDAOImpl.acceptDenyApplication(21, "Deny", "DirSup", "Bad performance");
		//System.out.println(AcceptDenyDAOImpl.getUsername(7));
		//ApplicationDAOImpl.insertProcess("depther");
		//System.out.println(ApplicationDAOImpl.getApplications("samsora"));
		//System.out.println(AcceptDenyDAOImpl.getNumAccepted(5));
		//System.out.println(ApplicationDAOImpl.getActiveApplications());
		//System.out.println(ApplicationDAOImpl.updateGrade("samsora", 5, "A"));
		//System.out.println(AcceptDenyDAOImpl.getDenials("nathangeo"));
		java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println(currentDate);
	
	
	
	}	

}
