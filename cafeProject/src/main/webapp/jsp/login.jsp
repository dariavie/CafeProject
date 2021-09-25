<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h3>Sign in:</h3>
<form name="LoginForm" method="POST" action="/cafeProject_war_exploded/controller">
    <input type="hidden" name="command" value="login" />
    <tr>
        <td>Login</td>
        <td><input type="text" name="login"/> </td>
    </tr>
    <tr>
        <td>Password</td>
        <td><input type="password" name="password"/>
            <br/>
                ${errorLoginPassMessage}
            <br/>
                ${wrongAction}
            <br/>
                ${nullPage}
        </td>
    </tr>
    <input type="submit" value="Sign in">
</form>
</body>
</html>

