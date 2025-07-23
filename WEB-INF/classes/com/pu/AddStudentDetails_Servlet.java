package com.pu;

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
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Servlet implementation class AddStudentDetails_Servlet
 */
public class AddStudentDetails_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentDetails_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userid = request.getParameter("userid");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String school = request.getParameter("school");
        String department = request.getParameter("department");
        String rollNumber = request.getParameter("rollNumber");
        String yearOfPassing = request.getParameter("yearOfPassing");

        // Determine if alumni
        String alumni = isAlumni(yearOfPassing);

        // JDBC variables
        Connection conn = null;
        PreparedStatement stmt = null;

        // Database connection parameters
        String dbUrl = "jdbc:mysql://localhost:3306/dhairya";
        String dbUser = "root";
        String dbPassword = "root";

        try {
            // Connect to the database
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // SQL query to insert data
            String sql = "INSERT INTO students (user_id, name, email, school, department, rollNumber, yearOfPassing, alumni) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userid);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, school);
            stmt.setString(5, department);
            stmt.setString(6, rollNumber);
            stmt.setString(7, yearOfPassing);
            stmt.setString(8, alumni);

            // Execute the query
            stmt.executeUpdate();

            // Redirect back to Alumni_Networking.jsp
            out.println("<h1>Details Added Successfully</h1>");
            out.print("<form action=\"./Alumni_Networking.jsp\">"); 
            out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
            out.print("<input type=\"hidden\" name=\"password\" value=\"" + password + "\">");
            out.print("<input type=\"hidden\" name=\"userid\" value=\"" + userid + "\">");
            out.print("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
            out.print("<input type=\"hidden\" name=\"name\" value=\"" + name + "\">");
            out.print("<input type=\"submit\" value=\"Alumni Dashboard\"/>");
            out.print("</form>");
            
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h1>Error in adding Details.</h1>");
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
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<h1>Error in adding Details.</h1>");
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

    // Method to check if the student is an alumni based on the year of passing
    private String isAlumni(String yearOfPassing) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int year = Integer.parseInt(yearOfPassing);
        if (year <= currentYear) {
            return "Yes";
        } else {
            return "No";
        }
    }


}
