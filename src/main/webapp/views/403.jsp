<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Access is denied</title>
    </head>
    <body>
        <h1>HTTP Status 403 - Access is denied</h1>
        <h3>Message : You do not have permission to access this page!</h3>	

        <form action="logout" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>            
        </form>

    </body>
</html>