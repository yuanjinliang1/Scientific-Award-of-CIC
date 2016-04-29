<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<!-- Set charset encoding to utf-8  -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
	<title>UserManagement</title>
</head>
<body>
<h1>
	Referee Management Prototype
</h1>
<c:out value="${person.name }"></c:out>

<spring:url value="/self-managed-by-admin" var="selfManageURL">
</spring:url>

<a id="selfManage" href="${fn:escapeXml(selfManageURL)}">个人管理</a>

<form action="/app/referee-managed-by-admin/referee-create" method="POST" >
<table>
	<tr>
		<td><label>用户id</label></td>
		<td><input type="text" name="uid" /></td>
		<td><label>用户名称</label></td>
		<td><input type="text" name="name" /></td>
		<td><input type="submit" value="添加用户" name="create"/></td>
	</tr>
</table>
</form>
<form action="/app/referee-managed-by-admin/" method="POST">
<table>
	<tr>
		<td>选择</td>
		<td>用户id</td>
		<td>用户名称</td>
		<td>密码</td>
		<td>操作</td>
		<td>操作</td>
	</tr>
	<c:forEach var="referee" items="${referees }">
		<tr>
			<td><input type="checkbox"/> </td>
			<td>${referee.uid } </td>
			<td>${referee.name }</td>
			<td>${referee.password}</td>
			
			<td>
				<c:url value="/referee-managed-by-admin/reset-password" var="resetURL">
					<c:param name="uid" value="${referee.uid }"></c:param>
				</c:url>
				<a id="resetPassword" href="${fn:escapeXml(resetURL)}">重置密码</a>
			</td>
			<td>
				<c:url value="/referee-managed-by-admin/delete-referee" var="deleteURL">
					<c:param name="uid" value="${referee.uid }"></c:param>
				</c:url>
				<a id="deleteReferee" href="${fn:escapeXml(deleteURL)}">删除用户</a>
			</td>
		</tr>
	</c:forEach>
</table>	
</form>
	

</body>
</html>