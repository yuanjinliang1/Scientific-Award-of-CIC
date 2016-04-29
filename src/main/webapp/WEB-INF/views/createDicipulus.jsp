<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Create Dicipulus</title>
</head>
<body>
<h1>
	Create Dicipulus 
</h1>
<c:out value="${person.name }"></c:out>
<table>
	<tr>
		<td>Id</td>
		<td>${freshman.id }</td>
	</tr>
	<tr>
		<td>Name</td>
		<td>${freshman.name}</td>
	</tr>
	<tr>
		<td>Phone</td>
		<td>${freshman.phone}</td>
	</tr>
</table>

<form action="/app/enroll" method="POST" modelAttribute="studentAttr">
	<tr>
		<td></td>
		<td><input type="text" name="id" value="12"></input></td>
	</tr>
	<tr>
		<td><label >name</label></td>
		<td><input type="text" name="name" value="${freshman2.name }"></input></td>
	</tr>
	<tr>
		<td><label >phone</label></td>
		<td><input type="text" name="phone" value="${freshman2.phone }"></input></td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="Enroll"/>
		</td>
	</tr>
</form>

</body>
</html>
