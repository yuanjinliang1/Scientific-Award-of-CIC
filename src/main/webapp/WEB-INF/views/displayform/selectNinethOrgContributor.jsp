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
	<title>九、主要完成单位情况表</title>
</head>
<body>
<h1>
	九、主要完成单位情况表
</h1>
<c:out value="${person.name }"></c:out>

<form action="/app/create-nineth-major-org-contributor" method="POST" >
<table>
	<tr>
		<td><input type="submit" value="添加主要完成单位" name="create"/></td>
	</tr>
</table>
</form>
<table border="1">
	<tr>
		<td>排名</td>
		<td>单位名称</td>
		<td>操作</td>
	</tr>
	<c:forEach var="ninethForm" items="${ninethForms }">
		<tr>
			<td>${ninethForm.rankOfOrg } </td>
			<td>${ninethForm.nameOfOrg }</td>
			<td>
				<spring:url value="/display-nineth-major-org-contributor/{idOfNinethForm}" var="editFormURL">
					<spring:param name="idOfNinethForm" value="${ninethForm.idOfNinethForm }"></spring:param>
				</spring:url>
				<a id="editOpinion" href="${fn:escapeXml(editFormURL)}">浏览</a>
			</td>
			
		</tr>
	</c:forEach>
</table>
				<input type="button" onclick="location.href='edit-first-project-basic-situation';" value="第一页">
				<input type="button" onclick="location.href='edit-brief-introduction';" value="第三页">
				<input type="button" onclick="location.href='edit-fourth-form';" value="第四页">
				<input type="button" onclick="location.href='edit-objective-evaluation';" value="第五页">
				<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
					<input type="button" onclick="location.href='/app/manage-apply-unit-situation';" value="第六页">
					<input type="button" onclick="location.href='manage-seventh-ip-doc';" value="第七页">
				</c:if>
				<c:if test="${applier.applicationType=='自然科学类' }">
					<input type="button" onclick="location.href='/app/manage-paper-monograph';" value="第七页">
					<input type="button" onclick="location.href='manage-seventh-paper-cited-by-others';" value="第六页">
				</c:if>
				<input type="button" onclick="location.href='manage-eighth-major-contributor';" value="第八页">
				<input type="button" onclick="location.href='app/manage-nineth-major-org-contributor';" value="第九页">
				<input type="button" onclick="location.href='confirm-whole-application-by-applier';" value="确认提交">
</body>
</html>
