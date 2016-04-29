<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true"%>
<!-- Set charset encoding to utf-8  -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<title>SelfManagement</title>
</head>
<body>
	<h1>Self Managed By Admin Prototype</h1>
	<c:out value="${person.name }"></c:out>
	
	<form action="/app/self-managed-by-admin/change-password"
		method="POST">
		<table>
			<tr>
				<td>修改密码</td>
			</tr>
			<tr>
				<td>输入旧密码</td>
				<td><input type="password" name="passwordOld" /></td>
			</tr>
			<tr>
				<td>输入新密码</td>
				<td><input type="password" name="passwordNew1" /></td>
			</tr>
			<tr>
				<td>再次输入新密码</td>
				<td><input type="password" name="passwordNew2" /> <input
					type="hidden" value="${person }" name="person" /></td>
				<td><input type="submit" value="修改密码" /></td>
			</tr>
		</table>
	</form>
</body>
</html>