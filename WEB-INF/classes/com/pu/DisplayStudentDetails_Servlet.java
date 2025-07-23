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
import java.sql.SQLException;

/**
 * Servlet implementation class DisplayStudentDetails_Servlet
 */
public class DisplayStudentDetails_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayStudentDetails_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Retrieve user details from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userid = request.getParameter("userid");
        String email = request.getParameter("email");
        String name = request.getParameter("name");

        // JDBC variables
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Database connection parameters
        String dbUrl = "jdbc:mysql://localhost:3306/dhairya";
        String dbUser = "root";
        String dbPassword = "root";

        try {
            // Connect to the database
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // SQL query to retrieve user details
            String sql = "SELECT * FROM students WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userid);

            // Execute the query
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Forward the user details to a JSP page for display
                request.setAttribute("userDetails", rs);
                RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayStudentDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                // Handle if user details are not found
            	out.println("<h1>No Details to Display.</h1>");
                out.print("<form action=\"./Alumni_Networking.jsp\">"); 
                out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
                out.print("<input type=\"hidden\" name=\"password\" value=\"" + password + "\">");
                out.print("<input type=\"hidden\" name=\"userid\" value=\"" + userid + "\">");
                out.print("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
                out.print("<input type=\"hidden\" name=\"name\" value=\"" + name + "\">");
                out.print("<input type=\"submit\" value=\"Alumni Dashboard\"/>");
                out.print("</form>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h1>Error in Displaying Details.</h1>");
            out.print("<form action=\"./Alumni_Networking.jsp\">"); 
            out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
            out.print("<input type=\"hidden\" name=\"password\" value=\"" + password + "\">");
            out.print("<input type=\"hidden\" name=\"userid\" value=\"" + userid + "\">");
            out.print("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
            out.print("<input type=\"hidden\" name=\"name\" value=\"" + name + "\">");
            out.print("<input type=\"submit\" value=\"Alumni Dashboard\"/>");
            out.print("</form>");
        } finally {
            // Close JDBC objects
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
