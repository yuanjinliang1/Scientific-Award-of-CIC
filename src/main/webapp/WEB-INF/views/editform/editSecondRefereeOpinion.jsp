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
	<br/>
	<div class="row" style="margin-left: 20px"><h1>二、推荐单位意见</h1></div>
	<div class="alert alert-danger"> <strong>您正在编辑的是  名称为：<b>${applier.name }</b>，ID为：<b>${applier.uid }</b> 的项目组的推荐单位意见。</strong></div>	
	<spring:url value="/edit-referee-unit-opinion-post/{applierUid}" var="editOpinionURL">
		<spring:param name="applierUid" value="${applier.uid}"></spring:param>
	</spring:url>
	<form id="secondFormer" action="${fn:escapeXml(editOpinionURL)}" method="POST" modelAttribute="secondFormAttr"
		 data-toggle="validator" role="form">
		<table class="table table-bordered">
		<tr>
			<td>推荐单位名称</td>
			<td><input type="hidden" name="refereeUnitName" value="${person.name}"/>${person.name}</td>
		</tr>
		<tr class="form-group">
			<td>通讯地址</td>
			<td>
				<input class="form-control" type="text" name="postAddress" value="${secondForm.postAddress}" data-error="请填写此项" required>
    			<div class="help-block with-errors"></div>
    		</td>
		</tr>
		<tr class="form-group">
			<td>邮政编码</td>
			<td>
				<input class="form-control" type="text" name="zipCode" value="${secondForm.zipCode }" data-error="请填写此项" required>
    			<div class="help-block with-errors"></div>
    		</td>
		</tr>
		<tr class="form-group">
			<td>联系人</td>
			<td>
				<input class="form-control" type="text" name="contact" value="${secondForm.contact }" data-error="请填写此项" required>
    			<div class="help-block with-errors"></div>
    		</td>
		</tr>
		<tr class="form-group">
			<td>联系电话</td>
			<td>
				<input class="form-control" type="text" name="phoneNumber" value="${secondForm.phoneNumber }" data-error="请填写此项" required>
    			<div class="help-block with-errors"></div>
    		</td>
		</tr>
		<tr class="form-group">
			<td>电子邮箱</td>
			<td>
				<input class="form-control" type="text" name="email" value="${secondForm.email }" data-error="请填写此项" required>
   			 	<div class="help-block with-errors"></div>
   			 </td>
		</tr>
		<tr class="form-group">
			<td>传真</td>
			<td>
				<input class="form-control" type="text" name="fax" value="${secondForm.fax}" data-error="请填写此项" required>
    			<div class="help-block with-errors"></div>
    		</td>
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
			<div class="row panel-body form-group">
				<textarea  class="form-control" rows="8" name="recommendOpinion" form="secondFormer"
				placeholder="不超过600字。文中以“该项目”“该领域”等第三人称进行表述。&#13;&#10推荐单位应认真审阅推荐书材料。科技创新点的创新性、先进性、应用效果和对行业科技进步的作用进行概述，并对照中国通信学会科学技术奖授奖条件，写明推荐理由和建议等级。"
				 maxlength="600"  data-error="请填写此项" required>${secondForm.recommendOpinion}</textarea>
    			<div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="row form-group" style="margin-left:5px">
			推荐该项目为中国通信学会${applier.applicationType}
			<select  name="referingScienceTechnologyAwardRank" data-error="请填写此项" required>
				<option value="${secondForm.referingScienceTechnologyAwardRank}">${secondForm.referingScienceTechnologyAwardRank}</option>
				<c:forEach items="${nominatedAwards }" var="nominatedAward">
					<c:if test="${secondForm.referingScienceTechnologyAwardRank!=nominatedAward}">
						<option value="${nominatedAward }">${nominatedAward }</option>
					</c:if>
				</c:forEach>
			</select>
			<div class="help-block with-errors"></div>
		</div>
		<div class="row" style="margin-left:20px;margin-top:10px;">
			<input type="submit" class="btn btn-default" value="保存" />
		</div>
	</form>
</div>
</body>
</html>