<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>六、论文专著目录</title>
</head>
<body>
	<form action="/app/create-paper-monograph" method="POST" >
		<table>
			<tr><td><input type="submit" value="添加"></td></tr>
		</table>
	</form>
	<table>
		<tr>
			<td>论文专注名称</td>
			<td>操作</td>
		</tr>
		<c:forEach var="sixthForm" items="${sixthPaperMonographForms}">
				<tr>
					<td>${sixthForm.name}</td>
					<td>
						<spring:url value="/select-sixth-paper-monograph/{idOfPaperMonograph}" var="editFormURL">
							<spring:param name="idOfPaperMonograph" value="${sixthForm.idOfPaperMonograph}"></spring:param>
						</spring:url>
						<a id="editOpinion" href="${fn:escapeXml(editFormURL)}">浏览</a>
					</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>