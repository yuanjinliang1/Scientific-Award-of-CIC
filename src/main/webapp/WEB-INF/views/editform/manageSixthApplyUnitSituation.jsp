<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<title>六、推广应用情况、经济效益和社会效益</title>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="6"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>六、推广应用情况、经济效益和社会效益</h1></div>
            <div class="row" style="margin-left: 20px"><h3>1.推广应用情况</h3>
            	<span>应就本项目的生产、应用、推广等情况进行概述，所列应用单位一般不超过15个。</span>
            	<span>同时应在附件中提供能证明本项目整体技术已正式应用两年以上（即2014年1月1日以前应用）的证明材料。需要行政审批的项目应在获得行政审批后应用两年以上。</span>
            </div>	
            <br>
            <form action="/app/create-apply-unit-situation"  method="POST">
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="添加新应用单位" />
				</div>
			</form>
			<br/>
			<table class="table table-bordered">
				<tr><td>单位名称</td>
					<td>操作</td>
					<td>操作</td>
				</tr>
				<c:forEach var="sixthForm" items="${sixthApplyUnitSituationForms}">
					<tr>
						<td>${sixthForm.unitName}</td>
						<td>
							<spring:url value="/edit-sixth-apply-unit-situation/{idOfApplyUnit}" var="editFormURL">
								<spring:param name="idOfApplyUnit" value="${sixthForm.idOfApplyUnit}"></spring:param>
							</spring:url>
							<a class="btn btn-primary" id="editOpinion" href="${fn:escapeXml(editFormURL)}">填写</a>
						</td>
						<td>
							<c:url value="/delete-sixth-apply-unit-situation" var="deleteURL">
								<c:param name="idOfApplyUnit" value="${sixthForm.idOfApplyUnit}"></c:param>
							</c:url>
							<a  class="btn btn-danger" id="deleteApplyUnit" href="${fn:escapeXml(deleteURL)}">删除</a>
						</td>
					</tr>
				</c:forEach>	
			</table>	
			<div class="row" style="margin-left: 20px"><h3>2．近三年经济效益（万元）</h3></div>	
			<form id="sixthFormer" action="/app/edit-economic-social-benefit" method="POST" 
				modelAttibute="sixthFormAttr" data-toggle="validator" role="form">
				<table class="table table-bordered">
					<tr>
						<td>自然年</td>
						<td>完成单位新增销售额</td>
						<td>完成单位新增利润</td>
						<td>其他应用单位新增销售额</td>
						<td>其他应用单位新增利润</td>
					</tr>
					<tr>
						<td><input type="text"  name="firstYear" value="${sixthEconomicAndSocialBenefitForms.firstYear}"></td>
						<td><input type="text" name="appendSales1" value="${sixthEconomicAndSocialBenefitForms.appendSales1}"></td>
						<td><input type="text" name="appendProfit1" value="${sixthEconomicAndSocialBenefitForms.appendProfit1}"></td>
						<td><input type="text" name="appendSalesByOtherUnit1" value="${sixthEconomicAndSocialBenefitForms.appendSalesByOtherUnit1}"></td>
						<td><input type="text" name="appendProfitByOtherUnit1" value="${sixthEconomicAndSocialBenefitForms.appendProfitByOtherUnit1}"></td>
					</tr>
					<tr>
						<td><input type="text"  name="secondYear" value="${sixthEconomicAndSocialBenefitForms.secondYear}"></td>
						<td><input type="text" name="appendSales2" value="${sixthEconomicAndSocialBenefitForms.appendSales2}"></td>
						<td><input type="text" name="appendProfit2" value="${sixthEconomicAndSocialBenefitForms.appendProfit2}"></td>
						<td><input type="text" name="appendSalesByOtherUnit2" value="${sixthEconomicAndSocialBenefitForms.appendSalesByOtherUnit2}"></td>
						<td><input type="text" name="appendProfitByOtherUnit2" value="${sixthEconomicAndSocialBenefitForms.appendProfitByOtherUnit2}"></td>
					</tr>
					<tr>
						<td><input type="text" name="thirdYear" value="${sixthEconomicAndSocialBenefitForms.thirdYear}"></td>
						<td><input type="text" name="appendSales3"value="${sixthEconomicAndSocialBenefitForms.appendSales3}"></td>
						<td><input type="text" name="appendProfit3" value="${sixthEconomicAndSocialBenefitForms.appendProfit3}"></td>
						<td><input type="text" name="appendSalesByOtherUnit3" value="${sixthEconomicAndSocialBenefitForms.appendSalesByOtherUnit3}"></td>
						<td><input type="text" name="appendProfitByOtherUnit3" value="${sixthEconomicAndSocialBenefitForms.appendProfitByOtherUnit3}"></td>
					</tr>
					<tr>
						<%-- <td><input type="text" name="totalOfThreeYears" value="${sixthEconomicAndSocialBenefitForms.totalOfThreeYears}"></td> --%>
						<td>累计</td>
						<td><input type="text" name="totalOfAppendSales" value="${sixthEconomicAndSocialBenefitForms.totalOfAppendSales}"></td>
						<td><input type="text" name="totalOfAppendProfit" value="${sixthEconomicAndSocialBenefitForms.totalOfAppendProfit}"></td>
						<td><input type="text" name="totalOfAppendSalesByOtherUnit" value="${sixthEconomicAndSocialBenefitForms.totalOfAppendSalesByOtherUnit}"></td>
						<td><input type="text" name="totalOfAppendProfitByOtherUnit" value="${sixthEconomicAndSocialBenefitForms.totalOfAppendProfitByOtherUnit}"></td>
					</tr>
				</table>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							主要经济指标的有关说明
						</h4>
						</div>
					</div>
					<div class="row panel-body form-group">
						<textarea class="form-control" rows="10"  name="mainEconomicProfitIntroduction"
						 placeholder="不超过300字。&#13;&#10需说明新增销售额和新增利润的数据来源，如会计报表、单位财务部门核准出具的财务证明等；以及其他证明内容。应用单位在提供应用证明时应附支撑以上说明的证据资料。" 
						 form="sixthFormer" data-error="请填写此项" required>${sixthEconomicAndSocialBenefitForms.mainEconomicProfitIntroduction}</textarea>
						 <div class="help-block with-errors" style="font-size:15px"></div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
						<h4 >
							其他经济效益指标的有关说明
						</h4>
						</div>
					</div>
					<div class="row panel-body form-group">
						<textarea class="form-control" rows="10"  name="otherEconomicProfitIntroduction" 
						placeholder="不超过300字。&#13;&#10如果项目申报单位认为新增销售额、新增利润两个指标不能有效反映本项目的经济效益贡献，项目单位可自行增加其他效益指标，但需说明其他经济指标的数据来源、计算方法和计算过程。包括新增税收、减少损失、降低成本、降低能耗等。" 
						form="sixthFormer" data-error="请填写此项" required>${sixthEconomicAndSocialBenefitForms.otherEconomicProfitIntroduction}</textarea>
						 <div class="help-block with-errors" style="font-size:15px"></div>
					</div>
				</div>
				<div class="row" style="margin-left: 20px"><h3>3.社会效益</h3></div>	
					<div class="row panel-body form-group">
						<textarea class="form-control" rows="10"  name="socialBenefit" 
						placeholder="不超过600字。&#13;&#10应说明本项目在推动科学技术进步、保护自然资源和生态环境、提高国防能力、保障国家和社会安全、改善人民物质文化生活、提升健康水平、提高国民科学文化素质和培养人才等方面所起的作用。"
						form="sixthFormer" data-error="请填写此项" required>${sixthEconomicAndSocialBenefitForms.socialBenefit}</textarea>
						<div class="help-block with-errors" style="font-size:15px"></div>
					</div>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-primary" value="保存" />
					<spring:url value="/edit-objective-evaluation" var="fifthFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(fifthFormURL)}">上一页</a>
					<spring:url value="/manage-seventh-ip-doc" var="seventhFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(seventhFormURL)}">下一页</a>
				</div>
			</form>
        </div>
		</div>
	</div>
</div>

</body>
</html>