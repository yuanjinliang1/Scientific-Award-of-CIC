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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>SelfManagement</title>
<style>
div.row {
	width: 300;
}
</style>
</head>
<body>
	<div class="container">


		<dicipulus:bodyHeaderForAdmin menuName="manageSelf" />
		<br />
		<br />
		<br />
		<h1>Self Managed By Admin Prototype</h1>
		<form action="/app/self-managed-by-admin/change-password"
			method="POST">
			<div class="form-group">
				<div class="row">
					<label>修改密码</label>
				</div>
				<div class="row">
					<label>输入旧密码</label> <input type="password" class="form-control"
						name="passwordOld" />
				</div>
				<div class="row">
					<label>输入新密码</label> <input type="password" class="form-control"
						name="passwordNew1" />
				</div>
				<div class="row">
					<label>再次输入新密码</label> <input type="password" class="form-control"
						name="passwordNew2" /> <input type="hidden" value="${person }"
						name="person" />
				</div>
				<div class="row">
					<input type="submit" class="btn btn-default" value="修改密码" />
				</div>
			</div>
		</form>

	</div>
</body>
</html>