package com.trs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trs.beans.TRS_User;
import com.trs.util.ConnFactory;

public class LoginDAOImpl {
	
	public static ConnFactory cf = ConnFactory.getInstance();
	

    public static boolean login(String username,String password) 
    {
        boolean st =false;
        try {
            //creating connection with the database
            Connection conn = cf.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from TRS_USERS where USERNAME=? and PASSWORD=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
            st = rs.next();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    } 
	
}
