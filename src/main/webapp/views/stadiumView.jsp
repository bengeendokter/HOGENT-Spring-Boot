<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/css/style.css" var="urlCss"/>
<link rel="stylesheet" href="${urlCss}" type="text/css" />
	<title>Stadion: ${stadiumNaam}</title>
</head>
<body>	
<h1>FIFA World Cup Qatar 2022</h1>
<h2>Stadion: ${stadiumNaam}</h2>
<table>
<thead><tr><th>Nr</th><th>Voetbalmatch</th><th>Datum</th><th>Aftrap</th><th>Tickets</th></tr></thead>
<tbody>
<c:forEach var="ticket" items="${ticketten}"><tr><td>${ticket.wedstrijd.id}</td><td>${ticket.wedstrijd.landen[0]}-${ticket.wedstrijd.landen[1]}</td><td>${ticket.wedstrijd.dag} november</td><td>${ticket.wedstrijd.uur}:00</td><td>${ticket.tickets}</td></tr></c:forEach>
</tbody>
</table>

</body>
</html>