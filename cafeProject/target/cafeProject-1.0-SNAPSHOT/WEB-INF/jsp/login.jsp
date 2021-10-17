<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<link rel="stylesheet" href="css/bootstrap.css">--%>
<%--<script src="js/bootstrap.js"></script>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Login page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h3>Sign in:</h3>--%>
<%--<form name="LoginForm" method="POST" action="/cafeProject_war_exploded/controller">--%>
<%--    <input type="hidden" name="command" value="login" />--%>
<%--    <tr>--%>
<%--        <td>Login</td>--%>
<%--        <td><input type="text" name="login"/> </td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td>Password</td>--%>
<%--        <td><input type="password" name="password"/>--%>
<%--            <br/>--%>
<%--                ${errorLoginPassMessage}--%>
<%--            <br/>--%>
<%--                ${wrongAction}--%>
<%--            <br/>--%>
<%--                ${nullPage}--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <input type="submit" value="Sign in">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%--<link rel="stylesheet" href="css/bootstrap.css">--%>
<%--<script src="js/bootstrap.js"></script>--%>
<u:html title="Вход в систему" message="${message}">
    <H2>Вход в систему</H2>
    <c:url value="/login.html" var="loginUrl"/>
    <FORM action="${loginUrl}" method="post">
        <LABEL for="login">Имя пользователя:</LABEL>
        <INPUT type="text" id="login" name="login" value="${param.login}">
        <LABEL for="password">Пароль:</LABEL>
        <INPUT type="password" id="password" name="password">
        <BUTTON type="submit">Войти</BUTTON>
    </FORM>
</u:html>