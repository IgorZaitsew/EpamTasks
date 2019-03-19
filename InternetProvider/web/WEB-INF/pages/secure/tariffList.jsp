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
</head>
<body>
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
