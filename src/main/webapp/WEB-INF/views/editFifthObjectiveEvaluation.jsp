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
	<form id="fifthFormer" action="/app/edit-objective-evaluation" method="POST" modelAttribute="objectiveEvaluation">
		<table>
			<tr>
				<td>五、客观评价</td>
			</tr>
			<tr>
				<!-- Don't break this line. It's textarea. -->
				<td><textarea rows="40" cols="100" name="objectiveEvaluation" form="fifthFormer">${objectiveEvaluationForm.objectiveEvaluation}</textarea></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="保存并查看" />
					<input type="button" onclick="location.href='edit-first-project-basic-situationTA';" value="第一页">
					<input type="button" onclick="location.href='edit-brief-introduction';" value="第三页">
					<input type="button" onclick="location.href='edit-objective-evaluation';" value="第五页">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>