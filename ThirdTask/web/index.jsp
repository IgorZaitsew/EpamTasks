<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="foodList" items="${list}">
    <tr>
        <td>${foodList.id}</td>
    </tr>
</c:forEach>
</body>
</html>