<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>六、论文专著目录</title>
</head>
<body>
	<spring:url value="/save-sixth-paper-monograph/{idOfPaperMonograph}" var="saveURL">
		<spring:param name="idOfPaperMonograph" value="${sixthPaperMonograph.idOfPaperMonograph}"></spring:param>
	</spring:url>
	<form id="sixthPaperMonograph" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="paperMonographFormAttr">
		<table>
			<tr>
				<td>论文专刊名称/刊名/作者</td>
				<td>${sixthPaperMonograph.name}</td>
			</tr>
			<tr>
				<td>影响因子</td>
				<td>${sixthPaperMonograph.influenceFactor}</td>
			</tr>
			<tr>
				<td>年卷页码（xx年xx卷xx页）</td>
				<td>${sixthPaperMonograph.yearPage}</td>
			</tr>
			<tr>
				<td>发表时间（年、月、日）</td>
				<td>${sixthPaperMonograph.publishTime}</td>
			</tr>
			<tr>
				<td>通讯作者</td>
				<td>${sixthPaperMonograph.correspondenceAuthor}</td>
			</tr>
			<tr>
				<td>第一作者</td>
				<td>${sixthPaperMonograph.firstAuthor}</td>
			</tr>
			<tr>
				<td>国内作者</td>
				<td>${sixthPaperMonograph.domesticAuthor}</td>
			</tr>
			<tr>
				<td>SCI他引次数</td>
				<td>${sixthPaperMonograph.referenceBySCI}</td>
			</tr>
			<tr>
				<td>他引总次数</td>
				<td>${sixthPaperMonograph.totalReference}</td>
			</tr>
			<tr>
				<td>知识产权是否归国内所有</td>
				<td>${sixthPaperMonograph.intellectualRightBelongToNation}</td>
			</tr>
			<tr>
				<td>是否是代表性论文专著</td>
				<td>${sixthPaperMonograph.representativePaperMonograph}</td>
			</tr>
		</table>
		<table>
			<tr><td><input type="submit" value="保存"></td></tr>
		</table>
	</form>
</body>
</html>