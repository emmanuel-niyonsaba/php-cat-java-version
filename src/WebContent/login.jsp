<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Employee Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #87CEFA, #ffffff); /* BlueSky to White */
            padding: 50px;
        }
        .login-form {
            max-width: 400px;
            margin: auto;
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        input[type=text], input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 10px 0 15px 0;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #1E90FF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover { background: #187bcd; }
        .remember {
            display: flex;
            align-items: center;
        }
        .remember input { margin-right: 10px; }
        .links { margin-top: 15px; text-align: center; }
        .links a { color: #1E90FF; text-decoration: none; }
        .links a:hover { text-decoration: underline; }
    </style>
</head>
<body>

<div class="login-form">
    <h2>Login</h2>
    <form method="post" action="LoginServlet">
        <label>Username:</label>
        <input type="text" name="username" required placeholder="Enter username">

        <label>Password:</label>
        <input type="password" name="password" required placeholder="Enter password">

        <div class="remember">
            <input type="checkbox" name="remember">
            <label>Remember Me (7 days)</label>
        </div>

        <button type="submit">Login</button>
    </form>

    <div class="links">
        <a href="register.jsp">Register here</a> | 
        <a href="homepage.jsp">Back to Home</a>
    </div>
</div>

</body>
</html>
