<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../static/navbar.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title>User add success</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.user_add_success.text" var="success_text"/>
</head>
<body>
<div class="alert alert-success" role="alert">
    ${success_text}
</div>
</body>
</html>
