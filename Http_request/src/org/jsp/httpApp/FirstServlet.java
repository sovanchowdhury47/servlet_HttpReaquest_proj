package org.jsp.httpApp;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException 
 {
	String sid=req.getParameter("i");
	int id=Integer.parseInt(sid);
	
	//Fetch the data from database//
	Connection con=null;
    PreparedStatement pstmt=null;
	ResultSet rs=null;
	String qry="select * from btm.student where id=?";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
		pstmt=con.prepareStatement(qry);
		//Set the data for place holder//
		pstmt.setInt(1,id);;
		//Execute sql quries//
		rs=pstmt.executeQuery();
		//Persistance Logic//
		PrintWriter out=resp.getWriter();
		if(rs.next()) {
			String name=rs.getString(2);
			String dept=rs.getString(3);
			out.println("<html><body bgcolor='yellow'>"
					+ "<h1>Student name"+name+" from "+dept+"</h1>"
							+ "</body><html>");
		}
		else {
			out.println("<html><body bgcolor='red'>"
					+ "<h1>Stdudent detais not found</h1>"
					+ "</body><html>");
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	if(rs!=null) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
	}
 }
}
 }
}
