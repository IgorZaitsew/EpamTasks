<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Меню</title>
</head>

<body>
<style>
    a {
        text-decoration: none;
    }
</style>
<form action="/menu?parser=SAX" method="post">
    <input type="hidden" name ="command" value = "do_anything_command" />
    <input type="hidden" name ="filename" value = "myfile" />
    <input type="submit" value="SAX"/>
</form>
<form action="/menu?parser=STaX" method="post">
    <input type="hidden" name ="command" value = "do_anything_command" />
    <input type="hidden" name ="filename" value = "myfile" />
    <input type="submit" value="STaX"/>
</form>
</body>
</html>