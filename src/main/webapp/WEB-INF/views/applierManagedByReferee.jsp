<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
<%@ page session="true" %>
<!-- Set charset encoding to utf-8  -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<title>项目组管理</title>
</head>

<body>
<div class="container">
<dicipulus:bodyHeaderForReferee menuName="manageApplier"/>
<br/><br/><br/>
<div class="row" style="margin-left: 20px"><h1>项目组管理</h1></div>



<form action="/app/applier-managed-by-referee/applier-create" method="POST" >
	<div >
		<input type="hidden" value="${person }" name="person" /> 
		<input type="submit" class="btn btn-default" value="添加项目组" name="create"/>
	</div>
</form>
<form action="/app/applier-managed-by-referee/" method="POST">
<table class="table table-bordered">
	<thead>
	<tr>
		<td>选择</td>
		<td>项目组id</td>
		<td>项目组名称</td>
		<td>密码</td>
		<td>操作</td>
		<td>操作</td>
	</tr>
	</thead>
	<tbody>
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
				<a id="resetPassword" class="btn btn-default" href="${fn:escapeXml(resetURL)}">重置密码</a>
			</td>
			<td>
				<c:url value="/applier-managed-by-referee/delete-applier" var="deleteURL">
					<c:param name="uid" value="${applier.uid }"></c:param>
				</c:url>
				<a id="deleteApplier" class="btn btn-default" href="${fn:escapeXml(deleteURL)}">删除用户</a>
			</td>
			
			
		</tr>
	</c:forEach>
	</tbody>
</table>	
</form>

</div>
</body>
</html>
