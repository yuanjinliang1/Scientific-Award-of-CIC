<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客观评价</title>
</head>
<body>
	<form action="/app/edit-objective-evaluation" method="POST" modelAttribute="objectiveEvaluation">
		<table>
			<tr><td>客观评价</td></tr>
			<td><input type="text" name="objectiveEvaluation" value="${objectiveEvaluationForm.objectiveEvaluation }"></td>
			<tr><td><input type="submit" value="保存"></td></tr>
		</table>
	</form>
</body>
</html>