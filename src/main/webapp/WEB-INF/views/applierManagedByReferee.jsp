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
	User Management Prototype
</h1>
<c:out value="${person.name }"></c:out>

<!-- 跳转到个人管理 -->
<spring:url value="/self-managed-by-referee/{refereeUid}" var="selfManageURL">
	<spring:param name="refereeUid" value="${person.uid}"></spring:param>
</spring:url>
<a id="selfManage" href="${fn:escapeXml(selfManageURL)}">个人管理</a>

<form action="/app/applier-managed-by-referee/applier-create" method="POST" >
<table>
	<tr>
		<td><input type="hidden" value="${person }" name="person" /> </td>
		<td><input type="submit" value="添加项目组" name="create"/></td>
	</tr>
</table>
</form>
<form action="/app/applier-managed-by-referee/" method="POST">
<table>
	<tr>
		<td>选择</td>
		<td>项目组id</td>
		<td>项目组名称</td>
		<td>密码</td>
		<td>操作</td>
		<td>操作</td>
		<td>操作</td>
	</tr>
	<c:forEach var="applier" items="${appliers }">
		<tr>
			<td><input type="checkbox"/> </td>
			<td>${applier.uid } </td>
			<td>${applier.name }</td>
			<td>${applier.password}</td>
			
			<td>
				<c:url value="/applier-managed-by-referee/reset-password" var="resetURL">
					<c:param name="uid" value="${applier.uid }"></c:param>
				</c:url>
				<a id="resetPassword" href="${fn:escapeXml(resetURL)}">重置密码</a>
			</td>
			<td>
				<c:url value="/applier-managed-by-referee/delete-applier" var="deleteURL">
					<c:param name="uid" value="${applier.uid }"></c:param>
				</c:url>
				<a id="deleteApplier" href="${fn:escapeXml(deleteURL)}">删除用户</a>
			</td>
			<td>
				<!-- 填写与查看推荐书 -->
				<spring:url value="/edit-referee-unit-opinion/{applierUid}" var="editOpinionURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a id="editOpinion" href="${fn:escapeXml(editOpinionURL)}">填写推荐书</a>
			</td>
		</tr>
	</c:forEach>
</table>	
</form>

</body>
</html>