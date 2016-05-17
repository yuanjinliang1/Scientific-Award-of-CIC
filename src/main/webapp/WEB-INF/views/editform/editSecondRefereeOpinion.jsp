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
	<dicipulus:bodyHeaderForReferee menuName="manageApplication"/>
	<br/><br/><br/>
	<div class="row" style="margin-left: 20px"><h1>二、推荐单位意见</h1></div>
	<div class="row" style="margin-left: 20px">
		<h5 style="color:red;">
			您正在编辑的是  名称为：<b>${applier.name }</b>，ID为：<b>${applier.uid }</b> 的项目组的推荐单位意见。
		</h5>
	</div>
	<spring:url value="/edit-referee-unit-opinion-post/{applierUid}" var="editOpinionURL">
		<spring:param name="applierUid" value="${applier.uid}"></spring:param>
	</spring:url>
	<form id="secondFormer" action="${fn:escapeXml(editOpinionURL)}" method="POST" modelAttribute="secondFormAttr">
		<table class="table table-bordered">
		<tr>
			<td>推荐单位名称</td>
			<td><input type="hidden" name="refereeUnitName" value="${person.name}"/>${person.name}</td>
		</tr>
		<tr>
			<td>通讯地址</td>
			<td><input type="text" name="postAddress" value="${secondForm.postAddress}"></td>
		</tr>
		<tr>
			<td>邮政编码</td>
			<td><input type="text" name="zipCode" value="${secondForm.zipCode }"></td>
		</tr>
		<tr>
			<td>联系人</td>
			<td><input type="text" name="contact" value="${secondForm.contact }"></td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td><input type="text" name="phoneNumber" value="${secondForm.phoneNumber }"></td>
		</tr>
		<tr><td>电子邮箱</td>
			<td><input type="text" name="email" value="${secondForm.email }"></td>
		</tr>
		<tr>
			<td>传真</td>
			<td><input type="text" name="fax" value="${secondForm.fax}"></td>
		</tr>
		</table>
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="row">
				<h4 >
					推荐意见
				</h4>
				</div>
			</div>
			<div class="row panel-body">
				<textarea rows="8" name="recommendOpinion" form="secondFormer">${secondForm.recommendOpinion}</textarea>
			</div>
		</div>
		<div class="row" style="margin-left:5px">
			推荐该项目为中国通信学会科学技术奖
			<select name="referingScienceTechnologyAwardRank">
				<option value="${secondForm.referingScienceTechnologyAwardRank}">${secondForm.referingScienceTechnologyAwardRank}</option>
				<c:forEach items="${nominatedAwards }" var="nominatedAward">
					<c:if test="${secondForm.referingScienceTechnologyAwardRank!=nominatedAward}">
						<option value="${nominatedAward }">${nominatedAward }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<div class="row" style="margin-left:20px;margin-top:10px;">
			<input type="submit" class="btn btn-default" value="保存" />
		</div>
	</form>
</div>
</body>
</html>