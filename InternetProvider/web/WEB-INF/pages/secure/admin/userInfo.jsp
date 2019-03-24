<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../../pages/static/navbar.jsp" %>

<html>
<head>
    <title>Title</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.user_info.user" var="user_col"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract" var="contract_col"/>
    <fmt:message bundle="${loc}" key="locale.user_info.tariff" var="tariff_col"/>
    <fmt:message bundle="${loc}" key="locale.user_info.user_id" var="user_id"/>
    <fmt:message bundle="${loc}" key="locale.user_info.user_role" var="user_role"/>
    <fmt:message bundle="${loc}" key="locale.user_info.user_status" var="user_status"/>
    <fmt:message bundle="${loc}" key="locale.user_info.user_email" var="user_email"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract_number" var="contract_number"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract_passport_id" var="contract_passport_id"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract_city" var="contract_city"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract_street" var="contract_street"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract_house_number" var="contract_house_number"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract_balance" var="contract_balance"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract_name" var="contract_name"/>
    <fmt:message bundle="${loc}" key="locale.user_info.contract_surname" var="contract_surname"/>
    <fmt:message bundle="${loc}" key="locale.user_info.tariff_id" var="tariff_id"/>
    <fmt:message bundle="${loc}" key="locale.user_info.tariff_name" var="tariff_name"/>
    <fmt:message bundle="${loc}" key="locale.user_info.tariff_price" var="tariff_price"/>
    <fmt:message bundle="${loc}" key="locale.user_info.tariff_speed" var="tariff_speed"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-xs-1" itemref="${requestScope.user}" var="user">
            <div class="alert alert-success" role="alert">
                <h4 class="alert-heading"> ${user_col}</h4>
                <hr>
                <p class="mb-0"> ${user_id} ${user.id}</p>
                <hr>
                <p class="mb-1">${user_email}${user.email}</p>
                <hr>
                <p class="mb-2">${user_role}${user.role}</p>
                <hr>
                <p class="mb-3">${user_status}${user.status}</p>
            </div>
        </div>
        <div class="col-xs-1" itemref="${requestScope.contract}" var="contract">
            <div class="alert alert-primary" role="alert">
                <h4 class="alert-heading"> ${contract_col}</h4>
                <hr>
                <p class="mb-0"> ${contract_number} ${contract.contractNumber}</p>
                <hr>
                <p class="mb-1">${contract_passport_id}${contract.passportId}</p>
                <hr>
                <p class="mb-2">${contract_city}${contract.city}</p>
                <hr>
                <p class="mb-3">${contract_street}${contract.street}</p>
                <hr>
                <p class="mb-4">${contract_house_number}${contract.houseNumber}</p>
                <hr>
                <p class="mb-5">${contract_name}${contract.name}</p>
                <hr>
                <p class="mb-6">${contract_surname}${contract.surname}</p>
                <hr>
                <p class="mb-7">${contract_balance}${contract.balance}</p>
            </div>
        </div>
        <div class="col-xs-1" itemref="${requestScope.tariff}" var="tariff">
            <div class="alert alert-info" role="alert">
                <h4 class="alert-heading"> ${tariff_col}</h4>
                <hr>
                <p class="mb-0"> ${tariff_id} ${tariff.id}</p>
                <hr>
                <p class="mb-1">${tariff_name}${tariff.name}</p>
                <hr>
                <p class="mb-2">${tariff_price}${tariff.price}</p>
                <hr>
                <p class="mb-3">${tariff_speed}${tariff.speed}</p>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
