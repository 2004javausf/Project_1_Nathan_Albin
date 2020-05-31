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
import com.trs.beans.Grade;
import com.trs.beans.Sentence;
import com.trs.dao.AcceptDenyDAOImpl;
import com.trs.dao.ApplicationDAOImpl;

/**
 * Servlet implementation class UpdateGradeServlet
 */
public class UpdateGradeServlet extends HttpServlet {
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
		
		System.out.println("in dopost of updategrade servlet");
		ObjectMapper mapper = new ObjectMapper();
		Grade g = mapper.readValue(request.getInputStream(), Grade.class);
		List<Sentence> value = ApplicationDAOImpl.updateGrade(g.getUsername(), g.getFormId(), g.getGrade());
		String infoJSON = mapper.writeValueAsString(value);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(infoJSON);
		
	}

}
