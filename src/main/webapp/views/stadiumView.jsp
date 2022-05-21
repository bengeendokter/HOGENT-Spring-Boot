<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/css/style.css" var="urlCss" />
<link rel="stylesheet" href="${urlCss}" type="text/css" />
<title>Stadion: ${stadiumNaam}</title>
</head>
<body>
	<h1>FIFA World Cup Qatar 2022</h1>
	<h2>Stadion: ${stadiumNaam}</h2>
	<table>
		<thead>
			<tr>
				<th>Nr</th>
				<th>Voetbalmatch</th>
				<th>Datum</th>
				<th>Aftrap</th>
				<th>Tickets</th>
			</tr>
		</thead>
		<tbody>

			<spring:url value="/fifa/" var="showWedstrijdUrl" />
			<c:forEach var="ticket" items="${ticketten}">
				<tr>
					<td><a href="${showWedstrijdUrl}${ticket.wedstrijd.id}">${ticket.wedstrijd.id}</a></td>
					<td>${ticket.wedstrijd.landen[0]}-${ticket.wedstrijd.landen[1]}</td>
					<td><fmt:parseDate value="${ticket.wedstrijd.dateTime}"
							pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" /> <fmt:formatDate
							value="${parsedDate}" pattern="dd MMMM" /></td>
					<td><fmt:parseDate value="${ticket.wedstrijd.dateTime}"
							pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" /> <fmt:formatDate
							value="${parsedDate}" pattern="HH:mm" /></td>
					<td>${ticket.tickets}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="/logout" method="post">
		<input type="submit" value="Log out" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

</body>
</html>