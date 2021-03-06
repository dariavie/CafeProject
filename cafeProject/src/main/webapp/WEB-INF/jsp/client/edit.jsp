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
    <c:set var="title" value="${clientInChange} ${client.surname} ${client.name}"/>
  </c:when>
  <c:otherwise>
    <c:if test="${not empty message}">
      <c:set var="title" value="${message}"/>
    </c:if>
    <c:set var="title" value="${clientNew}"/>
  </c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}">
  <H2>${title}</H2>
  <c:url value="/client/save.html" var="clientSaveUrl"/>
  <FORM action="${clientSaveUrl}" method="post" onsubmit="return validateEditClient(this)">
    <INPUT type="hidden" name="locale" value="${locale}">
    <c:if test="${not empty client}">
      <INPUT type="hidden" name="id" value="${client.id}">
    </c:if>
    <H1>${message}</H1>
    <LABEL for="login">${loginPar}</LABEL>
    <INPUT type="text" id="login" name="login" value="${login}">
    <LABEL for="password">${passwordPar}</LABEL>
    <INPUT type="password" id="password" name="password" value="${password}">
    <LABEL for="surname">${surnamePar}</LABEL>
    <INPUT type="text" id="surname" name="surname" value="${surname}">
    <LABEL for="name">${namePar}</LABEL>
    <INPUT type="text" id="name" name="name" value="${name}">
    <LABEL for="phone">${phonePar}</LABEL>
    <INPUT type="text" id="phone" name="phone" value="${phone}">
    <LABEL for="email">${emailPar}</LABEL>
    <INPUT type="email" id="email" name="email" value="${email}">
    <BUTTON type="submit">${save}</BUTTON>
    <BUTTON type="reset">${reset}</BUTTON>
  </FORM>
</u:html>