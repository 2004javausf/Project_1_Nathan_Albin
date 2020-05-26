package com.trs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnFactory {
	
	private static ConnFactory cf = new ConnFactory();
	private static final String url = "jdbc:oracle:thin:@trs-system.cn22bxy0aezi.us-east-2.rds.amazonaws.com:1521:TRS";
	private static final String username = "nathangeo";
	private static final String password = "9818234Na$";
	
	private ConnFactory() {
		super();
	}
	
	public static synchronized ConnFactory getInstance() {
		if(cf == null) {
			cf = new ConnFactory();
		}
		return cf;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
