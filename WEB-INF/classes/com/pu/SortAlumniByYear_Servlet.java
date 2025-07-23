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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SortAlumniByYear_Servlet
 */
public class SortAlumniByYear_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortAlumniByYear_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userid = request.getParameter("userid");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        // Retrieve user input for the year of passing
        String year = request.getParameter("year");

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

            // SQL query to retrieve alumni details for the specified year
            String sql = "SELECT name, rollNumber, email, school, department, yearOfPassing FROM students WHERE yearOfPassing=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, year);

            // Execute the query
            rs = stmt.executeQuery();

            // List to store alumni details
            List<Student> alumniList = new ArrayList<>();

            // Fetch alumni details from the ResultSet
            while (rs.next()) {
                Student alumni = new Student();
                alumni.setName(rs.getString("name"));
                alumni.setRollNumber(rs.getString("rollNumber"));
                alumni.setEmail(rs.getString("email"));
                alumni.setSchool(rs.getString("school"));
                alumni.setDepartment(rs.getString("department"));
                alumni.setYearOfPassing(rs.getString("yearOfPassing"));
                alumniList.add(alumni);
            }

            // Forward the alumni details to a JSP page for display
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("userid", userid);
            request.setAttribute("email", email);
            request.setAttribute("name", name);
            request.setAttribute("alumniList", alumniList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayAlumniByYear.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h1>Error in Displaying ALumni Details.</h1>");
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
                out.println("<h1>Error in Displaying ALumni Details.</h1>");
                out.print("<form action=\"./Alumni_Networking.jsp\">"); 
                out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
                out.print("<input type=\"hidden\" name=\"password\" value=\"" + password + "\">");
                out.print("<input type=\"hidden\" name=\"userid\" value=\"" + userid + "\">");
                out.print("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
                out.print("<input type=\"hidden\" name=\"name\" value=\"" + name + "\">");
                out.print("<input type=\"submit\" value=\"Alumni Dashboard\"/>");
                out.print("</form>");
            }
        }
    }

}
