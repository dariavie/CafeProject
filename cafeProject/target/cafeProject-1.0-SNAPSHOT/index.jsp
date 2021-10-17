<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to iFresh</title>
</head>
<body>
<jsp:forward page="/WEB-INF/jsp/login.jsp"></jsp:forward>
<%--<h1><%= "What would you like to do?" %>--%>
<%--</h1>--%>
<%--<br/>--%>
<%--<a href="jsp/login.jsp"> Sign in </a>--%>
<%--<br/>--%>
<%--<a href="jsp/registration.jsp">Sign up</a>--%>
<%--<br/>--%>
<%--<a href="list-of-dishes">See all dishes</a>--%>
</body>
</html>