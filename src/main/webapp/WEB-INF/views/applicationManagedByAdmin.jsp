<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<title>项目管理</title>
	<style>
		table { font-size:small}
	</style>
</head>
<body>
<div class="container" style="width:100%; zoom:75%">
<dicipulus:bodyHeaderForAdmin menuName="manageApplication" />
<br/>
<div class="row" style="margin-left: 20px"><h1>项目管理</h1></div>
<div id="feedback"></div>
<div id="message"></div>

<div style="float:right" class="form-inline form-group" >
	<label >全选/反选</label>
	<input type="checkbox" id="multi-checkbox">
	<button class="btn btn-success" id="multi-accept">批量接收</button>
	<button class="btn btn-danger" id="multi-reject">批量退回</button>
	<select id="multi-formalityExaminationResult" class="form-control">
			<option value="批量修改形审结果">批量修改形审结果</option>
			<option value="合格">合格</option>
			<option value="不合格">不合格</option>
	</select>
	<select id="multi-primaryExaminationResult" class="form-control">
			<option value="批量修改初审结果">批量修改初审结果</option>
			<option value="无">无</option>
			<option value="提名三等奖">提名三等奖</option>
			<option value="提名二等奖">提名二等奖</option>
			<option value="提名一等奖">提名一等奖</option>
			<option value="申请特等奖">申请特等奖</option>
	</select>
	<select id="multi-finalExaminationResult" class="form-control">
			<option value="批量修改终审结果">批量修改终审结果</option>
			<option value="无">无</option>
			<option value="三等奖">三等奖</option>
			<option value="二等奖">二等奖</option>
			<option value="一等奖">一等奖</option>
			<option value="特等奖">特等奖</option>
	</select>
	
	<jsp:useBean id="date" class="java.util.Date" />
	<fmt:formatDate value="${date}" pattern="yyyy" var="jspYear" />
	<spring:url value="/download-excel/{year}" var="excelURL">
		<spring:param name="year" value="${jspYear }"></spring:param>
	</spring:url>
	<input type="button" class="btn btn-success" onclick="location.href='${fn:escapeXml(excelURL)}';" value="下载总表（今年）">
</div>
<br>
<table class="table table-bordered" style="width:100%">
	<thead>
	<tr>
		<td>多选</td>
		<td>序号</td>
		<td>项目状态</td>
		<td>项目名称</td>
		<td>推荐单位</td>
		<td>奖种</td>
		<td>推荐等级</td>
		<td>形审结果</td>
		<td>初审结果</td>
		<td>终审结果</td>
		<td>备注</td>
		<td>操作</td>
		<td>操作</td>
		<td>操作</td>
		
	</tr>
	</thead>
	<tbody>
	<c:set var="serial" value="0"></c:set>
	<c:forEach var="application" items="${applications.applicationList }">
		<c:set var="serial" value="${serial+1 }"></c:set>
		<form id="form${serial}" class="app-form" data-serial="${serial }"  modelAttribute="applicationAttr">
		<tr>
				<td><input type="checkbox" id="checkbox${serial}" class="flag-checkbox"> 
				<input type="hidden" id="applierUid${serial}" value="${application.applierUid }"></input>
				</td>
				<td>${serial }</td>
				<td class="inline-form"><div id="projectStatus${serial}" data-serial="${serial }" class="projectStatus">${application.projectStatus }</div>
					<input type="button" id="accept-app${serial}" data-serial="${serial }" style="display:none" class="btn btn-success accept-app"  value="接收">
					<input type="button" id="reject-app${serial}" data-serial="${serial }" style="display:none" class="btn btn-danger reject-app"  value="退回">
				</td>
				<td>${application.projectName }</td>
				<td>${application.refereeString }</td>
				<td>${application.applicationType }</td>
				<td>${application.referingScienceTechnologyAwardRank }</td>
				<td>
					<select id="formalityExaminationResult${serial }" name="formalityExaminationResult" class="form-control">
						<option value="${application.formalityExaminationResult }">${application.formalityExaminationResult }</option>
						<c:forTokens items="合格,不合格" delims="," var="formaliltyResultOption">
							<c:if test="${application.formalityExaminationResult!=formaliltyResultOption}">
								<option value="${formaliltyResultOption }">${formaliltyResultOption }</option>
							</c:if>
						</c:forTokens>
					</select>
				</td>
				<td>
					<select id="primaryExaminationResult${serial }" name="primaryExaminationResult" class="form-control">
						<option value="${application.primaryExaminationResult }">${application.primaryExaminationResult }</option>
						<c:forTokens items="无,提名三等奖,提名二等奖,提名一等奖,申请特等奖" delims="," var="primaryResultOption">
							<c:if test="${application.primaryExaminationResult!=primaryResultOption}">
								<option value="${primaryResultOption }">${primaryResultOption }</option>
							</c:if>
						</c:forTokens>
					</select>
				</td>
				<td>
					<select id="finalExaminationResult${serial }" name="finalExaminationResult" class="form-control">
						<option value="${application.finalExaminationResult }">${application.finalExaminationResult }</option>
						<c:forTokens items="无,三等奖,二等奖,一等奖,特等奖" delims="," var="finalResultOption">
							<c:if test="${application.finalExaminationResult!=finalResultOption}">
								<option value="${finalResultOption }">${finalResultOption }</option>
							</c:if>
						</c:forTokens>
					</select>
				</td>
				<td><input id="commentOfAdmin${serial}" type="text" class="form-control" name="commentOfAdmin" value="${application.commentOfAdmin }"></td>
				<td>
					<spring:url value="/display-first-project-basic-situation/{applierUid}" var="firstFormURL">
						<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
					</spring:url>
					<input type="button" class="btn btn-primary" onclick="location.href='${fn:escapeXml(firstFormURL)}';" value="查看">
				</td>
				<td>
					<input type="submit" class="btn btn-primary" value="保存" />
				</td>
				<td>
					<spring:url value="/download-zip/{applierUid}" var="zipURL">
						<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
					</spring:url>
					<input type="button" class="btn btn-primary" onclick="location.href='${fn:escapeXml(zipURL)}';" value="下载压缩包">
				</td>
		</tr>
		</form>
		
	</c:forEach>
	</tbody>
</table>



</div>
<c:url var="ajaxTest" value="/ajax-test" scope="request" />
<c:url var="ajaxAccept" value="/accept-application-by-admin-via-ajax" scope="request" />
<script>
	jQuery(document).ready(function($) {
		
		$(".projectStatus").each(function(){
			var serial=$(this).attr("data-serial");
			if($(this).text()==="已推荐"){
				$("#accept-app"+serial).show();
				$("#reject-app"+serial).hide();
				console.log("已推荐");
			}
			else if($(this).text()==="已接收"){
				$("#reject-app"+serial).show();
				$("#accept-app"+serial).hide();
				console.log("已接收");
			}
			else{
				$("#accept-app"+serial).hide();
				$("#reject-app"+serial).hide();
				console.log(serial);
			}
		});
		
		
		$(".app-form").submit(function(event) {
			var serial=$(this).attr("data-serial");
			// Disble the search button
			enableSearchButton(false);

			// Prevent the form from submitting via the browser.
			event.preventDefault();
			
			searchViaAjax(serial);

		});
		
		$(".accept-app").click(function(event){
			var serial=$(this).attr("data-serial");
			enableSearchButton(false);
			event.preventDefault();
			
			acceptViaAjaxMulti(serial, "mullti-accept", "true")
		});
		
		$(".reject-app").click(function(event){
			var serial=$(this).attr("data-serial");
			enableSearchButton(false);
			event.preventDefault();
			
			acceptViaAjaxMulti(serial, "mullti-reject", "true")
		});
		
		$("#multi-checkbox").change(function(){
			if($("#multi-checkbox").is(':checked')){
				$(".flag-checkbox").prop('checked',true);
			}
			else if(!$("#multi-checkbox").is(':checked')){
				$(".flag-checkbox").prop('checked',false);
			}
		});
		
		$("#multi-accept").click(function(){
			var multiPosition="mullti-accept";
			multiSubmit(multiPosition);
		});
		
		$("#multi-reject").click(function(){
			var multiPosition="mullti-reject";
			multiSubmit(multiPosition);
		});
		
		$("#multi-formalityExaminationResult").change(function(){
			var multiPosition="formalityExaminationResult";
			var changedValue=$("#multi-formalityExaminationResult option:selected").text();
			if(changedValue==="批量修改形审结果"){
				return false;
			}
			multiSet(multiPosition,changedValue);
		});
		
		$("#multi-primaryExaminationResult").change(function(){
			var multiPosition="primaryExaminationResult";
			var changedValue=$("#multi-primaryExaminationResult option:selected").text();
			if(changedValue==="批量修改初审结果"){
				return false;
			}
			multiSet(multiPosition,changedValue);
		});
		
		$("#multi-finalExaminationResult").change(function(){
			var multiPosition="finalExaminationResult";
			var changedValue=$("#multi-finalExaminationResult option:selected").text();
			if(changedValue==="批量修改终审结果"){
				return false;
			}
			multiSet(multiPosition,changedValue);
		});

	});
	
	function multiSet(multiPosition,changedValue){
		if(!confirm("您确定批量修改所选项目的状态吗？")){
			return false;
		}
		$(".app-form").each(function(){
			var serial=$(this).attr("data-serial");
			if($("#checkbox"+serial).is(':checked')){
				// Disble the search button
				enableSearchButton(false);

				// Prevent the form from submitting via the browser.
				event.preventDefault();

				searchViaAjaxMulti(serial,multiPosition,changedValue);
			}
		});
		console.log("clean");
	}
	
	function cleanMultiSelect(multiPosition){
		if(multiPosition==="formalityExaminationResult"){
			$("#multi-formalityExaminationResult").val("批量修改形审结果");
		}
		if(multiPosition==="primaryExaminationResult"){
			$("#multi-primaryExaminationResult").val("批量修改初审结果");
		}
		if(multiPosition==="finalExaminationResult"){
			$("#multi-finalExaminationResult").val("批量修改终审结果");
		}
	}
	
	function searchViaAjaxMulti(serial,multiPosition,changedValue) {

		var search = {}
		search["applierUid"] = $("#applierUid"+serial).val();
		if(multiPosition==="formalityExaminationResult"){
			search["formalityExaminationResult"] = changedValue;
		}else{
			search["formalityExaminationResult"] = $("#formalityExaminationResult"+serial).val();
		}
		
		if(multiPosition==="primaryExaminationResult"){
			search["primaryExaminationResult"] = changedValue;
		}else{
			search["primaryExaminationResult"] = $("#primaryExaminationResult"+serial).val();
		}
		
		if(multiPosition==="finalExaminationResult"){
			search["finalExaminationResult"] = changedValue;
		}else{
			search["finalExaminationResult"] = $("#finalExaminationResult"+serial).val();
		}
		
		search["commentOfAdmin"] = $("#commentOfAdmin"+serial).val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${ajaxTest}",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				changeCurrentTable(serial,multiPosition,changedValue);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
				errorMessage();
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
	}
	
	function changeCurrentTable(serial,multiPosition,changedValue){
		if(multiPosition==="formalityExaminationResult"){
			$("#formalityExaminationResult"+serial).val(changedValue);
		}
		if(multiPosition==="primaryExaminationResult"){
			$("#primaryExaminationResult"+serial).val(changedValue);
		}
		if(multiPosition==="finalExaminationResult"){
			$("#finalExaminationResult"+serial).val(changedValue);
		}
	}
	
	function searchViaAjax(serial) {

		var search = {}
		search["applierUid"] = $("#applierUid"+serial).val();
		search["formalityExaminationResult"] = $("#formalityExaminationResult"+serial).val();
		search["primaryExaminationResult"] = $("#primaryExaminationResult"+serial).val();	
		search["finalExaminationResult"] = $("#finalExaminationResult"+serial).val();		
		search["commentOfAdmin"] = $("#commentOfAdmin"+serial).val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${ajaxTest}",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
				successMessage();
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
				errorMessage();
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
	}
	
	function multiSubmit(multiPosition){
		if(!confirm("您确定批量修改所选项目的状态吗？")){
			return false;
		}
		$(".app-form").each(function(){
			var serial=$(this).attr("data-serial");
			if($("#checkbox"+serial).is(':checked')){
				// Disble the search button
				enableSearchButton(false);

				// Prevent the form from submitting via the browser.
				event.preventDefault();

				acceptViaAjaxMulti(serial,multiPosition,"false");
			}
		});
	}
	
	
	function acceptViaAjaxMulti(serial,multiPosition,isSingle) {
		console.log("serial:"+serial+" multiPosition:"+multiPosition+" isSingle:"+isSingle);
		var accept = {};
			
		accept["applierUid"] = $("#applierUid"+serial).val();
		var serial=serial;
		var multiPosition=multiPosition;
		var isSingle=isSingle;
		if(multiPosition==="mullti-accept"){
			accept["projectStatus"] = "已接收";
		}
		else if(multiPosition==="mullti-reject"){
			accept["projectStatus"] = "已推荐";
		}
		else{
			console.log("wrong multiPosition:"+multiPosition);
		}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${ajaxAccept}",
			data : JSON.stringify(accept),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if(data["code"]=="400"){
					errorMessage();
				}
				else{
					console.log("SUCCESS: ", data);
					console.log(JSON.stringify(data, null, 4))
					afterHandle(serial,isSingle,multiPosition);
				}
				console.log("status:"+data["code"]);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
				errorMessage();
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
	}
	
	function afterHandle(serial,isSingle,multiPosition){
		console.log("afterHandle serial:"+serial+" isSingle:"+isSingle+" multiPosition:"+multiPosition);
		if(isSingle==="true"){
			successMessage();
		}
		if(multiPosition==="mullti-accept"){
			$("#projectStatus"+serial).text("已接收");
			$("#accept-app"+serial).hide();
			$("#reject-app"+serial).show();
			
		}
		else if(multiPosition==="mullti-reject"){
			$("#projectStatus"+serial).text("已推荐");
			$("#accept-app"+serial).show();
			$("#reject-app"+serial).hide();
		}
		else{
			console.log("wrong multiPosition:"+multiPosition);
		}
	}

	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}

	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		//$('#feedback').html(json);
		$("#feedback").fadeTo(1000, 500).slideUp(500, function(){
        $("#feedback").alert('close');
		});
		
	}
	
	function successMessage(){
		var successMessage='<div class="alert alert-success"> <strong>更新成功</strong></div>';
		$("#message").html(successMessage);
		$("#message").fadeTo(2000, 500).slideUp(500, function(){
        $("#message").alert('close');
		});
	}
	
	function errorMessage(){
		var successMessage='<div class="alert alert-danger"> <strong>更新失败</strong></div>';
		$("#message").html(successMessage);
		$("#message").fadeTo(2000, 500).slideUp(500, function(){
        $("#message").alert('close');
		});
	}
</script>
</body>
</html>
