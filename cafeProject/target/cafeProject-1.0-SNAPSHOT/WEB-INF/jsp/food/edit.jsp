<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%--<link rel="stylesheet" href="css/bootstrap.css">--%>
<%--<script src="js/bootstrap.js"></script>--%>
<c:choose>
    <c:when test="${not empty food}">
        <c:if test="${not empty message}">
            <c:set var="title" value="${message}"/>
        </c:if>
        <c:set var="foodTitle" value="${food.title}"/>
        <c:set var="description" value="${food.description}"/>
        <c:set var="type" value="${food.type}"/>
        <c:if test="${not empty food.ingredients}">
            <c:set var="foodIngredients" value="${food.ingredients}"/>
        </c:if>
        <c:set var="title" value="Блюдо ${food.title}"/>
        <c:if test="${not empty ingredients}">
            <c:set var="ingredients" value="${ingredients}"/>
        </c:if>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty message}">
            <c:set var="title" value="${message}"/>
        </c:if>
        <c:if test="${not empty ingredients}">
            <c:set var="ingredients" value="${ingredients}"/>
        </c:if>
        <c:set var="title" value="Новое блюдо"/>
    </c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}">
    <H2>${title}</H2>
    <c:url value="/food/save.html" var="foodSaveUrl"/>
    <FORM action="${foodSaveUrl}" method="post" onsubmit="return validateEditFood(this)">
        <c:if test="${not empty food}">
            <INPUT type="hidden" name="id" value="${food.id}">
        </c:if>
        <H1>${message}</H1>
        <LABEL for="foodTitle">Название</LABEL>
        <INPUT type="text" id="foodTitle" name="foodTitle" value="${foodTitle}">
        <LABEL for="description">Описание</LABEL>
        <INPUT type="text" id="description" name="description" value="${description}">
        <LABEL for="type">Тип</LABEL>
        <select id="type" name="type" size="2">
            <option>блюдо</option>
            <option>напиток</option>
        </select>
<%--        <INPUT type="text" id="type" name="type" value="${type}">--%>
<%--        <c:forEach items="${ingredients}" var="item">--%>
            <LABEL for="ingredients">Ингредиенты</LABEL>
<%--            <input type="text" id="ingredient" name="ingredient" value="${item.title}, ">--%>
<%--        </c:forEach>--%>
            <select id="ingredients" name="ingredients" multiple="multiple" size="5">
                <c:forEach items="${ingredients}" var="ingredient">
                    <option>${ingredient.title}</option>
                </c:forEach>
            </select>
<%--        <INPUT type="text" id="ingredients" name="ingredients" value="${ingredients}">--%>

        <BUTTON type="submit">Сохранить</BUTTON>
        <c:if test="${not empty food}">
            <%--      <c:if test="${not empty client.returnedUsages or not empty reader.currentUsages or not empty reader.overdueUsages}">--%>
            <c:set var="disabled" value="disabled"/>
            <%--      </c:if>--%>
            <BUTTON type="button" onclick="submitFormById('form-delete')" ${disabled}>Удалить</BUTTON>
        </c:if>
        <BUTTON type="reset">Сбросить</BUTTON>
    </FORM>
<%--    <c:if test="${not empty client}">--%>
<%--        <c:url value="/food/delete.html" var="clientDeleteUrl"/>--%>
<%--        <FORM action="${clientDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить посетителя?')">--%>
<%--            <INPUT type="hidden" name="id" value="${client.id}">--%>
<%--        </FORM>--%>
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
<%--    </c:if>--%>
</u:html>