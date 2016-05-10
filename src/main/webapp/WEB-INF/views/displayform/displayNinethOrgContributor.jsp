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
	<title>九、主要完成单位情况表</title>
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${person.role eq 'admin' }">
			<dicipulus:bodyHeaderForAdmin menuName=""/>
		</c:when>
		<c:when test="${person.role eq 'referee' }">
			<dicipulus:bodyHeaderForReferee menuName=""/>
		</c:when>
		<c:when test="${person.role eq 'applier' }">
			<dicipulus:bodyHeaderForApplier menuName=""/>
		</c:when>
		<c:otherwise>
			<c:out value="${person.uid} ${person.name } ${person.role} bad role name" />
		</c:otherwise>
	</c:choose>
	<div class="wrapper">
		<dicipulus:bodySidebarForDisplay page="9"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>九、主要完成单位情况表</h1></div>	
            <table class="table table-bordered">
				<tr>
					<td>单位名称</td>
					<td>
					${ninethForm.nameOfOrg}
					</td>
				</tr>
				<tr>
					<td>排名</td>
					<td>
					${ninethForm.rankOfOrg}
					</td>
				</tr>
				<tr>
					<td>法定代表人</td>
					<td>${ninethForm.legalRepresentative}</td>
				</tr>
				<tr>
					<td>所在地</td>
					<td>${ninethForm.locationOfOrg}</td>
				</tr>
				<tr>
					<td>单位性质</td>
					<td>${ninethForm.typeOfOrg}</td>
				</tr>
				<tr>
					<td>传真</td>
					<td>${ninethForm.faxOfOrg}</td>
				</tr>
				<tr>
					<td>邮政编码</td>
					<td>${ninethForm.zipCodeOfOrg}</td>
				</tr>
				<c:if test="${applier.applicationType!='科技进步类' }">
					<tr>
						<td>中国通信学会团体会员</td>
						<td>${ninethForm.isOrgMemberOfCIC}</td>
					</tr>
					<tr>
						<td>团体会员证书号</td>
						<td>${ninethForm.orgMemberIDOfCIC}</td>
					</tr>
				</c:if>
				<tr>
					<td>通讯地址</td>
					<td>${ninethForm.addressOfOrg}</td>
				</tr>
				<tr>
					<td>联系人</td>
					<td>${ninethForm.contactNameOfOrg}</td>
				</tr>
				<tr>
					<td>单位电话</td>
					<td>${ninethForm.contactPhoneOfOrg}</td>
				</tr>
				<tr>
					<td>移动电话</td>
					<td>${ninethForm.mobileOfOrg}</td>
				</tr>
				<tr>
					<td>电子邮箱</td>
					<td>${ninethForm.emailOfOrg}</td>
				</tr>
			</table>
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
					<h4>
						<c:choose>
							<c:when test="${applier.applicationType!='科技进步类' }">
								对本项目的贡献
							</c:when>
							<c:when test="${applier.applicationType=='科技进步类' }">
								对本项目科技创新和推广应用情况的贡献
							</c:when>
							<c:otherwise>
								bad applicationType!
							</c:otherwise>
						</c:choose>
					</h4>
					</div>
				</div>
				<div class="row panel-body">
					${ninethForm.contributionToProject}
				</div>
			</div>
        </div>
		</div>
	</div>
</div>
</body>
</html>