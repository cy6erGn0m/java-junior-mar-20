<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form method="post" action="login" enctype="application/x-www-form-urlencoded">
    <p>
        <label>
            Login:
            <input type="text" name="usernameField" value="${param['login']}">
        </label>
    </p>
    <p>
        <label>
            Password:
            <input type="password" name="passwordField">
        </label>
    </p>

    <p>
        <input type="submit" value="Login">
    </p>
</form>
</body>
</html>
