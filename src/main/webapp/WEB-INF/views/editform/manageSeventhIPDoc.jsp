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
	<title>七、主要知识产权证明目录（不超过10件）</title>
</head>
<body>
<div class="container" style="width:100%">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="7"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
        
            <div class="row" style="margin-left: 20px"><h1>七、主要知识产权证明目录（不超过10件）</h1></div>
           	<form action="/app/create-seventh-ip-doc" method="POST" >
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="添加主要知识产权证明" />
				</div>
			</form>
            <table class="table table-bordered" style="zoom:80%">
				<tr>
					<td>排名</td>
					<td>知识产权类别</td>
					<td>知识产权具体名称</td>
					<td>国家（地区）</td>
					<td>授权号</td>
					<td>授权日期</td>
					<td>证书编号</td>
					<td>权利人</td>
					<td>发明人</td>
					<td>发明专利有效状态</td>
					<td>操作</td>
					<td>操作</td>
				</tr>
				<c:forEach var="seventhIPForm" items="${seventhIPForms }">
					<spring:url value="/save-seventh-ip-doc/{idOfSeventhIPForm}" var="saveURL">
						<spring:param name="idOfSeventhIPForm" value="${seventhIPForm.idOfSeventhIPForm}"></spring:param>
					</spring:url>
					<form id="seventhIPFormer" action="${fn:escapeXml(saveURL)}" method="POST" 
						modelAttribute="seventhIPFormAttr" data-toggle="validator" role="form">
						<tr>
							<td class="form-group">
								<input type="number" class="form-control" name="rankOfIP" value="${seventhIPForm.rankOfIP}"
								data-error="请填写此项" required />
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="typeOfIP" value="${seventhIPForm.typeOfIP}" 
								data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="nameOfIP" value="${seventhIPForm.nameOfIP}" 
								data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="locationOfIP" value="${seventhIPForm.locationOfIP}" 
								data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="authorizationCodeOfIP" 
								value="${seventhIPForm.authorizationCodeOfIP}" data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="authorizationDateOfIP" 
								value="${seventhIPForm.authorizationDateOfIP}" data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="certificateNumberOfIP" 
								value="${seventhIPForm.certificateNumberOfIP}" data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="holderOfIP" 
								value="${seventhIPForm.holderOfIP}" data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="inventorOfIP" 
								value="${seventhIPForm.inventorOfIP}" data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input type="text" class="form-control" name="isValidIP" 
								value="${seventhIPForm.isValidIP}" data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
						 	<td class="form-group">
								<input type="submit" class="form-control btn btn-primary" value="保存" />
							</td>
							<td>
								<c:url value="/delete-seventh-ip-doc" var="deleteURL">
									<c:param name="idOfSeventhIPForm" value="${seventhIPForm.idOfSeventhIPForm }"></c:param>
								</c:url>
								<a id="delete" class="form-control btn btn-danger" href="${fn:escapeXml(deleteURL)}"
								 onclick="return confirm('您确定删除该条目吗？')">删除</a>
							</td>
						</tr>
					</form>
				</c:forEach>
			</table>
			<div class="row" style="margin-left:20px">
				<spring:url value="/manage-apply-unit-situation" var="sixthFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(sixthFormURL)}">上一页</a>
				<spring:url value="/manage-eighth-major-contributor" var="eighthFormURL"/>
					<a class="btn btn-primary" href="${fn:escapeXml(eighthFormURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>

</body>
</html>
