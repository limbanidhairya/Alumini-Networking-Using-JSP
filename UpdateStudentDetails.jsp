<!DOCTYPE html>
<html>
<head>
    <title>Update Student Details</title>
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
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
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
    </style>
</head>
<body>

<div class="container">
    <h2>Update Student Details</h2>
    <form action="UpdateStudentDetails_Servlet" method="post">
        <div class="form-group">
            <label for="school">School:</label>
            <input type="text" id="school" name="school" value="<%= request.getParameter("school") %>" required>
        </div>
        <div class="form-group">
            <label for="department">Department:</label>
            <input type="text" id="department" name="department" value="<%= request.getParameter("department") %>" required>
        </div>
        <div class="form-group">
            <label for="rollNumber">Roll Number:</label>
            <input type="text" id="rollNumber" name="rollNumber" value="<%= request.getParameter("rollNumber") %>" required>
        </div>
        <div class="form-group">
            <label for="yearOfPassing">Year of Passing:</label>
            <input type="text" id="yearOfPassing" name="yearOfPassing" value="<%= request.getParameter("yearOfPassing") %>" required>
        </div>
        <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
        <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
        <input type="hidden" name="userid" value="<%= request.getParameter("userid") %>">
        <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
        <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
        <input type="submit" value="Update">
    </form>
</div>

</body>
</html>
