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
	<c:choose>
		<c:when test="${applier.applicationType=='自然科学奖' }">
			<title>四、主要科学发现</title>
		</c:when>
		<c:when test="${applier.applicationType=='科技进步奖' }">
			<title>四、主要科技创新</title>
		</c:when>
		<c:when test="${applier.applicationType=='技术发明奖' }">
			<title>四、主要技术发明</title>
		</c:when>
	</c:choose>
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
		<dicipulus:bodySidebarForDisplay page="4"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px">
            	<h1>
				<c:choose>
					<c:when test="${applier.applicationType=='自然科学类' }">
						四、主要科学发现
					</c:when>
					<c:when test="${applier.applicationType=='科技进步类' }">
						四、主要科技创新
					</c:when>
					<c:when test="${applier.applicationType=='技术发明类' }">
						四、主要技术发明
					</c:when>
					<c:otherwise>
						bad applicationType
					</c:otherwise>
				</c:choose>
            	</h1>
            </div>	
            	<table class="table table-bordered">
				<tr>
					<td>
						<c:choose>
							<c:when test="${applier.applicationType=='自然科学类' }">
								1. 重要科学发现（限5页）
							</c:when>
							<c:when test="${applier.applicationType=='科技进步类' }">
								1. 主要科技创新（限5页）
							</c:when>
							<c:when test="${applier.applicationType=='技术发明类' }">
								1. 主要技术发明（限5页）
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>
					<pre>${fourthForm.fourthForm1}</pre>
					</td>
				</tr>
				<tr>
					<td>
						<c:choose>
							<c:when test="${applier.applicationType=='自然科学类' }">
								2. 研究局限性（限1页）
							</c:when>
							<c:when test="${applier.applicationType=='科技进步类' }">
								2. 科技局限性（限1页）
							</c:when>
							<c:when test="${applier.applicationType=='技术发明类' }">
								2. 技术局限性（限1页）
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td><pre>${fourthForm.fourthForm2}</pre></td>
				</tr>
			</table>
			<div class="row" style="margin-left:20px">
				<spring:url value="/display-brief-introduction/{applierUid}" var="thirdFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(thirdFormURL)}">上一页</a>
				<spring:url value="/display-objective-evaluation/{applierUid}" var="fifthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(fifthFormURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>
</body>
</html>