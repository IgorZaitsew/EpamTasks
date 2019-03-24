<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../static/navbar.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title>User delete</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.user_delete.text" var="confirm_text"/>
    <fmt:message bundle="${loc}" key="locale.user_delete.delete_button" var="delete_button"/>

</head>
<body>
<form class="form-horizontal" action="${pageContext.request.contextPath}/admin?command=userDelete&contract_number=${requestScope.contract_number}" method="post">
    <fieldset>
        <div class="alert alert-success" role="alert">
            ${confirm_text}(${requestScope.name},${requestScope.surname},${requestScope.contractNumber})?
        </div>

        <div class="control-group">
            <div class="controls">
                <button  type="submit" class="btn btn-success">${delete_button}</button>
            </div>
        </div>
    </fieldset>
</form>
</body>
</html>
