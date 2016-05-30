<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<title>六、论文专著目录</title>
</head>
<body>
<div class="container">
	<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="6"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>六、论文专著目录</h1></div>
            <spring:url value="/save-sixth-paper-monograph/{idOfPaperMonograph}" var="saveURL">
				<spring:param name="idOfPaperMonograph" value="${sixthPaperMonograph.idOfPaperMonograph}"></spring:param>
			</spring:url>
			<form id="sixthPaperMonograph" action="${fn:escapeXml(saveURL)}" method="POST" modelAttribute="paperMonographFormAttr">
				<table class="table table-bordered">
					<tr>
						<td>是否是代表性论文专著</td>
						<td>
							<select name="representativePaperMonograph" id="representativePaperMonograph">
								<c:choose>
									<c:when test="${sixthPaperMonograph.representativePaperMonograph eq '否'}">
										<option value="否">否</option>
										<option value="是">是</option>
									</c:when>									
									<c:otherwise>
										<option value="是">是</option>
										<option value="否">否</option>
									</c:otherwise>
								</c:choose>						
							</select>
						</td>
					</tr>
					<tr>
						<td>论文专刊名称/刊名/作者</td>
						<td><input type="text" name="name" value="${sixthPaperMonograph.name}"></td>
					</tr>
					<tr>
						<td>影响因子</td>
						<td><input type="text" name="influenceFactor" value="${sixthPaperMonograph.influenceFactor}"></td>
					</tr>
					<tr>
						<td>年卷页码（xx年xx卷xx页）</td>
						<td><input type="text" name="yearPage" value="${sixthPaperMonograph.yearPage}"></td>
					</tr>
					<tr>
						<td>发表时间（年、月、日）</td>
						<td><input type="text" name="publishTime" value="${sixthPaperMonograph.publishTime}"></td>
					</tr>
					<tr class="toHide">
						<td>通讯作者</td>
						<td><input type="text" name="correspondenceAuthor" value="${sixthPaperMonograph.correspondenceAuthor}"></td>
					</tr>
					<tr class="toHide">
						<td>第一作者</td>
						<td><input type="text" name="firstAuthor" value="${sixthPaperMonograph.firstAuthor}"></td>
					</tr>
					<tr class="toHide">
						<td>国内作者</td>
						<td><input type="text" name="domesticAuthor" value="${sixthPaperMonograph.domesticAuthor}"></td>
					</tr>
					<tr>
						<td>SCI他引次数</td>
						<td><input type="number" name="referenceBySCI" value="${sixthPaperMonograph.referenceBySCI}"></td>
					</tr>
					<tr>
						<td>他引总次数</td>
						<td><input type="number" name="totalReference" value="${sixthPaperMonograph.totalReference}"></td>
					</tr>
					<tr>
						<td>知识产权是否归国内所有</td>
						<td>
							<select name="intellectualRightBelongToNation" >
								<c:choose>
									<c:when test="${sixthPaperMonograph.intellectualRightBelongToNation eq '否'}">
										<option value="否">否</option>
										<option value="是">是</option>
									</c:when>									
									<c:otherwise>
										<option value="是">是</option>
										<option value="否">否</option>
									</c:otherwise>
								</c:choose>						
							</select>
						</td>
					</tr>
					
				</table>
				<div class="row" style="margin-left:20px">
					<input type="submit" class="btn btn-default" value="保存" />
					<spring:url value="/manage-paper-monograph" var="sixthFormURL"/>
						<a class="btn btn-default" href="${fn:escapeXml(sixthFormURL)}">返回</a>
				</div>
			</form>
            
        </div>
		</div>
	</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$("#representativePaperMonograph").ready(function(){
			if($("#representativePaperMonograph option:selected").text()=="是"){
				$(".toHide").show();
				console.log("yes");
			}
			if($("#representativePaperMonograph option:selected").text()=="否"){
				$(".toHide").hide();
				console.log("no");
			}
		});
		$("#representativePaperMonograph").change(function(){
			if($("#representativePaperMonograph option:selected").text()=="是"){
				$(".toHide").show();
				console.log("yes");
			}
			if($("#representativePaperMonograph option:selected").text()=="否"){
				$(".toHide").hide();
				console.log("no");
			}
		});
		
	})
</script>
</body>
</html>
