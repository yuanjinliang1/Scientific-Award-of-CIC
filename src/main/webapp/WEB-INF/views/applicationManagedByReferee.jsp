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
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<title>Application Management</title>
</head>
<body>
<div class="container">
<dicipulus:bodyHeaderForReferee menuName="manageApplication"/>
<br/><br/><br/>
<div class="row" style="margin-left: 20px"><h1>项目管理</h1></div>

<table class="table table-bordered">
	<thead>
	<tr>
		<td>序号</td>
		<td>项目状态</td>
		<td>项目名称</td>
		<td>奖种</td>
		<td>推荐等级</td>
		<td>形审结果</td>
		<td>操作</td>
		<td>操作</td>
	</tr>
	</thead>
	<tbody>
	<c:set var="serial" value="0"></c:set>
	<c:forEach var="application" items="${applications.applicationList }">
		<tr>
			<c:set var="serial" value="${serial+1 }"></c:set>
			<td>${serial }</td>
			<td >
				<div id="projectStatus${serial}">${application.projectStatus }</div>
				<c:if test="${application.projectStatus=='已提交' }">
					<spring:url value="/submit-application-by-referee/{applierUid}" var="acceptURL">
						<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
					</spring:url>
					<input id="submitApplication" type="button" class="btn btn-success" onclick="location.href='${fn:escapeXml(acceptURL)}';" value="推荐">
				</c:if>
				<c:if test="${application.projectStatus=='已推荐' }">
					<spring:url value="/withdraw-application-by-referee/{applierUid}" var="withdrawURL">
						<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
					</spring:url>
					<input id="recallApplication" type="button" class="btn btn-danger" onclick="location.href='${fn:escapeXml(withdrawURL)}';" value="撤回推荐">
				</c:if>
			</td>
			<td>${application.projectName }</td>
			<td>${application.applicationType }</td>
			<td>${application.referingScienceTechnologyAwardRank }</td>
			<td>${application.formalityExaminationResult }</td>
			<td>
				<spring:url value="/display-first-project-basic-situation/{applierUid}" var="firstFormURL">
					<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
				</spring:url>
				<input type="button" class="btn btn-default" onclick="location.href='${fn:escapeXml(firstFormURL)}';" value="查看申请书">
			</td>
			<td>
				<spring:url value="/edit-referee-unit-opinion/{applierUid}" var="editOpinionURL">
					<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
				</spring:url>
				<a id="editOpinion${serial}" data-serial="${serial}" class="btn btn-default editOpinion" href="${fn:escapeXml(editOpinionURL)}">填写推荐书</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<script type="text/javascript">
jQuery(document).ready(function($){
	$(".editOpinion").each(function(){
		var serialHere=$(this).data("serial");
		if($("#projectStatus"+serialHere).text()!="未提交"&&$("#projectStatus"+serialHere).text()!="已提交"){
			console.log("22");
			$(this).click(function(event){
				$(this).prop("disabled", false);
				event.preventDefault();
				window.location="#";
				alert("您已推荐此项目，请撤回推荐后再进行修改。");
			})
		}
	});
	$("#recallApplication").click(function(){
		if(!confirm("您确定要撤回推荐吗？")){
			return false;
		}
		else {
			window.location.href = "${fn:escapeXml(withdrawURL)}";
		}
	});
	$("#submitApplication").click(function(){
		if(!confirm("您确定要提交推荐吗？")){
			return false;
		}
		else {
			window.location.href = "${fn:escapeXml(acceptURL)}";
		}
	});
})

</script>
</body>
</html>