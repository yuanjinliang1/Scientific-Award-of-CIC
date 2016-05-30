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
	<title>二、推荐单位意见</title>
</head>

<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="2"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
       
            <div class="row" style="margin-left: 20px"><h1>二、推荐单位意见</h1></div>
             <div class="alert alert-danger"> <strong>此页由推荐单位填写，申请人可以进行浏览</strong></div>	
            <table class="table table-bordered">
				<tr>
					<td>推荐单位名称</td>
					<td>${secondForm.refereeUnitName}</td>
				</tr>
				<tr>
					<td>通讯地址</td>
					<td>${secondForm.postAddress}</td>
				</tr>
				<tr>
					<td>邮政编码</td>
					<td>${secondForm.zipCode }</td>
				</tr>
				<tr>
					<td>联系人</td>
					<td>${secondForm.contact }</td>
				</tr>
				<tr>
					<td>联系电话</td>
					<td>${secondForm.phoneNumber }</td>
				</tr>
				<tr><td>电子邮箱</td>
					<td>${secondForm.email }</td>
				</tr>
				<tr>
					<td>传真</td>
					<td>${secondForm.fax}</td>
				</tr>
			</table>
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
					<h4>
						推荐意见
					</h4>
					</div>
				</div>
				<div class="row panel-body">
					<pre>${secondForm.recommendOpinion}</pre>
				</div>
			</div>
			<div class="row">
				<h4>推荐该项目为中国通信学会科学技术奖${secondForm.referingScienceTechnologyAwardRank}。</h4>
			</div>
			<div class="row" style="margin-left:20px">
				<spring:url value="/edit-first-project-basic-situation" var="firstFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(firstFormURL)}">上一页</a>
				<spring:url value="/edit-brief-introduction" var="thirdFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(thirdFormURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>	
</body>
</html>