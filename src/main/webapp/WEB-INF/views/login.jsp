<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<html>
<head>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<title>登陆报奖系统</title>
</head>
<body>
<div class="container" style="margin-top:5%">
	<div class="panel panel-default pull-left" style="width:35%">
		<div class="panel-heading">
			<div class="row">
			<h4>
				文档下载
			</h4>
			</div>
		</div>
		<div class="row panel-body">
		<table class=" table table-bordered" style="font-size:15px">
			<tr><td><a href="/app/download-word/3">3-2016年度学会科技奖各奖种推荐书式样-自然科学类.docx</a></td></tr>
			<tr><td><a href="/app/download-word/3">4-2016年度学会科技奖各奖种推荐书式样-技术发明类.docx</a></td></tr>
			<tr><td><a href="/app/download-word/3">5-2016年度学会科技奖各奖种推荐书式样-科技进步类.docx</a></td></tr>
		</table>
		</div>
		
		
	</div>
<div style="margin:auto;width:60%" class="pull-right" >
	<div id="message"></div>
	<form class="form-horizontal" action="/app/login" method="POST" modelAttribute="loginAttr">
		<div class="form-group " style="margin-right:10%">
			<div class="row"><h1 style="text-align: center">中国通信学会报奖系统</h1></div>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<label for="uidGet">用户名</label>
    			<input type="text" class="form-control" name="uid" id="uidGet"/>
	    	</div>
			</div>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<label for="passwordGet" >密码</label>
    			<input type="password" class="form-control" name="password" id="passwordGet"/>
	    	</div>
			</div>
			<br/>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<button type="submit" class="btn btn-primary" name="Submit" onClick="return loginPreCheck()">登陆</button>
    			<button type="reset" class="btn btn-danger" name="Reset" id="reset">取消</button>
	    	</div>
			</div>
		</div>
	</form>
</div>
</div>
<c:set var="message" value="${param.message }"></c:set>
<script type="text/javascript">
    	function loginPreCheck(){
    		if(uidGet.value==""){
    			alert("请填写用户名");
    			return false;
    		}
    		else if(passwordGet.value==""){
    			alert("请填写密码");
    			return false;
    		}
    		else{
    			return true;
    		}
    	}
</script>
<script type="text/javascript">
	jQuery(document).ready(function($){
		if("${message}"=="wrong"){
			var successMessage='<div class="alert alert-danger"> <strong>用户名或密码错误</strong></div>';
			$("#message").html(successMessage);
			$("#message").fadeTo(2000, 500).slideUp(500, function(){
	        $("#message").alert('close');
			});
		};
		$("#reset").click(function(){
			$("input").not(':button, :submit, :reset, :hidden, :radio, :checkbox').val('  ');
			console.log("heh");
		})
	})
</script>
</body>
</html>