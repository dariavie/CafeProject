<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%--<link rel="stylesheet" href="css/bootstrap.css">--%>
<%--<script src="js/bootstrap.js"></script>--%>
<u:html title="Вход в систему" message="${message}">
<%--    <H2>Вход в систему</H2>--%>
    <c:choose>
        <c:when test="${not empty entry}">
            <H2>${entry}</H2>
        </c:when>
        <c:otherwise>
            <H2>Sign in</H2>
        </c:otherwise>
    </c:choose>
    <c:url value="/login.html" var="loginUrl"/>
    <FORM action="${loginUrl}" method="post">
<%--        <LABEL for="login">Имя пользователя:</LABEL>--%>
        <c:choose>
            <c:when test="${not empty login}">
                <LABEL for="login">${login}</LABEL>
            </c:when>
            <c:otherwise>
                <LABEL for="login">Login</LABEL>
            </c:otherwise>
        </c:choose>
        <INPUT type="text" id="login" name="login" value="${param.login}">
<%--        <LABEL for="password">Пароль:</LABEL>--%>
        <c:choose>
            <c:when test="${not empty password}">
                <LABEL for="password">${password}</LABEL>
            </c:when>
            <c:otherwise>
                <LABEL for="password">Password</LABEL>
            </c:otherwise>
        </c:choose>
        <INPUT type="password" id="password" name="password">
<%--        <BUTTON type="submit">Войти</BUTTON>--%>
        <c:choose>
            <c:when test="${not empty enter}">
                <BUTTON type="submit">${enter}</BUTTON>
            </c:when>
            <c:otherwise>
                <BUTTON type="submit">Enter</BUTTON>
            </c:otherwise>
        </c:choose>
    </FORM>
</u:html>