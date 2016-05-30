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
<div class="container" >
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
		<dicipulus:bodySidebarForDisplay page="8"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>八、主要完成人情况表</h1></div>	
			<table class="table table-bordered" >
				<tr>
					<th>姓名</th>
					<td>
					${eighthForm.nameOfContributor}
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
					${eighthForm.genderOfContributor}
					</td>
				</tr>
				<tr>
					<th>排名</th>
					<td>
					${eighthForm.rankOfContributor}
					</td>
				</tr>
				<tr>
					<th>国籍</th>
					<td>${eighthForm.nationalityOfContributor}</td>
				</tr>
				<c:if test="${applier.applicationType!='科技进步类' }">
					<tr>
						<th>中国通信学会会员</th>
						<td>
							${eighthForm.isMemberOfCIC}
						</td>
					</tr>
					<tr>
						<th>会员证号</th>
						<td>${eighthForm.memberIdOfCIc}</td>
					</tr>
				</c:if>
				<tr>
					<th>出生年月</th>
					<td>${eighthForm.birthdayOfContributor}</td>
				</tr>
				<tr>
					<th>出生地</th>
					<td>${eighthForm.birthPlaceOfContributor}</td>
				</tr>
				<tr>
					<th>民族</th>
					<td>${eighthForm.nationOfContributor}</td>
				</tr>
				<tr>
					<th>身份证号</th>
					<td>${eighthForm.citizenIdOfContributor}</td>
				</tr>
				<tr>
					<th>归国人员</th>
					<td>${eighthForm.isReturnedFormOverseas}</td>
				</tr>
				
				<tr>
					<th>技术职称</th>
					<td>
					${eighthForm.technicalTitleOfContributor}
					</td>
				</tr>
				<tr>
					<th>最高学历</th>
					<td>
					${eighthForm.highestEducationOfContributor}
					</td>
				</tr>
				<tr>
					<th>最高学位</th>
					<td>
					${eighthForm.highestDegreeOfContributor}
					</td>
				</tr>
				<tr>
					<th>毕业学校</th>
					<td>
					${eighthForm.universityOfContributor}
					</td>
				</tr>
				<tr>
					<th>毕业时间</th>
					<td>
					${eighthForm.graduateDateOfContributor}
					</td>
				</tr>
				<tr>
					<th>所学专业</th>
					<td>
					${eighthForm.majorOfContributor}
					</td>
				</tr>
				<tr>
					<th>电子邮箱</th>
					<td>
					${eighthForm.emailOfContributor}
					</td>
				</tr>
				<tr>
					<th>办公电话</th>
					<td>
					${eighthForm.officePhoneOfContributor}
					</td>
				</tr>
				<tr>
					<th>移动电话</th>
					<td>
					${eighthForm.mobileOfContributor}
					</td>
				</tr>
				<tr>
					<th>通讯地址</th>
					<td>
					${eighthForm.addressOfContributor}
					</td>
				</tr>
				<tr>
					<th>邮政编码</th>
					<td>
					${eighthForm.zipCodeOfContributor}
					</td>
				</tr>
				<tr>
					<th>工作单位</th>
					<td>
					${eighthForm.workUnitOfContributor}
					</td>
				</tr>
				<tr>
					<th>行政职务</th>
					<td>
					${eighthForm.administrativePositionOfContributor}
					</td>
				</tr>
				<tr>
					<th>二级单位</th>
					<td>
					${eighthForm.subWorkUnitOfContributor}
					</td>
				</tr>
				<tr>
					<th>党派</th>
					<td>
					${eighthForm.policitalPartyOfContributor}
					</td>
				</tr>
				<tr>
					<th>完成单位</th>
					<td>
					${eighthForm.completeUnitOfContributor}
					</td>
				</tr>
				<tr>
					<th>所在地</th>
					<td>
					${eighthForm.locationOfContributor}
					</td>
				</tr>
				<tr>
					<th>单位性质</th>
					<td>
					${eighthForm.typeOfUnit}
					</td>
				</tr>
				<tr>
					<th>参加本项目开始时间</th>
					<fmt:formatDate pattern="yyyy-MM-dd"  value="${eighthForm.startDateOfParticipation }" var="startDateVar" />
					<td>${startDateVar}</td>
				</tr>
				<tr>
					<th>参加本项目结束时间</th>
					<fmt:formatDate pattern="yyyy-MM-dd"  value="${eighthForm.endDateOfParticipation }" var="endDateVar" />
					<td>${endDateVar }</td>
				</tr>
			</table>
			
			<div class="panel panel-default">
				<div class="panel-heading">
				<div class="row">
					<h4>
					<c:choose>
						<c:when test="${applier.applicationType!='科技进步类' }">
						对本项目主要学术贡献
						</c:when>
						<c:when test="${applier.applicationType=='科技进步类' }">
						对本项目技术创造性贡献
						</c:when>
						<c:otherwise>
						bad applicationType!
						</c:otherwise>
					</c:choose>
					</h4>
				</div>
				</div>
				<div class="row panel-body">
					<pre>${eighthForm.contributionOfContributor}</pre>
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
				<pre>${eighthForm.formerRewardOfCIC}</pre>
				</div>
			</div>
			<div class="row" style="margin-left:20px">
				<spring:url value="/select-eighth-major-contributor/{applierUid}" var="eighthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(eighthFormURL)}">返回</a>
			</div>
        </div>
        </div>
	</div>
	</div>
</body>
</html>