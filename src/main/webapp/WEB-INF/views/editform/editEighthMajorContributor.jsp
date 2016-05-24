<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<title>八、主要完成人情况表</title>
</head>
<body>
<div class="container">
	
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="8"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>八、主要完成人情况表</h1></div>
            <spring:url value="/save-eighth-major-contributor/{idOfEighthForm}" var="saveURL">
				<spring:param name="idOfEighthForm" value="${eighthForm.idOfEighthForm}"></spring:param>
			</spring:url>
			<form id="eighthFormer" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="eighthFormAttr">
				<table class="table table-bordered">
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
						<td>${eighthForm.rankOfContributor}
						<input type="hidden" name="rankOfContributor" value="${eighthForm.rankOfContributor}" />
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
									<c:choose>
										<c:when test="${eighthForm.isMemberOfCIC eq '否'}">
											<option value="否">否</option>
											<option value="是">是</option>
										</c:when>	
										<c:when test="${eighthForm.isMemberOfCIC eq '是'}">
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
						<td>
							<select name="isReturnedFormOverseas" id="isReturnedFormOverseas">
								<c:choose>
									<c:when test="${eighthForm.isReturnedFormOverseas eq '否'}">
										<option value="否">否</option>
										<option value="是">是</option>
									</c:when>	
									<c:when test="${eighthForm.isReturnedFormOverseas eq '是'}">
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
				</table>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4>
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
						</h4>
						</div>
					</div>
					<div class="row panel-body">
						<textarea class="form-control" rows="16"  name="contributionOfContributor" form="eighthFormer">${eighthForm.contributionOfContributor}</textarea>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4>
							曾获中国通信学会科技奖励情况：
						</h4>
						</div>
					</div>
					<div class="row panel-body">
						<textarea class="form-control" rows="16"  name="formerRewardOfCIC" form="eighthFormer">${eighthForm.formerRewardOfCIC}</textarea>
					</div>
				</div>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-default" value="保存并查看" />
				</div>
			</form>
            	
            
        </div>
		</div>
	</div>
</div>



	
</body>
</html>