package com.trs.driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trs.util.ConnFactory;

public class methodTesting {

	public static ConnFactory cf = ConnFactory.getInstance();
	
	public static void main(String[] args) {
		
		//java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		//System.out.println(sqlDate);
		//long l = 0;

		//System.out.println(sqlDate.getTime());
		
		
		//System.out.println(ApplicationDAOImpl.createApplication("depther", "Seminar", 400, "Pass/Fail", "2020-06-26"));
		//System.out.println(java.sql.Date.valueOf("2020-05-23"));
		//System.out.println(ApplicationDAOImpl.getApplications("samsora"));
		
		
		String username = "samsora";
		String sqll = "SELECT BALANCE FROM TRS_USERS WHERE USERNAME = '" + username + "'";
		Connection conn = cf.getConnection();
		PreparedStatement pss;
		try {
			pss = conn.prepareStatement(sqll);
			ResultSet rss = pss.executeQuery();
			while(rss.next()) {
				System.out.println(rss.getDouble(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
