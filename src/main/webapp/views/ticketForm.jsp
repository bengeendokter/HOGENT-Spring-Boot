<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/style.css" var="urlCss"/>
<link rel="stylesheet" href="${urlCss}" type="text/css" />
<title>Tickets</title>
</head>
<body>
<h1>FIFA World Cup Qatar 2022</h1>
<h2>Stadion: ${stadiumNaam}</h2>
<h3>${wedstrijdTicket.wedstrijd}</h3>
<p>Aantal tickets beschikbaar: <strong>${wedstrijdTicket.tickets}</strong></p>

<form:form method="POST" modelAttribute="aankoopTicket">
	
	<form:errors path="email" cssClass="error" />
	<label for="email" >email:</label>
	<form:input id="email" path="email" size="20"/>
	
	<form:errors path="aantal" cssClass="error" />
	<label for="aantal">aantal tickets:</label>
	<form:input id="aantal" path="aantal" size="20"/>
	
	<form:errors path="voetbalCode1" cssClass="error" />
	<label for="voetbalCode1">voetbalCode1:</label>
	<form:input id="voetbalCode1" path="voetbalCode1" size="20"/>
	
	<form:errors path="voetbalCode2" cssClass="error" />
	<label for="voetbalCode2">voetbalCode2:</label>
	<form:input id="voetbalCode2" path="voetbalCode2" size="20"/>

	<input type="submit" value="Koop" />
</form:form>

</body>
</html>