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
	<title>Application Management</title>
	<style>
		table { font-size:small}
	</style>
</head>
<body>
<div class="container" style="width:100%;">
<dicipulus:bodyHeaderForAdmin menuName="manageApplication" />
<br/><br/><br/>
<div class="row" style="margin-left: 20px"><h1>Application Management</h1></div>
<div class="col-md-12">
<table class="table table-bordered" style="width:100%">
	<thead>
	<tr>
		<td>多选</td>
		<td>序号</td>
		<td>项目状态</td>
		<td>项目名称</td>
		<td>推荐单位</td>
		<td>奖种</td>
		<td>推荐等级</td>
		<td>形审结果</td>
		<td>初审结果</td>
		<td>终审结果</td>
		<td>操作</td>
		<td>操作</td>
		<td>备注</td>
		<td>操作</td>
		<td>操作</td>
		
	</tr>
	</thead>
	<tbody>
	<c:set var="serial" value="0"></c:set>
	<c:forEach var="application" items="${applications.applicationList }">
		<spring:url value="/application-managed-by-admin/post/{applierUid}" var="saveURL">
			<spring:param name="applierUid" value="${application.applierUid}"></spring:param>
		</spring:url>
		<form action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="applicationAttr">
			<tr>
				<td><input type="checkbox" id="${application.applierUid }"> </td>
				<c:set var="serial" value="${serial+1 }"></c:set>
				<td>${serial }</td>
				<td>${application.projectStatus }
					<c:if test="${application.projectStatus=='已推荐' }">
						<spring:url value="/accept-application-by-admin/{applierUid}" var="acceptURL">
							<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
						</spring:url>
						<input type="button"  class="btn btn-default" onclick="location.href='${fn:escapeXml(acceptURL)}';" value="接收">
					</c:if>
					<c:if test="${application.projectStatus=='已接收' }">
						<spring:url value="/withdraw-application-by-admin/{applierUid}" var="withdrawURL">
							<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
						</spring:url>
						<input type="button"  class="btn btn-default" onclick="location.href='${fn:escapeXml(withdrawURL)}';" value="退回">
					</c:if>
				</td>
				<td>${application.projectName }</td>
				<td>${application.refereeString }</td>
				<td>${application.applicationType }</td>
				<td>${application.referingScienceTechnologyAwardRank }</td>
				<td>
					<select name="formalityExaminationResult" class="form-control">
						<option value="${application.formalityExaminationResult }">${application.formalityExaminationResult }</option>
						<c:forTokens items="合格,不合格" delims="," var="formaliltyResultOption">
							<c:if test="${firstForm.formalityExaminationResult!=formaliltyResultOption}">
								<option value="${formaliltyResultOption }">${formaliltyResultOption }</option>
							</c:if>
						</c:forTokens>
					</select>
				</td>
				<td>
					<select name="primaryExaminationResult" class="form-control">
						<option value="${application.primaryExaminationResult }">${application.primaryExaminationResult }</option>
						<c:forTokens items="无,提名三等奖,提名二等奖,提名一等奖,申请特等奖" delims="," var="primaryResultOption">
							<c:if test="${firstForm.primaryExaminationResult!=primaryResultOption}">
								<option value="${primaryResultOption }">${primaryResultOption }</option>
							</c:if>
						</c:forTokens>
					</select>
				</td>
				<td>
					<select name="finalExaminationResult" class="form-control">
						<option value="${application.finalExaminationResult }">${application.finalExaminationResult }</option>
						<c:forTokens items="无,三等奖,二等奖,一等奖,特等奖" delims="," var="finalResultOption">
							<c:if test="${firstForm.finalExaminationResult!=finalResultOption}">
								<option value="${finalResultOption }">${finalResultOption }</option>
							</c:if>
						</c:forTokens>
					</select>
				</td>
				<td>
					<spring:url value="/display-first-project-basic-situation/{applierUid}" var="firstFormURL">
						<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
					</spring:url>
					<input type="button" class="btn btn-default" onclick="location.href='${fn:escapeXml(firstFormURL)}';" value="查看">
				</td>
				<td>
					<input type="button" class="btn btn-default" value="下载(未完工)">
				</td>
				<td><input type="text" class="form-control" name="commentOfAdmin" value="${application.commentOfAdmin }"></td>
				<td><input type="submit" class="btn btn-default" value="保存并查看" /></td>
				<td>
					<spring:url value="/download-zip/{applierUid}" var="zipURL">
						<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
					</spring:url>
					<input type="button" class="btn btn-default" onclick="location.href='${fn:escapeXml(zipURL)}';" value="生成并下载压缩包">
				</td>
			</tr>
		</form>
		
	</c:forEach>
	</tbody>
</table>
</div>
</div>
</body>
</html>