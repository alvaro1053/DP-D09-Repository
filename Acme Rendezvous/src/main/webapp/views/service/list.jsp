

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<jstl:if test="${wasSucessful}">	
	<spring:message code="${successfulMessage}" />
</jstl:if>

<display:table pagesize="5" class="displaytag" 
	name="services" requestURI="service/list.do" id="row">

	<!-- Esta es una forma provisional (NIVEL C)  -->
	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<jstl:if test="${principal.id == row.manager.id && row.isDeleted == false}">
				<a href="service/manager/edit.do?serviceId=${row.id}"><spring:message code ="service.edit"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="service.confirmCancel" var="confirmCancel"  />
		<display:column>
			<jstl:if test="${row.isDeleted == false}">
				<a href="service/admin/cancel.do?serviceId=${row.id}" onclick="return confirm('${confirmCancel}')"><spring:message code ="service.cancel" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>


<!-- name -->
	<spring:message code="service.name"
		var="nameHeader" />
	<display:column title="${nameHeader}" property="name" sortable="true" />
<!-- description -->
	<spring:message code="service.description"
		var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"/>
<!-- Picture -->
	<spring:message code="service.picture"
		var="picture" />
	<display:column title="${picture}" > <img src="${row.picture}"  width="auto" height="200"> </display:column>
			
		

	<spring:message code="service.isCanceled" var="isCanceled"/>
	<spring:message code="service.info" var="info"/>
		<display:column title="${info}">
			<jstl:if test="${row.isDeleted}">
				<img class="alarmImg" src="images/cancel.png" alt="${isCanceled}" title="${isCanceled}"/>
			</jstl:if>
		</display:column>
		
		<!-- requests -->
	<spring:message code="service.requests"
		var="requestHeader" />
	<display:column title="${requestHeader}">
	<jstl:choose>
			<jstl:when test="${not empty row.request}">

				<ul>
					<jstl:forEach items="${row.request}" var="requests">

						<li><jstl:out value="${requests.rende.name}">
							</jstl:out></li>


					</jstl:forEach>
				</ul>

			</jstl:when>
			<jstl:otherwise>

				<spring:message code="service.requests.empty" />

			</jstl:otherwise>

		</jstl:choose>
	</display:column>
		
		<!-- Request -->

<security:authorize access="hasRole('USER')">

<spring:message code="service.request" var="request"/>
		<display:column title="${request}">
			<jstl:if test="${row.isDeleted == false}">
				<a href="request/user/create.do?serviceId=${row.id}">${request}</a>
			</jstl:if>
		</display:column>
</security:authorize>
	
	
</display:table>

<security:authorize access="hasRole('MANAGER')">
	<a href = "service/manager/create.do"><spring:message code = "service.create"/></a>
</security:authorize>



