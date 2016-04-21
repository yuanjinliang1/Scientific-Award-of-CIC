<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<html>
<head>
	<title>UserManagement</title>
</head>
<body>
<h1>
	User Management Prototype
</h1>
<c:out value="${person.name }"></c:out>

<form action="/app/userManageForAdmin/create" method="POST" >
<table>
	<tr>
		<td><label>用户名</label></td>
		<td><input type="text" name="uid" /></td>
		<td><input type="submit" value="添加用户" name="create"/></td>
		<td><input type="submit" value="删除用户" name="delete"/></td>
	</tr>
</table>
</form>
<form action="/app/userManageForAdmin/list" method="POST">
<table>
	<tr>
		<td>选择</td>
		<td>用户id</td>
		<td>用户名称</td>
		<td>密码</td>
		<td>操作</td>
	</tr>
	<c:forEach var="referee" items="${referees }">
		<tr>
			<td><input type="checkbox"/> </td>
			<td>${referee.uid } </td>
			<td>${referee.name }</td>
			<td>${referee.password}</td>
			<td><a id="resetPassword" href="<c:url value="/app/userManageForAdmin/resetPassword" />">重置密码</a></td>
		</tr>
	</c:forEach>
</table>	
</form>
	
</table>

</body>
</html>