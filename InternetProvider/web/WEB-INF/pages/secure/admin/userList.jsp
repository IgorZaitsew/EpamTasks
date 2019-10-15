<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../static/navbar.jsp" %>

<html>
<head>
    <title>User list</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.user_list.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="locale.user_list.users" var="users_text"/>
    <fmt:message bundle="${loc}" key="locale.user_list.contract_number" var="contract_number"/>
    <fmt:message bundle="${loc}" key="locale.user_list.ban_status" var="ban_status"/>
    <fmt:message bundle="${loc}" key="locale.user_list.user_delete" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.user_list.user_add" var="user_add"/>
    <fmt:message bundle="${loc}" key="locale.user_list.add_contract_with_user" var="add_contract_with_user"/>
    <fmt:message bundle="${loc}" key="locale.user_list.add_contract" var="contract_add"/>
    <style>
        ul {
            padding: 0;
            margin-left: 1px;
        }

        info {
            padding: 0;
            margin-left: 1px;
        }
    </style>
</head>
<body>
<add>
    <a href="${pageContext.request.contextPath}/admin?command=goToContractRegistration"
       class="btn btn-success btn-sm">${add_contract_with_user}</a>
</add>
<div class="col-md-7">${users_text}</div>
</div>
<ul class="list-group">
    <c:forEach items="${sessionScope.users}" var="users">
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <a href="${pageContext.request.contextPath}/admin?command=user_info&contract_number=${users.contractNumber}"
               class="btn btn-success btn-lg">${contract_number}${users.contractNumber}</a>
            <span class="badge badge-primary badge-pill">${ban_status}${users.status}</span>
            <span class="badge badge-primary badge-pill">${users.role}</span>
            <span class="badge badge-primary badge-pill">${users.email}</span>
            <a href="${pageContext.request.contextPath}/admin?command=go_to_user_delete&contract_number=${users.contractNumber}"
               class="btn btn-danger btn-lg">${delete}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
