<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../static/navbar.jsp" %>


<html>
<head>
    <title>Title</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.userdata.no_tariff" var="no_tariff"/>
    <fmt:message bundle="${loc}" key="locale.userdata.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="locale.userdata.tariff_title" var="tariff_title"/>
    <fmt:message bundle="${loc}" key="locale.userdata.contract_title" var="contract_title"/>
    <fmt:message bundle="${loc}" key="locale.userdata.up_balance" var="up_balance"/>
    <fmt:message bundle="${loc}" key="locale.userdata.amount" var="amount"/>
    <fmt:message bundle="${loc}" key="locale.userdata.amount_error" var="amount_error"/>
    <fmt:message bundle="${loc}" key="locale.userdata.ban_message" var="ban_msg"/>
    <fmt:message bundle="${loc}" key="locale.userdata.contract_number" var="contract_number"/>
    <fmt:message bundle="${loc}" key="locale.userdata.passport_id" var="passport_id"/>

<body>
<c:if test="${sessionScope.user.status=='banned'}">
    <div class="bg-danger text-white">
        <strong><c:out value="${ban_msg}"/></strong>
    </div>
</c:if>
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
        <hr>
        <form class="form-inline" action="/secure?command=up_balance" method="post">>
            <div class="form-group mx-sm-3 mb-2">
                <label for="amount" class="sr-only"></label>
                <input type="number" step="0.01" class="form-control" id="amount" name="amount" placeholder=${amount}>
            </div>
            <button type="submit" class="btn btn-primary mb-2">${up_balance}</button>
        </form>
    </div>
</c:if>
<c:if test="${param.amountError==true}">
    <div class="alert alert-success">
        <strong><c:out value="${amount_error}"/></strong>
    </div>
</c:if>
<div class="alert alert-info" role="alert">
    <h4 class="alert-heading">${tariff_title}</h4>
    <hr>
    <p class="mb-0">${contract_number}${sessionScope.contract.contractNumber}</p>
    <hr>
    <hr>
    <p class="mb-0">${passport_id}${sessionScope.contract.passportId}</p>
    <hr>
</div>
</body>
</html>
