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
	<title>一、项目基本情况</title>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="1"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>一、项目基本情况</h1></div>
         		<form id="firstFormer" action="/app/save-first-project-basic-situation" method="POST" modelAttribute="firstFormAttr"
         			data-toggle="validator" role="form">
				<table class="table table-bordered">
					<tr>
						<td>推荐单位（盖章）或推荐专家</td>
						<td>${firstForm.refereeString}</td>
					</tr>
					<tr>
						<td>项目名称</td>
						<td class="form-group">
							<input class="form-control" type="text" name="projectName" value="${firstForm.projectName }" 
							placeholder="${person.name}" data-error="请填写此项" maxlength="30" required/>
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
							
					</tr>
					<tr>
						
						<td>主要完成人<small data-toggle="tooltip" title="系统根据申请书信息自动生成，不需要填写">(自动生成)</small></td>
						<td>${firstForm.majorContributorNames }</td>
					</tr>
					<tr>
						<td>主要完成单位<small data-toggle="tooltip" title="系统根据申请书信息自动生成，不需要填写">(自动生成)</small></td>
						<td>${firstForm.majorContributingOrgNames }</td>
					</tr>
					<tr>
						<td>项目密级</td>
						<td>${firstForm.secretLevel }</td>
					</tr>
					<tr>
						<td>学科分类名称1</td>
						<td  class="form-group"> 
							<input class="form-control" type="text" name="subjectCategoryName1" value="${firstForm.subjectCategoryName1 }" 
							 data-error="请填写此项" required/>
							 <div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>学科分类代码1</td>
						<td  class="form-group"> 
							<input class="form-control" type="text" name="subjectCategoryId1" value="${firstForm.subjectCategoryId1 }" 
							 data-error="请填写此项" required/>
							 <div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>学科分类名称2（选填）</td>
						<td  class="form-group"> 
							<input class="form-control" type="text" name="subjectCategoryName2" value="${firstForm.subjectCategoryName2 }"/>
						</td>
					</tr>
					<tr>
						<td>学科分类代码2（选填）</td>
						<td  class="form-group"> 
							<input class="form-control" type="text" name="subjectCategoryId2" value="${firstForm.subjectCategoryId2 }" />
						</td>
					</tr>
					<tr>
						<td>学科分类名称3（选填）</td>
						<td  class="form-group"> 
							<input class="form-control" type="text" name="subjectCategoryName3" value="${firstForm.subjectCategoryName3 }"/>
						</td>
					</tr>
					<tr>
						<td>学科分类代码3（选填）</td>
						<td  class="form-group"> 
							<input class="form-control" type="text" name="subjectCategoryId3" value="${firstForm.subjectCategoryId3 }" />
						</td>
					</tr>
					<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
						<tr>
							<td>所属国民经济行业</td>
							<td  class="form-group"> 
							<input class="form-control" type="text" name="economicField" value="${firstForm.economicField }" 
							 data-error="请填写此项" maxlength="30" required/>
							 <div class="help-block with-errors" style="font-size:15px"></div>
							</td>
						</tr>
						<tr>
							<td>所属国家重点发展领域</td>
							<td class="form-group">
								<input class="form-control" type="text" name="nationalFocusField" value="${firstForm.nationalFocusField }" 
							 data-error="请填写此项" maxlength="30" required/>
								 <div class="help-block with-errors" style="font-size:15px"></div>
							</td>
						</tr>
						<input type="hidden" name="technologicalField" value="NEVER" />
					</c:if>
					<c:if test="${applier.applicationType eq'自然科学类' }">
						<tr>
							<td>所属科学技术领域</td>
							<td class="form-group">
							<input class="form-control" type="text" name="technologicalField" value="${firstForm.technologicalField }" 
							 data-error="请填写此项" maxlength="30" required/>
							 <div class="help-block with-errors" style="font-size:15px"></div>
							 </td>
						</tr>
					</c:if>
					<tr>
						<td>任务来源1</td>
						<td class="form-group">
								<input class="form-control" type="text" name="taskSource1" value="${firstForm.taskSource1 }" 
							 data-error="请填写此项" maxlength="30" required/>
							 <div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>任务来源2（选填）</td>
						<td class="form-group">
								<input class="form-control" type="text" name="taskSource2" value="${firstForm.taskSource2 }" 
							 maxlength="30" />
							 
						</td>
					</tr><tr>
						<td>任务来源3（选填）</td>
						<td class="form-group">
								<input class="form-control" type="text" name="taskSource3" value="${firstForm.taskSource3 }" 
							  maxlength="30"/>
						</td>
					</tr>
				</table>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4>
							具体计划、基金的名称和编号
						</h4>
						</div>
					</div>
					<div class="row panel-body form-group">
						<textarea class="form-control" rows="5"  name="nameAndCodeOfPlansOrFundations" form="firstFormer" 
						 placeholder="不超过300字。应按重要程度依次填写，先国家计划，后其他计划，不超过10项" maxlength="300"
						 data-error="请填写此项" required>${firstForm.nameAndCodeOfPlansOrFundations }</textarea>
						 <div class="help-block with-errors" style="font-size:15px"></div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4>
							已呈交的科技报告编号
						</h4>
						</div>
					</div>
					<div class="row panel-body" >
						<textarea class="form-control" rows="3"  name="technicalReportNumber" form="firstFormer" placeholder="指在国家科技计划项目申报中心呈交的科技报告编号，未呈交的可不填"
						>${firstForm.technicalReportNumber }</textarea>
					</div>
				</div>
				<table class="table table-bordered">
					<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
						<tr>
							<td>授权发明专利项数</td>
							<td class="form-group">
								<input type="number" name="numOfInventionPatent" value="${firstForm.numOfInventionPatent }" 
								class="form-control" data-error="请填写此项" required/>
								<div class="help-block with-errors" style="font-size:15px"></div>
							</td>
						</tr>
						<tr>
							<td>授权的其它知识产权项数</td>
							<td class="form-group">
								<input type="number" name="numOfOtherIntellectualProperty" value="${firstForm.numOfOtherIntellectualProperty }" 
								class="form-control" data-error="请填写此项" required/>
								<div class="help-block with-errors" style="font-size:15px"></div>
							</td>
						</tr>
					</c:if>
					<c:if test="${applier.applicationType=='自然科学类'}">
						<input type="hidden" name="numOfInventionPatent" value="-1" />
						<input type="hidden" name="numOfOtherIntellectualProperty" value="-1" />	
					</c:if>
					<tr>
						<td>项目开始时间</td>
						<td class="form-group">
							<input class="form-control" type="text" name="startDate" value="${firstForm.startDate}" 
							class="form-control" data-error="请填写此项" required/>
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>项目完成时间</td>
						
						<td class="form-group">
							<input class="form-control" type="text"  name="finishDate" value="${firstForm.finishDate }"  
							class="form-control" data-error="请填写此项" required/>
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>推荐单位联系人<small data-toggle="tooltip" title="系统根据申请书信息自动生成，不需要填写">(自动生成)</small></td>
						<td>${firstForm.refereeContactName }</td>
					</tr>
					<tr>
						<td>推荐单位联系人电话<small data-toggle="tooltip" title="系统根据申请书信息自动生成，不需要填写">(自动生成)</small></td>
						<td>${firstForm.refereeContactPhone }</td>
					</tr>
					<tr>
						<td>推荐单位联系人邮箱<small data-toggle="tooltip" title="系统根据申请书信息自动生成，不需要填写">(自动生成)</small></td>
						<td>${firstForm.refereeContactEmail }</td>
					</tr>
					<tr>
						<td>项目联系人</td>
						<td class="form-group">
							<input class="form-control" type="text" name="applierContactName" value="${firstForm.applierContactName }" 
							data-error="请填写此项" required/>
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>项目联系人电话</td>
						<td class="form-group">
							<input class="form-control" type="text" name="applierContactPhone" value="${firstForm.applierContactPhone }" 
							 data-error="请填写此项" required/>
							 <div class="help-block with-errors" style="font-size:15px"></div>
						 </td>
					</tr>
					<tr>
						<td>项目联系人邮箱</td>
						<td class="form-group">
							<input class="form-control" type="text" name="applierContactEmail" value="${firstForm.applierContactEmail }"  
							data-error="请填写此项" required/>
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
				</table>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="保存" />
					<spring:url value="/display-second-form-when-applier-editing" var="secondFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(secondFormURL)}">下一页<span><small>（不保存）</small></span></a>
				</div>
			</form>
        </div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
});
</script>





</body>
</html>
