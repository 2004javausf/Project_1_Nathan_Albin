package com.trs.driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trs.dao.AcceptDenyDAOImpl;
import com.trs.dao.ApplicationDAOImpl;
import com.trs.util.ConnFactory;

public class methodTesting {

	public static ConnFactory cf = ConnFactory.getInstance();
	
	public static void main(String[] args) {
		//System.out.println(ApplicationDAOImpl.getActiveApplications());
		//AcceptDenyDAOImpl.acceptDenyApplication(21, "Deny", "DirSup", "Bad performance");
		//System.out.println(AcceptDenyDAOImpl.getUsername(7));
		ApplicationDAOImpl.insertProcess("depther");
		
	}	

}
