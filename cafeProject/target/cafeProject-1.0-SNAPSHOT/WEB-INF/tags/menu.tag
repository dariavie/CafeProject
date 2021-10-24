<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous"></script>

<%--<nav class="navbar navbar-default">--%>
<%--    <div class="container-fluid">--%>
<%--        <div class="navbar-header">--%>
<%--            <c:url value="/img/logo_img.jpeg" var="logoUrl"/>--%>
<%--                        <IMG class="arrow" src="${logoUrl}" width="200px">Кафе «IFresh»--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            <c:if test="${not empty authorizedUser}">--%>
<%--                            <div class="row" style="background-color: #c1d6cc">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <ul>--%>
<%--                                        <c:forEach items="${menu}" var="item">--%>
<%--                                            <c:url value="${item.url}" var="itemUrl"/>--%>
<%--                                            <LI class="item"><A href="${itemUrl}" style="color: #1c3636">${item.name}</A></LI>--%>
<%--                                        </c:forEach>--%>
<%--                                    </ul>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <A href="#" class="drop-button" style="color: #1c3636">${authorizedUser.login} </A>--%>
<%--                                    <ul>--%>
<%--                                        <c:url value="/profile/edit.html" var="profileEditUrl"/>--%>
<%--                                        <li><A href="${profileEditUrl}" style="color: #1c3636">профиль</A></li>--%>
<%--                                        <c:url value="/logout.html" var="logoutUrl"/>--%>
<%--                                        <li><A href="${logoutUrl}" style="color: #1c3636">выход</A></li>--%>
<%--                                    </ul>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </c:if>--%>
<%--            </nav>--%>
<DIV class="container-fluid" style="background-color: #04414d">
    <div class="row">
        <div class="col-md-6">
            <H1 style="color: papayawhip">
                <c:url value="/img/logo_img.jpeg" var="logoUrl"/>
                <IMG class="arrow" src="${logoUrl}" width="200px">
                Кафе «FreshTy»
            </H1>
        </div>
        <div class="col-md-6" style="text-align: right">
            <ul style="child-align: right; color: papayawhip">
                язык:<br/>
                <c:url value="${redirectedData}" var="requestUrl"/>
                <form action="${requestUrl}" method="post">
                    <a href="${requestUrl}" style="color: papayawhip; text-align: right">
                        <input type="hidden" name="locale" value="ru">
                        ru <br/>
                    </a>
                    <a href="${requestUrl}" style="color: papayawhip; text-align: right">
                        <input type="hidden" name="locale" value="en">
                        en <br/>
                    </a>
                </form>
            </ul>
        </div>
    </div>
<%--        <div class="col-md-6" style="color: papayawhip; text-align: right">--%>
<%--&lt;%&ndash;            <li style="color: papayawhip; text-align: right">&ndash;%&gt;--%>
<%--                --%>
<%--&lt;%&ndash;            </li>&ndash;%&gt;--%>
<%--        </div>--%>
    </div>
<%--    <div class="col-md-6">--%>

<%--    </div>--%>
        <c:if test="${not empty authorizedUser}">
            <div class="row" style="background-color: #c1d6cc">
                <div class="col-md-6">
                    <ul>
                        <c:forEach items="${menu}" var="item">
                            <c:url value="${item.url}" var="itemUrl"/>
                            <LI class="item"><A href="${itemUrl}" style="color: #1c3636">${item.name}</A></LI>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-6">
                    <A href="#" class="drop-button" style="color: #1c3636">${authorizedUser.login} </A>
                    <ul>
                        <c:url value="/profile/edit.html" var="profileEditUrl"/>
                        <li><A href="${profileEditUrl}" style="color: #1c3636">профиль</A></li>
                        <c:url value="/logout.html" var="logoutUrl"/>
                        <li><A href="${logoutUrl}" style="color: #1c3636">выход</A></li>
                    </ul>
                </div>
            </div>
        </c:if>
</DIV>
