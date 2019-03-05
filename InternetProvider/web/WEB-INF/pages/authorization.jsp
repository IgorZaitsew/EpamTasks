<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.registration.enter_login_message" var="enter_login"/>
<fmt:message bundle="${loc}" key="locale.registration.enter_password_message" var="enter_password"/>
<fmt:message bundle="${loc}" key="locale.authorization.enter" var="enter"/>

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


<form action="/controller" method="get">
    <input type="hidden" name="command" value="authorization">
    ${enter_login}:
    <input type="text" name="login" value=""/>
    <br/>
    ${enter_password}:
    <input type="password" name="password" value=""/>
    <br/>

    <input type="submit" name="submit" value=${enter} />
</form>
<h3>
    <c:out value="${request.error}"/>
</h3>
</body>
</html>
