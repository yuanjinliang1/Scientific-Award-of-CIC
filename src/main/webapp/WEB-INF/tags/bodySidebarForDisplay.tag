<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ tag pageEncoding="utf-8" %>
<%@ attribute name="page" required="true" rtexprvalue="true"  description=""%>

<c:set var="firstDisplay" value=""></c:set>
<c:set var="secondDisplay" value=""></c:set>
<c:set var="thirdDisplay" value=""></c:set>
<c:set var="fourthDisplay" value=""></c:set>
<c:set var="fifthDisplay" value=""></c:set>
<c:set var="sixthDisplay" value=""></c:set>
<c:set var="seventhDisplay" value=""></c:set>
<c:set var="eighthDisplay" value=""></c:set>
<c:set var="ninethDisplay" value=""></c:set>
<c:choose>
	<c:when test="${page eq '1' }">
		<c:set var="firstDisplay" value="active"></c:set>
	</c:when>
	<c:when test="${page eq '2' }">
		<c:set var="secondDisplay" value="active"></c:set>
	</c:when>
	<c:when test="${page eq '3' }">
		<c:set var="thirdDisplay" value="active"></c:set>
	</c:when>
	<c:when test="${page eq '4' }">
		<c:set var="fourthDisplay" value="active"></c:set>
	</c:when>
	<c:when test="${page eq '5' }">
		<c:set var="fifthDisplay" value="active"></c:set>
	</c:when>
	<c:when test="${page eq '6' }">
		<c:set var="sixthDisplay" value="active"></c:set>
	</c:when>
	<c:when test="${page eq '7' }">
		<c:set var="seventhDisplay" value="active"></c:set>
	</c:when>
	<c:when test="${page eq '8' }">
		<c:set var="eighthDisplay" value="active"></c:set>
	</c:when>
	<c:when test="${page eq '9' }">
		<c:set var="ninethDisplay" value="active"></c:set>
	</c:when>
	<c:otherwise>
		<c:out value="bad page"></c:out>
	</c:otherwise>
</c:choose>


<div>
	<spring:url value="/display-first-project-basic-situation/{applierUid}" var="firstFormURL">
		<spring:param name="applierUid" value="${applier.uid }"></spring:param>
	</spring:url>
	<spring:url value="/display-referee-unit-opinion/{applierUid}" var="secondFormURL">
		<spring:param name="applierUid" value="${applier.uid }"></spring:param>
	</spring:url>
	<spring:url value="/display-brief-introduction/{applierUid}" var="thirdFormURL">
		<spring:param name="applierUid" value="${applier.uid }"></spring:param>
	</spring:url>
	<spring:url value="/display-fourth-form/{applierUid}" var="fourthFormURL">
		<spring:param name="applierUid" value="${applier.uid }"></spring:param>
	</spring:url>
	<spring:url value="/display-objective-evaluation/{applierUid}" var="fifthFormURL">
		<spring:param name="applierUid" value="${applier.uid }"></spring:param>
	</spring:url>
	<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
		<spring:url value="/select-apply-unit-situation/{applierUid}" var="sixthFormURL">
			<spring:param name="applierUid" value="${applier.uid }"></spring:param>
		</spring:url>
		<spring:url value="/select-seventh-ip-doc/{applierUid}" var="seventhFormURL">
			<spring:param name="applierUid" value="${applier.uid }"></spring:param>
		</spring:url>
	</c:if>
	<c:if test="${applier.applicationType=='自然科学类' }">
		<spring:url value="/select-paper-monograph/{applierUid}" var="sixthFormURL">
			<spring:param name="applierUid" value="${applier.uid }"></spring:param>
		</spring:url>
		<spring:url value="/select-seventh-paper-cited-by-others/{applierUid}" var="seventhFormURL">
			<spring:param name="applierUid" value="${applier.uid }"></spring:param>
		</spring:url>
	</c:if>
	<spring:url value="/select-eighth-major-contributor/{applierUid}" var="eighthFormURL">
		<spring:param name="applierUid" value="${applier.uid }"></spring:param>
	</spring:url>
	<spring:url value="/select-nineth-major-org-contributor/{applierUid}" var="ninethFormURL">
		<spring:param name="applierUid" value="${applier.uid }"></spring:param>
	</spring:url>
</div>
<style>
	li.active {
		
	}
</style>
<!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
						查看申请表
                    </a>
                </li>
                <li class="${ firstDisplay }">
                    <a href="${fn:escapeXml(firstFormURL)}">第一页</a>
                </li>
                <li class="${ secondDisplay }">
                    <a href="${fn:escapeXml(secondFormURL)}">第二页</a>
                </li>
                <li class="${ thirdDisplay }">
                    <a href="${fn:escapeXml(thirdFormURL)}">第三页</a>
                </li>
                <li class="${ fourthDisplay }">
                    <a href="${fn:escapeXml(fourthFormURL)}">第四页</a>
                </li>
                <li class="${ fifthDisplay }">
                    <a href="${fn:escapeXml(fifthFormURL)}">第五页</a>
                </li>
                <li class="${ sixthDisplay }">
                    <a href="${fn:escapeXml(sixthFormURL)}">第六页</a>
                </li>
                <li class="${ seventhDisplay }">
                    <a href="${fn:escapeXml(seventhFormURL)}">第七页</a>
                </li>
                <li class="${ eighthDisplay }">
                    <a  href="${fn:escapeXml(eighthFormURL)}">第八页</a>
                </li>
                <li class="${ ninethDisplay }">
                    <a href="${fn:escapeXml(ninethFormURL)}">第九页</a>
                </li>
                
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->