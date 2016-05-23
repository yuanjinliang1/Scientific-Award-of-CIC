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
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<title>出错了！</title>
	<style type="text/css">
		.center {
				     float: none;
				     margin-left: auto;
				     margin-right: auto;
				     margin-top: 20%;
				     margin-bottom: auto;
				     zoom:150%
				}
	</style>
</head>
<body >
<div class="container">
	<div class="form-group col-md-4 panel panel-default center">
	
			<div class="panel-heading row">
				<h3 class=" panel-title ">出错了</h3>
			</div>
			<div class="panel-body">
				<div class="row">
				<h3><c:out value="${message }"></c:out></h3>
				</div>
				<div class="row">
					<a class="btn btn-default" onClick="return goBack()" >返回</a>
				</div>
			</div>
	</div>
</div>
<script>
function goBack() {
    window.history.back();
}
</script>
</body>
</html>