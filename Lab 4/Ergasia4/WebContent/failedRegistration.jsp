<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Exercise 4</title>
</head>
<body>
	<jsp:include page="register.html" />
	<%
    out.print("<h1 style='color:red'>User "+request.getAttribute("username")+" already exists!</h1>");
	%>
</body>
</html>