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
			
		System.out.println(AcceptDenyDAOImpl.getNotifications("samsora"));
	
	}	

}
