<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tickets</title>
</head>
<body>
<h1>FIFA World Cup Qatar 2022</h1>
<h2>Stadion: ${stadiumNaam}</h2>
<h3>${ticketten[0].wedstrijd}</h3>
<h3>aantal tickets beschikbaar: ${ticketten[0].tickets}</h3>

<!-- TODO modelAttribute -->
<form:form method="POST" action="fifa/${ticketten[0].wedstrijd.id}" modelAttribute="ticket">
	<p>
	<label for="email" >email:</label>
	<form:input id="email" path="" size="20"/>
	</p>
	<p>
	<label for="aantal">aantal tickets:</label>
	<form:input id="aantal" path="" size="20"/>
	</p>
	<p>
	<label for="voetbalCode1">voetbalCode1:</label>
	<form:input id="voetbalCode1" path="" size="20"/>
	</p>
	<p>
	<label for="voetbalCode2">voetbalCode2:</label>
	<form:input id="voetbalCode2" path="" size="20"/>
	</p>
	<input type="submit" value="Koop" />
</form:form>

</body>
</html>