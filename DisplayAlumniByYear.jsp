<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List, java.util.Map" %>
<%@ page import="com.pu.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Alumni by Year</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Alumni for Year <%= request.getParameter("year") %></h2>
    
    <table>
        <tr>
            <th>Name</th>
            <th>Roll Number</th>
            <th>Email</th>
            <th>School</th>
            <th>Department</th>
            <th>Year of Passing</th>
        </tr>
        <%
            List<Student> alumniList = (List<Student>)request.getAttribute("alumniList");
            for (Student alumni : alumniList) {
        %>
        <tr>
            <td><%= alumni.getName() %></td>
            <td><%= alumni.getRollNumber() %></td>
            <td><%= alumni.getEmail() %></td>
            <td><%= alumni.getSchool() %></td>
            <td><%= alumni.getDepartment() %></td>
            <td><%= alumni.getYearOfPassing() %></td>
        </tr>
        <% } %>
    </table>
   <form action="./Alumni_Networking.jsp" method="post">
    	<input type="hidden" name="username" value="<%= (String) request.getAttribute("username") %>">
        <input type="hidden" name="password" value="<%= (String) request.getAttribute("password") %>">
        <input type="hidden" name="userid" value="<%= (String) request.getAttribute("userid") %>">
        <input type="hidden" name="name" value="<%= (String) request.getAttribute("name") %>">
        <input type="hidden" name="email" value="<%= (String) request.getAttribute("email") %>">
    	<input type="submit" value="Alumni Dashboard"/>
	</form>
</div>
</body>
</html>
