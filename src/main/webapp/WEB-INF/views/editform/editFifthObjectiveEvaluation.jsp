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
            <div class="row" style="margin-left: 20px"><h1>五、客观评价</h1></div>
            	<form id="fifthFormer" action="/app/edit-objective-evaluation" method="POST" modelAttribute="objectiveEvaluation">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
							<h4 style="margin-left:20px">
								客观评价
							</h4>
							</div>
						</div>
						<div class="row panel-body" style="padding-top:0px;padding-bottom:0px">
							<textarea class="form-control" style="padding:0px" rows="40"  name="objectiveEvaluation" form="fifthFormer">${objectiveEvaluationForm.objectiveEvaluation}</textarea>
						</div>
					</div>
					<div class="row" style="margin-left:20px">
						<input type="submit" class="btn btn-default" value="保存" />
					</div>
				</form>
        </div>
		</div>
	</div>
</div>
</body>
</html>