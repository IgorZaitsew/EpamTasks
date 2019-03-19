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
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.enter_name" var="enter_name"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.enter_surname" var="enter_surname"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.enter_city" var="enter_city"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.enter_street" var="enter_street"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.enter_house_number" var="enter_house_number"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.enter_passport_id" var="enter_passport_id"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.enter_email" var="enter_email"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.name_error" var="name_error"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.surname_error" var="surname_error"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.city_error" var="city_error"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.street_error" var="street_error"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.house_number_error" var="house_number_error"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.email_error" var="email_error"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.passport_id_error" var="passport_id_error"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.add" var="add"/>
<fmt:message bundle="${loc}" key="locale.contract_reg.email_exist" var="email_exist"/>
<div class="container">
    <div class="row">
        <div class="col-sm">
            <form class="form-horizontal" action="/controller?command=contractRegistration" method="post">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="name">${enter_name}</label>
                        <div class="controls">
                            <input type="text" id="name" name="name" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="surname">${enter_surname}</label>
                        <div class="controls">
                            <input type="text" id="surname" name="surname" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="city">${enter_city}</label>
                        <div class="controls">
                            <input type="text" id="city" name="city" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="street">${enter_street}</label>
                        <div class="controls">
                            <input type="text" id="street" name="street" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="house_number">${enter_house_number}</label>
                        <div class="controls">
                            <input type="text" id="house_number" name="house_number" placeholder=""
                                   class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="passport_id">${enter_passport_id}</label>
                        <div class="controls">
                            <input type="text" id="passport_id" name="passport_id" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="email">${enter_email}</label>
                        <div class="controls">
                            <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
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
            <c:if test="${param.incorrectName==true}">
                <h3>
                    <div class="alert alert-success">
                        <strong><c:out value="${name_error}"/></strong>
                    </div>
                </h3>
            </c:if>
            <c:if test="${param.incorrectSurname==true}">
                <h4>
                    <div class="alert alert-success">
                        <strong><c:out value="${surname_error}"/></strong>
                    </div>
                </h4>
            </c:if>
            <c:if test="${param.incorrectCity==true}">
                <h5>
                    <div class="alert alert-success">
                        <strong><c:out value="${city_error}"/></strong>
                    </div>
                </h5>
            </c:if>
            <c:if test="${param.incorrectStreet==true}">
                <h5>
                    <div class="alert alert-success">
                        <strong><c:out value="${street_error}"/></strong>
                    </div>
                </h5>
            </c:if>
            <c:if test="${param.incorrectHouseNumber==true}">
                <h5>
                    <div class="alert alert-success">
                        <strong><c:out value="${house_number_error}"/></strong>
                    </div>
                </h5>
            </c:if>
            <c:if test="${param.incorrectPassportId==true}">
                <h5>
                    <div class="alert alert-success">
                        <strong><c:out value="${passport_id_error}"/></strong>
                    </div>
                </h5>
            </c:if>
            <c:if test="${param.incorrectEmail==true}">
                <h5>
                    <div class="alert alert-success">
                        <strong><c:out value="${email_error}"/></strong>
                    </div>
                </h5>
            </c:if>
            <c:if test="${param.emailAdressExist==true}">
                <h5>
                    <div class="alert alert-success">
                        <strong><c:out value="${email_exist}"/></strong>
                    </div>
                </h5>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>