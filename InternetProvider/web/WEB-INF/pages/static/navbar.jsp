<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en"/>
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru"/>
    <fmt:message bundle="${loc}" key="locale.navbar.sign_in" var="sign_in"/>
    <fmt:message bundle="${loc}" key="locale.navbar.registrate" var="registrate"/>
    <fmt:message bundle="${loc}" key="locale.navbar.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="locale.navbar.go_to_personal_data" var="personal_data"/>
    <fmt:message bundle="${loc}" key="locale.navbar.tariff_list" var="tariff_list"/>
    <fmt:message bundle="${loc}" key="locale.navbar.exist_contract" var="exist_contract"/>
    <fmt:message bundle="${loc}" key="locale.navbar.new_contract" var="new_contract"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
            aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <c:if test="${sessionScope.user!=null}">
                <li class="nav-item" style="margin: 5px">
                    <a href="${pageContext.request.contextPath}/secure?command=personalData"
                       class="btn btn-outline-success">${personal_data}</a>
                </li>
                <li class="nav-item" style="margin: 5px">
                    <a href="${pageContext.request.contextPath}/secure?command=tariffList&id=0&name=%&min_price=0&max_price=0&min_speed=0&max_speed=0"
                       class="btn btn-outline-success">${tariff_list}</a>

                </li>
            </c:if>
        </ul>
        <nav class="navbar navbar-expand-lg navbar-light py-0">
            <div class="container-fluid">

                <div class="navbar-header mr-auto" style="margin-left: 10%">
                    <c:if test="${sessionScope.user==null}">
                    <a class="navbar-brand"
                       href="${pageContext.request.contextPath}/controller?command=goToIndex">
                        </c:if>
                        <c:if test="${sessionScope.user!=null}">
                        <a class="navbar-brand"
                           href="${pageContext.request.contextPath}/secure?command=goToMainPage">
                            </c:if>
                            <h3 style="font-weight: bold"><span style="color: darkslateblue">&lt;</span>epam<span
                                    style="color: darkslateblue">&gt;</span> Internet</h3></a>

                </div>

                <ul class="navbar-nav ml-auto">


                </ul>
                <ul class="navbar-nav" style="margin-left: 15px">
                    <li class="nav-item" style="margin: 5px;">
                        <a class="navbar-brand"
                           href="${pageContext.request.contextPath}/locale?command=change_locale&locale=ru">
                            <img width="39" height="30" src="${pageContext.request.contextPath}/images/icons/rus.png"
                                 alt="eng">
                        </a>
                    </li>
                    <li class="nav-item" style="margin: 5px; margin-left: -10px">
                        <a class="navbar-brand"
                           href="${pageContext.request.contextPath}/locale?command=change_locale&locale=en">
                            <img width="39" height="30" src="${pageContext.request.contextPath}/images/icons/uk.png"
                                 alt="eng">
                        </a>

                    </li>
                    <div class="container" style="height: 59px;width: 175px">
                        <c:if test="${sessionScope.user==null}">
                            <li class="nav-item" style="margin-top: 1px">
                                <a href="${pageContext.request.contextPath}/controller?command=goToAuthorizationPage"
                                   class="btn btn-success btn-sm btn-block">${sign_in}</a>
                            </li>
                            <li class="nav-item" style="margin-top: 1px">
                                <a href="${pageContext.request.contextPath}/controller?command=goToContractRegistration"
                                   class="btn btn-success btn-sm btn-block">${registrate}</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user!=null}">
                            <li class="nav-item" style="margin-bottom: 1px">
                                <a href="${pageContext.request.contextPath}/secure?command=exit"
                                   class="btn btn-success btn-sm btn-block">${sign_out}</a>
                            </li>
                        </c:if>
                    </div>
                </ul>

            </div>
        </nav>
    </div>
</nav>
</body>
</html>
