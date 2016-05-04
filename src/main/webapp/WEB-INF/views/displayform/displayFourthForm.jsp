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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<table>
		<tr>
			<td>
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
						bad applicationType
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
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
			</td>
		</tr>
		<tr>
			<td>${fourthForm.fourthForm1}</td>
		</tr>
		<tr>
			<td>
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
			</td>
		</tr>
		<tr>
			<td>${fourthForm.fourthForm2}</td>
		</tr>
	</table>
	<jsp:include page="fragments/footerPagination.jsp"></jsp:include>
</body>
</html>