<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<!-- Set charset encoding to utf-8  -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
	<title>九、主要完成单位情况表</title>
</head>
<body>
	<spring:url value="/save-nineth-major-org-contributor/{idOfNinethForm}" var="saveURL">
		<spring:param name="idOfNinethForm" value="${ninethForm.idOfNinethForm}"></spring:param>
	</spring:url>
	<form id="ninethFormer" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="ninethFormAttr">
		<table border="1">
		
			<tr>
				<td>单位名称</td>
				<td>
				<input type="text" name="nameOfOrg" value="${ninethForm.nameOfOrg}" />
				</td>
			</tr>
			<tr>
				<td>排名</td>
				<td>
				<input type="number" name="rankOfOrg" value="${ninethForm.rankOfOrg}" />
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
					<td>中国通信学会会员</td>
					<td>
						<select name="isOrgMemberOfCIC">
							<option value="${ninethForm.isOrgMemberOfCIC}">${ninethForm.isOrgMemberOfCIC}</option>
							<c:if test="${ninethForm.isOrgMemberOfCIC!='是'}">
								<option value="是">是</option>
							</c:if>
							<c:if test="${ninethForm.isOrgMemberOfCIC!='否'}">
								<option value="否">否</option>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td>团体会员证书号</td>
					<td><input type="text" name="OrgMemberIDOfCIC" value="${ninethForm.OrgMemberIDOfCIC}" /></td>
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
			<tr>
				<c:choose>
					<c:when test="${applier.applicationType!='科技进步类' }">
					<td>对本项目的贡献</td>
					</c:when>
					<c:when test="${applier.applicationType=='科技进步类' }">
					<td>对本项目科技创新<br />和推广应用情况的贡献</td>
					</c:when>
					<c:otherwise>
					<td>bad applicationType!</td>
					</c:otherwise>
				</c:choose>
				<!-- Don't break this line. It's textarea. -->
				<td><textarea rows="16" cols="100" name="contributionToProject" form="ninethFormer">${ninethForm.contributionToProject}</textarea></td>
			</tr>
			<tr>
			<td>
				<input type="submit" value="保存并查看" />
			</td>
			<td>
				<input type="button" onclick="location.href='/app/edit-first-project-basic-situation';" value="第一页">
				<input type="button" onclick="location.href='/app/edit-brief-introduction';" value="第三页">
				<input type="button" onclick="location.href='/app/edit-fourth-form';" value="第四页">
				<input type="button" onclick="location.href='/app/edit-objective-evaluation';" value="第五页">
				<input type="button" onclick="location.href='/app/manage-nineth-major-org-contributor';" value="第九页">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>