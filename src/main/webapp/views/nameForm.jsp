
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter your name</title>
</head>
<body>
	<h1>Enter your name</h1>


	
	<form:form method="POST" action="hello" modelAttribute="name">

            Name: 

            <form:input path="value" size="15" />
		<input type="submit" value="OK" />


	
	</form:form>
</body>
</html>

