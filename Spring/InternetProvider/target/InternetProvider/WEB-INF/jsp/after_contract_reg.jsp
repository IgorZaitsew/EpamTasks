<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../pages/static/navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>After contract add</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.after_reg.text" var="text"/>
<fmt:message bundle="${loc}" key="locale.after_reg.text_second" var="text_second"/>
<fmt:message bundle="${loc}" key="locale.after_reg.ref_text" var="ref"/>

<h2>
    ${text}<a href="${pageContext.request.contextPath}/secure?command=goToUserRegistration" ${ref}>${text_second}
</h2>
<div class="alert alert-success">
    <strong><c:out value=" ${text}${pageContext.request.contextPath}/secure?command=goToUserRegistration${ref}>${text_second}"/></strong>
</div>
</body>
</html>