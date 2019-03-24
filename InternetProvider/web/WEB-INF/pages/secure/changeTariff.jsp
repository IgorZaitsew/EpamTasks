<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../pages/static/navbar.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Authorization page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.change_tariff.confirm" var="confirm_text"/>
    <fmt:message bundle="${loc}" key="locale.change_tariff.confirm_button" var="change_button"/>

</head>
<body>
<form class="form-horizontal" action="${pageContext.request.contextPath}/secure?command=changeTariff&tariff_id=${requestScope.tariff_id}" method="post">
    <fieldset>
        <div class="alert alert-success" role="alert">
            ${confirm_text}${requestScope.name}?
        </div>

        <div class="control-group">
            <div class="controls">
                <button  type="submit" class="btn btn-success">${change_button}</button>
            </div>
        </div>
    </fieldset>
</form>
</body>
</html>
