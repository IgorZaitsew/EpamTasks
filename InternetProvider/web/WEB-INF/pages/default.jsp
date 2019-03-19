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
    <style>
    h1 {
        font-size: 20pt;
    }
</style>
</head>
<body>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.welcom_message" var="welcom_message"/>
<fmt:message bundle="${loc}" key="locale.default.authorizate" var="authorization"/>
<fmt:message bundle="${loc}" key="locale.default.registrate" var="registration"/>

<h1>${welcom_message}</h1>

</body>
</html>