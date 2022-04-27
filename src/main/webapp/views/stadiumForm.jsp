<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/style.css" var="urlCss"/>
<link rel="stylesheet" href="${urlCss}" type="text/css" />
<title>Select Stadium</title>
</head>
<body>
    <c:if test="${not empty message}">
    <p class="${message.type}">${message.message}</p>     
    </c:if>
        
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