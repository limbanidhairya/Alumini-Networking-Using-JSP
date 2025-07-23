<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Student Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .message {
            margin-bottom: 20px;
            font-weight: bold;
            color: #ff0000;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Delete Student Details</h2>
    <%-- Retrieve user details from the form --%>
    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userid = request.getParameter("userid");
        String email = request.getParameter("email");
        String name = request.getParameter("name");

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

            // SQL query to delete user details
            String sql = "DELETE FROM students WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userid);

            // Execute the query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
    %>
            <p class="message">Student details deleted successfully!</p>
    <%
    out.print("<form action=\"./Alumni_Networking.jsp\">"); 
    out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
    out.print("<input type=\"hidden\" name=\"password\" value=\"" + password + "\">");
    out.print("<input type=\"hidden\" name=\"userid\" value=\"" + userid + "\">");
    out.print("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
    out.print("<input type=\"hidden\" name=\"name\" value=\"" + name + "\">");
    out.print("<input type=\"submit\" value=\"Alumni Dashboard\"/>");
    out.print("</form>");
            } else {
    %>
            <p class="message">Failed to delete student details. User ID not found.</p>
    <%
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
            out.println("<h1>Error in Deleting Student Details.</h1>");
            out.print("<form action=\"./Alumni_Networking.jsp\">"); 
            out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
            out.print("<input type=\"hidden\" name=\"password\" value=\"" + password + "\">");
            out.print("<input type=\"hidden\" name=\"userid\" value=\"" + userid + "\">");
            out.print("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
            out.print("<input type=\"hidden\" name=\"name\" value=\"" + name + "\">");
            out.print("<input type=\"submit\" value=\"Alumni Dashboard\"/>");
            out.print("</form>");
    %>
            <p class="message">An error occurred while deleting student details.</p>
    <%
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
                out.println("<h1>Error in Deleting Student Details.</h1>");
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
    %>
</div>

</body>
</html>
