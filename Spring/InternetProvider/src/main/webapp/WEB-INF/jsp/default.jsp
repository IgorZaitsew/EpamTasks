<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../pages/static/navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Default page</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.welcom_message" var="welcom_message"/>

<h4>${welcom_message}</h4>

</body>
</html>