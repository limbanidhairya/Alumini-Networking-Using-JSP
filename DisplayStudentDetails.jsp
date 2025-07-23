<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Student Details</title>
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
        .details {
            margin-bottom: 20px;
        }
        .details label {
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>User Details</h2>
    <div class="details">
        <label>Username:</label> <%= request.getParameter("username") %><br>
        <label>Password:</label> <%= request.getParameter("password") %><br>
        <label>User ID:</label> <%= request.getParameter("userid") %><br>
        <label>Email:</label> <%= request.getParameter("email") %><br>
        <label>Name:</label> <%= request.getParameter("name") %><br>
        <%
            java.sql.ResultSet rs = (java.sql.ResultSet)request.getAttribute("userDetails");
            if (rs.next()) {
        %>
        <label>School:</label> <%= rs.getString("school") %><br>
        <label>Department:</label> <%= rs.getString("department") %><br>
        <label>Roll Number:</label> <%= rs.getString("rollNumber") %><br>
        <label>Year of Passing:</label> <%= rs.getString("yearOfPassing") %><br>
        <label>Alumni:</label> <%= rs.getString("alumni") %><br>
        <% } %>
    </div>
    <form action="./Alumni_Networking.jsp" method="post">
    <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
        <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
        <input type="hidden" name="userid" value="<%= request.getParameter("userid") %>">
        <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
        <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
    <input type="submit" value="Alumni Dashboard"/>
</form>
</div>

</body>
</html>
