<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<!-- Set charset encoding to utf-8  -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
	<title>一、项目基本情况</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>推荐单位（盖章）或推荐专家</td>
			<td>${firstForm.refereeString}</td>
		</tr>
		<tr>
			<td>项目名称</td>
			<td>${firstForm.projectName }</td>
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
				${firstForm.subjectCategoryName1}
			</td>
		</tr>
		<tr>
			<td>学科分类名称2</td>
			<td>
				${firstForm.subjectCategoryName2}
			</td>
		</tr>
		<tr>
			<td>学科分类名称3</td>
			<td>
				${firstForm.subjectCategoryName3}
			</td>
		</tr>
		<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
			<tr>
				<td>所属国民经济行业</td>
				<td>
					${firstForm.economicField}
				</td>
			</tr>
			<tr>
				<td>所属国家重点发展领域</td>
				<td>
					${firstForm.nationalFocusField}
				</td>
			</tr>
		</c:if>
		<c:if test="${applier.applicationType eq'自然科学类' }">
			<tr>
				<td>所属科学技术领域</td>
				<td>
					${firstForm.technologicalField}
				</td>
			</tr>
		</c:if>
		<tr>
			<td>任务来源</td>
			<td>
				${firstForm.taskSource}
			</td>
		</tr>
		<tr>
			<td>具体计划、基金的名称和编号</td>
			<td>${firstForm.nameAndCodeOfPlansOrFundations }</td>
		</tr>
		<tr>
			<td>已呈交的科技报告编号</td>
			<td>${firstForm.technicalReportNumber }</td>
		</tr>
		<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
			<tr>
				<td>授权发明专利项数</td>
				<td>${firstForm.numOfInventionPatent }</td>
			</tr>
			<tr>
				<td>授权的其它知识产权项数</td>
				<td>${firstForm.numOfOtherIntellectualProperty }</td>
			</tr>
		</c:if>
		<c:if test="${applier.applicationType=='自然科学类'}">
		</c:if>
		<tr>
			<td>项目开始时间</td>
			<fmt:formatDate pattern="yyyy-MM-dd"  value="${firstForm.startDate }" var="startDateVar" />
			<td>${startDateVar}</td>
		</tr>
		<tr>
			<td>项目完成时间</td>
			<fmt:formatDate pattern="yyyy-MM-dd"  value="${firstForm.finishDate }" var="finishDateVar" />
			<td>${finishDateVar }</td>
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
			<td>${firstForm.applierContactName }</td>
		</tr>
		<tr>
			<td>项目联系人电话</td>
			<td>${firstForm.applierContactPhone }</td>
		</tr>
		<tr>
			<td>项目联系人邮箱</td>
			<td>${firstForm.applierContactEmail }</td>
		</tr>
	</table>
	<jsp:include page="fragments/footerPagination.jsp"></jsp:include>

</body>
</html>