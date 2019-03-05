<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
<fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.authorizate" var="authorization"/>
<fmt:message bundle="${loc}" key="locale.default.registrate" var="registration"/>

<div align="right">
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="locale" value="ru">
        <input type="submit" name="${locale_button_ru}" value="${locale_button_ru}"/>
    </form>
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="locale" value="eng">
        <input type="submit" name="${locale_button_en}" value="${locale_button_en}"/>
    </form>
</div>


<h1>${welcom_message}</h1>

<form action="/controller" method="get">
    <a href="/controller?command=goToAuthorizationPage">${authorization}</a>
</form>

<h3>
    <c:out value="${requestScope.error}"/>
</h3>

<a href="/controller?command=goToRegistrationPage">${registration}</a>

<br/>
<br/>
<br/>
<br/>
<br/>


<table border="1">
    <tr>
        <td>Title</td>
        <td>Price</td>
        <td>Name</td>
    </tr>
    <c:forEach items="${requestScope.tariffs}" var="tariff">
        <tr>
            <td>${tariff.name}</td>
            <td>${tariff.price}</td>
            <td>${tariff.speed}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>