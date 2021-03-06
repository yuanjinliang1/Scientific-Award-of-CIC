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
	<title>三、项目简介</title>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="3"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>三、项目简介</h1></div>
            <form id="thirdFormer" action="/app/edit-brief-introduction" method="POST" modelAttribute="briefIntroduction"
            data-toggle="validator" role="form">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							项目简介（限1200字）
						</h4>
						<p>由于系统增加了富文本功能，纯文本格式在富文本框内显示不佳。<u><i>若要兼容以前纯文本格式的内容</i></u>，
						或者不需要富文本功能，则请点击富文本框左上角按钮（源码），在纯文本模式下编辑。</p>
						</div>
						<p><b>注意</b>：<ul>
						<li>请不要直接粘贴图片和表格，若要上传图片，请在富文本辅助栏中选择上传图片按钮手动上传。若要上传表格，可转换为图片后上传，也可以利用编辑器内置表格工具</li>
						<li>不推荐直接从word中粘贴格式化文本，网页格式与word格式会有兼容性问题</li>
						<li>若要彻底删除所有内容，请点击左上角按钮（源码）然后清空所显示出的所有html代码</li>
						<li style="color:red">编辑器的图片大小的调整只在网页上有效，无法反映在pdf中。若需在pdf中调整图片大小，请您直接上传对应大小（像素）的图片</li>
						</ul>
						</p>
					</div>
					<div class="row panel-body form-group">
						<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
							<c:set var="placeholder" value="（应包含项目主要技术内容、授权专利情况、技术经济指标、应用及效益情况等。）"></c:set>
						</c:if>
						<c:if test="${applier.applicationType=='自然科学类'}">
							<c:set var="placeholder" value="（应包含项目主要研究内容、科学发现点、科学价值、同行引用及评价等）"></c:set>
						</c:if>
						<!--  <textarea rows="40" name="briefIntroduction" form="thirdFormer"
						class="form-control" placeholder="${placeholder }" maxlength="1200"
						 data-error="请检查此项" required>${briefIntroductionForm.briefIntroduction}</textarea>-->
						 <textarea id="editor-trigger" style="height:400px;display:none;" name="briefIntroduction"  placeholder="${placeholder }" 
							form="thirdFormer" data-error="请填写此项" required>${briefIntroductionForm.briefIntroduction}</textarea>
						 <div class="help-block with-errors" style="font-size:15px"></div>
					</div>
					
				</div>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="保存" />
					<spring:url value="/display-second-form-when-applier-editing" var="secondFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(secondFormURL)}">上一页</a>
					<spring:url value="/edit-fourth-form" var="fourthFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(fourthFormURL)}">下一页</a>
				</div>
			</form>	
        </div>
		</div>
	</div>
</div>
<spring:url value="/attached/{applierUid}/3" var="uploadURL">
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
					'eraser',
					'|',
					'alignleft',
					'aligncenter',
					'alignright',
					'|',
					'table',
					'|',
					'img',
                        ];
    // 上传图片
    editor.config.uploadImgUrl = '${uploadURL}';
    
    editor.config.uploadParams = {};
    editor.config.uploadHeaders = {
        // 'Accept' : 'text/x-json'
    }
    editor.create();
</script>
</body>
</html>