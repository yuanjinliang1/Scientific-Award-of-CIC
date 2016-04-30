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
<title>三、项目简介</title>
</head>
<body>
<form id="thirdFormer" action="/app/edit-brief-introduction" method="POST" modelAttribute="briefIntroduction">
	<table>
		<tr>
			<td>项目简介</td>
		</tr>
		<tr>
			<!-- Don't break this line. It's textarea. -->
			<td><textarea rows="40" cols="100" name="briefIntroduction" form="thirdFormer">${briefIntroductionForm.briefIntroduction}</textarea></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="保存并查看" />
				<input type="button" onclick="location.href='edit-first-project-basic-situationTA';" value="第一页">
				<input type="button" onclick="location.href='edit-brief-introduction';" value="第三页">
				<input type="button" onclick="location.href='edit-fourth-form';" value="第四页">
				<input type="button" onclick="location.href='edit-objective-evaluation';" value="第五页">
			</td>
		</tr>
	</table>

</form>
</body>
</html>