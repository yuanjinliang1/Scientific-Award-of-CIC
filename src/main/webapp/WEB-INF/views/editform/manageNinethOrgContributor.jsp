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
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="9"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>九、主要完成单位情况表</h1></div>
            <form action="/app/create-nineth-major-org-contributor" method="POST" >
			<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-default" value="添加主要完成单位" />
				</div>
			</form>	
            <table class="table table-bordered">
				<tr>
					<td>排名</td>
					<td>单位名称</td>
					<td>操作</td>
					<td>操作</td>
				</tr>
				<c:forEach var="ninethForm" items="${ninethForms }">
					<tr>
						<td>${ninethForm.rankOfOrg } </td>
						<td>${ninethForm.nameOfOrg }</td>
						<td>
							<spring:url value="/edit-nineth-major-org-contributor/{idOfNinethForm}" var="editFormURL">
								<spring:param name="idOfNinethForm" value="${ninethForm.idOfNinethForm }"></spring:param>
							</spring:url>
							<a id="editOpinion" href="${fn:escapeXml(editFormURL)}">填写</a>
						</td>
						<td>
							<c:url value="/delete-nineth-major-org-contributor" var="deleteURL">
								<c:param name="idOfNinethForm" value="${ninethForm.idOfNinethForm }"></c:param>
							</c:url>
							<a id="deleteApplier" href="${fn:escapeXml(deleteURL)}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="row" style="margin-left:20px">
				<spring:url value="/manage-eighth-major-contributor" var="eighthFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(eighthFormURL)}">上一页</a>
				<spring:url value="/upload/{applierUid}/1" var="firstAttachmentURL">
					<spring:param name="applierUid" value="${applier.uid}"></spring:param>
				</spring:url>
					<a class="btn btn-default" href="${fn:escapeXml(firstAttachmentURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>









				
</body>
</html>
