<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Registration</title>
</head>
<body>
<p>Регистрация нового пользователя системы</p>

<form method="POST">
    Email: <input type="text" name="email"/>
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="password"/>
    <input type="submit" value="Зарегистрироваться">
</form>
<a href="http://localhost:8080/">Войти, если уже зарегистрирован.</a>
</body>
</html>