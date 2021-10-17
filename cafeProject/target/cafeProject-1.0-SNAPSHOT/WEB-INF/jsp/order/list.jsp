<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<u:html title="блюда" message="${message}">
    <H1>${message}</H1>
    <H2>Список заказов</H2>
    <TABLE>
        <TR>
            <TH>Имя клиента</TH>
            <TH>Блюда</TH>
            <TH>Стоимость</TH>
            <TH>Статус</TH>
        </TR>
        <c:url value="/order/edit.html" var="orderEditUrl"/>
            <%--        <c:url value="/client/delete.html" var="clientDeleteUrl"/>--%>
        <c:forEach items="${orders}" var="order">
            <%--            <c:choose>--%>
            <%--                <c:when test="${not empty client.overdueUsages}">--%>
            <%--                    <c:set var="classname" value="special"/>--%>
            <%--                </c:when>--%>
            <%--                <c:otherwise>--%>
            <%--                    <c:remove var="classname"/>--%>
            <%--                </c:otherwise>--%>
            <%--            </c:choose>--%>
            <TR onclick="submitFormById('form-${order.id}')" class="${classname}">

                    <%--                        ${reader.libraryCardNumber}--%>
                <FORM id="form-${order.id}" action="${orderEditUrl}" method="post">
                    <INPUT type="hidden" name="id" value="${order.id}">
                </FORM>
                <TD>${order.clientId.name}</TD>
                <TD>
                    <c:forEach items="${order.foods}" var="item">
                        ${item.title},
                    </c:forEach>
                </TD>
                <TD>${order.price}</TD>
                <TD>${order.orderStatus}</TD>
            </TR>
        </c:forEach>
    </TABLE>
    <FORM action="${orderEditUrl}" method="post">
        <BUTTON type="submit">Создать новый</BUTTON>
    </FORM>
</u:html>