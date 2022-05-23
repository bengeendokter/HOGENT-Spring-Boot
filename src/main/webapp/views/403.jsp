<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/style.css" var="urlCss" />
<link rel="stylesheet" href="${urlCss}" type="text/css" />
<title>Toegang geweigerd</title>
</head>
<body>
	<h1>HTTP Status 403 - Toegang geweigerd</h1>
	<p class="error">Melding: U heeft geen toegang tot deze pagina!</p>

	<form action="/logout" method="post">
		<input type="submit" value="Keer terug" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

</body>
</html>