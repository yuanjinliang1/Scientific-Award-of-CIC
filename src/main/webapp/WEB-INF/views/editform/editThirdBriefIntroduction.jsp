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
	<title>三、项目简介</title>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="3"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>三、项目简介</h1></div>
            <form id="thirdFormer" action="/app/edit-brief-introduction" method="POST" modelAttribute="briefIntroduction">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							项目简介
						</h4>
						</div>
					</div>
					<div class="row panel-body">
						<textarea rows="40" name="briefIntroduction" form="thirdFormer">${briefIntroductionForm.briefIntroduction}</textarea>
					</div>
				</div>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-default" value="保存并查看" />
				</div>
			</form>	
        </div>
		</div>
	</div>
</div>
</body>
</html>