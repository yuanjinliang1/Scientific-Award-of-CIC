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
	<title>八、主要完成人情况表</title>
</head>
<body>
<h1>
	八、主要完成人情况表
</h1>
<c:out value="${person.name }"></c:out>

<form action="/app/create-eighth-major-contributor" method="POST" >
<table>
	<tr>
		<td><input type="submit" value="添加主要完成人" name="create"/></td>
	</tr>
</table>
</form>
<table border="1">
	<tr>
		<td>排名</td>
		<td>姓名</td>
		<td>操作</td>
		<td>操作</td>
	</tr>
	<c:forEach var="eighthForm" items="${eighthForms }">
		<tr>
			<td>${eighthForm.rankOfContributor } </td>
			<td>${eighthForm.nameOfContributor }</td>
			<td>
				<spring:url value="/edit-eighth-major-contributor/{idOfEighthForm}" var="editFormURL">
					<spring:param name="idOfEighthForm" value="${eighthForm.idOfEighthForm }"></spring:param>
				</spring:url>
				<a id="editOpinion" href="${fn:escapeXml(editFormURL)}">填写</a>
			</td>
			<td>
				<c:url value="/delete-eighth-major-contributor" var="deleteURL">
					<c:param name="idOfEighthForm" value="${eighthForm.idOfEighthForm }"></c:param>
				</c:url>
				<a id="deleteApplier" href="${fn:escapeXml(deleteURL)}">删除</a>
			</td>
			
		</tr>
	</c:forEach>
</table>

</body>
</html>
