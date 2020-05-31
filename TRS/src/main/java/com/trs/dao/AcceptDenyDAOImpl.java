package com.trs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trs.beans.AcceptDeny;
import com.trs.beans.Denial;
import com.trs.beans.Sentence;
import com.trs.util.ConnFactory;

public class AcceptDenyDAOImpl {
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public static List<Sentence> acceptDenyApplication(int formId, String acceptDeny, String accType, String reason) {
		List<Sentence> s = new ArrayList<Sentence>();
		Sentence sen = new Sentence();
		int numaccepted = getNumAccepted(formId);
		Connection conn = cf.getConnection();
		String sql = "";
		
		if(acceptDeny.equals("Accept")) {
			if(numaccepted >= 2) {
				updateStatus(formId, acceptDeny, accType, reason);
				sql = "UPDATE FORMS SET F_STATUS = 'Processing' WHERE F_ID = ?";
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, formId);
					ps.executeUpdate();
					sen.setSentence("Successfully accepted account. Form state is now in processing.");
					s.add(sen);
					return s;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				updateStatus(formId, acceptDeny, accType, reason);
				sen.setSentence("Successfully accepted account");
				s.add(sen);
				return s;
			}
		} else {
			updateStatus(formId, acceptDeny, accType, reason);
			denyUser(formId);
			sql = "UPDATE FORMS SET F_STATUS = 'Denied' WHERE F_ID = ?";
			try {
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, formId);
				ps.executeUpdate();
				sen.setSentence("Successfully denied account");
				s.add(sen);
				return s;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}

	public static int getNumAccepted(int formId) {
		int j = 0;
		Connection conn = cf.getConnection();
		String sql = "SELECT DIR_SUP_APPROVE, DEPT_HEAD_APPROVE, BENCO_APPROVE FROM PROCESSING WHERE F_ID = " + formId;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				for(int i = 1; i <= 3; i++) {
					if(rs.getString(i).equals("Accept")) {
						j = j + 1;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	

	
	public static void updateStatus(int formId, String acceptDeny, String accType, String reason) {
		Connection conn = cf.getConnection();
		String tmp = "";
		String tmp2 = "";
		if(accType.equals("BenCo")) {
			tmp = "BENCO_APPROVE";
			tmp2 = "BENCO_REASON";
		} else if(accType.equals("DirSup")) {
			tmp = "DIR_SUP_APPROVE";
			tmp2 = "DIR_SUP_REASON";
		} else {
			tmp = "DEPT_HEAD_APPROVE";
			tmp2 = "DEPT_HEAD_REASON";
		}
		String sql = "UPDATE PROCESSING SET " + tmp + " = '" + acceptDeny + "', " + tmp2 + " = '" + reason + "' WHERE F_ID = " + formId;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getUsername(int formId) {
		Connection conn = cf.getConnection();
		String sql = "SELECT USERNAME FROM FORMS WHERE F_ID = " + formId;
		String usr = "";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				usr = rs.getString(1);
				return usr;
			}
			return usr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usr;
	}
	
	public static void denyUser(int formId) {
		String usr = getUsername(formId);
		double reamt = getReAmt(formId);
		double bal = getBal(formId);
		bal = bal + reamt;
		Connection conn = cf.getConnection();
		String sql = "UPDATE TRS_USERS SET BALANCE = " + bal + " WHERE USERNAME = '" + usr + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static double getReAmt(int formId) {
		Connection conn = cf.getConnection();
		String sql = "SELECT R_AMT FROM FORMS WHERE F_ID = " + formId;
		double usr = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				usr = rs.getDouble(1);
				return usr;
			}
			return usr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usr;
	}

	public static double getBal(int formId) {
		String username = getUsername(formId);
		Connection conn = cf.getConnection();
		String sql = "SELECT BALANCE FROM TRS_USERS WHERE USERNAME = '" + username + "'";
		double usr = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				usr = rs.getDouble(1);
				return usr;
			}
			return usr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usr;
	}

	public static List<Denial> getDenials(String username) {
		List<Denial> ad = new ArrayList<Denial>();
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM PROCESSING WHERE USERNAME = ? AND (BENCO_APPROVE = 'Deny' OR DIR_SUP_APPROVE = 'Deny' OR DEPT_HEAD_APPROVE = 'Deny')";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ad.add(new Denial(rs.getInt(2), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			return ad;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ad;
	}
	
	
	
}

