package com.trs.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trs.beans.Denial;
import com.trs.beans.Notification;
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
					conn.close();
					return s;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				updateStatus(formId, acceptDeny, accType, reason);
				sen.setSentence("Successfully accepted account");
				s.add(sen);
				try {
					conn.close();
					return s;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				conn.close();
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
		try {
			conn.close();
			return j;
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
			conn.close();
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
			conn.close();
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
		if(bal >= 1000) {
			bal = 1000;
		}
		Connection conn = cf.getConnection();
		String sql = "UPDATE TRS_USERS SET BALANCE = " + bal + " WHERE USERNAME = '" + usr + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			conn.close();
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
			conn.close();
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
			conn.close();
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
			conn.close();
			return ad;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ad;
	}
	
	public static List<Sentence> processApp(int formId, double addReim, String status, String reason) {
		List<Sentence> l = new ArrayList<Sentence>();
		Sentence s = new Sentence();
		Connection conn = cf.getConnection();
		String sql = "UPDATE FORMS SET F_STATUS = ? WHERE F_ID = ?";
		if(status.equals("Deny")) {
			denyUser(formId);
			updateStatus(formId, status, "BenCo", reason);
			s.setSentence("Process successfully denied.");
			l.add(s);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "Not Awarded");
				ps.setInt(2, formId);
				ps.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return l;
		} else if (status.equals("Accept")) {
			updateStatus(formId, status, "BenCo", reason);
			updateAddReim(formId, addReim);
			s.setSentence("Process successfully accepted.");
			l.add(s);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "Awarded");
				ps.setInt(2, formId);
				ps.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return l;
		}
		return l;
	}
	
	public static void updateAddReim(int formId, double addReim) {
		Connection conn = cf.getConnection();
		String sql = "UPDATE FORMS SET R_ADDITIONAL = ? WHERE F_ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, addReim);
			ps.setInt(2, formId);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Sentence> changeReimbursementAmt(int formId, double changeAmt) throws SQLException {
		List<Sentence> s = new ArrayList<Sentence>();
		Sentence sen = new Sentence();
		double bal = getBal(formId);
		double reAmt = getReAmt(formId);
		String username = getUsername(formId);
		Connection conn = cf.getConnection();
		String sql = "{ call CHANGE_REIMBURSEMENT(?, ?, ?, ?)";
		
		if(changeAmt > reAmt) {
			if(bal <= (changeAmt - reAmt)) {
				bal = 0;
				CallableStatement call = conn.prepareCall(sql);
				call.setDouble(1, changeAmt);
				call.setInt(2, formId);
				call.setDouble(3, bal);
				call.setString(4, username);
				call.execute();
				sen.setSentence("Successfully changed reimbursement amount.");
				s.add(sen);
				conn.close();
				return s;
			} else {
				bal = bal - (changeAmt - reAmt);
				CallableStatement call = conn.prepareCall(sql);
				call.setDouble(1, changeAmt);
				call.setInt(2, formId);
				call.setDouble(3, bal);
				call.setString(4, username);
				call.execute();
				sen.setSentence("Successfully changed reimbursement amount.");
				s.add(sen);
				conn.close();
				return s;
			}
		} else if(changeAmt <= reAmt) {
			bal = bal + (reAmt - changeAmt);
			CallableStatement call = conn.prepareCall(sql);
			call.setDouble(1, changeAmt);
			call.setInt(2, formId);
			call.setDouble(3, bal);
			call.setString(4, username);
			call.execute();
			sen.setSentence("Successfully changed reimbursement amount.");
			s.add(sen);
			conn.close();
			return s;
		}
		return s;
	}
	
	public static List<Notification> getNotifications(String username) {
		List<Notification> notif = new ArrayList<Notification>();
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM NOTIF WHERE USERNAME = ? AND NOTIF_STATUS = 'Not Accepted'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				notif.add(new Notification(rs.getString(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getString(5)));
			}
			conn.close();
			return notif;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notif;
	}
	
	public static boolean removeApplication(int formId, String accdeny) {
		boolean b = false;
		Connection conn = cf.getConnection();
		double bal = getBal(formId);
		double reAmt = getReAmt(formId);
		String username = getUsername(formId);
		bal = bal + reAmt;
		if(bal >= 1000) {
			bal = 1000;
		}
		String sql = "{ call ACCDENY_NOTIF(?, ?, ?)";
		if(accdeny.equals("Deny")) {
			try {
				CallableStatement call = conn.prepareCall(sql);
				call.setInt(1, formId);
				call.setDouble(2, bal);
				call.setString(3, username);
				call.execute();
				b = false;
				conn.close();
				return b;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(accdeny.equals("Accept")) {
			b = true;
			String sql2 = "UPDATE NOTIF SET NOTIF_STATUS = 'Accept' WHERE F_ID = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql2);
				ps.setInt(1, formId);
				ps.executeUpdate();
				conn.close();
				return b;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return b;
		}
		
		return b;
	}
	
}

