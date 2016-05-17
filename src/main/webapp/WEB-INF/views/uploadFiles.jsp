<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
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
<jsp:include page="/WEB-INF/views/fragments/header.jsp"></jsp:include>
<title>十、附件</title>
<script src="<c:url value="/resources/js/jquery.iframe-transport.js" />"></script>
<script src="<c:url value="/resources/js/vendor/jquery.ui.widget.js" />"></script>
<script src="<c:url value="/resources/js/jquery.fileupload.js" />"></script>

<!-- we code these -->
<style type="text/css">
	#dropzone {
	    background: #ccccc;
	    width: 400px;
	    height: 200px;
	    line-height: 200px;
	    text-align: center;
	    font-weight: bold;
	    outline: 1px dotted black;
	}
	#dropzone.in {
	    width: 600px;
	    height: 200px;
	    line-height: 200px;
	    font-size: larger;
	}
	#dropzone.hover {
	    background: lawngreen;
	}
	#dropzone.fade {
	    -webkit-transition: all 0.3s ease-out;
	    -moz-transition: all 0.3s ease-out;
	    -ms-transition: all 0.3s ease-out;
	    -o-transition: all 0.3s ease-out;
	    transition: all 0.3s ease-out;
	    opacity: 1;
	}
	
	.row {
		margin-top: 10px;
	}

</style>
</head>

<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="a${index }"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>八、主要完成人情况表</h1></div>
            <c:out value="${attachmentDisplay }"></c:out>
            <div class="row">
            	<c:choose>
					<c:when test="${index==1 }">
					<h1>1.核心知识产权证明</h1>
					<p>不超过3件，指已获授权的主要知识产权证明材料，包括发明专利说明书首页（摘要页）、计算机软件著作权证书、集成电路布图设计权证书以及相关论文专著等。提供复印件即可。
					<br>
					核心发明专利的电子版附件需提交发明专利说明书全文（含摘要页、权利要求书和说明书）,以PDF文件提交。</p>
					</c:when>
					<c:when test="${index==2 }">
					<h1>2．评价证明及国家法律法规要求审批的批准文件</h1>
					<p>指验收鉴定、权威部门出具的检测报告和批准文件等。提供复印件即可。
					<br>
					国家法律法规要求审批的相关行业如：通信设备、压力容器等相关项目必须提交批准文件复印件，且审批时间应在2014年1月1日之前。
					</p>
					</c:when>
					<c:when test="${index==3 }">
					<h1>3．应用证明（模板见附表1）</h1>
					<p>指本项目整体技术的应用单位提供的应用证明，且能证明本项整体技术已正式应用两年以上（即2014年1月1日以前应用）。应用证明须法定代表人签名（特殊情况下，
					可由法定代表人委托指定人签名并出具书面委托书），并加盖应用单位（法人单位）公章。应用单位须与推广应用情况中《主要应用单位情况表》一致。要求提供原件。如果实际2页
					，请双面打印，视为1页。
					<br>
					电子版以JPG文件提交。
					</p>
					</c:when>
					<c:when test="${index==4 }">
					<h1>4. 完成人合作关系说明及情况汇总表（模板见附表2）</h1>
					<p>简要叙述完成人在项目中的合作经历，包括合作时间、方式、产出及证明材料等，由第一完成人声明对上述内容真实性负责并签名，并填写《完成人合作关系情况汇总表》，以PDF文件提交。独立完成的可不提交此说明。
					<br>合作方式：包括专著合著、论文合著、共同立项、共同知识产权、共同获奖、工艺规范、产业合作、其他。
					<br>合作者/项目排名：填写此项合作内容中涉及的完成人及其在项目中的排名，合作者应该在证明材料中体现，如专利合作，合作者应同时为对应发明专利的发明人。
					<br>合作时间：合作时间应在本项目起始至完成时间范围内。
					<br>合作成果：只需要列出成果名称，如专著名称、论文名称、发明专利名称、合同名称等。
					<br>证明材料：直接列出证明材料的附件编号即可（一般都在其他证明材料中体现，如知识产权、论文专著等），如没有证明填写无。
					<br>电子版以PDF文件提交。
					</p>
					</c:when>
					<c:when test="${index==5 }">
					<h1>5．其他证明</h1>
					<p>指支持项目技术发明和完成人贡献的其他相关证明。论文须提供全文或首页，不超过10篇。以JPG文件提交。
					</p>
					</c:when>
				</c:choose>
            </div>
            <div class="row">
            	<spring:url value="/upload/{applierUid}/{index}" var="uploadURL">
					<spring:param name="applierUid" value="${person.uid}"></spring:param>
					<spring:param name="index" value="${index}"></spring:param>
				</spring:url>
				<input class="file" id="fileupload" type="file"  name="files[]" data-url="${uploadURL}" multiple>
            </div>
            <div class="row">
            	<div id="dropzone" style="text-align: center;">拖动文件至此处</div>
				<div id="progress">
					<div style="width: 0%;"></div>
				</div>
            </div>
            <div class="row">
            	<table id="uploaded-files" class="table table-bordered">
					<tr>
						<th>File Name</th>
						<th>File Size</th>
						<th>File Type</th>
						<th>Download</th>
					</tr>
				</table>
            </div>
            <br><br>
            <div class="row">
            	<spring:url value="/delete/{applierUid}/{index}" var="deleteURL">
					<spring:param name="applierUid" value="${person.uid}"></spring:param>
					<spring:param name="index" value="${index}"></spring:param>
				</spring:url>
				<a class="btn btn-danger" href="${deleteURL}" style="margin-right:30px">删除</a>
				<c:if test="${index>=5 }">
					<a class="btn btn-primary" type="button"  href="#">完成</a>
				</c:if>
				<c:if test="${index<5 }">
						<spring:url value="/upload/{applierUid}/{index}" var="nextURL">
							<spring:param name="applierUid" value="${person.uid}"></spring:param>
							<spring:param name="index" value="${index+1}"></spring:param>
						</spring:url>
						<a class="btn btn-primary" type="button"  href="${fn:escapeXml(nextURL)}">下一页</a>
				</c:if>
            </div>
            
   		</div>
   		</div>
   	</div>
</div>

	
	
	<script type="text/javascript">
		$(function () {
		    $('#fileupload').fileupload({
		        dataType: 'json',
		 
		        done: function (e, data) {
		            $("tr:has(td)").remove();
		            $.each(data.result, function (index, file) {
		 
		                $("#uploaded-files").append(
		                        $('<tr/>')
		                        .append($('<td/>').text(file.fileName))
		                        .append($('<td/>').text(file.fileSize))
		                        .append($('<td/>').text(file.fileType))
		                        .append($('<td/>').html("<a class=\"btn btn-default\" href='get/"+index+"'>download</a>"))
		                        )//end $("#uploaded-files").append()
		            }); 
		        },
		 
		        progressall: function (e, data) {
		            var progress = parseInt(data.loaded / data.total * 100, 10);
		            $('#progress .bar').css(
		                'width',
		                progress + '%'
		            );
		        },
		 
		        dropZone: $('#dropzone')
		    })
		}
		);
	</script>
</body>
</html>