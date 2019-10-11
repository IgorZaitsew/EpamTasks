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
<fmt:message bundle="${loc}" key="locale.user_readjust.enter_role" var="enter_role"/>
<fmt:message bundle="${loc}" key="locale.user_readjust.enter_status" var="enter_status"/>
<fmt:message bundle="${loc}" key="locale.user_readjust.enter_email" var="enter_email"/>
<fmt:message bundle="${loc}" key="locale.user_readjust.enter_contract_number" var="enter_contract_number"/>

<fmt:message bundle="${loc}" key="locale.user_readjust.error_role" var="error_role"/>
<fmt:message bundle="${loc}" key="locale.user_readjust.error_status" var="error_status"/>
<fmt:message bundle="${loc}" key="locale.user_readjust.error_email" var="error_email"/>
<fmt:message bundle="${loc}" key="locale.user_readjust.error_contract_number" var="error_contract_number"/>
<fmt:message bundle="${loc}" key="locale.user_readjust.readjust" var="readjust"/>
<body>
<div class="row">
    <div class="col-sm">
        <form class="form-horizontal" action="/admin?command=user_readjust&id=${requestScope.id}"
              method="post">
            <fieldset>
                <div class="control-group">
                    <label class="control-label" for="role">${enter_role}</label>
                    <div class="controls">
                        <input type="text" id="role" value=${requestScope.role} name="role"
                               class="input-xlarge">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="status">${enter_status}</label>
                    <div class="controls">
                        <input type="text" id="status" value=${requestScope.status} name="status"
                               class="input-xlarge">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="email">${enter_email}</label>
                    <div class="controls">
                        <input type="text" id="email" value=${requestScope.email} name="email"
                               class="input-xlarge">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="contract_number">${enter_contract_number}</label>
                    <div class="controls">
                        <input type="text" id="contract_number" value=${requestScope.contract_number} name="contract_number"
                               class="input-xlarge">
                    </div>
                </div>

                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn btn-success">${readjust}</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="col-sm">
        <c:if test="${param.incorrectRole==true}">
            <h3>
                <div class="alert alert-success">
                    <strong><c:out value="${error_role}"/></strong>
                </div>
            </h3>
        </c:if>
        <c:if test="${param.incorrectStatus==true}">
            <h4>
                <div class="alert alert-success">
                    <strong><c:out value="${error_status}"/></strong>
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
        <c:if test="${param.incorrectContractNumber==true}">
            <h5>
                <div class="alert alert-success">
                    <strong><c:out value="${error_contract_number}"/></strong>
                </div>
            </h5>
        </c:if>
    </div>
</div>
</div>
</body>
</html>