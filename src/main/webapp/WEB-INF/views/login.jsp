<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Login Prototype
</h1>
<c:out value="${person.name }"></c:out>

<form action="/app/login" method="POST" modelAttribute="loginAttr">
	<tr>
		<td><label>uid</label></td>
		<td><input type="text" name="uid" /> </td>
	</tr>
	<tr>
		<td><label>password</label></td>
		<td><input type="text" name="password" /> </td>
	</tr>
	<tr>
		<input type="submit" value="提交"/>
	</tr>
</form>

</body>
</html>