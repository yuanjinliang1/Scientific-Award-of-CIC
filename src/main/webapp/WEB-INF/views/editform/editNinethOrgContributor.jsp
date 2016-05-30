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
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="9"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>九、主要完成单位情况表</h1></div>
           	<spring:url value="/save-nineth-major-org-contributor/{idOfNinethForm}" var="saveURL">
				<spring:param name="idOfNinethForm" value="${ninethForm.idOfNinethForm}"></spring:param>
			</spring:url>
			<form id="ninethFormer" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="ninethFormAttr">
				<table class="table table-bordered">
					<tr>
						<td>单位名称</td>
						<td>
						<input type="text" name="nameOfOrg" value="${ninethForm.nameOfOrg}" />
						</td>
					</tr>
					<tr>
						<td>排名</td>
						<td>${ninethForm.rankOfOrg}
						<input type="hidden" name="rankOfOrg" value="${ninethForm.rankOfOrg}" />
						</td>
					</tr>
					<tr>
						<td>法定代表人</td>
						<td><input type="text" name="legalRepresentative" value="${ninethForm.legalRepresentative}" /></td>
					</tr>
					<tr>
						<td>所在地</td>
						<td><input type="text" name="locationOfOrg" value="${ninethForm.locationOfOrg}" /></td>
					</tr>
					<tr>
						<td>单位性质</td>
						<td><input type="text" name="typeOfOrg" value="${ninethForm.typeOfOrg}" /></td>
					</tr>
					<tr>
						<td>传真</td>
						<td><input type="text" name="faxOfOrg" value="${ninethForm.faxOfOrg}" /></td>
					</tr>
					<tr>
						<td>邮政编码</td>
						<td><input type="text" name="zipCodeOfOrg" value="${ninethForm.zipCodeOfOrg}" /></td>
					</tr>
					<c:if test="${applier.applicationType!='科技进步类' }">
						<tr>
							<td>中国通信学会团体会员</td>
							<td>
								<select name="isOrgMemberOfCIC">
									<c:choose>
										<c:when test="${ninethForm.isOrgMemberOfCIC eq '否'}">
											<option value="否">否</option>
											<option value="是">是</option>
										</c:when>	
										<c:when test="${ninethForm.isOrgMemberOfCIC eq '是'}">
											<option value="是">是</option>
											<option value="否">否</option>
										</c:when>								
										<c:otherwise>
											<option disabled selected value>请选择</option>
											<option value="是">是</option>
											<option value="否">否</option>
										</c:otherwise>
									</c:choose>		
								</select>
							</td>
						</tr>
						<tr>
							<td>团体会员证书号</td>
							<td><input type="text" name="orgMemberIDOfCIC" value="${ninethForm.orgMemberIDOfCIC}" /></td>
						</tr>
					</c:if>
					<tr>
						<td>通讯地址</td>
						<td><input type="text" name="addressOfOrg" value="${ninethForm.addressOfOrg}" /></td>
					</tr>
					<tr>
						<td>联系人</td>
						<td><input type="text" name="contactNameOfOrg" value="${ninethForm.contactNameOfOrg}" /></td>
					</tr>
					<tr>
						<td>单位电话</td>
						<td><input type="text" name="contactPhoneOfOrg" value="${ninethForm.contactPhoneOfOrg}" /></td>
					</tr>
					<tr>
						<td>移动电话</td>
						<td><input type="text" name="mobileOfOrg" value="${ninethForm.mobileOfOrg}" /></td>
					</tr>
					<tr>
						<td>电子邮箱</td>
						<td><input type="text" name="emailOfOrg" value="${ninethForm.emailOfOrg}" /></td>
					</tr>
				</table>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
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
						<textarea rows="16" name="contributionToProject" form="ninethFormer">${ninethForm.contributionToProject}</textarea>
					</div>
				</div>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="保存" />
					<spring:url value="/manage-nineth-major-org-contributor" var="ninethFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(ninethFormURL)}">返回</a>
				</div>
			</form>
            
        </div>
		</div>
	</div>
</div>




	
</body>
</html>