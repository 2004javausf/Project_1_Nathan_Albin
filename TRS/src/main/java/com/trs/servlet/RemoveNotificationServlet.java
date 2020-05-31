package com.trs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.beans.AcceptDeny;
import com.trs.beans.Notification;
import com.trs.beans.TRS_User;
import com.trs.dao.AcceptDenyDAOImpl;

/**
 * Servlet implementation class RemoveNotificationServlet
 */
public class RemoveNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		AcceptDeny ad = mapper.readValue(request.getInputStream(), AcceptDeny.class);
		boolean tmp;
		tmp = AcceptDenyDAOImpl.removeApplication(ad.getFormId(), ad.getAcceptDeny());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		pw.print(tmp);
	}

}
