<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<title>六、论文专著目录</title>
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
		<dicipulus:bodySidebarForDisplay page="6"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>六、论文专著目录</h1></div>
            <table class="table table-bordered">
            	<tr>
					<td>论文专注名称</td>
					<td>操作</td>
				</tr>
				<c:forEach var="sixthForm" items="${sixthPaperMonographForms}">
						<tr>
							<td>${sixthForm.name}</td>
							<td>
								<spring:url value="/display-sixth-paper-monograph/{idOfPaperMonograph}" var="editFormURL">
									<spring:param name="idOfPaperMonograph" value="${sixthForm.idOfPaperMonograph}"></spring:param>
								</spring:url>
								<a id="editOpinion" class="btn btn-default" href="${fn:escapeXml(editFormURL)}">浏览</a>
							</td>
						</tr>
					</c:forEach>
			</table>
			<div class="row" style="margin-left:20px">
				<spring:url value="/display-objective-evaluation/{applierUid}" var="fifthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>				
				<a class="btn btn-default" href="${fn:escapeXml(fifthFormURL)}">上一页</a>
				<spring:url value="/select-seventh-paper-cited-by-others/{applierUid}" var="seventhFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(seventhFormURL)}">下一页</a>
			</div>
            
        </div>
		</div>
	</div>
</div>
</body>
</html>