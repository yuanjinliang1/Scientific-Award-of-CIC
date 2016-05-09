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

<div class="container" style="margin-top:12.5%">
<div style="margin:auto">
	
	<form class="form-horizontal" action="/app/login" method="POST" modelAttribute="loginAttr">
		<div class="form-group ">
			<div class="row"><h1 style="text-align: center">中国通信学会报奖系统</h1></div>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<label for="uidGet">用户名</label>
    			<input type="text" class="form-control" name="uid" id="uidGet" value="100116001" />
	    	</div>
			</div>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<label for="passwordGet" >密码</label>
    			<input type="password" class="form-control" name="password" id="passwordGet" value="8888"/>
	    	</div>
			</div>
			<div class="row">
	    	<div style="width:300;margin:auto">
    			<button type="submit" class="btn btn-default" name="Submit" onClick="return loginPreCheck()">登陆</button>
    			<button type="reset" class="btn btn-default" name="Reset">取消</button>
	    	</div>
			</div>
		</div>
	</form>
</div>
</div>

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
</body>
</html>