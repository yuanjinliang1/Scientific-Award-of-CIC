<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title>jQuery File Upload Example</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.iframe-transport.js" />"></script>
<script src="<c:url value="/resources/js/vendor/jquery.ui.widget.js" />"></script>
<script src="<c:url value="/resources/js/jquery.fileupload.js" />"></script>

<!-- we code these -->
<link rel="stylesheet" href="<c:url value="/resources/css/dropzone.css" />">
</head>

<body>
	<h1>Spring MVC - jQuery File Upload</h1>
	<div style="width: 500px; padding: 20px">

		<input id="fileupload" type="file" name="files[]"
			data-url="upload" multiple>
		
		<div id="dropzone">Drop files here</div>

		<div id="progress">
			<div style="width: 0%;"></div>
		</div>

		<table id="uploaded-files">
			<tr>
				<th>File Name</th>
				<th>File Size</th>
				<th>File Type</th>
				<th>Download</th>
				<th>Delete</th>
			</tr>
		</table>
		<a class="btn btn-danger" href="delete">删除</a>
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