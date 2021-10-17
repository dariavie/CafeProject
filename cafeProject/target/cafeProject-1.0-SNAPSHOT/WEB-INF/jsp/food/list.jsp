<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<u:html title="блюда" message="${message}">
    <FORM action="/order/list.html" method="post">
    </FORM>
    <H1>${message}</H1>
    <H2>Список блюд</H2>
    <TABLE>
        <TR>
            <TH>Название</TH>
            <TH>Описание</TH>
            <TH>Цена</TH>
            <TH>Тип</TH>
            <th>Ингредиенты</th>
        </TR>
        <c:url value="/food/edit.html" var="foodEditUrl"/>
<%--        <c:url value="/client/delete.html" var="clientDeleteUrl"/>--%>
        <c:forEach items="${foods}" var="food">
            <%--            <c:choose>--%>
            <%--                <c:when test="${not empty client.overdueUsages}">--%>
            <%--                    <c:set var="classname" value="special"/>--%>
            <%--                </c:when>--%>
            <%--                <c:otherwise>--%>
            <%--                    <c:remove var="classname"/>--%>
            <%--                </c:otherwise>--%>
            <%--            </c:choose>--%>
            <TR onclick="submitFormById('form-${food.id}')" class="${classname}">

                    <%--                        ${reader.libraryCardNumber}--%>
                <FORM id="form-${food.id}" action="${foodEditUrl}" method="post">
                    <INPUT type="hidden" name="id" value="${food.id}">
                </FORM>

                <TD>${food.title}</TD>
                <TD>${food.description}</TD>
                <TD>${food.price}</TD>
                <TD>${food.type}</TD>
                <TD>
                        <c:forEach items="${food.ingredients}" var="item">
                            ${item.title},
                        </c:forEach>
                </TD>
            </TR>
        </c:forEach>
    </TABLE>
    <FORM action="${foodEditUrl}" method="post">
        <BUTTON type="submit">Добавить</BUTTON>
    </FORM>
</u:html>