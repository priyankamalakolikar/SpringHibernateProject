package com.love2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Spring;

/**
 * Servlet implementation class TestDbServlete
 */
@WebServlet("/TestDbServlete")
public class TestDbServlete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestDbServlete() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//setup connection variables
		String user="springstudent";
		String pass="springstudent";
		//get connection to database
		String jdbcurl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimeZone=UTC";
		String driver="com.mysql.cj.jdbc.Driver";
		
		try {
			PrintWriter out=response.getWriter();
			out.println("connecting to database "+jdbcurl);
			Class.forName(driver);
			Connection mycon=DriverManager.getConnection(jdbcurl,user,pass);
			out.println("connection successful ");
			mycon.close();
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
