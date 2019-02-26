<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>
		<c:out value="${requestScope.new_book}" />
		<c:out value="${param.new_book}" />
		
	</h3>
	
	<h1>Book</h1>
	<h4> <c:out value="${requestScope.book.title}" />   </h4>
	<h4> <c:out value="${requestScope.book.id}" />   </h4>
</body>
</html>