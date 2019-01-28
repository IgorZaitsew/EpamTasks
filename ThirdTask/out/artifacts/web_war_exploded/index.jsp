<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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