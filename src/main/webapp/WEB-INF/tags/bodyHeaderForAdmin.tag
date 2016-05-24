<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ tag pageEncoding="utf-8" %>
<%@ attribute name="menuName" required="true" rtexprvalue="true"  description="项目管理、推荐单位管理、个人管理"%>
<c:choose>
	<c:when test="${menuName eq 'manageApplication' }">
		<c:set var="manageApplication" value="active"></c:set>
		<c:set var="manageReferee" value=""></c:set>
		<c:set var="manageSelf" value=""></c:set>
	</c:when>
	<c:when test="${menuName eq 'manageReferee' }">
		<c:set var="manageApplication" value=""></c:set>
		<c:set var="manageReferee" value="active"></c:set>
		<c:set var="manageSelf" value=""></c:set>
	</c:when>
	<c:when test="${menuName eq 'manageSelf' }">
		<c:set var="manageApplication" value=""></c:set>
		<c:set var="manageReferee" value=""></c:set>
		<c:set var="manageSelf" value="active"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="manageApplication" value=""></c:set>
		<c:set var="manageReferee" value=""></c:set>
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
      	<spring:url value="/application-managed-by-admin" var="manageApplicationURL"></spring:url>
        <li  class="${manageApplication }"><a href="${fn:escapeXml(manageApplicationURL)}" >项目管理</a></li>
        <spring:url value="/referee-managed-by-admin/referee-view" var="manageRefereeURL"></spring:url>
        <li  class="${manageReferee }" ><a href="${fn:escapeXml(manageRefereeURL)}" >推荐单位管理</a></li>
      </ul>
    </div>
    <div >
    	<ul class="nav navbar-nav navbar-right">
    	<li><a>欢迎管理员老师！</a></li>
    	<spring:url value="/self-managed-by-admin" var="selfManageURL"></spring:url>
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