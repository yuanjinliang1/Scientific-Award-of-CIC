<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	<title>SelfManagement</title>
	<style type="text/css">
		.center {
				     float: none;
				     margin-left: auto;
				     margin-right: auto;
				}
	</style>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="manageSelf"/>
	<br/><br/><br/>
	<div class="row" id="selectAppType">
		<form action="/app/edit-initialize-application" method="post">
			<label>申请类型</label>
			<select name="applicationType">
				<option value="自然科学类">自然科学类</option>
				<option value="科技进步类">科技进步类</option>
				<option value="技术发明类">技术发明类</option>
			</select>
			<input type="submit" class="btn btn-default" value="设置奖种" />
		</form>
	</div>
	
	<table class="table table-bordered">
		<tr>
			<td>项目状态</td>
			<td>项目名称</td>
			<td>奖种</td>
			<td>推荐等级</td>
			<td>形审结果</td>
			<td>操作</td>
			<td>操作</td>
			<td>下载pdf</td>
		</tr>
		<tr>
			<c:set var="serial" value="${serial+1 }"></c:set>
			<td>
				${application.projectStatus }
				<c:if test="${application.projectStatus=='未提交' }">
					<spring:url value="/submit-application-by-applier" var="acceptURL">
					</spring:url>
					<input type="button" class="btn btn-default" onclick="location.href='${fn:escapeXml(acceptURL)}';" value="提交">
				</c:if>
				<c:if test="${application.projectStatus=='已提交' }">
					<spring:url value="/withdraw-application-by-applier" var="withdrawURL">
					</spring:url>
					<input type="button" class="btn btn-default" onclick="location.href='${fn:escapeXml(withdrawURL)}';" value="撤回">
				</c:if>
			</td>
			<td>${application.projectName }</td>
			<td >${application.applicationType }</td>
			<td>${application.referingScienceTechnologyAwardRank }</td>
			<td>${application.formalityExaminationResult }</td>
			<td>
				<spring:url value="/display-first-project-basic-situation/{applierUid}" var="firstFormURL">
					<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
				</spring:url>
				<input type="button" class="btn btn-default" onclick="location.href='${fn:escapeXml(firstFormURL)}';" value="查看">
			</td>
			<td>
				<spring:url value="/edit-first-project-basic-situation" var="editFirstFormURL">
				</spring:url>
				<input type="button" class="btn btn-default" onclick="location.href='${fn:escapeXml(editFirstFormURL)}';" value="编辑">
			</td>
				<spring:url value="/download-pdf/{applierUid}" var="pdfURL">
					<spring:param name="applierUid" value="${application.applierUid}"></spring:param>
				</spring:url>
			<td><input type="button" class="btn btn-default" onclick="location.href='${fn:escapeXml(pdfURL)}';" value="下载pdf"></td>
		</tr>
	</table>
	<div>
	<form action="/app/self-managed-by-applier/change-name" method="POST">
		<div class="form-group col-md-4 panel panel-default center">
			<div class="panel-heading row">
				<h3 class=" panel-title ">修改名称</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<label>原名称: <c:out value="${ person.name }"/></label>
				</div>
				<div class="row">
					<label>新名称</label>
					<input type="hidden" value="${person }" name="person" />
					<input type="text" class="form-control" name="name" />
					<br/>
				</div>
				<div class="row">
					<input type="submit" class="btn btn-default" value="修改名称" />
				</div>
			</div>
		</div>
	</form>
	</div>
	<div><br/>
	<form action="/app/self-managed-by-applier/change-password" 	method="POST">
		<div class="form-group col-md-4 panel panel-default center">
			<div class="panel-heading row">
				<h3 class=" panel-title ">修改密码</h3>
			</div>
			<div class="panel-body">
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
					<input type="submit" class="btn btn-default" value="修改密码" />
				</div>
			</div>
		</div>
	</form>
	</div>
</div>
</body>
</html>