<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alumni Networking</title>
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
        max-width: 600px;
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
    table {
        width: 100%;
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        width: 30%;
    }
    input[type="text"],
    input[type="password"],
    input[type="submit"],
    input[type="button"] {
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
    input[type="button"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 10px 20px;
        cursor: pointer;
        font-size: 16px;
        text-decoration: none;
        display: inline-block;
        margin-right: 10px;
    }
    input[type="button"]:last-child {
        margin-right: 0;
    }
    input[type="button"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<div class="container">
    <h1>Welcome to Presidency University! Alumni Networking Web Application</h1>
    <form action="LoginServlet" method="post"> 
        <table>
            <tr>
                <th>Username:</th>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <th>Password:</th>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Login">
                    <a href="./index.html"><input type="button" value="Home"></a>
                    <a href="./User_Register.jsp"><input type="button" value="Register"></a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
