<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>


<form action="contoller" method="post">
		<input type="hidden" name="command" value="save_new_book">
		new title: <input type="text" name="title" value="" /> <br /> 
		
         <input	type="submit" name="submit" value="press me" />
</form>

</body>
</html>