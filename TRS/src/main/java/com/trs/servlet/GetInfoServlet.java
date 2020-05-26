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
import com.trs.beans.TRS_User;
import com.trs.dao.GetInfoImpl;

/**
 * Servlet implementation class GetInfoServlet
 */
public class GetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		TRS_User user = mapper.readValue(request.getInputStream(), TRS_User.class);
		List<TRS_User> tmp;
		try {
			tmp = GetInfoImpl.getInfo(user.getUsername());
			String infoJSON = mapper.writeValueAsString(tmp);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			pw.print(infoJSON);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
