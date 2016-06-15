<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
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
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<title>个人管理</title>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForReferee menuName="manageSelf"/>
	<br/>
	<div class="row" ><h3>修改密码</h3></div>
	<br/><br/>
	<form action="/app/self-managed-by-referee/change-password"
		method="POST">
		<div class="form-group col-md-4">
			<div class="row">
				<label>输入旧密码</label>
				<input type="password" class="form-control" name="passwordOld" />
				<br/>
			</div>
			<div class="row">
				<label>输入新密码</label>
				<input type="password" class="form-control" name="passwordNew1" />
				<br/>
			</div>
			<div class="row">
				<label>再次输入新密码</label>
				<input type="password" class="form-control" name="passwordNew2" /> 
				<input type="hidden" value="${person }" name="person" />
				<br/>
			</div>
			<div class="row">
				<input type="submit" class="btn btn-primary" value="修改密码" />
			</div>
		</div>
	</form>
</div>
</body>
</html>