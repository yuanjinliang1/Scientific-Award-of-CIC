<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	<h1>Self Managed By Applier Prototype</h1>
	<c:out value="${person.name }"></c:out>

<spring:url value="/edit-initialize-application" var="editURL">
</spring:url>
<a id="editAddress" href="${fn:escapeXml(editURL)}">建立项目</a>

	<form action="/app/self-managed-by-applier/change-name" method="POST">
		<table>
			<tr>
				<td>修改名称</td>
			</tr>
			<tr>
				<td>原名称:</td>
				<td><c:out value="${person.name }"></c:out></td>
			</tr>
			<tr>
				<td>新名称:<input type="hidden" value="${person }" name="person" /></td>
				<td><input type="text" name="name" /></td>
				<td><input type="submit" value="修改名称" /></td>
			</tr>
		</table>
	</form>
	<form action="/app/self-managed-by-applier/change-password"
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