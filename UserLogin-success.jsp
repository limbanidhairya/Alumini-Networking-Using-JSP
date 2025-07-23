<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Dashboard</title>
<style>
    /* Internal CSS styling */
    body {
        font-family: 'Arial', sans-serif;
        background-color: #CCCCFF;
        margin: 0;
        padding: 0;
        display: grid;
        place-items: center;
        height: 100vh;
    }
    .container {
        text-align: center;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        padding: 30px;
        max-width: 700px;
        margin: auto;
    }
    h1 {
        color: green;
        font-size: 24px;
        margin-bottom: 20px;
    }
    table {
        width: 100%;
        background-color: #E3E4FA;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        padding: 10px;
        text-align: left;
        border: 1px solid #ccc;
    }
    input[type="submit"],
    input[type="button"] {
        width: auto;
        padding: 10px 20px;
        margin: 5px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        background-color: #007bff;
        color: #fff;
    }
    input[type="submit"]:hover,
    input[type="button"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<div class="container">
    <h1>Welcome to Presidency University! Alumni Networking</h1>
    <% 
        String name = (String) request.getAttribute("name");
        out.print("<h1>");
        out.println(name + ", You are Successfully Logged In");
        out.print("</h1>");
        String username = (String) request.getAttribute("login_uname");
        String password = (String) request.getAttribute("login_password");
        
        int user_id = 0;
        String email = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dhairya", "root", "root");  
            PreparedStatement ps = con.prepareStatement("select * from users where username=?");  
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();  
            
            out.print("<table>");
            out.print("<caption>Your Details:</caption>");
            
            ResultSetMetaData rsmd = rs.getMetaData();  
            int total = rsmd.getColumnCount();  
            out.print("<tr>");  
            for(int i = 1; i <= total; i++) {  
                out.print("<th>" + rsmd.getColumnName(i) + "</th>");  
            }              
            while(rs.next()) {
                user_id = rs.getInt(1); // Retrieve the user ID from the ResultSet
                email = rs.getString(5);
                out.print("<tr><td>" + user_id + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td></tr>");  
            }   
            out.print("</table>");
            out.print("<form action=\"./Alumni_Networking.jsp\">"); 
            out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
            out.print("<input type=\"hidden\" name=\"password\" value=\"" + password + "\">");
            out.print("<input type=\"hidden\" name=\"userid\" value=\"" + user_id + "\">");
            out.print("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
            out.print("<input type=\"hidden\" name=\"name\" value=\"" + name + "\">");
            out.print("<input type=\"submit\" value=\"Alumni Networking\"/>");
            out.print("</form>");
            
            out.print("<form action=\"./UserDetails_update.jsp\">"); 
            out.print("<input type=\"hidden\" name=\"login_username\" value=\"" + username + "\">");
            out.print("<input type=\"hidden\" name=\"login_pass\" value=\"" + password + "\">");
            out.print("<input type=\"hidden\" name=\"userid\" value=\"" + user_id + "\">");
            out.print("<input type=\"submit\" value=\"Update Your Details\"/>");
            out.print("</form>");
            
            out.print("<form action=\"./UserPass_Update.jsp\">"); 
            out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
            out.print("<input type=\"hidden\" name=\"password\" value=\"" + password + "\">");
            out.print("<input type=\"hidden\" name=\"user_id\" value=\"" + user_id + "\">");
            out.print("<input type=\"submit\" value=\"Update Your Password\"/>");
            out.print("</form>");
            
            out.print("<form action=\"./Delete_User.jsp\">"); 
            out.print("<input type=\"hidden\" name=\"login_username\" value=\"" + username + "\">");
            out.print("<input type=\"hidden\" name=\"login_pass\" value=\"" + password + "\">");
            out.print("<input type=\"hidden\" name=\"user_id\" value=\"" + user_id + "\">");
            out.print("<input type=\"submit\" value=\"Delete Account\"/>");
            out.print("</form>");
            
            out.print("<a href=\"./index.html\"><input type=\"button\" value=\"Logout\"></a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</div>
</body>
</html>
