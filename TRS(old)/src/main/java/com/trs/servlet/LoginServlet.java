package com.trs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.beans.TRS_User;
import com.trs.dao.LoginDAOImpl;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		TRS_User user = mapper.readValue(request.getInputStream(), TRS_User.class);
		LoginDAOImpl.login(user.getUsername(), user.getPassword());
    }  
}