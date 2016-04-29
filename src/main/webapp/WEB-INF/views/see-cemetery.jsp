<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Ja 
</h1>

<table>
	<tr>
		<td>Name</td>
		<td>${name}</td>
	</tr>
	<tr>
		<td>Phone</td>
		<td>${phone}</td>
	</tr>
	<form:form method="POST" action="/app/redirect">
		<form:label path="location">location</form:label>
    	<form:input path="location" type="text"/>
	</form:form>
</table>

</body>
</html>


