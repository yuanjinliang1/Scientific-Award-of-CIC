<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>六、推广应用情况、经济效益和社会效益</title>
</head>
<body>
		<table>
			<tr><td>1.推广应用情况</td></tr>
		</table>
	<form action="/app/create-apply-unit-situation"  method="POST">
		<table>
			<tr><td><input type="submit" value="添加新应用单位"></td></tr>
		</table>
	</form>
	<table>
		<tr><td>单位名称</td>
			<td>操作</td>
		</tr>
		<c:forEach var="sixthForm" items="${sixthApplyUnitSituationForms}">
			<tr>
				<td>${sixthForm.unitName}</td>
				<td>
					<spring:url value="/select-sixth-apply-unit-situation/{idOfApplyUnit}" var="editFormURL">
						<spring:param name="idOfApplyUnit" value="${sixthForm.idOfApplyUnit}"></spring:param>
					</spring:url>
					<a id="editOpinion" href="${fn:escapeXml(editFormURL)}">浏览</a>
				</td>
			</tr>
		</c:forEach>	
	</table>
	<form action="/app/edit-economic-social-benefit" method="POST" modelAttibute="sixthFormAttr">
			<table>
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
		<table>
			<tr><td>主要经济指标的有关说明</td></tr>
			<tr><td>${ sixthEconomicAndSocialBenefitForms.mainEconomicProfitIntroduction}</td></tr>
			<tr><td>其他经济效益指标的有关说明</td></tr>
			<tr><td>${ sixthEconomicAndSocialBenefitForms.otherEconomicProfitIntroduction}</td></tr>
		</table>
		<table>
			<tr><td>2.社会效益</td></tr>
			<tr><td>${ sixthEconomicAndSocialBenefitForms.socialBenefit}</td></tr>
		</table>
		<table>
			<tr><td><input type="submit" value="保存"></td></tr>
		</table>
	</form>
</body>
</html>