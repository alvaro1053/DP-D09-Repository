<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="welcome/admin/edit.do" modelAttribute="welcomePage">

	<form:hidden path="id" />
	<form:hidden path="version" />


	<form:label path="banner">
		<spring:message code="welcome.welcomeBanner" />:
	</form:label>
	<form:input path="banner" />
	<form:errors cssClass="error" path="banner" />
	<br />
	<br />
	
	<form:label path="englishWelcome">
		<spring:message code="welcome.englishWelcome" />:
	</form:label>
	<form:input path="englishWelcome" />
	<form:errors cssClass="error" path="englishWelcome" />
	<br />
	<br />
	
	<form:label path="spanishWelcome">
		<spring:message code="welcome.spanishWelcome" />:
	</form:label>
	<form:input path="spanishWelcome" />
	<form:errors cssClass="error" path="spanishWelcome" />
	<br />
	<br />
	



	<form:label path="englishName">
		<spring:message code="welcome.englishName" />:
	</form:label>
	<form:textarea path="englishName" />
	<form:errors cssClass="error" path="englishName" />
	<br />
	<br />





	<form:label path="spanishName">
		<spring:message code="welcome.spanishName" />:
	</form:label>
	<form:textarea path="spanishName" />
	<form:errors cssClass="error" path="spanishName" />
	<br />
	<br />


	<spring:message code="welcome.save" var="saveCustomisation"  />
	<spring:message code="welcome.cancel" var="cancelCustomisation"  />

	<input type="submit" name="save"
		value="${saveCustomisation}" />&nbsp; 


	<input type="button" name="cancel"
		value="${cancelCustomisation}"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />





</form:form>



