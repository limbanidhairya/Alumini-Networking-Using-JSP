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
 * Servlet implementation class UpdateStudentDetails_Servlet
 */
public class UpdateStudentDetails_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentDetails_Servlet() {
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
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String school = request.getParameter("school");
        String department = request.getParameter("department");
        String rollNumber = request.getParameter("rollNumber");
        String yearOfPassing = request.getParameter("yearOfPassing");
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

            // SQL query to update data
            String sql = "UPDATE students SET school=?, department=?, rollNumber=?, yearOfPassing=?, alumni=? WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, school);
            stmt.setString(2, department);
            stmt.setString(3, rollNumber);
            stmt.setString(4, yearOfPassing);
            stmt.setString(5, alumni);
            stmt.setString(6, userid);

            // Execute the query
            stmt.executeUpdate();

            out.println("<h1>Details Updated Successfully</h1>");
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
