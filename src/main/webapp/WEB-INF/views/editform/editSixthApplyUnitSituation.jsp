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
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="6"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>六、推广应用情况、经济效益和社益</h1></div>
            <div class="row" style="margin-left: 20px"><h3>编辑应用单位</h3></div>
           	<spring:url value="/save-sixth-apply-unit-situation/{idOfApplyUnit}" var="saveURL">
				<spring:param name="idOfApplyUnit" value="${sixthApplyUnitSituationForm.idOfApplyUnit}"></spring:param>
			</spring:url>
			<form id="sixthApplyUnitSituationForm" action="${fn:escapeXml(saveURL)}" method="POST" 
				modelAttribute="sixthFormAttr" data-toggle="validator" role="form">
				<table class="table table-bordered">
					<tr><td>应用单位名称</td>
						<td class="form-group">
							<input type="text" name="unitName" value="${sixthApplyUnitSituationForm.unitName }" 
								data-error="请填写此项" required>
						 	<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr><td>应用技术</td>
						<td class="form-group">
							<input type="text" name="applyTechnology" value="${sixthApplyUnitSituationForm.applyTechnology }"
							data-error="请填写此项" required>
						 	<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>应用起止时间</td>
						<td class="form-group">
							<input type="text" name="startDate" value="${ sixthApplyUnitSituationForm.startDate}"
							data-error="请填写此项" required>
						 	<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>应用单位联系人/电话</td>
						<td class="form-group">
							<input type="text" name="contactAndPhoneNumber" value="${sixthApplyUnitSituationForm.contactAndPhoneNumber }"
							data-error="请填写此项" required>
							 <div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>应用情况</td>
						<td class="form-group">
							<input type="text" name="applySituation" value="${sixthApplyUnitSituationForm.applySituation }"
							data-error="请填写此项" required>
						 	<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
				</table>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="保存" />
					<spring:url value="/manage-apply-unit-situation" var="sixthFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(sixthFormURL)}">返回</a>
				</div>
			</form>
        </div>
		</div>
	</div>
</div>




	
</body>
</html>