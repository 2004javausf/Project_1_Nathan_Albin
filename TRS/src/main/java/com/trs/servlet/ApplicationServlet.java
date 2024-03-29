package com.trs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.beans.Application;
import com.trs.dao.ApplicationDAOImpl;
import com.trs.dao.LoginDAOImpl;

/**
 * Servlet implementation class ApplicationServlet
 */
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doGet of application servlet");
		ObjectMapper mapper = new ObjectMapper();
		List<Application> l = ApplicationDAOImpl.getActiveApplications();
		String infoJSON = mapper.writeValueAsString(l);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(infoJSON);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		Application app = mapper.readValue(request.getInputStream(), Application.class);
		boolean s = ApplicationDAOImpl.createApplication(app.getUsername(), app.getEventType(), app.getEventCost(), 
				app.getGradeType(), app.getStartDate());
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		pw.print(s);
		
	}

}
