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
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<title>选择项目基本信息</title>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="createApplication"/>
	<br/>
	<div class="row" style="margin-left: 20px"><h1>TO BE MERGED</h1></div>
	<br/>
	<form action="/app/edit-initialize-application" method="post">
		<div class="row">
			<label>申请类型</label>
		</div>
		<div class="row">
			<select name="applicationType">
				<option value="自然科学类">自然科学类</option>
				<option value="科技进步类">科技进步类</option>
				<option value="技术发明类">技术发明类</option>
			</select>
		</div><br/>
		<div class="row" style="margin-left:20px">
			<input type="submit" class="btn btn-default" value="创建项目" />
		</div>
	</form>
</div>
</body>
</html>