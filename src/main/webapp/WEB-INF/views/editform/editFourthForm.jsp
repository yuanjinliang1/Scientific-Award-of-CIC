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
	<form id="fourthFormer" action="/app/edit-fourth-form" method="POST" modelAttribute="fourthFormAttr">
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
				<!-- Don't break this line. It's textarea. -->
				<td><textarea rows="80" cols="100" name="fourthForm1" form="fourthFormer">${fourthForm.fourthForm1}</textarea></td>
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
				<!-- Don't break this line. It's textarea. -->
				<td><textarea rows="40" cols="100" name="fourthForm2" form="fourthFormer">${fourthForm.fourthForm2}</textarea></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="保存并查看" />
					<input type="button" onclick="location.href='edit-first-project-basic-situation';" value="第一页">
					<input type="button" onclick="location.href='edit-brief-introduction';" value="第三页">
					<input type="button" onclick="location.href='edit-fourth-form';" value="第四页">
					<input type="button" onclick="location.href='edit-objective-evaluation';" value="第五页">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>