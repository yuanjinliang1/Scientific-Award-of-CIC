<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<title>六、论文专著目录</title>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="6"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>六、论文专著目录</h1></div>
            <form action="/app/create-paper-monograph" method="POST" >
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-default" value="添加论文专著" />
				</div>
			</form>	
			<br/>
            <table class="table table-bordered">
				<tr>
					<td>论文专著名称</td>
					<td>操作</td>
					<td>操作</td>
				</tr>
				<c:forEach var="sixthForm" items="${sixthPaperMonographForms}">
					<tr>
						<td>${sixthForm.name}</td>
						<td>
							<spring:url value="/edit-sixth-paper-monograph/{idOfPaperMonograph}" var="editFormURL">
								<spring:param name="idOfPaperMonograph" value="${sixthForm.idOfPaperMonograph}"></spring:param>
							</spring:url>
							<a id="editOpinion" href="${fn:escapeXml(editFormURL)}">填写</a>
						</td>
						<td>
							<c:url value="/delete-sixth-paper-monograph" var="deleteURL">
								<c:param name="idOfPaperMonograph" value="${sixthForm.idOfPaperMonograph}"></c:param>
							</c:url>
							<a id="deletePaperMonograph" href="${fn:escapeXml(deleteURL)}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="row" style="margin-left:20px">
				<spring:url value="/edit-objective-evaluation" var="fifthFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(fifthFormURL)}">上一页</a>
				<spring:url value="/manage-seventh-paper-cited-by-others" var="seventhFormURL"/>
					<a class="btn btn-default" href="${fn:escapeXml(seventhFormURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>




	
	
</body>
</html>