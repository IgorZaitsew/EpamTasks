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
        <div style="float:right;"></div>
    </tr>
    <c:forEach var="foodList" items="${list}">

        <tr>
            <td>${foodList.id}</td>
            <td><img src=${foodList.imageURL} width="50" height="50"></td>
            <td>${foodList.name}</td>
            <td>${foodList.description}</td>
            <td>${foodList.weight}</td>
            <td>${foodList.price}</td>
        </tr>

    </c:forEach>

    <form action="/menu?parser=<%=request.getParameter("parser")%>" method="post">
        <input type="hidden" name="command" value="do_anything_command"/>
        <input type="hidden" name="filename" value="myfile"/>
        <input type="hidden" name="foodType" value="ColdSnack"/>
        <input type="hidden" name="startPos" value="0"/>
        <input type="hidden" name="count" value="6"/>
        <input type="submit" value="Холодные закуски"/>
        <div style="float:left;"></div>
    </form>

    <form action="/menu?parser=<%=request.getParameter("parser")%>" method="post">
        <input type="hidden" name="command" value="do_anything_command"/>
        <input type="hidden" name="filename" value="myfile"/>
        <input type="hidden" name="foodType" value="HotSnack"/>
        <input type="hidden" name="startPos" value="0"/>
        <input type="hidden" name="count" value="6"/>
        <input type="submit" value="Горячие закуски"/>
        <div style="float:left;"></div>
    </form>

    <form action="/menu?parser=<%=request.getParameter("parser")%>" method="post">
        <input type="hidden" name="command" value="do_anything_command"/>
        <input type="hidden" name="filename" value="myfile"/>
        <input type="hidden" name="foodType" value="<%=request.getParameter("foodType")%>"/>
        <input type="hidden" name="startPos" value="<%=Integer.parseInt(request.getParameter("startPos"))-1%>"/>
        <input type="hidden" name="count" value="6"/>
        <input type="submit" value="<-"/>
        <div style="float:left;"></div>
    </form>

    <form action="/menu?parser=<%=request.getParameter("parser")%>" method="post">
        <input type="hidden" name="command" value="do_anything_command"/>
        <input type="hidden" name="filename" value="myfile"/>
        <input type="hidden" name="foodType" value="<%=request.getParameter("foodType")%>"/>
        <input type="hidden" name="startPos" value="<%=Integer.parseInt(request.getParameter("startPos"))+1%>"/>
        <input type="hidden" name="count" value="6"/>
        <input type="submit" value="->"/>
        <div style="float:left;"></div>
    </form>
</table>
</body>
</html>

