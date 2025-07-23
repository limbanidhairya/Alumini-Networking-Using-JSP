package com.pu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Delete_UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    // Update these values with your actual database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dhairya";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Query to check if the username and password match
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            // If the user exists and the password matches, delete the user
            if (rs.next()) {
                String deleteQuery = "DELETE FROM users WHERE username=?";
                pstmt = conn.prepareStatement(deleteQuery);
                pstmt.setString(1, username);
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    out.println("<h2>User deleted successfully!</h2>");
                    out.println("<a href=\"./index.html\"><INPUT TYPE = \"button\" VALUE=\"Home\"></a>");
                } else {
                    
                    out.print("<h2>Error deleting user.</h2>\r\n"
                    		+"<html>\r\n"
        					+ "			<TABLE>\r\n"
        					+ "			<tr><th>\r\n"
        					+ "				<a href=\"./index.html\"><INPUT TYPE = \"button\" VALUE=\"Home\"></a>\r\n"
        					+ "				<a href=\"./User_Login.jsp\"><INPUT TYPE = \"button\" VALUE=\"Login\"></a>\r\n"
        					+ "			</th></tr>\r\n"
        					+ "			</TABLE>\r\n"
        					+"</html>");
                }
            } else {
                out.println("<h2>Invalid username or password.</h2>\r\n"
                		+"<html>\r\n"
    					+ "			<TABLE>\r\n"
    					+ "			<tr><th>\r\n"
    					+ "				<a href=\"./index.html\"><INPUT TYPE = \"button\" VALUE=\"Home\"></a>\r\n"
    					+ "				<a href=\"./User_Login.jsp\"><INPUT TYPE = \"button\" VALUE=\"Login\"></a>\r\n"
    					+ "			</th></tr>\r\n"
    					+ "			</TABLE>\r\n"
                		+"</html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close all database resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
