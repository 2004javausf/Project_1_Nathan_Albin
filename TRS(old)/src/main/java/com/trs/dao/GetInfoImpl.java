package com.trs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trs.beans.TRS_User;
import com.trs.util.ConnFactory;

public class GetInfoImpl {

	public static ConnFactory cf =  ConnFactory.getInstance();
	
	public static List<TRS_User> getInfo() throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TRS_USERS";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<TRS_User> tmpList = new ArrayList<TRS_User>();
		while(rs.next()) {
			tmpList.add(new TRS_User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
			
		}
		return tmpList;
	}
	
}
