<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<u:html title="Заказы" message="${message}">
    <H1>${message}</H1>
    <H2>${list}</H2>
    <TABLE>
        <TR>
            <TH>${name}</TH>
            <TH>${dishes}</TH>
            <TH>${price}</TH>
            <TH>${status}</TH>
        </TR>
        <c:url value="/order/edit.html" var="orderEditUrl"/>
        <c:forEach items="${orders}" var="order">
            <TR>
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
        <INPUT type="hidden" name="locale" value="${locale}">
        <BUTTON type="submit">${add}</BUTTON>
    </FORM>
</u:html>