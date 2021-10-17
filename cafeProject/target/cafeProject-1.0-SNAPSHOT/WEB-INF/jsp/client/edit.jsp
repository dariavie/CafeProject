<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%--<link rel="stylesheet" href="css/bootstrap.css">--%>
<%--<script src="js/bootstrap.js"></script>--%>
<c:choose>
  <c:when test="${not empty client}">
    <c:if test="${not empty message}">
      <c:set var="title" value="${message}"/>
    </c:if>
    <c:if test="${not empty client.userId}">
      <c:set var="login" value="${client.userId.login}"/>
      <c:set var="password" value="${client.userId.password}"/>
    </c:if>
    <c:set var="surname" value="${client.surname}"/>
    <c:set var="name" value="${client.name}"/>
    <c:set var="phone" value="${client.phone}"/>
    <c:set var="email" value="${client.email}"/>
    <c:set var="title" value="Посетитель ${client.surname} ${client.name}"/>
  </c:when>
  <c:otherwise>
    <c:if test="${not empty message}">
      <c:set var="title" value="${message}"/>
    </c:if>
    <c:set var="title" value="Новый посетитель"/>
  </c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}">
  <H2>${title}</H2>
  <c:url value="/client/save.html" var="clientSaveUrl"/>
  <FORM action="${clientSaveUrl}" method="post" onsubmit="return validateEditClient(this)">
    <c:if test="${not empty client}">
      <INPUT type="hidden" name="id" value="${client.id}">
    </c:if>
    <H1>${message}</H1>
    <LABEL for="login">Login</LABEL>
    <INPUT type="text" id="login" name="login" value="${login}">
    <LABEL for="password">Password</LABEL>
    <INPUT type="password" id="password" name="password" value="${password}">
    <LABEL for="surname">Фамилия:</LABEL>
    <INPUT type="text" id="surname" name="surname" value="${surname}">
    <LABEL for="name">Имя:</LABEL>
    <INPUT type="text" id="name" name="name" value="${name}">
    <LABEL for="phone">Телефон:</LABEL>
    <INPUT type="text" id="phone" name="phone" value="${phone}">
    <LABEL for="email">email:</LABEL>
    <INPUT type="email" id="email" name="email" value="${email}">
    <BUTTON type="submit">Сохранить</BUTTON>
    <c:if test="${not empty client}">
<%--      <c:if test="${not empty client.returnedUsages or not empty reader.currentUsages or not empty reader.overdueUsages}">--%>
        <c:set var="disabled" value="disabled"/>
<%--      </c:if>--%>
      <BUTTON type="button" onclick="submitFormById('form-delete')" ${disabled}>Удалить</BUTTON>
    </c:if>
    <BUTTON type="reset">Сбросить</BUTTON>
  </FORM>
  <c:if test="${not empty client}">
    <c:url value="/client/delete.html" var="clientDeleteUrl"/>
    <FORM action="${clientDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить посетителя?')">
      <INPUT type="hidden" name="id" value="${client.id}">
    </FORM>
<%--    <c:if test="${not empty client.overdueUsages}">--%>
<%--      <H2>Список невозвращённых вовремя книг</H2>--%>
<%--      <TABLE>--%>
<%--        <TR>--%>
<%--          <TH>Автор</TH>--%>
<%--          <TH>Название</TH>--%>
<%--          <TH>Дата выдачи</TH>--%>
<%--          <TH>Планируемая дата возврата</TH>--%>
<%--        </TR>--%>
<%--        <c:forEach items="${reader.overdueUsages}" var="usage">--%>
<%--          <TR>--%>
<%--            <TD>--%>
<%--              <c:choose>--%>
<%--                <c:when test="${not empty usage.book.author}">--%>
<%--                  <u:person value="${usage.book.author}"/>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                  Без автора--%>
<%--                </c:otherwise>--%>
<%--              </c:choose>--%>
<%--            </TD>--%>
<%--            <TD>${usage.book.title}</TD>--%>
<%--            <TD><fmt:formatDate value="${usage.deliveryDate}" pattern="dd.MM.yyyy"/></TD>--%>
<%--            <TD><fmt:formatDate value="${usage.planReturnDate}" pattern="dd.MM.yyyy"/></TD>--%>
<%--          </TR>--%>
<%--        </c:forEach>--%>
<%--      </TABLE>--%>
<%--    </c:if>--%>
  </c:if>
</u:html>