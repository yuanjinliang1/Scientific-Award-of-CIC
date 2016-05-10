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
	<title>九、主要完成单位情况表</title>
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
		<dicipulus:bodySidebarForDisplay page="9"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>九、主要完成单位情况表</h1></div>	
           	<table class="table table-bordered">
				<tr>
					<td>排名</td>
					<td>单位名称</td>
					<td>操作</td>
				</tr>
				<c:forEach var="ninethForm" items="${ninethForms }">
					<tr>
						<td>${ninethForm.rankOfOrg } </td>
						<td>${ninethForm.nameOfOrg }</td>
						<td>
							<spring:url value="/display-nineth-major-org-contributor/{idOfNinethForm}" var="editFormURL">
								<spring:param name="idOfNinethForm" value="${ninethForm.idOfNinethForm }"></spring:param>
							</spring:url>
							<a id="editOpinion"  class="btn btn-default" href="${fn:escapeXml(editFormURL)}">浏览</a>
						</td>
					</tr>
				</c:forEach>
			</table>
        </div>
		</div>
	</div>
</div>
</body>
</html>
