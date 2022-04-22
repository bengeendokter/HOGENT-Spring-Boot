<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Select Stadium</title>
</head>
<body>
	<h1>FIFA World Cup Qatar 2022</h1>
	<form:form method="POST" action="fifa" modelAttribute="stadium">
            stadiums 
        <form:select path="naam" multiple="false">

			<form:options items="${stadiums}"></form:options>

		</form:select>
		<input type="submit" value="Voer uit" />
	</form:form>
</body>
</html>