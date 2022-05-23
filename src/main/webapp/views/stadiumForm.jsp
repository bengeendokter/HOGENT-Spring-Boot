<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/style.css" var="urlCss" />
<link rel="stylesheet" href="${urlCss}" type="text/css" />
<title>Stadium</title>
</head>
<body>

	<form action="/logout" method="post">
		<input type="submit" value="Log uit" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
	<h1>FIFA World Cup Qatar 2022</h1>

	<c:if test="${not empty message}">
		<p class="${message.type}">${message.message}</p>
	</c:if>

	<form:form method="POST" action="fifa" modelAttribute="stadium">
            <label for="stadiums">Stadiums:</label> 
        <form:select id="stadiums" path="naam" multiple="false">

			<form:options items="${stadiums}"></form:options>

		</form:select>
		<input type="submit" value="Voer uit" />
	</form:form>

</body>
</html>