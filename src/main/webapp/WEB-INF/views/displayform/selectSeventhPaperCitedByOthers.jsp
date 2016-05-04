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

	<table border="1">
		<tr>
			<td>序号</td>
			<td>被引代表性论文专著序号</td>
			<td>引文题目/作者</td>
			<td>引文刊名/影响因子</td>
			<td>引文发表时间（年 月 日）</td>
			<td>操作</td>
		</tr>
		<c:forEach var="seventhPaperForm" items="${seventhPaperForms }">
		<tr>
			<td>${seventhPaperForm.rankOfPaper}</td>
			<td>${seventhPaperForm.doiOfPaper} </td>
			<td>${seventhPaperForm.titleAndAuthorOfPaper} </td>
			<td>${seventhPaperForm.journalAndIF} </td>
			<fmt:formatDate pattern="yyyy-MM-dd"  value="${seventhPaperForm.publishDate }" var="publishDateVar" />
			<td>${publishDateVar}</td>
		</tr>
		</c:forEach>
	</table>
	
	<jsp:include page="fragments/footerPagination.jsp"></jsp:include>
</body>
</html>
