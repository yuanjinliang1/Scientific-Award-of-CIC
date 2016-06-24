<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<title>八、主要完成人情况表</title>
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
		<dicipulus:bodySidebarForDisplay page="8"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>八、主要完成人情况表</h1></div>	
            <table class="table table-bordered">
				<tr>
					<td>排名</td>
					<td>姓名</td>
					<td>操作</td>
				</tr>
				<c:forEach var="eighthForm" items="${eighthForms }">
					<tr>
						<td>${eighthForm.rankOfContributor } </td>
						<td>${eighthForm.nameOfContributor }</td>
						<td>
							<spring:url value="/display-eighth-major-contributor/{idOfEighthForm}" var="editFormURL">
								<spring:param name="idOfEighthForm" value="${eighthForm.idOfEighthForm }"></spring:param>
							</spring:url>
							<a id="editOpinion" class="btn btn-default" href="${fn:escapeXml(editFormURL)}">浏览</a>
						</td>
						
					</tr>
				</c:forEach>
			</table>
			<div class="row" style="margin-left:20px">
				<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
					<spring:url value="/select-seventh-ip-doc/{applierUid}" var="seventhFormURL">
						<spring:param name="applierUid" value="${applier.uid }"></spring:param>
					</spring:url>
				</c:if>
				<c:if test="${applier.applicationType=='自然科学类' }">
					<spring:url value="/select-seventh-paper-cited-by-others/{applierUid}" var="seventhFormURL">
						<spring:param name="applierUid" value="${applier.uid }"></spring:param>
					</spring:url>
				</c:if>
				<a class="btn btn-default" href="${fn:escapeXml(seventhFormURL)}">上一页</a>
				<spring:url value="/select-ninth-major-org-contributor/{applierUid}" var="ninthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(ninthFormURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>
</body>
</html>
