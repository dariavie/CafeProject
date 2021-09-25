<%--
  Created by IntelliJ IDEA.
  User: dariarogovets
  Date: 20.09.21
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<h3>Sign up:</h3>
<form name="RegistrationForm" method="POST" action="/cafeProject_war_exploded/controller">
    <input type="hidden" name="command" value="signup" />
    <tr>
        <td>Login</td>
        <td><input type="text" name="login"/> </td>
    </tr>
    <tr>
        <td>Password</td>
        <td><input type="password" name="password"/>
        </td>
    </tr>
    <tr>
        <td>Name</td>
        <td><input type="text" name="name"/> </td>
    </tr>
    <tr>
        <td>Surname</td>
        <td><input type="text" name="surname"/> </td>
    </tr>
    <tr>
        <td>Phone</td>
        <td><input type="tel" name="phone"> </td>
    </tr>
    <tr>
        <td>Email</td>
        <td><input type="email" name="email">
        <br/>
        ${errorLoginPassMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        </td>
    </tr>
    <input type="submit" value="Sign up">
</form>
</body>
</html>
