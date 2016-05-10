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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<title>六、推广应用情况、经济效益和社会效益</title>
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
            <div class="row" style="margin-left: 20px">
            <h1>六、推广应用情况、经济效益和社会效益</h1>
            </div>	
            <table class="table table-bordered">
				<tr><td>应用单位名称</td>
					<td>${sixthApplyUnitSituationForm.unitName }</td>
				</tr>
				<tr><td>应用技术</td>
					<td>${sixthApplyUnitSituationForm.applyTechnology }</td>
				</tr>
				<tr>
					<td>应用起止时间</td>
					<td>${ sixthApplyUnitSituationForm.startDate}</td>
				</tr>
				<tr>
					<td>应用单位联系人/电话</td>
					<td>${sixthApplyUnitSituationForm.contactAndPhoneNumber }</td>
				</tr>
				<tr>
					<td>应用情况</td>
					<td>${sixthApplyUnitSituationForm.applySituation }</td>
				</tr>
			</table>
        </div>
		</div>
	</div>
</div>
</body>
</html>