<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
        <title>Login</title>
        <spring:url value="/css/style.css" var="urlCss"/>
       <link rel="stylesheet" href="${urlCss}" type="text/css" />
</head>

    <body>


            <h1>Log in met gebruikersnaam en wachtwoord</h1>

            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
                
            <c:if test="${not empty msg}">
                <p class="msg">${msg}</p>
            </c:if>

           <form action="login" method="POST">

                
                        <label for="username" >Gebruikersnaam:</label>
                        <input id="username" type='text' name='username' autofocus value=''/>
            
                        <label for="password" >Wachtwoord:</label>
                        <input id="password" type='password' name='password' />

                        <input name="submit" type="submit"
                                               value="Log in" />
                    


                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />

            </form>


    </body>
</html>