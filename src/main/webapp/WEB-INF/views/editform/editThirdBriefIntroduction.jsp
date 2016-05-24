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
            <form id="thirdFormer" action="/app/edit-brief-introduction" method="POST" modelAttribute="briefIntroduction"
            data-toggle="validator" role="form">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							项目简介
						</h4>
						</div>
					</div>
					<div class="row panel-body form-group">
						<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
							<c:set var="placeholder" value="（应包含项目主要技术内容、授权专利情况、技术经济指标、应用及效益情况等。）"></c:set>
						</c:if>
						<c:if test="${applier.applicationType=='自然科学类'}">
							<c:set var="placeholder" value="（应包含项目主要研究内容、科学发现点、科学价值、同行引用及评价等）"></c:set>
						</c:if>
						<textarea rows="40" name="briefIntroduction" form="thirdFormer"
						class="form-control" placeholder="${placeholder }" maxlength="300"
						 data-error="请填写此项" required>${briefIntroductionForm.briefIntroduction}</textarea>
						 <div class="help-block with-errors" style="font-size:15px"></div>
					</div>
					
				</div>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="保存并查看" />
				</div>
			</form>	
        </div>
		</div>
	</div>
</div>
</body>
</html>