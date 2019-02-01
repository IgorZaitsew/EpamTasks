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
    <title>Menu</title>
    <meta charset="utf-8">
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
        }

        td, th {
            font-family: "Cambria";
            padding: 4px;
            border: 1px solid #999;
        }

        th {
            background: #666;
            color: #f6eff6;
        }
    </style>
</head>
<body>
<table border="2">
    <tr>
        <th>Номер</th>
        <th>Фото</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Порция</br>(грамм)</th>
        <th>Цена</br>(руб.)</th>
    </tr>
    <c:forEach var="foodList" items="${list}">

        <tr>
            <td>${foodList.id}</td>
            <td><img src=${foodList.imageURL} width="50" height="50"></td>
            <td>${foodList.name}</td>
            <td>${foodList.descrArray}</td>
            <td>${foodList.weight}</td>
            <td>${foodList.priceArray}</td>
        </tr>

    </c:forEach>
</table>
</body>
</html>

