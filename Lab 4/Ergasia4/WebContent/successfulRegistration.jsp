<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Exercise 4</title>
</head>
<body>

	<nav>
		<a href="./index.html">Home</a>  
		<a href="./register.html">Register</a>
		<a href="./login.html">Log in</a>
	</nav>

	<%
    out.print("<h1>Welcome, "+request.getAttribute("username")+".");
	%>
	<br>
	You account has been successfully created!</h1>
</body>
</html>