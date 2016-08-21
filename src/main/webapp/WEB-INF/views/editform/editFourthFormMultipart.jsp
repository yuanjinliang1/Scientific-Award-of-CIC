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
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/wangEditor/dist/css/wangEditor.min.css" />">
	
	<c:choose>
		<c:when test="${applier.applicationType=='自然科学奖' }">
			<title>四、主要科学发现</title>
		</c:when>
		<c:when test="${applier.applicationType=='科技进步奖' }">
			<title>四、主要科技创新</title>
		</c:when>
		<c:when test="${applier.applicationType=='技术发明奖' }">
			<title>四、主要技术发明</title>
		</c:when>
	</c:choose>
</head>

<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="4"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px">
            	<h1>
            		<c:choose>
						<c:when test="${applier.applicationType=='自然科学类' }">
							四、主要科学发现
						</c:when>
						<c:when test="${applier.applicationType=='科技进步类' }">
							四、主要科技创新
						</c:when>
						<c:when test="${applier.applicationType=='技术发明类' }">
							四、主要技术发明
						</c:when>
						<c:otherwise>
							bad application type
						</c:otherwise>
					</c:choose>
            	</h1>
            </div>
            <form id="fourthFormer" action="/app/edit-fourth-form" method="POST" modelAttribute="fourthFormAttr"
            data-toggle="validator" role="form">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							<c:choose>
								<c:when test="${applier.applicationType=='自然科学类' }">
									1. 重要科学发现（限5页）
									<c:set var="placeholder1" value="（该部分是项目科学研究内容在创造性方面的归纳提炼，应围绕代表性论文专著的核心内容，客观、真实、准确地进行阐述。科学发现点按重要程度排序。涉及实质研究内容的说明、论证及实验结果等，应有相应论文专著或他人引文的支持）"></c:set>
								</c:when>
								<c:when test="${applier.applicationType=='科技进步类' }">
									1. 主要科技创新（限5页）
									<c:set var="placeholder1" value="（应以支持本项目科技创新内容成立的证明材料为依据（如：专利、验收、论文等），客观、真实、准确地阐述项目的立项背景和具有创造性的关键技术内容，对比国内外同类技术的主要参数等。&#10;科技创新点按重要程度排序。每项科技创新在阐述前应首先说明所属的学科分类名称和支持其成立的专利授权号、论文等相关证明材料。）"></c:set>
								</c:when>
								<c:when test="${applier.applicationType=='技术发明类' }">
									1. 主要技术发明（限5页）
									<c:set var="placeholder1" value="（应以核心知识产权证明为依据，客观、真实、准确地阐述项目的立项背景，技术内容中前人没有的、具有创造性的关键技术，对比当前国内外同类技术的主要参数等。 技术发明点按重要程度排序。每项技术发明在阐述前应首先说明所属的学科分类名称和已获授权的知识产权情况。核心发明点必须取得授权知识产权。）"></c:set>
								</c:when>
							</c:choose>
						</h4>
						</div>
					</div>
					<div id="editor-container" class="row panel-body form-group">
						
						<!--  <textarea rows="50" name="fourthForm1" form="fourthFormer" class="form-control" placeholder="${placeholder1 }" 
						 data-error="请填写此项" required>${fourthForm.fourthForm1}</textarea>-->
						 <textarea id="editor-trigger" style="height:400px;display:none;" name="fourthForm1"  placeholder="${placeholder1 }" 
							form="fourthFormer" data-error="请填写此项" required>${fourthForm.fourthForm1}</textarea>
						 <div class="help-block with-errors" style="font-size:15px"></div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							<c:choose>
								<c:when test="${applier.applicationType=='自然科学类' }">
									2. 研究局限性（限1页）
									<c:set var="placeholder2" value="（简明、准确地阐述本项目在现阶段研究中还存在的局限性及今后的主要研究方向。）"></c:set>
								</c:when>
								<c:when test="${applier.applicationType=='科技进步类' }">
									2. 科技局限性（限1页）
									<c:set var="placeholder2" value="（应包含项目主要技术内容、授权专利情况、技术经济指标、应用及效益情况等。）"></c:set>
								</c:when>
								<c:when test="${applier.applicationType=='技术发明类' }">
									2. 技术局限性（限1页）
									<c:set var="placeholder2" value="（简明、准确地阐述本项目在现阶段还存在的技术局限性及今后主要研究方向。）"></c:set>
								</c:when>
							</c:choose>
						</h4>
						</div>
					</div>
					<div class="row panel-body form-group">
						<textarea  class="form-control" rows="20" name="fourthForm2" form="fourthFormer" placeholder="${placeholder2 }"
						data-error="请填写此项" required>${fourthForm.fourthForm2}</textarea>
						<!-- <textarea id="editor-trigger2" style="height:400px;display:none;" name="fourthForm2" 
							form="fourthFormer" placeholder="${placeholder2 }" data-error="请填写此项" required>${fourthForm.fourthForm2}</textarea> -->
						<div class="help-block with-errors" style="font-size:15px"></div>
					</div>
				</div>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="保存" />
					<spring:url value="/edit-brief-introduction" var="thirdFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(thirdFormURL)}">上一页</a>
					<spring:url value="/edit-objective-evaluation" var="fifthFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(fifthFormURL)}">下一页</a>
				</div>
			</form>
        </div>
		</div>
	</div>
</div>
<spring:url value="/attached/{applierUid}/4" var="uploadURL1">
	<spring:param name="applierUid" value="${person.uid}"></spring:param>
</spring:url>
<script type="text/javascript" src="<c:url value="/resources/wangEditor/dist/js/lib/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/wangEditor/dist/js/wangEditor.js" />"></script>
<script type="text/javascript">
    // 阻止输出log
    // wangEditor.config.printLog = false;
	 
    var editor = new wangEditor('editor-trigger');
    editor.config.menus = [
			'source',
			'|',
			'bold',
			'underline',
			'italic',
			'strikethrough',
			'eraser',
			'forecolor',
			'|',
			'quote',
			'fontfamily',
			'fontsize',
			'head',
			'unorderlist',
			'orderlist',
			'alignleft',
			'aligncenter',
			'alignright',
			'|',
			'table',
			'|',
			'img',
			'|',
			'undo',
			'redo',
			'fullscreen'
                        ];
    // 上传图片
    editor.config.uploadImgUrl = '${uploadURL1}';
    
    editor.config.uploadParams = {};
    editor.config.uploadHeaders = {
        // 'Accept' : 'text/x-json'
    }
    editor.create();
</script>
</body>
</html>