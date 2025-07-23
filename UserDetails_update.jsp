<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<style>
    /* Internal CSS styling */
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f2f9fc;
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
        max-width: 400px;
        margin: auto;
    }
    h1 {
        color: #007bff;
        font-size: 24px;
        margin-bottom: 20px;
    }
    form {
        margin-top: 20px;
    }
    label {
        color: #333;
        font-size: 16px;
    }
    input[type="text"],
    input[type="password"],
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }
    input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 10px 20px;
        cursor: pointer;
        font-size: 16px;
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<%
String username = (String) request.getParameter("login_username");
String password = (String) request.getParameter("login_pass");
int userid = Integer.parseInt(request.getParameter("userid"));
%>
<body>
<div class="container">
    <h1>Alumni Networking Web Application</h1>
    <form action="./Upd_Details_Servlet" method="post">
        <label for="first_name">Username:</label><br>
        <input type="text" id="username" name="username" required><br>
        
        <label for="first_name">Name:</label><br>
        <input type="text" id="name" name="name" required><br>
        
        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" required><br>
        
        <label for="contact">Contact:</label><br>
        <input type="text" id="contact" name="contact" required><br><br>
        
        <input type="hidden" name="user_id" value=<%= userid %>>
        <input type="hidden" name="username" value="<%= username %>">
        <input type="hidden" name="login_pass" value=<%= password %>>
        <input type="submit"value="Submit"/>
    </form>
    <form action="LoginServlet" method="post">
        <input type="hidden" name="username" value="<%= username %>">
        <input type="hidden" name="password" value="<%= password %>">
        <input type="submit" value="My Dashboard"> 
    </form>
</div>
</body>
</html>
