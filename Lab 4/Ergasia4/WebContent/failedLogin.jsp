<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Exercise 4</title>
</head>
<body>
	<jsp:include page="login.html" />
	<%
    out.print("<h1 style='color:red'>Wrong username/password combination!</h1>");
	%>
</body>
</html>