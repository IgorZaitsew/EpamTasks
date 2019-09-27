<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../pages/static/navbar.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Authorization page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.authorization.enter_contract_number_message" var="enter_contract"/>
    <fmt:message bundle="${loc}" key="locale.authorization.enter_password_message" var="enter_password"/>
    <fmt:message bundle="${loc}" key="locale.authorization.enter" var="enter_button"/>
    <fmt:message bundle="${loc}" key="locale.authorization.error_contract_number" var="error_contract"/>
    <fmt:message bundle="${loc}" key="locale.authorization.error_password" var="error_password"/>
    <fmt:message bundle="${loc}" key="locale.authorization.contract_not_exist" var="contract_not_exist"/>
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.validationEngine.js" type="text/javascript"></script>
</head>
<body>
<form id ="formID"class="form-horizontal" action="/controller?command=authorization" method="post">
    <fieldset>
        <div class="control-group">
            <label class="control-label"  for="contract_number">${enter_contract}</label>
            <div class="controls">
                <input class="validate[required,custom[onlyLetter],length[12,12]] text-input" type="text" name="contract_number" id="contract_number"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="password">${enter_password}</label>
            <div class="controls">
                <input class="validate[required,length[9]] text-input" type="password" name="password" id="password"/>

            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <button  type="submit" class="btn btn-success">${enter_button}</button>
            </div>
        </div>
    </fieldset>
</form>
<c:if test="${param.incorrectContractNumber==true}">
    <h3>
        <div class="alert alert-success">
            <strong><c:out value="${error_contract}"/></strong>
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
<c:if test="${param.contractExist==false}">
    <h4>
        <div class="alert alert-success">
            <strong><c:out value="${contract_not_exist}"/></strong>
        </div>
    </h4>
</c:if>
</body>
</html>
