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
	<c:choose>
		<c:when test="${applier.applicationType=='自然科学奖' }">
			<title>四、主要科学发现</title>
		</c:when>
		<c:when test="${applier.applicationType=='科技进步奖' }">
			<title>四、主要科技创新</title>
		</c:when>
		<c:when test="${applier.applicationType=='技术发明奖' }">
			<title>四、主要技术发明</title>
		</c:when>
	</c:choose>
</head>

<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="4"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px">
            	<h1>
            		<c:choose>
						<c:when test="${applier.applicationType=='自然科学类' }">
							四、主要科学发现
						</c:when>
						<c:when test="${applier.applicationType=='科技进步类' }">
							四、主要科技创新
						</c:when>
						<c:when test="${applier.applicationType=='技术发明类' }">
							四、主要技术发明
						</c:when>
						<c:otherwise>
							bad application type
						</c:otherwise>
					</c:choose>
            	</h1>
            </div>
            <form id="fourthFormer" action="/app/edit-fourth-form" method="POST" modelAttribute="fourthFormAttr">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							<c:choose>
								<c:when test="${applier.applicationType=='自然科学类' }">
									1. 重要科学发现（限5页）
								</c:when>
								<c:when test="${applier.applicationType=='科技进步类' }">
									1. 主要科技创新（限5页）
								</c:when>
								<c:when test="${applier.applicationType=='技术发明类' }">
									1. 主要技术发明（限5页）
								</c:when>
							</c:choose>
						</h4>
						</div>
					</div>
					<div class="row panel-body">
						<textarea rows="50" name="fourthForm1" form="fourthFormer">${fourthForm.fourthForm1}</textarea>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							<c:choose>
								<c:when test="${applier.applicationType=='自然科学类' }">
									2. 研究局限性（限1页）
								</c:when>
								<c:when test="${applier.applicationType=='科技进步类' }">
									2. 科技局限性（限1页）
								</c:when>
								<c:when test="${applier.applicationType=='技术发明类' }">
									2. 技术局限性（限1页）
								</c:when>
							</c:choose>
						</h4>
						</div>
					</div>
					<div class="row panel-body">
						<textarea rows="20" name="fourthForm2" form="fourthFormer">${fourthForm.fourthForm2}</textarea>
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