package com.pu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateDetails
 */
public class Upd_Details_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upd_Details_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("login_pass");
		int userid = Integer.parseInt(request.getParameter("user_id"));
		try{ 

		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dhairya","root","root");  
		              
		PreparedStatement st=con.prepareStatement("update users set username=?, name=?, email=? ,contact=?  where user_id= ?");  
		
         st.setString(1, request.getParameter("username"));
         st.setString(2, request.getParameter("name"));
         st.setString(3, request.getParameter("email"));
         st.setString(4, request.getParameter("contact"));	
         st.setInt(5, userid);	
		st.executeUpdate();  
        st.close();
		con.close();
		
		out.println("<h1>Your Details Updated Successfully</h1>\r\n"
				+ "\r\n"
				+ "<form action=\"LoginServlet\" method=\"post\">\r\n"
				+ "        <input type=\"hidden\" name=\"username\" value=\""+username+"\">\r\n"
				+ "        <input type=\"hidden\" name=\"password\" value=\""+password+"\">\r\n"
				+ "        <input type=\"submit\" value=\"My Dashboard\"> \r\n"
				+ "    </form>");
		
	}catch(Exception e){ 
		System.out.println(e);
		out.print("<h1>Error in updating your details</h1>\r\n"
				+ "\r\n"
				+ "<form action=\"LoginServlet\" method=\"post\">\r\n"
				+ "        <input type=\"hidden\" name=\"username\" value=\""+ username+"\">\r\n"
				+ "        <input type=\"hidden\" name=\"password\" value=\""+ password +"\">\r\n"
				+ "        <input type=\"submit\" value=\"My Dashboard\"> \r\n"
				+ "    </form>");}
	
        
		finally{out.close();}
	}

}
