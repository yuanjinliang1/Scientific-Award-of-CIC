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
            <div class="row" style="margin-left: 20px"><h1>六、论文专著目录</h1></div>	
            	<table class="table table-bordered">
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
        </div>
		</div>
	</div>
</div>
</body>
</html>