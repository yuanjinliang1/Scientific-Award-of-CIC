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
		<dicipulus:bodySidebarForDisplay page="6"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>六、推广应用情况、经济效益和社会效益</h1></div>
             <div class="row" style="margin-left: 20px"><h3>1.推广应用情况</h3></div>
            <table class="table table-bordered">
            	<tr>
            		<td>单位名称</td>
					<td>操作</td>
				</tr>
				<c:forEach var="sixthForm" items="${sixthApplyUnitSituationForms}">
					<tr>
						<td>${sixthForm.unitName}</td>
						<td>
							<spring:url value="/display-sixth-apply-unit-situation/{idOfApplyUnit}" var="editFormURL">
								<spring:param name="idOfApplyUnit" value="${sixthForm.idOfApplyUnit}"></spring:param>
							</spring:url>
							<a id="editOpinion" class="btn btn-default" href="${fn:escapeXml(editFormURL)}">浏览</a>
						</td>
					</tr>
				</c:forEach>	
			</table>
			
			<table class="table table-bordered">
				<tr>
					<td>自然年</td>
					<td>完成单位新增销售额</td>
					<td>完成单位新增利润</td>
					<td>其他应用单位新增销售额</td>
					<td>其他应用单位新增利润</td>
				</tr>
				<tr>
					<td>${sixthEconomicAndSocialBenefitForms.firstYear}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendSales1}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendProfit1}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendSalesByOtherUnit1}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendProfitByOtherUnit1}</td>
				</tr>
				<tr>
					<td>${sixthEconomicAndSocialBenefitForms.secondYear}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendSales2}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendProfit2}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendSalesByOtherUnit2}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendProfitByOtherUnit2}</td>
				</tr>
				<tr>
					<td>${sixthEconomicAndSocialBenefitForms.thirdYear}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendSales3}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendProfit3}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendSalesByOtherUnit3}</td>
					<td>${sixthEconomicAndSocialBenefitForms.appendProfitByOtherUnit3}</td>
				</tr>
				<tr>
					<%-- <td>${sixthEconomicAndSocialBenefitForms.totalOfThreeYears}</td> --%>
					<td>累计</td>
					<td>${sixthEconomicAndSocialBenefitForms.totalOfAppendSales}</td>
					<td>${sixthEconomicAndSocialBenefitForms.totalOfAppendProfit}</td>
					<td>${sixthEconomicAndSocialBenefitForms.totalOfAppendSalesByOtherUnit}</td>
					<td>${sixthEconomicAndSocialBenefitForms.totalOfAppendProfitByOtherUnit}</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<tr><td>主要经济指标的有关说明</td></tr>
				<tr><td><pre>${ sixthEconomicAndSocialBenefitForms.mainEconomicProfitIntroduction}</pre></td></tr>
				<tr><td>其他经济效益指标的有关说明</td></tr>
				<tr><td><pre>${ sixthEconomicAndSocialBenefitForms.otherEconomicProfitIntroduction}</pre></td></tr>
			</table>
			<div class="row" style="margin-left: 20px"><h3>2.社会效益</h3></div>
			<table class="table table-bordered">
				<tr><td><pre>${ sixthEconomicAndSocialBenefitForms.socialBenefit}</pre></td></tr>
			</table>
            <div class="row" style="margin-left:20px">
				<spring:url value="/display-objective-evaluation/{applierUid}" var="fifthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>				
				<a class="btn btn-default" href="${fn:escapeXml(fifthFormURL)}">上一页</a>
				<spring:url value="/select-seventh-ip-doc/{applierUid}" var="seventhFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<a class="btn btn-default" href="${fn:escapeXml(seventhFormURL)}">下一页</a>
			</div>
        </div>
		</div>
	</div>
</div>
</body>
</html>