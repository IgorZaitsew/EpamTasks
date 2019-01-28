<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 28.01.2019
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
</head>
<body>
<table border="2">
    <tr>
        <td>imageURL</td>
        <td>id</td>
        <td>name</td>
        <td>description</td>
        <td>weight</td>
        <td>price</td>
    </tr>
    <c:forEach var="foodList" items="${list}">

        <tr>
            <td>${foodList.imageURL}</td>
            <td>${foodList.id}</td>
            <td>${foodList.name}</td>
            <td>${foodList.descrArray}</td>
            <td>${foodList.weight}</td>
            <td>${foodList.priceArray}</td>
        </tr>

    </c:forEach>
</table>
</body>
</html>

