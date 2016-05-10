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
	<title>七、主要知识产权证明目录（不超过10件）</title>
	<style>
		table { font-size:small}
	</style>
</head>
<body>
<div class="container" style="width:100%">
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
	<div class="wrapper" >
		<dicipulus:bodySidebarForDisplay page="8"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>八、主要完成人情况表</h1></div>
            <table class="table table-bordered" style="width:100%">
		        <tr>
					<td>排名</td>
					<td>知识产权类别</td>
					<td>知识产权具体名称</td>
					<td>国家（地区）</td>
					<td>授权号</td>
					<td>授权日期</td>
					<td>证书编号</td>
					<td>权利人</td>
					<td>发明人</td>
					<td>发明专利有效状态</td>
				</tr>
				<c:forEach var="seventhIPForm" items="${seventhIPForms }">
						<tr>
							<td>${seventhIPForm.rankOfIP}</td>
							<td>${seventhIPForm.typeOfIP} </td>
							<td>${seventhIPForm.nameOfIP} </td>
							<td>${seventhIPForm.locationOfIP} </td>
							<td>${seventhIPForm.authorizationCodeOfIP} </td>
							<td>${seventhIPForm.authorizationDateOfIP} </td>
							<td>${seventhIPForm.certificateNumberOfIP} </td>
							<td>${seventhIPForm.holderOfIP} </td>
							<td>${seventhIPForm.inventorOfIP} </td>
							<td>${seventhIPForm.isValidIP} </td>
						</tr>
				</c:forEach>
			</table>
        </div>
		</div>
	</div>
</div>
</body>
</html>
