<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="message" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="validator" required="false" rtexprvalue="true" type="java.lang.String"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<HTML>
<HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <META name="viewport" content="width=device-width, initial-scale=1">
    <TITLE>Кафе - ${title}</TITLE>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous"></script>
    <c:if test="${not empty message}">
        <SCRIPT type="text/javascript">
            startMessage = "${message}";
        </SCRIPT>
    </c:if>
    <c:if test="${not empty validator}">
        <SCRIPT type="text/javascript" src="${javascriptUrl}/js/validator.js"></SCRIPT>
        <SCRIPT type="text/javascript" src="${javascriptUrl}/${validator}"></SCRIPT>
    </c:if>
</HEAD>
<BODY>
<u:menu/>
<DIV id="page">
    <jsp:doBody/>
</DIV>
</BODY>
</HTML>