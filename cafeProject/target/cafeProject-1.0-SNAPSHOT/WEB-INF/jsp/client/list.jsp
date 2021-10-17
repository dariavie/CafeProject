<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<u:html title="посетители" message="${message}">
    <H1>${message}</H1>
    <H2>Список посетителей</H2>
    <TABLE>
        <TR>
            <TH>Логин</TH>
            <TH>Пароль</TH>
            <TH>Фамилия</TH>
            <TH>Имя</TH>
            <TH>Телефон</TH>
            <TH>email</TH>
        </TR>
        <c:url value="/client/edit.html" var="clientEditUrl"/>
        <c:url value="/client/delete.html" var="clientDeleteUrl"/>
        <c:forEach items="${clients}" var="client">
<%--            <c:choose>--%>
<%--                <c:when test="${not empty client.overdueUsages}">--%>
<%--                    <c:set var="classname" value="special"/>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <c:remove var="classname"/>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
            <TR onclick="submitFormById('form-${client.id}')" class="${classname}">

<%--                        ${reader.libraryCardNumber}--%>
                    <FORM id="form-${client.id}" action="${clientEditUrl}" method="post">
                        <INPUT type="hidden" name="id" value="${client.id}">
                    </FORM>

                <TD>${client.userId.login}</TD>
                <TD>${client.userId.password}</TD>
                <TD>${client.surname}</TD>
                <TD>${client.name}</TD>
                <TD>${client.phone}</TD>
                <TD>${client.email}
                    <FORM action="${clientDeleteUrl}" method="post">
                        <INPUT type="hidden" name="email" value="${client.email}">
                        <BUTTON type="submit">Удалить посетителя</BUTTON>
                    </FORM>
                    <FORM action="${clientEditUrl}" method="post">
                        <INPUT type="hidden" name="id" value="${client.userId.id}">
                        <BUTTON type="submit">Изменить посетителя</BUTTON>
                    </FORM>
                </TD>
            </TR>
        </c:forEach>
    </TABLE>
    <FORM action="${clientEditUrl}" method="post">
        <BUTTON type="submit">Добавить</BUTTON>
    </FORM>
</u:html>