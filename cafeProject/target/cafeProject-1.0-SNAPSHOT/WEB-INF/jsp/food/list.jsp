<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<u:html title="Блюда" message="${message}">
<%--    <c:url value="/food/list.html" var="foodList"/>--%>
<%--        язык:<br/>--%>
<%--    <form action="${foodList}" method="post">--%>
<%--            <button type="submit">--%>
<%--                <input type="hidden" name="locale" value="ru">--%>
<%--                ru <br/>--%>
<%--            </button>--%>
<%--    </form>--%>
<%--    <form action="${foodList}" method="post">--%>
<%--            <button type="submit">--%>
<%--                <input type="hidden" name="locale" value="en">--%>
<%--                en <br/>--%>
<%--            </button>--%>
<%--    </form>--%>
    <FORM action="/order/list.html" method="post">
    </FORM>
    <H1>${message}</H1>
    <H2>${list}</H2>
    <TABLE>
        <TR>
            <TH>${foodTitle}</TH>
            <TH>${description}</TH>
            <TH>${price}</TH>
            <TH>${type}</TH>
            <th>${ingredients}</th>
        </TR>
        <c:url value="/food/edit.html" var="foodEditUrl"/>
        <c:forEach items="${foods}" var="food">
            <TR>
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
        <INPUT type="hidden" name="locale" value="${locale}">
        <BUTTON type="submit">${add}</BUTTON>
    </FORM>
</u:html>