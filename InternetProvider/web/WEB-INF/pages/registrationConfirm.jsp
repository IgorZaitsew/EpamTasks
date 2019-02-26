<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 21.02.2019
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>

<h1>HELLO,</h1>
<h2>

    <c:out value="${requestScope.user.name}" />
</h2>

</body>
</html>
