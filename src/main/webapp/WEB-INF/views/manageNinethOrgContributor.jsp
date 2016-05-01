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

<form action="/app/create-nineth-org-contributor" method="POST" >
<table>
	<tr>
		<td><input type="hidden" value="${person }" name="person" /> </td>
		<td><input type="submit" value="添加主要完成单位" name="create"/></td>
	</tr>
</table>
</form>
<table>
	<tr>
		<td>排名</td>
		<td>单位名称</td>
		<td>操作</td>
		<td>操作</td>
		<td>操作</td>
	</tr>
	<c:forEach var="ninethForm" items="${ninethForms }">
		<tr>
			<td>${ninethForm.rankOfOrg } </td>
			<td>${ninethForm.nameOfOrg }</td>
			<td>
				<spring:url value="/edit-nineth-major-org-contributor/{idOfNinethForm}" var="editFormURL">
					<spring:param name="idOfNinethForm" value="${ninethForm.idOfNinethForm }"></spring:param>
				</spring:url>
				<a id="editOpinion" href="${fn:escapeXml(editFormURL)}">填写主要完成单位情况表</a>
			</td>
			<td>
				<c:url value="/delete-nineth-major-org-contributor" var="deleteURL">
					<c:param name="idOfNinethForm" value="${ninethForm.idOfNinethForm }"></c:param>
				</c:url>
				<a id="deleteApplier" href="${fn:escapeXml(deleteURL)}">删除主要完成单位情况表</a>
			</td>
			
		</tr>
	</c:forEach>
</table>

</body>
</html>
