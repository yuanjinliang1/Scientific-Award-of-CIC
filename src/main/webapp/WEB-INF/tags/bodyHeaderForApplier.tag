<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ tag pageEncoding="utf-8" %>
<%@ attribute name="menuName" required="true" rtexprvalue="true"  description="项目管理、项目组用户管理、个人管理"%>
<c:choose>
	<c:when test="${menuName eq 'createApplication' }">
		<c:set var="createApplication" value="active"></c:set>
		<c:set var="editApplication" value=""></c:set>
		<c:set var="viewApplication" value=""></c:set>
		<c:set var="manageSelf" value=""></c:set>
	</c:when>
	<c:when test="${menuName eq 'editApplication' }">
		<c:set var="createApplication" value=""></c:set>
		<c:set var="editApplication" value="active"></c:set>
		<c:set var="viewApplication" value=""></c:set>
		<c:set var="manageSelf" value=""></c:set>
	</c:when>
	<c:when test="${menuName eq 'viewApplication' }">
		<c:set var="createApplication" value=""></c:set>
		<c:set var="editApplication" value=""></c:set>
		<c:set var="viewApplication" value="active"></c:set>
		<c:set var="manageSelf" value=""></c:set>
	</c:when>
	<c:when test="${menuName eq 'manageSelf' }">
		<c:set var="createApplication" value=""></c:set>
		<c:set var="editApplication" value=""></c:set>
		<c:set var="viewApplication" value=""></c:set>
		<c:set var="manageSelf" value="active"></c:set>
	</c:when>
	
	<c:otherwise>
		<c:set var="manageApplication" value=""></c:set>
		<c:set var="manageApplier" value=""></c:set>
		<c:set var="manageSelf" value=""></c:set>
	</c:otherwise>
</c:choose>

<div class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" >中国通信学会报奖系统</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
      	<spring:url value="/edit-initialize-application" var="editURL"/>
        <li  class="${createApplication }"><a id="editAddress" href="${fn:escapeXml(editURL)}">建立项目</a></li>
        
        <spring:url value="/edit-first-project-basic-situation" var="fisrtEditURL"/>
        <li  class="${editApplication }"><a id="firstEditAddress" href="${fn:escapeXml(fisrtEditURL)}">编辑项目</a></li>
        
        <spring:url value="/display-first-project-basic-situation/{applierUid}" var="displayFisrtEditURL">
			<spring:param name="applierUid" value="${person.uid}"></spring:param>
		</spring:url>
        <li  class="${viewApplication }" ><a id="firstEditAddress" href="${fn:escapeXml(displayFisrtEditURL)}">查看项目</a></li>
      </ul>
    </div>
    <div >
    	<ul class="nav navbar-nav navbar-right">
    	<li><a><c:out value="${ person.name }(${person.uid })"/>，欢迎您！</a></li>
    	<spring:url value="/self-managed-by-applier/{applierUid}" var="selfManageURL">
			<spring:param name="applierUid" value="${person.uid}"></spring:param>
		</spring:url>
        <li  class="${manageSelf }" >
       		<a id="selfManage" href="${fn:escapeXml(selfManageURL)}">个人管理</a>
        </li>
    	<li>
	    	<a href="/app/login">
	    	<span class="glyphicon glyphicon-log-out"></span>
	    	登出</a>
    	</li>
    	</ul>
    </div>
  </div>
 </div>