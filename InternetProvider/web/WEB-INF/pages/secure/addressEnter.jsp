<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../pages/static/navbar.jsp" %>

<html>
<head>
    <title>Title</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.authorization.enter_contract_number_message" var="enter_login"/>

</head>
<body>
<form class="form-horizontal" action="/controller?command=authorization" method="post">
    <fieldset>
        <div class="control-group">
            <!-- Username -->
            <label class="control-label"  for="login">${enter_login}</label>
            <div class="controls">
                <input type="text" id="login" name="login" placeholder="" class="input-xlarge">
            </div>
        </div>

        <div class="control-group">
            <!-- Password-->
            <label class="control-label" for="password">${enter_password}</label>
            <div class="controls">
                <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
            </div>
        </div>

        <div class="control-group">
            <!-- Button -->
            <div class="controls">
                <button  type="submit" class="btn btn-success">${enter_button}</button>
            </div>
        </div>
    </fieldset>
</form>
<c:if test="${param.incorrectLogin==true}">
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
</body>
</html>
