<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
<%@ page session="true" %>
<!-- Set charset encoding to utf-8  -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<title>七、代表性论文专著被他人引用的情况（不超过8篇）</title>
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${person.role eq 'admin' }">
			<dicipulus:bodyHeaderForAdmin menuName=""/>
		</c:when>
		<c:when test="${person.role eq 'referee' }">
			<dicipulus:bodyHeaderForReferee menuName=""/>
		</c:when>
		<c:when test="${person.role eq 'applier' }">
			<dicipulus:bodyHeaderForApplier menuName=""/>
		</c:when>
		<c:otherwise>
			<c:out value="${person.uid} ${person.name } ${person.role} bad role name" />
		</c:otherwise>
	</c:choose>
	<div class="wrapper">
		<dicipulus:bodySidebarForDisplay page="7"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>七、代表性论文专著被他人引用的情况（不超过8篇）</h1></div>
            <table class="table table-bordered">
            	<tr>
					<td>序号</td>
					<td>被引代表性论文专著序号</td>
					<td>引文题目/作者</td>
					<td>引文刊名/影响因子</td>
					<td>引文发表时间（年 月 日）</td>
				</tr>
				<c:forEach var="seventhPaperForm" items="${seventhPaperForms }">
				<tr>
					<td>${seventhPaperForm.rankOfPaper}</td>
					<td>${seventhPaperForm.doiOfPaper} </td>
					<td>${seventhPaperForm.titleAndAuthorOfPaper} </td>
					<td>${seventhPaperForm.journalAndIF} </td>
					<td>${seventhPaperForm.publishDate}</td>
				</tr>
				</c:forEach>
			</table>
            <div class="row" style="margin-left:20px">
				<spring:url value="/select-paper-monograph/{applierUid}" var="sixthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(sixthFormURL)}">上一页</a>
				<spring:url value="/select-eighth-major-contributor/{applierUid}" var="eighthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(eighthFormURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>
</body>
</html>
