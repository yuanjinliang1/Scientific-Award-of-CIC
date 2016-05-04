<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<title>八、主要完成人情况表</title>
</head>
<body>
	<spring:url value="/save-eighth-major-contributor/{idOfEighthForm}" var="saveURL">
		<spring:param name="idOfEighthForm" value="${eighthForm.idOfEighthForm}"></spring:param>
	</spring:url>
	<form id="eighthFormer" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="eighthFormAttr">
		<table border="1">
		
			<tr>
				<td>姓名</td>
				<td>
				<input type="text" name="nameOfContributor" value="${eighthForm.nameOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
				<input type="text" name="genderOfContributor" value="${eighthForm.genderOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>排名</td>
				<td>
				<input type="number" name="rankOfContributor" value="${eighthForm.rankOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>国籍</td>
				<td><input type="text" name="nationalityOfContributor" value="${eighthForm.nationalityOfContributor}" /></td>
			</tr>
			<c:if test="${applier.applicationType!='科技进步类' }">
				<tr>
					<td>中国通信学会会员</td>
					<td>
						<select name="isMemberOfCIC">
							<option value="${eighthForm.isMemberOfCIC}">${eighthForm.isMemberOfCIC}</option>
							<c:if test="${eighthForm.isMemberOfCIC!='是'}">
								<option value="是">是</option>
							</c:if>
							<c:if test="${eighthForm.isMemberOfCIC!='否'}">
								<option value="否">否</option>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td>会员证号</td>
					<td><input type="text" name="memberIdOfCIc" value="${eighthForm.memberIdOfCIc}" /></td>
				</tr>
			</c:if>
			<tr>
				<td>出生年月</td>
				<td><input type="text" name="birthdayOfContributor" value="${eighthForm.birthdayOfContributor}" /></td>
			</tr>
			<tr>
				<td>出生地</td>
				<td><input type="text" name="birthPlaceOfContributor" value="${eighthForm.birthPlaceOfContributor}" /></td>
			</tr>
			<tr>
				<td>民族</td>
				<td><input type="text" name="nationOfContributor" value="${eighthForm.nationOfContributor}" /></td>
			</tr>
			<tr>
				<td>身份证号</td>
				<td><input type="text" name="citizenIdOfContributor" value="${eighthForm.citizenIdOfContributor}" /></td>
			</tr>
			<tr>
				<td>归国人员</td>
				<td><input type="text" name="isReturnedFormOverseas" value="${eighthForm.isReturnedFormOverseas}" /></td>
			</tr>
			
			<tr>
				<td>技术职称</td>
				<td>
				<input type="text" name="technicalTitleOfContributor" value="${eighthForm.technicalTitleOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>最高学历</td>
				<td>
				<input type="text" name="highestEducationOfContributor" value="${eighthForm.highestEducationOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>最高学位</td>
				<td>
				<input type="text" name="highestDegreeOfContributor" value="${eighthForm.highestDegreeOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>毕业学校</td>
				<td>
				<input type="text" name="universityOfContributor" value="${eighthForm.universityOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>毕业时间</td>
				<td>
				<input type="text" name="graduateDateOfContributor" value="${eighthForm.graduateDateOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>所学专业</td>
				<td>
				<input type="text" name="majorOfContributor" value="${eighthForm.majorOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>电子邮箱</td>
				<td>
				<input type="text" name="emailOfContributor" value="${eighthForm.emailOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>办公电话</td>
				<td>
				<input type="text" name="officePhoneOfContributor" value="${eighthForm.officePhoneOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>移动电话</td>
				<td>
				<input type="text" name="mobileOfContributor" value="${eighthForm.mobileOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>通讯地址</td>
				<td>
				<input type="text" name="addressOfContributor" value="${eighthForm.addressOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>邮政编码</td>
				<td>
				<input type="text" name="zipCodeOfContributor" value="${eighthForm.zipCodeOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>工作单位</td>
				<td>
				<input type="text" name="workUnitOfContributor" value="${eighthForm.workUnitOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>行政职务</td>
				<td>
				<input type="text" name="administrativePositionOfContributor" value="${eighthForm.administrativePositionOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>二级单位</td>
				<td>
				<input type="text" name="subWorkUnitOfContributor" value="${eighthForm.subWorkUnitOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>党派</td>
				<td>
				<input type="text" name="policitalPartyOfContributor" value="${eighthForm.policitalPartyOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>完成单位</td>
				<td>
				<input type="text" name="completeUnitOfContributor" value="${eighthForm.completeUnitOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>所在地</td>
				<td>
				<input type="text" name="locationOfContributor" value="${eighthForm.locationOfContributor}" />
				</td>
			</tr>
			<tr>
				<td>单位性质</td>
				<td>
				<input type="text" name="typeOfUnit" value="${eighthForm.typeOfUnit}" />
				</td>
			</tr>
			<tr>
				<td>参加本项目开始时间</td>
				<fmt:formatDate pattern="yyyy-MM-dd"  value="${eighthForm.startDateOfParticipation }" var="startDateVar" />
				<td><input type="date" name="startDateOfParticipation" value="${startDateVar}" /></td>
			</tr>
			<tr>
				<td>参加本项目结束时间</td>
				<fmt:formatDate pattern="yyyy-MM-dd"  value="${eighthForm.endDateOfParticipation }" var="endDateVar" />
				<td><input type="date"  name="endDateOfParticipation" value="${endDateVar }" /></td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${applier.applicationType!='科技进步类' }">
					<td>对本项目主要学术贡献</td>
					</c:when>
					<c:when test="${applier.applicationType=='科技进步类' }">
					<td>对本项目技术创造性贡献</td>
					</c:when>
					<c:otherwise>
					<td>bad applicationType!</td>
					</c:otherwise>
				</c:choose>
				<!-- Don't break this line. It's textarea. -->
				<td><textarea rows="8" cols="100" name="contributionOfContributor" form="eighthFormer">${eighthForm.contributionOfContributor}</textarea></td>
			</tr>
			<tr>
				<td>曾获中国通信学会科技奖励情况：</td>
				<!-- Don't break this line. It's textarea. -->
				<td><textarea rows="8" cols="100" name="formerRewardOfCIC" form="eighthFormer">${eighthForm.formerRewardOfCIC}</textarea></td>
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
				<input type="button" onclick="location.href='/app/manage-eighth-major-contributor';" value="第八页">
				<input type="button" onclick="location.href='/app/manage-nineth-major-org-contributor';" value="第九页">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>