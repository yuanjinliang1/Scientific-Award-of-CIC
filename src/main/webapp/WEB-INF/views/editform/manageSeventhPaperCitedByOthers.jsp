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
	<title>七、代表性论文专著被他人引用的情况（不超过8篇）</title>
</head>
<body>
<div class="container" style="width:100%;">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="7"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>七、代表性论文专著被他人引用的情况（不超过8篇）</h1></div>
            <form action="/app/create-seventh-paper-cited-by-others" method="POST" >
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-default" value="添加被引用论文专著" />
				</div>
			</form>	
            <table class="table table-bordered" style="zoom:80%">
				<tr>
					<td>序号</td>
					<td>被引代表性论文专著序号</td>
					<td>引文题目/作者</td>
					<td>引文刊名/影响因子</td>
					<td>引文发表时间（年 月 日）</td>
					<td>操作</td>
					<td>操作</td>
				</tr>
				<c:forEach var="seventhPaperForm" items="${seventhPaperForms }">
					<spring:url value="/save-seventh-paper-cited-by-others/{idOfSeventhPaperForm}" var="saveURL">
						<spring:param name="idOfSeventhPaperForm" value="${seventhPaperForm.idOfSeventhPaperForm}"></spring:param>
					</spring:url>
					<form action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="seventhPaperFormAttr" data-toggle="validator" role="form">
						<tr>
							<td class="form-group">
								<input class="form-control" type="number" name="rankOfPaper" value="${seventhPaperForm.rankOfPaper}"
								data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input class="form-control" type="text" name="doiOfPaper" value="${seventhPaperForm.doiOfPaper}"
								data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input class="form-control" type="text" name="titleAndAuthorOfPaper" value="${seventhPaperForm.titleAndAuthorOfPaper}"
								data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input class="form-control" type="text" name="journalAndIF" value="${seventhPaperForm.journalAndIF}"
								data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td class="form-group">
								<input class="form-control" type="text" name="publishDate" value="${seventhPaperForm.publishDate}"
								data-error="请填写此项" required/>
						 		<div class="help-block with-errors" style="font-size:15px"></div>
						 	</td>
							<td  class="form-group">
								<input class="btn btn-primary" type="submit" value="保存" />
							</td>
							<td>
								<c:url value="/delete-seventh-paper-cited-by-others" var="deleteURL">
									<c:param name="idOfSeventhPaperForm" value="${seventhPaperForm.idOfSeventhPaperForm }"></c:param>
								</c:url>
								<a class="btn btn-danger" href="${fn:escapeXml(deleteURL)}">删除</a>
							</td>
							<td>
							<div class="form-group">
							    <input type="text" class="form-control" id="inputName" placeholder="Cina Saffary" required>
							    <div class="help-block with-errors"></div>
							</div>
							</td>
						</tr>
					</form>
				</c:forEach>
			</table>
			<div class="row" style="margin-left:20px">
				<spring:url value="/manage-paper-monograph" var="sixthFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(sixthFormURL)}">上一页</a>
				<spring:url value="/manage-eighth-major-contributor" var="eighthFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(eighthFormURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>
</body>
</html>
