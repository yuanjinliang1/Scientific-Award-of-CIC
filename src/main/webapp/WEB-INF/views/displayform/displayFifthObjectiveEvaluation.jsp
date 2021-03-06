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
	<title>五、客观评价</title>
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
		<dicipulus:bodySidebarForDisplay page="5"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>五、客观评价</h1></div>
            <table class="table table-bordered">
				<tr>
					<td>客观评价</td>
				</tr>
				<tr>
					<td><pre>${objectiveEvaluationForm.objectiveEvaluation}</pre></td>
				</tr>
			</table>
			<div class="row" style="margin-left:20px">
				<spring:url value="/display-fourth-form/{applierUid}" var="fourthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(fourthFormURL)}">上一页</a>
				<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
					<spring:url value="/select-apply-unit-situation/{applierUid}" var="sixthFormURL">
						<spring:param name="applierUid" value="${applier.uid }"></spring:param>
					</spring:url>
				</c:if>
				<c:if test="${applier.applicationType=='自然科学类' }">
					<spring:url value="/select-paper-monograph/{applierUid}" var="sixthFormURL">
						<spring:param name="applierUid" value="${applier.uid }"></spring:param>
					</spring:url>
				</c:if>
				<a class="btn btn-default" href="${fn:escapeXml(sixthFormURL)}">下一页</a>
			</div>
        </div>
        </div>
    </div>
</div>
</body>
</html>