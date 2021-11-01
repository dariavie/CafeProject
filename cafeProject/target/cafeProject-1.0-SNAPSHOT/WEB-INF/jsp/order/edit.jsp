<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%--<link rel="stylesheet" href="css/bootstrap.css">--%>
<%--<script src="js/bootstrap.js"></script>--%>
<c:choose>
    <c:when test="${not empty order}">
        <c:if test="${not empty message}">
            <c:set var="title" value="${message}"/>
        </c:if>
        <c:if test="${not empty order.clientId}">
            <c:set var="clientName" value="${order.clientId.name}"/>
        </c:if>
        <c:set var="price" value="${order.price}"/>
        <c:set var="status" value="${order.orderStatus}"/>
        <c:set var="title" value="${orderInChange} ${order.id}"/>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty message}">
            <c:set var="title" value="${message}"/>
        </c:if>
        <c:set var="title" value="${orderNew}"/>
    </c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}">
    <H2>${title}</H2>
    <c:url value="/order/save.html" var="orderSaveUrl"/>
    <FORM action="${orderSaveUrl}" method="post" onsubmit="return validateEditOrder(this)">
        <c:if test="${not empty order}">
            <INPUT type="hidden" name="id" value="${order.id}">
        </c:if>
        <H1>${message}</H1>
        <label for="foods">${foodChoose}</label>
        <select id="foods" name="foods" multiple="multiple" size="5">
            <c:forEach items="${foods}" var="food">
                <option>${food.title}</option>
            </c:forEach>
        </select>
        <BUTTON type="${save}">Сохранить</BUTTON>
        <BUTTON type="${reset}">Сбросить</BUTTON>
        <br/>
        <a href="/cafeProject_war_exploded/food/edit.html" style="color:black">${foodCreate}</a>
    </FORM>
</u:html>