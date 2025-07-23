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
import java.sql.ResultSet;

/**
 * Servlet implementation class Updatepassword
 */
public class Pass_UpdationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pass_UpdationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		try{  
		
		String username = request.getParameter("username");	
			
		
		
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dhairya","root","root");  
		
		PreparedStatement st=con.prepareStatement("update users set password=?  where username=?");  
		st.setString(1, request.getParameter("update_password"));
		st.setString(2, username);
		int uq =st.executeUpdate();
		st.close();
		con.close();
		if(uq>0) {
			out.println("<h1>Successfully Updated Your Password</h1>\r\n"
            		+ "			<TABLE>\r\n"
            		+ "			<tr><th>\r\n"
            		+ "				<a href=\"./index.html\"><INPUT TYPE = \"button\" VALUE=\"Home\"></a>\r\n"
            		+ "				<a href=\"./User_Login.jsp\"><INPUT TYPE = \"button\" VALUE=\"Login\"></a>\r\n"
            		+ "			</th></tr>\r\n"
            		+ "			</TABLE>");
		}
		else {
			out.println("<h1>Error in updating the password</h1>\r\n"
					+ "			<TABLE>\r\n"
					+ "			<tr><th>\r\n"
					+ "				<a href=\"./index.html\"><INPUT TYPE = \"button\" VALUE=\"Home\"></a>\r\n"
					+ "				<a href=\"./User_Login.jsp\"><INPUT TYPE = \"button\" VALUE=\"Login\"></a>\r\n"
					+ "			</th></tr>\r\n"
					+ "			</TABLE>");
		}
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
	
    
		finally{out.close();}
	}
}
