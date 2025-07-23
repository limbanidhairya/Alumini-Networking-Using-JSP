<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        h2 {
            color: #007bff;
        }
        form {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            padding: 30px;
            max-width: 600px;
            margin: auto;
        }
        label {
            color: #333;
            font-size: 16px;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 20px 0;
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
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>User Registration</h2>
    <form action="UserRegister_Servlet" method="post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username" required><br>
        
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" required><br>
        
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br>
         
        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" required><br>
        
        <label for="contact">Contact:</label><br>
        <input type="text" id="contact" name="contact" required><br><br>
        
        <input type="submit" value="Register">
    </form>
    <a href="./index.html">Home</a>
</body>
</html>
