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
	<title>选择项目基本信息</title>
</head>
<body>
	<form action="/app/edit-initialize-application" method="post">
		<table>
			<tr>
				<td>申请类型</td>
				<td>
					<select name="applicationType">
						<option value="自然科学类">自然科学类</option>
						<option value="科技进步类">科技进步类</option>
						<option value="技术发明类">技术发明类</option>
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