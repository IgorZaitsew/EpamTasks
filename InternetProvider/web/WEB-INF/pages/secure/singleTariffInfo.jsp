<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../pages/static/navbar.jsp" %>

<html>
<head>
    <title>Title</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.single_tariff.descr_name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.single_tariff.descr_speed_first" var="speed_first"/>
    <fmt:message bundle="${loc}" key="locale.single_tariff.descr_speed_second" var="speed_second"/>
    <fmt:message bundle="${loc}" key="locale.single_tariff.descr_price_first" var="price_first"/>
    <fmt:message bundle="${loc}" key="locale.single_tariff.descr_price_second" var="price_second"/>
    <fmt:message bundle="${loc}" key="locale.single_tariff.connect" var="connect"/>
</head>
<body>


<div class="alert alert-success" role="alert">
    <h4 class="alert-heading"> ${name} ${requestScope.tariff.name}</h4>
    <hr>
    <p class="mb-0"> ${speed_first} ${requestScope.tariff.speed} ${speed_second}</p>
    <hr>
    <p class="mb-1">${price_first}${requestScope.tariff.price} ${price_second}</p>

    <p class="mb-2">
        <c:if test="${requestScope.tariff.id!=sessionScope.contract.tariffId}">
    <hr>
    <li class="nav-item" style="margin-top: 1px">
        <a href="${pageContext.request.contextPath}/secure?command=goToChangeTariffPage&tariff_id=${requestScope.tariff.id}&name=${requestScope.tariff.name}"
           class="btn btn-success btn-sm">${connect}</a>
    </li>
    </c:if>
    </p>
</div>
</body>
</html>
