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
	<title>一、项目基本情况</title>
</head>
<body>
<form action="/app/save-first-project-basic-situation-TA" method="POST" modelAttribute="firstFormAttr">
	<table border="1">
		<tr>
			<td>推荐单位（盖章）或推荐专家</td>
			<td>${firstForm.refereeString}</td>
		</tr>
		<tr>
			<td>项目名称</td>
			<td><input type="text" name="projectName" value="${firstForm.projectName }" /></td>
		</tr>
		<tr>
			<td>主要完成人</td>
			<td></td>
		</tr>
		<tr>
			<td>主要完成单位</td>
			<td></td>
		</tr>
		<tr>
			<td>项目密级</td>
			<td>非密</td>
		</tr>
		<tr>
			<td>学科分类名称1</td>
			<td>
				<select name="subjectCategoryName1">
					<option value="${firstForm.subjectCategoryName1}">${firstForm.subjectCategoryName1}</option>
					<c:forEach items="${subjectCategories }" var="subjectCategory">
						<option value="${subjectCategory }">${subjectCategory }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>学科分类名称2</td>
			<td>
				<select name="subjectCategoryName2">
					<option value="${firstForm.subjectCategoryName2}">${firstForm.subjectCategoryName2}</option>
					<c:forEach items="${subjectCategories }" var="subjectCategory">
						<option value="${subjectCategory }">${subjectCategory }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>学科分类名称3</td>
			<td>
				<select name="subjectCategoryName3">
					<option value="${firstForm.subjectCategoryName3}">${firstForm.subjectCategoryName3}</option>
					<c:forEach items="${subjectCategories }" var="subjectCategory">
						<option value="${subjectCategory }">${subjectCategory }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="next-page" />下一页</td>
		</tr>
	</table>

</form>


</body>
</html>