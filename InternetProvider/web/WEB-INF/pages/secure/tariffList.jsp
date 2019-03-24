<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../pages/static/navbar.jsp" %>

<html>
<head>
    <title>Title</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.tariff.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.tariff.speed" var="speed"/>
    <fmt:message bundle="${loc}" key="locale.tariff.price" var="price"/>
    <fmt:message bundle="${loc}" key="locale.tariff.add" var="add_text"/>
</head>
<body>
<c:if test="${sessionScope.user.role=='admin'}">
    <add>
        <a href="${pageContext.request.contextPath}/controller?command=tariff_add_page"
           class="btn btn-success btn-sm">${add_text}</a>
    </add>
</c:if>
<ul class="list-group">
    <c:forEach items="${requestScope.tariffs}" var="tariffs">
        <c:if test="${tariffs.id!=1}">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                <a href="${pageContext.request.contextPath}/secure?command=show_tariff&id=${tariffs.id}"
                   class="btn btn-success btn-lg">${tariffs.name}</a>
                <span class="badge badge-primary badge-pill">${speed}${tariffs.speed}</span>
                <span class="badge badge-primary badge-pill">${price}${tariffs.price}</span>
            </li>
        </c:if>
    </c:forEach>
</ul>
</body>
</html>
