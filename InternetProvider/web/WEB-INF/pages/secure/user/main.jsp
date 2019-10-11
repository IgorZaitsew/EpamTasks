<%@ page language="java" import="by.tc.zaycevigor.entity.*" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../static/navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.welcom_message" var="welcom_message"/>
<h1></h1>

</body>
</html>