<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../static/navbar.jsp" %>

<html>
<head>
    <title>Title</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.tariff.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.tariff.speed" var="speed"/>
    <fmt:message bundle="${loc}" key="locale.tariff.price" var="price"/>
    <fmt:message bundle="${loc}" key="locale.tariff.add" var="add_text"/>
    <fmt:message bundle="${loc}" key="locale.tariff_delete.delete_btn" var="delete"/>
</head>

<body>
<nav aria-label="Тарифы">
    <c:if test="${sessionScope.user.role=='admin'}">
        <add>
            <a href="${pageContext.request.contextPath}/admin?command=tariff_add_page"
               class="btn btn-success btn-sm">${add_text}</a>
        </add>
    </c:if>
    <ul class="list-group">
        <c:forEach items="${requestScope.tariffs}" var="tariffs" end="5">
            <c:if test="${tariffs.id!=1}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <a href="${pageContext.request.contextPath}/secure?command=show_tariff&id=${tariffs.id}"
                       class="btn btn-success btn-lg">${tariffs.name}</a>
                    <span class="badge badge-primary badge-pill">${speed}${tariffs.speed}</span>
                    <span class="badge badge-primary badge-pill">${price}${tariffs.price}</span>
                    <c:if test="${sessionScope.user.role=='admin'}">
                        <add>
                            <a href="${pageContext.request.contextPath}/admin?command=go_to_tariff_delete&tariff_id=${tariffs.id}&name=${tariffs.name}"
                               class="btn btn-danger btn-lg">${delete}</a>
                        </add>
                    </c:if>
                </li>
            </c:if>
        </c:forEach>
    </ul>

    <ul class="pagination justify-content-center">
        <c:if test="${requestScope.first_id>2}">
            <li class="page-item">
                <a href="${pageContext.request.contextPath}/secure?command=tariffList&id=${requestScope.first_id-5}"
                   class="page-link" aria-label="Previous">
                    <span aria-hidden="true">«</span>
                </a>
            </li>
        </c:if>
        <c:if test="${requestScope.first_id<=2}">
            <li class="page-item disabled">
                <a class="page-link" aria-label="Previous" href="#">
                    <span aria-hidden="true">«</span>
                </a>
            </li>
        </c:if>
        <c:if test="${requestScope.first_id+5<requestScope.tariffs_size}">
            <li class="page-item">
                <a href="${pageContext.request.contextPath}/secure?command=tariffList&id=${requestScope.first_id+5}" class="page-link"
                   aria-label="Next">
                    <span aria-hidden="true">»</span>
                </a>
            </li>
        </c:if>
        <c:if test="${requestScope.first_id+5>=requestScope.tariffs_size}">
            <li class="page-item disabled">
                <a class="page-link" href="#"
                   aria-label="Next">
                    <span aria-hidden="true">»</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>
</body>
</html>
