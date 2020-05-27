package com.trs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.trs.beans.Application;
import com.trs.beans.TRS_User;
import com.trs.util.ConnFactory;

public class ApplicationDAOImpl {
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public static boolean createApplication(String username, String type, double cost, String grade_type, String date) {
		double reamt = 0;
		String t = type;
		double remBal = 0;
		switch(t) {
		case "University Course":
			reamt = cost * (0.8);
			break;
		case "Seminar":
			reamt = cost * (0.6);
			break;
		case "Certification Prep":
			reamt = cost * (0.75);
			break;
		case "Certification":
			reamt = cost;
			break;
		case "Technical Training":
			reamt = cost * (0.9);
			break;
		case "Other":
			reamt = cost * (0.3);
			break;
		}
		
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO FORMS VALUES(SEQ_F_ID.NEXTVAL, ?, ?, ?, ?, TO_DATE(?, 'YYY-MM-DD'), '" + reamt + "', 0, 'N/A', ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, type);
			ps.setDouble(3, cost);
			ps.setString(4, grade_type);
			java.sql.Date eventDate = java.sql.Date.valueOf(date);
			ps.setDate(5, eventDate);
			java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			if(currentDate.getTime() >= (eventDate.getTime() - 604800000)) {
				return false;
			} else if (currentDate.getTime() >= (eventDate.getTime() - 1209600000)) {
				ps.setString(6, "Not Accepted (Urgent)");
				remBal = getTheBalance(username);
				if(remBal < reamt) {
					reamt = remBal;
					remBal = 0;
					updateUser(username, remBal);
					insertProcess(username);
					ps.executeQuery();
					return true;
						
				} else {
					remBal = remBal - reamt;
					updateUser(username, remBal);
					insertProcess(username);
					ps.executeQuery();
					return true;
				}
				
			} else {
				ps.setString(6, "Not Accepted");
				remBal = getTheBalance(username);
				if(remBal < reamt) {
					reamt = remBal;
					remBal = 0;
					updateUser(username, remBal);
					insertProcess(username);
					ps.executeQuery();
					return true;
						
				} else {
					remBal = remBal - reamt;
					updateUser(username, remBal);
					insertProcess(username);
					ps.executeQuery();
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static double getTheBalance(String username) {
		Connection conn = cf.getConnection();
		String sql = "SELECT BALANCE FROM TRS_USERS WHERE USERNAME = '" + username + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				double remBal = rs.getDouble(1);
				return remBal;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void updateUser(String username, double remBal) {
		Connection conn = cf.getConnection();
		String sql = "UPDATE TRS_USERS SET BALANCE = " + remBal + "WHERE USERNAME = '" + username + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertProcess(String username) {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO PROCESSING VALUES('" + username + "', SEQ_F_ID2.NEXTVAL, 'none', 'none', 'none', 'none', 'none', 'none')";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static List<Application> getApplications(String username) {
		
		List<Application> app = new ArrayList<Application>();
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM FORMS WHERE USERNAME = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				app.add(new Application(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getString(10)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return app;
		
		
	}
	
	public static List<Application> getActiveApplications() {
		List<Application> app = new ArrayList<Application>();
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM FORMS WHERE F_STATUS = 'Not Accepted' OR F_STATUS = 'Not Accepted (Urgent)' OR F_STATUS = 'Processing'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				app.add(new Application(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getString(10)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return app;
		
	}
	
	
}
