package com.trs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.beans.AcceptDeny;
import com.trs.beans.ReAmtChange;
import com.trs.beans.Sentence;
import com.trs.dao.AcceptDenyDAOImpl;

/**
 * Servlet implementation class ChangeReAmtServlet
 */
public class ChangeReAmtServlet extends HttpServlet {
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
		
		System.out.println("in dopost of changereamt servlet");
		ObjectMapper mapper = new ObjectMapper();
		ReAmtChange rac = mapper.readValue(request.getInputStream(), ReAmtChange.class);
		List<Sentence> value;
		try {
			value = AcceptDenyDAOImpl.changeReimbursementAmt(rac.getFormId(), rac.getChangeAmt());
			String infoJSON = mapper.writeValueAsString(value);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw = response.getWriter();
			pw.print(infoJSON);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
