<%--
  Created by IntelliJ IDEA.
  User: dariarogovets
  Date: 16.09.21
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<link rel="stylesheet" href="css/bootstrap.css">--%>
<%--<script src="js/bootstrap.js"></script>--%>
<%--<@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%--<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Error Page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--    Request from ${pageContext.errorData.requestURI} is failed--%>
<%--    <br/>--%>
<%--    Servlet name or type: ${pageContext.errorData.servletName}--%>
<%--    <br/>--%>
<%--    Status code: ${pageContext.errorData.statusCode}--%>
<%--    <br/>--%>
<%--    Exception: ${pageContext.errorData.throwable}--%>
<%--</body>--%>
<%--</html>--%>

<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Ошибка">
    <c:choose>
        <c:when test="${not empty error}">
            <H2>${error}</H2>
        </c:when>
        <c:when test="${not empty pageContext.errorData.requestURI}">
            <H2>Запрошенная страница ${pageContext.errorData.requestURI} не найдена на сервере</H2>
        </c:when>
        <c:otherwise>Непредвиденная ошибка приложения</c:otherwise>
    </c:choose>
    <c:url value="/index.html" var="mainUrl"/>
    <A href="${mainUrl}">На главную</A>
</u:html>