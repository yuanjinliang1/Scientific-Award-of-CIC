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

<form action="/app/create-seventh-ip-doc" method="POST" >
<table>
	<tr>
		<td><input type="submit" value="添加主要完成单位" name="create"/></td>
	</tr>
</table>
</form>


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
			<spring:url value="/save-seventh-ip-doc/{idOfSeventhIPForm}" var="saveURL">
				<spring:param name="idOfSeventhIPForm" value="${seventhIPForm.idOfSeventhIPForm}"></spring:param>
			</spring:url>
			<form id="seventhIPFormer" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="seventhIPFormAttr">
				<tr>
					<td>${seventhIPForm.rankOfIP}</td>
					<td><input type="text" name="typeOfIP" value="${seventhIPForm.typeOfIP}" /> </td>
					<td><input type="text" name="nameOfIP" value="${seventhIPForm.nameOfIP}" /> </td>
					<td><input type="text" name="locationOfIP" value="${seventhIPForm.locationOfIP}" /> </td>
					<td><input type="text" name="authorizationCodeOfIP" value="${seventhIPForm.authorizationCodeOfIP}" /> </td>
					<td><input type="text" name="authorizationDateOfIP" value="${seventhIPForm.authorizationDateOfIP}" /> </td>
					<td><input type="text" name="certificateNumberOfIP" value="${seventhIPForm.certificateNumberOfIP}" /> </td>
					<td><input type="text" name="holderOfIP" value="${seventhIPForm.holderOfIP}" /> </td>
					<td><input type="text" name="inventorOfIP" value="${seventhIPForm.inventorOfIP}" /> </td>
					<td><input type="text" name="isValidIP" value="${seventhIPForm.isValidIP}" /> </td>
					<td>
						<c:url value="/delete-seventh-ip-doc" var="deleteURL">
							<c:param name="idOfSeventhIPForm" value="${seventhIPForm.idOfSeventhIPForm }"></c:param>
						</c:url>
						<a id="delete" href="${fn:escapeXml(deleteURL)}">删除</a>
					</td>
					<td>
						<input type="submit" value="保存并查看" />
					</td>
				</tr>
			</form>
		</c:forEach>
	</table>

</body>
</html>
