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

<html>
<head>
	<title>七、代表性论文专著被他人引用的情况（不超过8篇</title>
</head>
<body>
<h1>
	七、代表性论文专著被他人引用的情况（不超过8篇
</h1>
<c:out value="${person.name }"></c:out>

<form action="/app/create-seventh-paper-cited-by-others" method="POST" >
<table>
	<tr>
		<td><input type="submit" value="添加被引用论文专著" name="create"/></td>
	</tr>
</table>
</form>


	<table border="1">
		<tr>
			<td>序号</td>
			<td>被引代表性论文专著序号</td>
			<td>引文题目/作者</td>
			<td>引文刊名/影响因子</td>
			<td>引文发表时间（年 月 日）</td>
			<td>操作</td>
			<td>操作</td>
		</tr>
		<c:forEach var="seventhPaperForm" items="${seventhPaperForms }">
			<spring:url value="/save-seventh-paper-cited-by-others/{idOfSeventhPaperForm}" var="saveURL">
				<spring:param name="idOfSeventhPaperForm" value="${seventhPaperForm.idOfSeventhPaperForm}"></spring:param>
			</spring:url>
			<form id="seventhPaperFormer" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="seventhPaperFormAttr">
				<tr>
					<td>${seventhPaperForm.rankOfPaper}</td>
					<td><input type="text" name="doiOfPaper" value="${seventhPaperForm.doiOfPaper}" /> </td>
					<td><input type="text" name="titleAndAuthorOfPaper" value="${seventhPaperForm.titleAndAuthorOfPaper}" /> </td>
					<td><input type="text" name="journalAndIF" value="${seventhPaperForm.journalAndIF}" /> </td>
					<fmt:formatDate pattern="yyyy-MM-dd"  value="${seventhPaperForm.publishDate }" var="publishDateVar" />
					<td><input type="date" name="publishDate" value="${publishDateVar}" /></td>
					<td>
						<c:url value="/delete-seventh-paper-cited-by-others" var="deleteURL">
							<c:param name="idOfSeventhPaperForm" value="${seventhPaperForm.idOfSeventhPaperForm }"></c:param>
						</c:url>
						<a id="delete" href="${fn:escapeXml(deleteURL)}">删除</a>
					</td>
					<td>
						<input type="submit" value="保存并查看" />
					</td>
				</tr>
			</form>
		</c:forEach>
	</table>

</body>
</html>