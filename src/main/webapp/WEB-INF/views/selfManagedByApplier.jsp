<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<title>SelfManagement</title>
</head>
<body>
	<h1>Self Managed By Applier Prototype</h1>
	<c:out value="${person.name }"></c:out>

<spring:url value="/edit-initialize-application" var="editURL">
</spring:url>
<a id="editAddress" href="${fn:escapeXml(editURL)}">建立项目</a>

<spring:url value="/display-first-project-basic-situation/{applierUid}" var="displayFisrtEditURL">
	<spring:param name="applierUid" value="${person.uid}"></spring:param>
</spring:url>
<a id="firstEditAddress" href="${fn:escapeXml(displayFisrtEditURL)}">查看项目</a>

<spring:url value="/edit-first-project-basic-situation" var="fisrtEditURL">
</spring:url>
<a id="firstEditAddress" href="${fn:escapeXml(fisrtEditURL)}">编辑项目</a>
<br/>项目状态： ${application.projectStatus }

<c:if test="${application.projectStatus=='未提交' }">
	<spring:url value="/submit-application-by-applier" var="acceptURL">
	</spring:url>
	<input type="button" onclick="location.href='${fn:escapeXml(acceptURL)}';" value="提交">
</c:if>
<c:if test="${application.projectStatus=='已提交' }">
	<spring:url value="/withdraw-application-by-applier" var="withdrawURL">
	</spring:url>
	<input type="button" onclick="location.href='${fn:escapeXml(withdrawURL)}';" value="撤回">
</c:if>


	<form action="/app/self-managed-by-applier/change-name" method="POST">
		<table>
			<tr>
				<td>修改名称</td>
			</tr>
			<tr>
				<td>原名称:</td>
				<td><c:out value="${person.name }"></c:out></td>
			</tr>
			<tr>
				<td>新名称:<input type="hidden" value="${person }" name="person" /></td>
				<td><input type="text" name="name" /></td>
				<td><input type="submit" value="修改名称" /></td>
			</tr>
		</table>
	</form>
	<form action="/app/self-managed-by-applier/change-password"
		method="POST">
		<table>
			<tr>
				<td>修改密码</td>
			</tr>
			<tr>
				<td>输入旧密码</td>
				<td><input type="password" name="passwordOld" /></td>
			</tr>
			<tr>
				<td>输入新密码</td>
				<td><input type="password" name="passwordNew1" /></td>
			</tr>
			<tr>
				<td>再次输入新密码</td>
				<td><input type="password" name="passwordNew2" /> <input
					type="hidden" value="${person }" name="person" /></td>
				<td><input type="submit" value="修改密码" /></td>
			</tr>
		</table>
	</form>
	
	<table border="1">
		<tr>
			<td>项目状态</td>
			<td>项目名称</td>
			<td>奖种</td>
			<td>推荐等级</td>
			<td>形审结果</td>
			<td>操作</td>
			<td>操作</td>
			<td>确认提交</td>
		</tr>
		<tr>
			<c:set var="serial" value="${serial+1 }"></c:set>
			<td>${application.projectStatus }</td>
			<td>${application.projectName }</td>
			<td>${application.applicationType }</td>
			<td>${application.referingScienceTechnologyAwardRank }</td>
			<td>${application.formalityExaminationResult }</td>
			<td>
				<spring:url value="/display-first-project-basic-situation/{applierUid}" var="firstFormURL">
					<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
				</spring:url>
				<input type="button" onclick="location.href='${fn:escapeXml(firstFormURL)}';" value="查看">
			</td>
			<td>
				<spring:url value="/edit-first-project-basic-situation/{applierUid}" var="editFirstFormURL">
					<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
				</spring:url>
				<input type="button" onclick="location.href='${fn:escapeXml(editFirstFormURL)}';" value="编辑">
			</td>
				<spring:url value="/confirm-whole-application-by-applier" var="confirmURL">
				</spring:url>
			<td><input type="button" onclick="location.href='${fn:escapeXml(confirmURL)}';" value="确认提交"></td>
		</tr>
	</table>


</body>
</html>