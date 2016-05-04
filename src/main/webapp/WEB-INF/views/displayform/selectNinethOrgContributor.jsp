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
<jsp:include page="fragments/footerPagination.jsp"></jsp:include>
</body>
</html>
