<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<!-- Set charset encoding to utf-8  -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>六、推广应用情况、经济效益和社会效益</title>
</head>
<body>
	<spring:url value="/save-sixth-apply-unit-situation/{idOfApplyUnit}" var="saveURL">
		<spring:param name="idOfApplyUnit" value="${sixthApplyUnitSituationForm.idOfApplyUnit}"></spring:param>
	</spring:url>
	<form id="sixthApplyUnitSituationForm" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="sixthFormAttr">
		<table>
			<tr><td>应用单位名称</td>
				<td>${sixthApplyUnitSituationForm.unitName }</td>
			</tr>
			<tr><td>应用技术</td>
				<td>${sixthApplyUnitSituationForm.applyTechnology }</td>
			</tr>
			<tr>
				<td>应用起止时间</td>
				<td>${ sixthApplyUnitSituationForm.startDate}</td>
			</tr>
			<tr>
				<td>应用单位联系人/电话</td>
				<td>${sixthApplyUnitSituationForm.contactAndPhoneNumber }</td>
			</tr>
			<tr>
				<td>应用情况</td>
				<td>${sixthApplyUnitSituationForm.applySituation }</td>
			</tr>
		</table>
		<table>
			<tr><td><input type="submit" value="保存"></td></tr>
		</table>
	</form>
</body>
</html>