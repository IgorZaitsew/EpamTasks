<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../pages/static/navbar.jsp" %>


<html>
<head>
    <title>Title</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.userdata.no_tariff" var="no_tariff"/>
    <fmt:message bundle="${loc}" key="locale.userdata.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="locale.userdata.tariff_title" var="tariff_title"/>
<body>
    <c:if test="${requestScope.tariff.id==1}">
        <div class="alert alert-success">
            <strong><c:out value="${no_tariff}"/></strong>
        </div>
    </c:if>
    <c:if test="${requestScope.tariff.id!=1}">
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading">${tariff_title}</h4>
            <a href="${pageContext.request.contextPath}/secure?command=show_tariff&id=${requestScope.tariff.id}"
               class="btn btn-success btn-lg">${requestScope.tariff.name}</a>
            <hr>
            <p class="mb-0">${balance}${sessionScope.contract.balance}</p>
        </div>
    </c:if>
</body>
</html>
