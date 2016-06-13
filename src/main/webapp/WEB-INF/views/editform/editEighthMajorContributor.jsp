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
			<form id="eighthFormer" action="${fn:escapeXml(saveURL)}" method="POST" 
				modelAttribute="eighthFormAttr"  data-toggle="validator" role="form">
				<table class="table table-bordered">
					<tr>
						<td>姓名</td>
						<td class="form-group">
						<input class="form-control" type="text" name="nameOfContributor" value="${eighthForm.nameOfContributor}"
						 data-error="请填写此项" required />
						<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>性别</td>
						<td class="form-group">
							<input class="form-control" type="text" name="genderOfContributor" value="${eighthForm.genderOfContributor}" 
							data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>排名</td>
						<td  class="form-group">
							<input class="form-control" type="number" name="rankOfContributor" value="${eighthForm.rankOfContributor}" 
							data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>国籍</td>
						<td class="form-group">
							<input class="form-control" type="text" name="nationalityOfContributor" value="${eighthForm.nationalityOfContributor}" 
							data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>中国通信学会会员</td>
						<td class="form-group">
							<select id="isMemberOfCIC" name="isMemberOfCIC" data-error="请填写此项" required >
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
										<option disabled selected value="">请选择</option>
										<option value="是">是</option>
										<option value="否">否</option>
									</c:otherwise>
								</c:choose>		
							</select>
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr  class="toHide">
						<td>会员证号</td>
						<td>
							<input class="form-control"  type="text" name="memberIdOfCIc" value="${eighthForm.memberIdOfCIc}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>出生年月</td>
						<td class="form-group">
							<input class="form-control" type="text" name="birthdayOfContributor" value="${eighthForm.birthdayOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>出生地</td>
						<td class="form-group">
							<input class="form-control" type="text" name="birthPlaceOfContributor" value="${eighthForm.birthPlaceOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>民族</td>
						<td class="form-group">
							<input class="form-control" type="text" name="nationOfContributor" value="${eighthForm.nationOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>身份证号</td>
						<td class="form-group">
							<input class="form-control" type="text" name="citizenIdOfContributor" value="${eighthForm.citizenIdOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>归国人员</td>
						<td class="form-group">
							<select name="isReturnedFormOverseas" id="isReturnedFormOverseas" data-error="请填写此项" required >
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
										<option disabled selected value="">请选择</option>
										<option value="是">是</option>
										<option value="否">否</option>
									</c:otherwise>
								</c:choose>						
							</select>
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					
					<tr>
						<td>技术职称</td>
						<td class="form-group">
						<input class="form-control" type="text" name="technicalTitleOfContributor" value="${eighthForm.technicalTitleOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>最高学历</td>
						<td class="form-group">
						<input class="form-control" type="text" name="highestEducationOfContributor" value="${eighthForm.highestEducationOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>最高学位</td>
						<td class="form-group">
						<input  class="form-control"type="text" name="highestDegreeOfContributor" value="${eighthForm.highestDegreeOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>毕业学校</td>
						<td class="form-group">
						<input class="form-control" type="text" name="universityOfContributor" value="${eighthForm.universityOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>毕业时间</td>
						<td class="form-group">
						<input class="form-control" type="text" name="graduateDateOfContributor" value="${eighthForm.graduateDateOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>所学专业</td>
						<td class="form-group">
						<input class="form-control" type="text" name="majorOfContributor" value="${eighthForm.majorOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>电子邮箱</td>
						<td class="form-group">
						<input class="form-control" type="text" name="emailOfContributor" value="${eighthForm.emailOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>办公电话</td>
						<td class="form-group">
						<input class="form-control" type="text" name="officePhoneOfContributor" value="${eighthForm.officePhoneOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>移动电话</td>
						<td class="form-group">
						<input class="form-control" type="text" name="mobileOfContributor" value="${eighthForm.mobileOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>通讯地址</td>
						<td class="form-group">
						<input class="form-control" type="text" name="addressOfContributor" value="${eighthForm.addressOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>邮政编码</td>
						<td class="form-group">
						<input class="form-control" type="text" name="zipCodeOfContributor" value="${eighthForm.zipCodeOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>工作单位</td>
						<td class="form-group">
						<input class="form-control" type="text" name="workUnitOfContributor" value="${eighthForm.workUnitOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>行政职务</td>
						<td class="form-group">
						<input class="form-control" type="text" name="administrativePositionOfContributor" value="${eighthForm.administrativePositionOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>二级单位</td>
						<td class="form-group">
						<input class="form-control" type="text" name="subWorkUnitOfContributor" value="${eighthForm.subWorkUnitOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>党派</td>
						<td class="form-group">
						<input class="form-control" type="text" name="policitalPartyOfContributor" value="${eighthForm.policitalPartyOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>完成单位</td>
						<td class="form-group">
						<input class="form-control" type="text" name="completeUnitOfContributor" value="${eighthForm.completeUnitOfContributor}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>所在地</td>
						<td class="form-group">
						<input class="form-control" type="text" name="locationOfContributor" value="${eighthForm.locationOfContributor}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>单位性质</td>
						<td class="form-group">
						<input class="form-control" type="text" name="typeOfUnit" value="${eighthForm.typeOfUnit}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>参加本项目开始时间</td>
						<td class="form-group">
							<input class="form-control" type="text" name="startDateOfParticipation" value="${eighthForm.startDateOfParticipation}" 
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						</td>
					</tr>
					<tr>
						<td>参加本项目结束时间</td>
						<td class="form-group"><input class="form-control" type="text"  name="endDateOfParticipation" value="${eighthForm.startDateOfParticipation}"
						 	data-error="请填写此项" required />
							<div class="help-block with-errors" style="font-size:15px"></div>
						 </td>
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
					<div class="row panel-body form-group">
						<textarea class="form-control" rows="16"  name="contributionOfContributor" 
						placeholder="不超过300字。应具体写明完成人对本项目做出的实质性贡献并注明对应第几项科技创新；与他人合作完成的科技创新，要细致说明本人独立于合作者的具体贡献，以及支持本人贡献成立的证明材料。提及的证明材料如存在于主要知识产权证明目录，应写明目录编号，否则应在附件中提供并注明附件编号。"
						form="eighthFormer" maxlength="300" data-error="请填写此项" required>${eighthForm.contributionOfContributor}</textarea>
						<div class="help-block with-errors" style="font-size:15px"></div>
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
					<div class="row panel-body form-group">
						<textarea class="form-control" rows="16"  name="formerRewardOfCIC" form="eighthFormer"
						placeholder="填写完成人曾获中国通信学会科技奖励的获奖年度、等级、项目名称、排名及证书编号等内容"  
						data-error="请填写此项" required>${eighthForm.formerRewardOfCIC}</textarea>
						<div class="help-block with-errors" style="font-size:15px"></div>
					</div>
				</div>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="保存" />
					<spring:url value="/manage-eighth-major-contributor" var="eighthFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(eighthFormURL)}">返回</a>
				</div>
			</form>
            	
            
        </div>
		</div>
	</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$("#isMemberOfCIC").ready(function(){
			if($("#isMemberOfCIC option:selected").text()=="是"){
				$(".toHide").show();
				console.log("yes");
			}
			if($("#isMemberOfCIC option:selected").text()=="否"){
				$(".toHide").hide();
				console.log("no");
			}
		});
		$("#isMemberOfCIC").change(function(){
			if($("#isMemberOfCIC option:selected").text()=="是"){
				$(".toHide").show();
				console.log("yes");
			}
			if($("#isMemberOfCIC option:selected").text()=="否"){
				$(".toHide").hide();
				console.log("no");
			}
		});
		
	})
</script>


	
</body>
</html>
