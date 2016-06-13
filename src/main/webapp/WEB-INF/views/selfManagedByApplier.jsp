<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
<%@ page session="true"%>
<!-- Set charset encoding to utf-8  -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<title>项目与个人管理</title>
	<style type="text/css">
		.center {
				     float: none;
				     margin-left: auto;
				     margin-right: auto;
				}
	</style>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="manageSelf"/>
	<br/><br/><br/>
	<div id="message"></div>
	<div class="row" id="selectAppType">
		<form action="/app/edit-initialize-application" method="post">
			<label style="font-size:20px">设置申请类型:</label>
			<select style="width:150px" name="applicationType">
				<option value="自然科学类">自然科学类</option>
				<option value="科技进步类">科技进步类</option>
				<option value="技术发明类">技术发明类</option>
			</select>
			<input id="setAppType" type="submit" class="btn btn-success" value="确定" />
		</form>
	</div>
	
	<table class="table table-bordered">
		<tr>
			<td>项目状态</td>
			<td>成果登记</td>
			<td>项目名称</td>
			<td>申请类型</td>
			<td>推荐等级</td>
			<td>形审结果</td>
			<td>操作</td>
			<td>操作</td>
			<td>下载pdf</td>
		</tr>
		<tr>
			<c:set var="serial" value="${serial+1 }"></c:set>
			<td>
				${application.projectStatus }
				<c:if test="${application.projectStatus=='未提交' }">
					<spring:url value="/submit-application-by-applier" var="acceptURL">
					</spring:url>
					<input id="submitApplication" type="button" class="btn btn-success" value="提交">
				</c:if>
				<c:if test="${application.projectStatus=='已提交' }">
					<spring:url value="/withdraw-application-by-applier" var="withdrawURL">
					</spring:url>
					<input id="recallApplication" type="button" class="btn btn-danger" value="撤回">
				</c:if>
			</td>
			<td>
				<form action="/app/set-result-registration" method="post">
				 	<select id="resultRegistration" name="resultRegistration" class="form-control" >
					 	<c:choose>
					 		<c:when test="${application.resultRegistration == '是'}" >
					 			<option value="是">是</option>
								<option value="否">否</option>	
					 		</c:when>
					 		<c:when test="${application.resultRegistration == '否'}" >
					 			<option value="否">否</option>
								<option value="是">是</option>	
					 		</c:when>
					 		<c:otherwise>
						 		<option selected disabled>请选择</option>
								<option value="是">是</option>
								<option value="否">否</option>
					 		</c:otherwise>
					 	</c:choose>
					</select>
				</form>
			 </td>
			<td>${application.projectName }</td>
			<td id="applicationType">${application.applicationType }</td>
			<td>${application.referingScienceTechnologyAwardRank }</td>
			<td>${application.formalityExaminationResult }</td>
			<td>
				<spring:url value="/display-first-project-basic-situation/{applierUid}" var="firstFormURL">
					<spring:param name="applierUid" value="${application.applierUid }"></spring:param>
				</spring:url>
				<a type="button" class="btn btn-default to-read" href='${fn:escapeXml(firstFormURL)}'>查看</a>
			</td>
			<td>
				<spring:url value="/edit-first-project-basic-situation" var="editFirstFormURL">
				</spring:url>
				<a type="button" class="btn btn-default to-edit" href='${fn:escapeXml(editFirstFormURL)}'>编辑</a>
			</td>
				<spring:url value="/download-pdf/{applierUid}" var="pdfURL">
					<spring:param name="applierUid" value="${application.applierUid}"></spring:param>
				</spring:url>
			<td>
				<a type="button" class="btn btn-default to-read" href='${fn:escapeXml(pdfURL)}'>下载pdf</a>
		</tr>
	</table>
	<div>
	<form action="/app/self-managed-by-applier/change-name" method="POST">
		<div class="form-group col-md-4 panel panel-default center">
			<div class="panel-heading row">
				<h3 class=" panel-title ">修改用户名</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<label>原名称: <c:out value="${ person.name }"/></label>
				</div>
				<div class="row">
					<label>新名称</label>
					<input type="hidden" value="${person }" name="person" />
					<input type="text" class="form-control" name="name" />
					<br/>
				</div>
				<div class="row">
					<input type="submit" class="btn btn-default" value="修改名称" />
				</div>
			</div>
		</div>
	</form>
	</div>
	<div><br/>
	<form action="/app/self-managed-by-applier/change-password" 	method="POST">
		<div class="form-group col-md-4 panel panel-default center">
			<div class="panel-heading row">
				<h3 class=" panel-title ">修改密码</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<label>输入旧密码</label>
					<input type="password" class="form-control" name="passwordOld" />
					<br/>
				</div>
				<div class="row">
					<label>输入新密码</label>
					<input type="password" class="form-control" name="passwordNew1" />
					<br/>
				</div>
				<div class="row">
					<label>再次输入新密码</label>
					<input type="password" class="form-control" name="passwordNew2" /> 
					<input type="hidden" value="${person }" name="person" />
					<br/>
				</div>
				<div class="row">
					<input type="submit" class="btn btn-default" value="修改密码" />
				</div>
			</div>
		</div>
	</form>
	</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(function($){
		$(".to-edit").each(function(){
			if($("#applicationType").text()!="自然科学类"&&$("#applicationType").text()!="科技进步类"&&$("#applicationType").text()!="技术发明类"){
				$(this).click(function(event){
					enableSearchButton(false);
					event.preventDefault();
					window.location="#";
					var failMessage='<div class="alert alert-danger"> <strong>请先选定申请类型</strong></div>';
					$("#message").html(failMessage);
					$("#message").fadeTo(2000, 500).slideUp(500, function(){
				        $("#message").alert('close');
				        return false;
					});
				});
			}
			else if("${application.projectStatus}"!="未提交"){
				$(this).click(function(event){
					enableSearchButton(false);
					event.preventDefault();
					window.location="#";
					alert("您已提交，请先撤回项目再进行修改。");
				});
			}
		});
		
		$(".to-read").each(function(){
			if($("#applicationType").text()!="自然科学类"&&$("#applicationType").text()!="科技进步类"&&$("#applicationType").text()!="技术发明类"){
				$(this).click(function(event){
					enableSearchButton(false);
					event.preventDefault();
					window.location="#";
					var failMessage='<div class="alert alert-danger"> <strong>请先选定申请类型</strong></div>';
					$("#message").html(failMessage);
					$("#message").fadeTo(2000, 500).slideUp(500, function(){
				        $("#message").alert('close');
				        return false;
					});
				});
			}
		});
		
		$("#setAppType").click(function(){
			if("${application.projectStatus}"!="未提交"){
				enableSearchButton(false);
				event.preventDefault();
				window.location="#";
				alert("您已提交，请先撤回项目再进行修改。");
			}
		})
		
		$("#resultRegistration").change(function(){
			if("${application.projectStatus}"!="未提交"){
				alert("您已提交，请先撤回项目再进行修改。");
				return false;
			}
			if(!confirm("您确定修改成果登记信息吗？")){
				return false;
			}
			this.form.submit();
		});
		
		$("#submitApplication").click(function(){
			if($("#resultRegistration option:selected").text()!='是'&&
					$("#resultRegistration option:selected").text()!='否'){
				alert("请您选择成果登记情况后再提交。");
				return false;
			}
			if(!confirm("您确定要提交申请书吗？")){
				return false;
			}
			else {
				window.location.href = "${fn:escapeXml(acceptURL)}";
			}
		});
		$("#recallApplication").click(function(){
			if(!confirm("您确定要撤回申请书吗？")){
				return false;
			}
			else {
				window.location.href = "${fn:escapeXml(withdrawURL)}";
			}
		});
		
		function enableSearchButton(flag) {
			$(".to-edit").prop("disabled", flag);
		}
	})
</script>
</body>
</html>