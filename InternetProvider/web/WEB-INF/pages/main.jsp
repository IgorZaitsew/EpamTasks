<%@ page language="java" import="by.tc.zaycevigor.entity.*" contentType="text/html; charset=utf-8"
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
<h1></h1>

</body>
</html>