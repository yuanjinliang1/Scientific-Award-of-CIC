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
<title>推荐单位意见</title>
</head>
<body>
	<form action="/app/edit-referee-unit-opinion" method="POST" modelAttribute="secondFormAttr">
		<table><tr>
			<td>推荐单位名称</td>
			<td><input type="text" name="refereeUnitName" value="${secondForm.refereeUnitName}"/></td>
		</tr>
		<tr>
			<td>通讯地址</td>
			<td><input type="text" name="postAddress" value="${secondForm.postAddress}"></td>
		</tr>
		<tr>
			<td>邮政编码</td>
			<td><input type="text" name="zipCode" value="${secondForm.zipCode }"></td>
		</tr>
		<tr>
			<td>联系人</td>
			<td><input type="text" name="contact" value="${secondForm.contact }"></td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td><input type="text" name="phoneNumber" value="${secondForm.phoneNumber }"></td>
		</tr>
		<tr><td>电子邮箱</td>
			<td><input type="text" name="email" value="${secondForm.email }"></td>
		</tr>
		<tr>
			<td>传真</td>
			<td><input type="text" name="fax" value="${secondForm.fax}"></td>
		</tr>
		
		<tr>
			<td>推荐意见</td>
			<td><input type="text" name="recommendOpinion" value="${secondForm.recommendOpinion}"></td>
			
		
		</tr>
		<tr><input type="submit" value="保存"/></tr>
	</table>
	<table>
		
		
	</table>	
	</form>
	
	
</body>
</html>