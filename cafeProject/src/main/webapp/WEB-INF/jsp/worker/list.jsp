<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<u:html title="Работники" message="${message}">
    <H1>${message}</H1>
<%--    <H2>Список работников</H2>--%>
<%--    <TABLE>--%>
<%--        <TR>--%>
<%--            <TH>Логин</TH>--%>
<%--            <TH>Пароль</TH>--%>
<%--            <TH>Фамилия</TH>--%>
<%--            <TH>Имя</TH>--%>
<%--            <TH>Телефон</TH>--%>
<%--            <TH>email</TH>--%>
<%--            <TH>Дата начала работы</TH>--%>
<%--            <TH>Дата окончания работы</TH>--%>
<%--            <TH>Специализация</TH>--%>
<%--        </TR>--%>
    <H2>${list}</H2>
    <TABLE>
        <TR>
            <TH>${login}</TH>
            <TH>${password}</TH>
            <TH>${surname}</TH>
            <TH>${name}</TH>
            <TH>${phone}</TH>
            <TH>${email}</TH>
            <TH>${startDate}</TH>
            <TH>${endDate}</TH>
            <TH>${specialization}</TH>
        </TR>
        <c:url value="/worker/edit.html" var="workerEditUrl"/>
        <c:url value="/worker/delete.html" var="workerDeleteUrl"/>
        <c:forEach items="${workers}" var="worker">
            <TR>
                    <FORM id="form-${worker.id}" action="${workerEditUrl}" method="post">
                        <INPUT type="hidden" name="id" value="${worker.id}">
                    </FORM>
                <TD>${worker.userInfoId.userId.login}</TD>
                <TD>${worker.userInfoId.userId.password}</TD>
                <TD>${worker.userInfoId.surname}</TD>
                <TD>${worker.userInfoId.name}</TD>
                <TD>${worker.userInfoId.phone}</TD>
                <TD>${worker.userInfoId.email}</TD>
                <TD>${worker.startOfWork}</TD>
                <TD>${worker.endOfWork}</TD>
                <TD>${worker.specialization}
                    <FORM action="${workerDeleteUrl}" method="post">
                        <INPUT type="hidden" name="id" value="${worker.id}">
                        <INPUT type="hidden" name="locale" value="${locale}">
                        <BUTTON type="submit">${remove}</BUTTON>
                    </FORM>
                    <FORM action="${workerEditUrl}" method="post">
                        <INPUT type="hidden" name="id" value="${worker.id}">
                        <INPUT type="hidden" name="locale" value="${locale}">
                        <BUTTON type="submit">${change}</BUTTON>
                    </FORM>
                </TD>
            </TR>
        </c:forEach>
    </TABLE>
    <FORM action="${workerEditUrl}" method="post">
        <INPUT type="hidden" name="locale" value="${locale}">
        <BUTTON type="submit">${add}</BUTTON>
    </FORM>
</u:html>