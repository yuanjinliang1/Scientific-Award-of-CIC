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
	<title>七、主要知识产权证明目录（不超过10件）</title>
</head>
<body>
	<h1>
		七、主要知识产权证明目录（不超过10件）
	</h1>
	<c:out value="${person.name }"></c:out>

	<table border="1">
		<tr>
			<td>排名</td>
			<td>知识产权类别</td>
			<td>知识产权具体名称</td>
			<td>国家（地区）</td>
			<td>授权号</td>
			<td>授权日期</td>
			<td>证书编号</td>
			<td>权利人</td>
			<td>发明人</td>
			<td>发明专利有效状态</td>
		</tr>
		<c:forEach var="seventhIPForm" items="${seventhIPForms }">
				<tr>
					<td>${seventhIPForm.rankOfIP}</td>
					<td>${seventhIPForm.typeOfIP} </td>
					<td>${seventhIPForm.nameOfIP} </td>
					<td>${seventhIPForm.locationOfIP} </td>
					<td>${seventhIPForm.authorizationCodeOfIP} </td>
					<td>${seventhIPForm.authorizationDateOfIP} </td>
					<td>${seventhIPForm.certificateNumberOfIP} </td>
					<td>${seventhIPForm.holderOfIP} </td>
					<td>${seventhIPForm.inventorOfIP} </td>
					<td>${seventhIPForm.isValidIP} </td>
				</tr>
		</c:forEach>
	</table>

	<jsp:include page="fragments/footerPagination.jsp"></jsp:include>

</body>
</html>
