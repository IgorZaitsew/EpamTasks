<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../../pages/static/navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>User registration</title>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.tariff_add.enter_name" var="enter_name"/>
<fmt:message bundle="${loc}" key="locale.tariff_add.enter_speed" var="enter_speed"/>
<fmt:message bundle="${loc}" key="locale.tariff_add.enter_price" var="enter_price"/>
<fmt:message bundle="${loc}" key="locale.tariff_add.error_name" var="error_name"/>
<fmt:message bundle="${loc}" key="locale.tariff_add.error_price" var="error_price"/>
<fmt:message bundle="${loc}" key="locale.tariff_add.error_speed" var="error_speed"/>
<fmt:message bundle="${loc}" key="locale.tariff_add.error_tariff_exist" var="error_tariff_exist"/>
<fmt:message bundle="${loc}" key="locale.tariff_add.add" var="add"/>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm">
            <form class="form-horizontal" action="/admin?command=tariff_add" method="post">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="name">${enter_name}</label>
                        <div class="controls">
                            <input type="text" id="name" name="name" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="speed">${enter_speed}</label>
                        <div class="controls">
                            <input type="text" id="speed" name="speed" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="price">${enter_price}</label>
                        <div class="controls">
                            <input type="text" id="price" name="price" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-success">${add}</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="col-sm">
            <c:if test="${param.incorrectTariffName==true}">
                <h3>
                    <div class="alert alert-success">
                        <strong><c:out value="${error_name}"/></strong>
                    </div>
                </h3>
            </c:if>
            <c:if test="${param.incorrectTariffPrice==true}">
                <h4>
                    <div class="alert alert-success">
                        <strong><c:out value="${error_price}"/></strong>
                    </div>
                </h4>
            </c:if>
            <c:if test="${param.incorrectTariffSpeed==true}">
                <h5>
                    <div class="alert alert-success">
                        <strong><c:out value="${error_speed}"/></strong>
                    </div>
                </h5>
            </c:if>
            <c:if test="${param.tariffExist==true}">
                <h5>
                    <div class="alert alert-success">
                        <strong><c:out value="${error_tariff_exist}"/></strong>
                    </div>
                </h5>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>