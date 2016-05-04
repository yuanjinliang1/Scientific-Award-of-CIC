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
<title>二、推荐单位意见</title>
<h1>项目组名称: ${applier.name }</h1>
<h1>项目组ID: ${applier.uid }</h1>
</head>

<body>
	<spring:url value="/select-referee-unit-opinion-post/{applierUid}" var="editOpinionURL">
		<spring:param name="applierUid" value="${applier.uid}"></spring:param>
	</spring:url>
	
		<table>
		<tr><td>二、推荐单位意见</td></tr>
		<tr>
			<td>推荐单位名称</td>
			<td>${secondForm.refereeUnitName}</td>
		</tr>
		<tr>
			<td>通讯地址</td>
			<td>${secondForm.postAddress}</td>
		</tr>
		<tr>
			<td>邮政编码</td>
			<td>${secondForm.zipCode }</td>
		</tr>
		<tr>
			<td>联系人</td>
			<td>${secondForm.contact }</td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td>${secondForm.phoneNumber }</td>
		</tr>
		<tr><td>电子邮箱</td>
			<td>${secondForm.email }</td>
		</tr>
		<tr>
			<td>传真</td>
			<td>${secondForm.fax}</td>
		</tr>
		
		<tr>
			<td>推荐意见</td>
			<td>${secondForm.recommendOpinion}</td>
		</tr>
		<tr>
			<td>推荐该项目为中国通信学会科学技术奖${secondForm.referingScienceTechnologyAwardRank}等奖。</td>
		</tr>
		
	</table>
	
<jsp:include page="fragments/footerPagination.jsp"></jsp:include>
	
	
</body>
</html>