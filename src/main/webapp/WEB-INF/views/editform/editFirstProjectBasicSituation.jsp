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
         		<form id="firstFormer" action="/app/save-first-project-basic-situation" method="POST" modelAttribute="firstFormAttr">
				<table class="table table-bordered">
					<tr>
						<td>推荐单位（盖章）或推荐专家</td>
						<td>${firstForm.refereeString}</td>
					</tr>
					<tr>
						<td>项目名称</td>
						<td><input type="text" name="projectName" value="${firstForm.projectName }" /></td>
					</tr>
					<tr>
						<td>主要完成人</td>
						<td>${firstForm.majorContributorNames }</td>
					</tr>
					<tr>
						<td>主要完成单位</td>
						<td>${firstForm.majorContributingOrgNames }</td>
					</tr>
					<tr>
						<td>项目密级</td>
						<td>${firstForm.secretLevel }</td>
					</tr>
					<tr>
						<td>学科分类名称1</td>
						<td>
							<select name="subjectCategoryName1">
								<option value="${firstForm.subjectCategoryName1}">${firstForm.subjectCategoryName1}</option>
								<c:forEach items="${subjectCategories }" var="subjectCategory">
									<c:if test="${firstForm.subjectCategoryName1!=subjectCategory}">
										<option value="${subjectCategory }">${subjectCategory }</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>学科分类名称2</td>
						<td>
							<select name="subjectCategoryName2">
								<option value="${firstForm.subjectCategoryName2}">${firstForm.subjectCategoryName2}</option>
								<c:forEach items="${subjectCategories }" var="subjectCategory">
									<c:if test="${firstForm.subjectCategoryName2!=subjectCategory}">
										<option value="${subjectCategory }">${subjectCategory }</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>学科分类名称3</td>
						<td>
							<select name="subjectCategoryName3">
								<option value="${firstForm.subjectCategoryName3}">${firstForm.subjectCategoryName3}</option>
								<c:forEach items="${subjectCategories }" var="subjectCategory">
									<c:if test="${firstForm.subjectCategoryName3!=subjectCategory}">
										<option value="${subjectCategory }">${subjectCategory }</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
						<tr>
							<td>所属国民经济行业</td>
							<td>
								<select name="economicField">
									<option value="${firstForm.economicField}">${firstForm.economicField}</option>
									<c:forEach items="${economicFields }" var="economicField">
										<c:if test="${firstForm.economicField!=economicField}">
											<option value="${economicField }">${economicField }</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>所属国家重点发展领域</td>
							<td>
								<select name="nationalFocusField">
									<option value="${firstForm.nationalFocusField}">${firstForm.nationalFocusField}</option>
									<c:forEach items="${nationalFocusFields }" var="nationalFocusField">
										<c:if test="${firstForm.nationalFocusField!=nationalFocusField}">
											<option value="${nationalFocusField }">${nationalFocusField }</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<input type="hidden" name="technologicalField" value="NEVER" />
					</c:if>
					<c:if test="${applier.applicationType eq'自然科学类' }">
						<tr>
							<td>所属科学技术领域</td>
							<td>
								<input type="hidden" name="economicField" value="NEVER" />
								<input type="hidden" name="nationalFocusField" value="NEVER" />
								<select name="technologicalField">
									<option value="${firstForm.technologicalField}">${firstForm.technologicalField}</option>
									<c:forEach items="${technologicalFields }" var="technologicalField">
										<c:if test="${firstForm.technologicalField!=technologicalField}">
											<option value="${technologicalField }">${technologicalField }</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
					</c:if>
					<tr>
						<td>任务来源</td>
						<td>
							<select name="taskSource">
								<option value="${firstForm.taskSource}">${firstForm.taskSource}</option>
								<c:forEach items="${taskSources }" var="taskSource">
									<c:if test="${firstForm.taskSource!=taskSource}">
										<option value="${taskSource }">${taskSource }</option>
									</c:if>
								</c:forEach>
							</select>
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
					<div class="row panel-body">
						<textarea class="form-control" rows="5"  name="nameAndCodeOfPlansOrFundations" form="firstFormer">${firstForm.nameAndCodeOfPlansOrFundations }</textarea>
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
						<textarea class="form-control" rows="3"  name="technicalReportNumber" form="firstFormer">${firstForm.technicalReportNumber }</textarea>
					</div>
				</div>
				<table class="table table-bordered">
					<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
						<tr>
							<td>授权发明专利项数</td>
							<td><input type="text" name="numOfInventionPatent" value="${firstForm.numOfInventionPatent }" /></td>
						</tr>
						<tr>
							<td>授权的其它知识产权项数</td>
							<td><input type="text" name="numOfOtherIntellectualProperty" value="${firstForm.numOfOtherIntellectualProperty }" /></td>
						</tr>
					</c:if>
					<c:if test="${applier.applicationType=='自然科学类'}">
						<input type="hidden" name="numOfInventionPatent" value="-1" />
						<input type="hidden" name="numOfOtherIntellectualProperty" value="-1" />	
					</c:if>
					<tr>
						<td>项目开始时间</td>
						<fmt:formatDate pattern="yyyy-MM-dd"  value="${firstForm.startDate }" var="startDateVar" />
						<td><input type="date" name="startDate" value="${startDateVar}" /></td>
					</tr>
					<tr>
						<td>项目完成时间</td>
						<fmt:formatDate pattern="yyyy-MM-dd"  value="${firstForm.finishDate }" var="finishDateVar" />
						<td><input type="date"  name="finishDate" value="${finishDateVar }" /></td>
					</tr>
					<tr>
						<td>推荐单位联系人</td>
						<td>${firstForm.refereeContactName }</td>
					</tr>
					<tr>
						<td>推荐单位联系人电话</td>
						<td>${firstForm.refereeContactPhone }</td>
					</tr>
					<tr>
						<td>推荐单位联系人邮箱</td>
						<td>${firstForm.refereeContactEmail }</td>
					</tr>
					<tr>
						<td>项目联系人</td>
						<td><input type="text" name="applierContactName" value="${firstForm.applierContactName }" /></td>
					</tr>
					<tr>
						<td>项目联系人电话</td>
						<td><input type="text" name="applierContactPhone" value="${firstForm.applierContactPhone }" /></td>
					</tr>
					<tr>
						<td>项目联系人邮箱</td>
						<td><input type="text" name="applierContactEmail" value="${firstForm.applierContactEmail }" /></td>
					</tr>
				</table>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-default" value="保存并查看" />
				</div>
			</form>
        </div>
		</div>
	</div>
</div>







</body>
</html>
