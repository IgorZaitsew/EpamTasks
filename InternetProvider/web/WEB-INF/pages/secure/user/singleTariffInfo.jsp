<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../static/navbar.jsp" %>

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
    <fmt:message bundle="${loc}" key="locale.single_tariff.readjust" var="readjust"/>
    <fmt:message bundle="${loc}" key="locale.single_tariff.ban_status_msg" var="ban_status_msg"/>
    <fmt:message bundle="${loc}" key="locale.tariff_delete.delete_btn" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.tariff_delete.delete_btn" var="delete"/>
</head>
<body>


<div class="alert alert-success" role="alert">
<h4 class="alert-heading"> ${name} ${requestScope.tariff.name}</h4>
<hr>
<p class="mb-0"> ${speed_first} ${requestScope.tariff.speed} ${speed_second}</p>
<hr>
<p class="mb-1">${price_first}${requestScope.tariff.price} ${price_second}</p>

<p class="mb-2">
<c:if test="${sessionScope.user.role!='admin'}">
    <c:if test="${requestScope.tariff.id!=sessionScope.contract.tariffId}">
        <c:if test="${sessionScope.user.status}!='banned'">
            <hr>
            <li class="nav-item" style="margin-top: 1px">
                <a href="${pageContext.request.contextPath}/secure?command=goToChangeTariffPage&tariff_id=${requestScope.tariff.id}&name=${requestScope.tariff.name}"
                   class="btn btn-success btn-sm">${connect}</a>
            </li>
        </c:if>
        <c:if test="${sessionScope.user.status}=='banned'">
            ${ban_status_msg}
        </c:if>
        </>
    </c:if>
    <c:if test="${sessionScope.user.role=='admin'}">
        <add>
            <a href="${pageContext.request.contextPath}/admin?command=go_to_tariff_delete&tariff_id=${requestScope.tariff.id}&name=${requestScope.tariff.name}"
               class="btn btn-danger btn-lg">${delete}</a>
            <a href="${pageContext.request.contextPath}/admin?command=go_to_tariff_readjust&tariff_id=${requestScope.tariff.id}&name=${requestScope.tariff.name}&speed=${requestScope.tariff.speed}&price=${requestScope.tariff.price}"
               class="btn btn-primary btn-lg">${readjust}</a>
        </add>
    </c:if>
    </p>
    </div>
    </body>
    </html>
