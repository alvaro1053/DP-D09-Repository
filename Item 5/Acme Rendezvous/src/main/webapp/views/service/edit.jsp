

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="service/manager/edit.do" modelAttribute="serviceForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
	<acme:textbox code="service.name" path="name"/>
	
	<acme:textarea code="service.description" path="description"/>
	
	<acme:textbox code="service.picture" path="picture"/>
	
	
	<acme:select items="${categories}" itemLabel="name" code="service.category" path="category"/>
		
	<acme:submit name="save" code="service.save"/>
	<jstl:if test="${serviceForm.id != 0 && not empty (service.request)}">	
		<spring:message code = "service.confirm" var = "confirmService"/>
		<acme:submit name = "delete" code="service.delete" onclick="return confirm('${confirmService}')" />
	</jstl:if>
	<acme:cancel url="service/list.do" code="service.cancel"/>
	
	
</form:form>