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
	Insha Allah.  
</h1>
<table>
	<tr>
		<td>Name</td>
		<td>${command2.name}</td>
	</tr>
	<tr>
		<td>Phone</td>
		<td>${command2.phone}</td>
	</tr>
</table>

<form action="/app/takeme" method="POST">
	<tr>
		<td><form:label path="command2.name">name</form:label></td>
		<td><form:input path="command2.name" ></form:input></td>
	</tr>
	<tr>
		<td><form:label path="command2.phone">phone223</form:label> </td>
		<td><form:input path="command2.phone"></form:input> </td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="hit me"/>
		</td>
	</tr>
</form>

</body>
</html>
