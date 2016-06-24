<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

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
				<spring:url value="/select-ninth-major-org-contributor/{applierUid}" var="ninthFormURL">
					<spring:param name="applierUid" value="${applier.uid }"></spring:param>
				</spring:url>
				<input type="button" onclick="location.href='${fn:escapeXml(firstFormURL)}';" value="第一页">
				<input type="button" onclick="location.href='${fn:escapeXml(secondFormURL)}';" value="第二页">
				<input type="button" onclick="location.href='${fn:escapeXml(thirdFormURL)}';" value="第三页">
				<input type="button" onclick="location.href='${fn:escapeXml(fourthFormURL)}';" value="第四页">
				<input type="button" onclick="location.href='${fn:escapeXml(fifthFormURL)}';" value="第五页">
				<input type="button" onclick="location.href='${fn:escapeXml(sixthFormURL)}';" value="第六页">
				<input type="button" onclick="location.href='${fn:escapeXml(seventhFormURL)}';" value="第七页">
				<input type="button" onclick="location.href='${fn:escapeXml(eighthFormURL)}';" value="第八页">
				<input type="button" onclick="location.href='${fn:escapeXml(ninthFormURL)}';" value="第九页">