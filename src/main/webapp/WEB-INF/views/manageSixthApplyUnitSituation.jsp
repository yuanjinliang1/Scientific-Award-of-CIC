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
				<td>操作</td>
			</tr>
			<c:forEach var="sixthForm" items="${sixthApplyUnitSituationForms}">
				<tr>
					<td>${sixthForm.unitName}</td>
					<td>
						<spring:url value="/edit-sixth-apply-unit-situation/{idOfApplyUnit}" var="editFormURL">
							<spring:param name="idOfApplyUnit" value="${sixthForm.idOfApplyUnit}"></spring:param>
						</spring:url>
						<a id="editOpinion" href="${fn:escapeXml(editFormURL)}">填写</a>
					</td>
					<td>
						<c:url value="/delete-sixth-apply-unit-situation" var="deleteURL">
							<c:param name="idOfApplyUnit" value="${sixthForm.idOfApplyUnit}"></c:param>
						</c:url>
						<a id="deleteApplyUnit" href="${fn:escapeXml(deleteURL)}">删除</a>
					</td>
				</tr>
			</c:forEach>	
		</table>	
	</form>
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
		<table>
			<tr><td>主要经济指标的有关说明</td></tr>
			<tr><td><input type="text" name="mainEconomicProfitIntroduction" value="${ sixthEconomicAndSocialBenefitForms.mainEconomicProfitIntroduction}"></td></tr>
			<tr><td>其他经济效益指标的有关说明</td></tr>
			<tr><td><input type="text" name="otherEconomicProfitIntroduction" value="${ sixthEconomicAndSocialBenefitForms.otherEconomicProfitIntroduction}"></td></tr>
		</table>
		<table>
			<tr><td>2.社会效益</td></tr>
			<tr><td><input type="text" name="socialBenefit" value="${ sixthEconomicAndSocialBenefitForms.socialBenefit}"></td></tr>
		</table>
		<table>
			<tr><td><input type="submit" value="保存"></td></tr>
		</table>
	</form>
</body>
</html>