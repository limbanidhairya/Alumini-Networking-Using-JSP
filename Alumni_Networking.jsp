<!DOCTYPE html>
<html>
<head>
    <title>Alumni Networking</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        h1 {
            text-align: center;
            margin-top: 50px;
            color: #333;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        form {
            margin-bottom: 20px;
        }
        input[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        input[type="hidden"] {
            display: none;
        }
    </style>
</head>
<body>

<h1>Welcome to Alumni Networking</h1>

<div class="container">
    <!-- Form for adding user's student details -->
    <form action="AddStudentDetails.jsp" method="post">
        <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
        <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
        <input type="hidden" name="userid" value="<%= request.getParameter("userid") %>">
        <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
        <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
        <input type="submit" value="Add Student Details">
    </form>

    <form action="UpdateStudentDetails.jsp" method="post">
    <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
    <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
    <input type="hidden" name="userid" value="<%= request.getParameter("userid") %>">
    <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
    <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
    <input type="submit" value="Update Student Details">
</form>

<!-- Form for displaying user's student details -->
<form action="DisplayStudentDetails_Servlet" method="post">
    <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
    <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
    <input type="hidden" name="userid" value="<%= request.getParameter("userid") %>">
    <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
    <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
    <input type="submit" value="Display Student Details">
</form>

<!-- Form for displaying all alumni -->
<form action="DisplayAllAlumni_Servlet" method="post">
    <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
    <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
    <input type="hidden" name="userid" value="<%= request.getParameter("userid") %>">
    <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
    <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
    <input type="submit" value="Display All Alumni">
</form>

<!-- Form for deleting user's student details -->
<form action="DeleteStudentDetails.jsp" method="post">
    <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
    <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
    <input type="hidden" name="userid" value="<%= request.getParameter("userid") %>">
    <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
    <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
    <input type="submit" value="Delete Student Details">
</form>
<form action="LoginServlet" method="post">
        <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
        <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
        <button type="submit">My Dashboard</button>
    </form>
</div>

</body>
</html>
