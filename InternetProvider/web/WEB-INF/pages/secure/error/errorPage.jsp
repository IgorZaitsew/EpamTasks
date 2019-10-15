<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../static/navbar.jsp" %>

<html>s
<head>
    <meta charset="utf-8">
    <title>Authorization page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.error_page.error_text" var="error_text"/>
</head>
<body><h1>
    ${error_text}
</h1>
</body>
</html>
