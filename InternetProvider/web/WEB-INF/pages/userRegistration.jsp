<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../pages/static/navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>User registration</title>
    <style>
        h3 {
            font-size: 18pt;
        }
        h4 {
            font-size: 18pt;
        }
        h5 {
            font-size: 18pt;
        }
    </style>

</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.registration.enter_login_message" var="enter_login"/>
<fmt:message bundle="${loc}" key="locale.registration.enter_password_message" var="enter_password"/>
<fmt:message bundle="${loc}" key="locale.registration.enter_email_message" var="enter_email"/>
<fmt:message bundle="${loc}" key="locale.registration.register" var="register"/>
<fmt:message bundle="${loc}" key="locale.registration.login-help" var="help_login"/>
<fmt:message bundle="${loc}" key="locale.registration.password-help" var="help_password"/>
<fmt:message bundle="${loc}" key="locale.registration.email-help" var="help_email"/>
<fmt:message bundle="${loc}" key="locale.registration.error_email" var="error_email"/>
<fmt:message bundle="${loc}" key="locale.registration.error_login" var="error_login"/>
<fmt:message bundle="${loc}" key="locale.registration.error_password" var="error_password"/>
<fmt:message bundle="${loc}" key="locale.registration.error_user_exist" var="error_user_exist"/>

<form class="form-horizontal" action="/controller?command=registration" method="post">
    <fieldset>
        <div class="control-group">
            <label class="control-label"  for="contract_number">${}</label>
            <div class="controls">
                <input type="text" id="contract_number" name="contract_number" placeholder="" class="input-xlarge">
                <p class="help-block">${help_login}</p>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="email">${enter_email}</label>
            <div class="controls">
                <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
                <p class="help-block">${help_email}</p>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="password">${enter_password}</label>
            <div class="controls">
                <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
                <p class="help-block">${help_password}</p>
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <button  type="submit" class="btn btn-success">${registrate}</button>
            </div>
        </div>
    </fieldset>
</form>
<c:if test="${param.incorrectContractNumber==true}">
    <h3>
        <div class="alert alert-success">
            <strong><c:out value="${error_login}"/></strong>
        </div>
    </h3>
</c:if>
<c:if test="${param.incorrectPassword==true}">
    <h4>
        <div class="alert alert-success">
            <strong><c:out value="${error_password}"/></strong>
        </div>
    </h4>
</c:if>
<c:if test="${param.incorrectEmail==true}">
    <h5>
        <div class="alert alert-success">
            <strong><c:out value="${error_email}"/></strong>
        </div>
    </h5>
</c:if>
<c:if test="${param.userExist==true}">
<h5>
    <div class="alert alert-success">
        <strong><c:out value="${error_user_exist}"/></strong>
    </div>
</h5>
</c:if>
</body>
</html>