<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<html>
<head>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<title>登陆报奖系统</title>
	<style type="text/css">
		.footer {
		  text-align:center;
		  clear: both;
		  border: 0px groove #aaaaaa;
		  background: white;
		  color: black;
		  padding: 0;
		  text-align: center;
		  vertical-align: middle;
		  line-height: normal;
		  margin: 0;
		  position: fixed;
		  bottom: 0px;
		  width: 100%;
		} 
	</style>
</head>
<body onload="detectBrowser()">
<div class="container" style="margin-top:5%">
<div style="margin:auto;width:60%" >
	<div id="message"></div>
	<form class="form-horizontal" action="/app/login" method="POST" modelAttribute="loginAttr">
		<div class="form-group " >
			<div class="row"><h1 style="text-align: center">中国通信学会报奖系统（试行版）</h1></div>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<label for="uidGet">用户名</label>
    			<input type="text" class="form-control" name="uid" id="uidGet" value="100116001"/>
	    	</div>
			</div>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<label for="passwordGet" >密码</label>
    			<input type="password" class="form-control" name="password" id="passwordGet" value="8888" />
	    	</div>
			</div>
			<br/>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<button type="submit" class="btn btn-primary" name="Submit" onClick="return loginPreCheck()">登陆</button>
    			<button type="reset" class="btn btn-danger" name="Reset" id="reset">取消</button>
    			<button type="reset" class="btn btn-default" name="Reset" id="getPass" onclick="alert('请联系上级推荐单位  或  拨打学会工作电话：010-68209086')">我没有用户名和密码</button>
	    	</div>
			</div>
		</div>
	</form>
	<div class="panel panel-default" style="width:100%">
		<div class="panel-heading">
			<div class="row">
				<h4>文档下载</h4>
			</div>
		</div>
		<div class="row panel-body">
		<table class=" table table-bordered" style="font-size:15px">
			<tr><td><a href="/app/download-word/3">3-2016年度学会科技奖各奖种推荐书式样-自然科学类.docx</a></td></tr>
			<tr><td><a href="/app/download-word/4">4-2016年度学会科技奖各奖种推荐书式样-技术发明类.docx</a></td></tr>
			<tr><td><a href="/app/download-word/5">5-2016年度学会科技奖各奖种推荐书式样-科技进步类.docx</a></td></tr>
		</table>
		</div>
	</div>
	<div class="panel panel-default" style="width:100%">
		<div class="panel-heading">
			<div class="row">
				<h4>联系方式</h4>
			</div>
		</div>
		<div class="row panel-body">
		<table class=" table table-bordered" style="font-size:15px">
			<tr><td>技术支持QQ(推荐)：992073183</td></tr>
			<tr><td>技术支持微信：daisongtai </td></tr>
			<tr><td>学会工作电话：010-68209086</td></tr>
			<tr><td>或发送邮件至：jlb@china-cic.cn </td></tr>
		</table>
		</div>
	</div>
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
<script type="text/javascript">
	
	function detectBrowser()
	{
	var browser=navigator.appName;
	var b_version=navigator.appVersion;
	var version=parseFloat(b_version);
	var isChromium = window.chrome,
    winNav = window.navigator,
    vendorName = winNav.vendor,
    isOpera = winNav.userAgent.indexOf("OPR") > -1,
    isIEedge = winNav.userAgent.indexOf("Edge") > -1,
    isIOSChrome = winNav.userAgent.match("CriOS");
   	if(isIOSChrome){
   	   // is Google Chrome on IOS
   	} else if(isChromium !== null && isChromium !== undefined && vendorName === "Google Inc." && isOpera == false && isIEedge == false) {
   	   // is Google Chrome
   	} else { 
   		alert("温馨提示：奖项申请系统在Chrome浏览器上最为稳定，建议使用Chrome浏览器填报！")
   	}
   	}
</script>
</body>
</html>
