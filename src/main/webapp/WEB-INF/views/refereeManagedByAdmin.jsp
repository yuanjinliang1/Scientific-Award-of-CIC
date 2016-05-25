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
	<title>UserManagement</title>
</head>
<body>
<div class="container" style>
<dicipulus:bodyHeaderForAdmin menuName="manageReferee" />
<br/><br/><br/>
<div class="row" style="margin-left: 20px">
	<h1>推荐单位管理</h1>
</div>


<form action="/app/referee-managed-by-admin/referee-create" method="POST" >
<div class="form-group">
	<div >
		<label>用户id</label>
		<input type="text" name="uid" />
		<label style="margin-left: 40px">用户名称</label>
		<input style="margin-left: 0px" class="" type="text" name="name" />
		<input type="submit"  class="btn btn-default" value="添加用户" name="create"/>
	</div>
</div>
</form>
<form action="/app/referee-managed-by-admin/" method="POST">
<table class="table table-bordered" style="width: auto">
	<thead>
	<tr>
		<td>选择</td>
		<td>用户id</td>
		<td>用户名称</td>
		<td>密码</td>
		<td>操作</td>
		<td>操作</td>
	</tr>
	</thead>
	<tbody>
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
				<a id="resetPassword"  class="btn btn-default" href="${fn:escapeXml(resetURL)}">重置密码</a>
			</td>
			<td>
				<c:url value="/referee-managed-by-admin/delete-referee" var="deleteURL">
					<c:param name="uid" value="${referee.uid }"></c:param>
				</c:url>
				<a id="deleteReferee"  class="btn btn-default" href="${fn:escapeXml(deleteURL)}">删除用户</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>	
</form>
</div>

</body>
</html>
