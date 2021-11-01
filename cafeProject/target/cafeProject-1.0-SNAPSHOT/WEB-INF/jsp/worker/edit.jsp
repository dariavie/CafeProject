<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%--<link rel="stylesheet" href="css/bootstrap.css">--%>
<%--<script src="js/bootstrap.js"></script>--%>
<c:choose>
    <c:when test="${not empty worker}">
        <c:if test="${not empty message}">
            <c:set var="title" value="${message}"/>
        </c:if>
        <c:if test="${not empty worker.userInfoId}">
            <c:if test="${not empty worker.userInfoId.userId}">
                <c:set var="login" value="${worker.userInfoId.userId.login}"/>
                <c:set var="password" value="${worker.userInfoId.userId.password}"/>
            </c:if>
            <c:set var="surname" value="${worker.userInfoId.surname}"/>
            <c:set var="name" value="${worker.userInfoId.name}"/>
            <c:set var="phone" value="${worker.userInfoId.phone}"/>
            <c:set var="email" value="${worker.userInfoId.email}"/>
        </c:if>
        <c:set var="startOfWork" value="${worker.startOfWork}"/>
        <c:if test="${not empty worker.endOfWork}">
            <c:set var="endOfWork" value="${worker.endOfWork}"/>
        </c:if>
        <c:set var="specialization" value="${worker.specialization}"/>
        <c:set var="title" value="${workerInChange} ${worker.userInfoId.surname} ${worker.userInfoId.name}"/>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty message}">
            <c:set var="title" value="${message}"/>
        </c:if>
        <c:set var="title" value="${workerNew}"/>
    </c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}">
    <H2>${title}</H2>
    <c:url value="/worker/save.html" var="workerSaveUrl"/>
    <FORM action="${workerSaveUrl}" method="post" onsubmit="return validateEditWorker(this)">
        <INPUT type="hidden" name="locale" value="${locale}">
    <c:if test="${not empty worker}">
        <INPUT type="hidden" name="id" value="${worker.id}">
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
    <LABEL for="startOfWork">${startDatePar}</LABEL>
    <INPUT type="date" id="startOfWork" name="startOfWork" value="${startOfWork}">
    <LABEL for="endOfWork">${endDatePar}</LABEL>
    <INPUT type="date" id="endOfWork" name="endOfWork" value="${endOfWork}">
    <LABEL for="specialization">${specializationPar}</LABEL>
    <INPUT type="text" id="specialization" name="specialization" value="${specialization}">
    <BUTTON type="submit">${save}</BUTTON>
    <BUTTON type="reset">${reset}</BUTTON>
    </FORM>
    <c:if test="${not empty worker}">
        <c:url value="/worker/delete.html" var="workerDeleteUrl"/>
    </c:if>
</u:html>