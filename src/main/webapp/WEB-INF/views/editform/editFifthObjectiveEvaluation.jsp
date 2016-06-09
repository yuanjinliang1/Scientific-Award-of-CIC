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

<html>
<head>
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<title>五、客观评价</title>
</head>
<body>
<div class="container">
			<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="5"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>五、客观评价<small>（限两页）</small></h1></div>
            	<form id="fifthFormer" action="/app/edit-objective-evaluation" method="POST" modelAttribute="objectiveEvaluation"
            		data-toggle="validator" role="form">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
							<h4 style="margin-left:20px">
								客观评价
							</h4>
							</div>
						</div>
						<div class="row panel-body form-group" style="padding-top:0px;padding-bottom:0px">
							<textarea class="form-control" style="padding:0px" rows="40"  name="objectiveEvaluation" 
							placeholder="限2页.&#13;&#10围绕科技创新点的创新性、先进性、应用效果和对行业科技进步的作用，做出客观、真实、准确评价。填写的评价意见要有客观依据，主要包括与国内外相关技术的比较，国家相关部门正式作出的技术检测报告、验收意见、鉴定结论，国内外重要科技奖励，国内外同行在重要学术刊物、学术专著和重要国际学术会议公开发表的学术性评价意见等，可在附件中提供证明材料。非公开资料（如私人信函等）不能作为评价依据。"
							form="fifthFormer" data-error="请填写此项" required>${objectiveEvaluationForm.objectiveEvaluation}</textarea>
							<div class="help-block with-errors" style="font-size:15px"></div>
						</div>
					</div>
					<div class="row" style="margin-left:20px">
						<input type="submit" class="btn btn-primary" value="保存" />
						<spring:url value="/edit-fourth-form" var="fourthFormURL"/>
							<a class="btn btn-default" href="${fn:escapeXml(fourthFormURL)}">上一页</a>
						<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
							<spring:url value="/manage-apply-unit-situation" var="sixthFormURL"/>
						</c:if>
						<c:if test="${applier.applicationType=='自然科学类' }">
							<spring:url value="/manage-paper-monograph" var="sixthFormURL"/>
						</c:if>
							<a class="btn btn-default" href="${fn:escapeXml(sixthFormURL)}">下一页</a>
					</div>
				</form>
        </div>
		</div>
	</div>
</div>
</body>
</html>