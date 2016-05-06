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
	<title>Application Management</title>
</head>
<body>
<h1>Application Management</h1>
<h1>list size: ${application.applicationList.size }</h1>

<!-- 跳转到个人管理 -->
<spring:url value="/self-managed-by-referee/{refereeUid}" var="selfManageURL">
	<spring:param name="refereeUid" value="${person.uid}"></spring:param>
</spring:url>
<a id="selfManage" href="${fn:escapeXml(selfManageURL)}">个人管理</a>

<spring:url value="/applier-managed-by-referee/applier-view/{ownerUid}" var="applierURL">
	<spring:param name="ownerUid" value="${person.uid}"></spring:param>
</spring:url>
<a href="${fn:escapeXml(applierURL)}" >项目组用户管理</a>

<a href="/app/application-managed-by-referee" >项目管理</a>

<table border="1">
	<tr>
		<td>序号</td>
		<td>项目状态</td>
		<td>项目名称</td>
		<td>奖种</td>
		<td>推荐等级</td>
		<td>形审结果</td>
		<td>操作</td>
	</tr>
	<c:set var="serial" value="0"></c:set>
	<c:forEach var="application" items="${applications.applicationList }">
		<tr>
			<c:set var="serial" value="${serial+1 }"></c:set>
			<td>${serial }</td>
			<td>${application.projectStatus }</td>
			<td>${application.projectName }</td>
			<td>${application.refereeString }</td>
			<td>${application.applicationType }</td>
			<td>${application.referingScienceTechnologyAwardRank }</td>
			<td>${application.formalityExaminationResult }</td>
			<td>
				<spring:url value="/display-first-project-basic-situation/{applierUid}" var="firstFormURL">
					<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
				</spring:url>
				<input type="button" onclick="location.href='${fn:escapeXml(firstFormURL)}';" value="查看">
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>