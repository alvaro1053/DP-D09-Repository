

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<security:authorize access="hasRole('ADMIN')">
	
	<h1 id="h1Statistics"><spring:message code="administrator.statistics" /></h1>
	<br/>
	<h2 id="hDeCategory">Acme-Rendezvous 1.0</h2>
	<!-- 1 -->
	<table class="displayStyle">

		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.average" /></th>
			<th><spring:message code="administrator.deviation" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.averageAndDesviationOfRendesPerUser" /></td>
			<td>${averageOfRendesPerUser }</td>
			<td>${desviationOfRendesPerUser }</td>
		</tr>
	</table>
	
	<!-- 2 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.average" /></th>
			<th><spring:message code="administrator.deviation" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.averageAndDesviationOfRSVPPerRende" /></td>
			<td>${averageOfRSVPPerRende }</td>
			<td>${desviationOfRSVPPerRende }</td>
		</tr>
	</table>
	
	<!-- 3 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.average" /></th>
			<th><spring:message code="administrator.deviation" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.averageAndDesviationOfRSVPPerUser" /></td>
			<td>${averageOfRSVPPerUser }</td>
			<td>${desviationOfRSVPPerUser }</td>
		</tr>
	</table>
	
	<!-- 4 -->
	<table class="displayStyle">
	
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.average" /></th>
			<th><spring:message code="administrator.deviation" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.averageAndDesviationOfAnnouncementPerRende" /></td>
			<td>${averageOfAnnouncementPerRende }</td>
			<td>${desviationOfAnnouncementPerRende }</td>
		</tr>
	</table>
	
	<!-- 5 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.average" /></th>
			<th><spring:message code="administrator.deviation" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.averageAndDesviationOfQuestionsPerRende" /></td>
			<td>${averageOfQuestionsPerRende }</td>
			<td>${desviationOfQuestionsPerRende }</td>
		</tr>
	</table>
	
	<!-- 6 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.average" /></th>
			<th><spring:message code="administrator.deviation" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.averageAndDesviationOfRepliesPerComment" /></td>
			<td>${averageOfRepliesPerComment }</td>
			<td>${desviationOfRepliesPerComment }</td>
		</tr>
	</table>
	
	<!-- 7 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.top10RendesByAttendants" /></td>
			<td>${top10RendesByAttendants }</td>
		</tr>
	</table>
	
	<!-- 8 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.rendesWithMoreAnnouncementsThanTheAverage" /></td>
			<td>${rendesWithMoreAnnouncementsThanTheAverage }</td>
		</tr>
	</table>
	
	<!-- 9 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.rendesWithMoreLinkedThanTheAverage" /></td>
			<td>${rendesWithMoreLinkedThanTheAverage }</td>
		</tr>
	</table>
	
	<!-- 10 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.ratioOfUsersWithRendesCreated" /></td>
			<td>${ratioOfUsersWithRendesCreated }</td>
		</tr>
	</table>
	
	<!-- 11 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.average" /></th>
			<th><spring:message code="administrator.deviation" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.averageAndDesviationOfRepliesPerQuestion" /></td>
			<td>${averageOfRepliesPerQuestion }</td>
			<td>${desviationOfRepliesPerQuestion }</td>
		</tr>
	</table>
	
	
	<br/>
	<!-- Acme-Rendezvous 2.0 -->
	<h2 id="hDeCategory">Acme-Rendezvous 2.0</h2>
		<!-- 1 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.topSellingServices" /></td>
			<td>${topSellingServices }</td>
		</tr>
	</table>
	
		<!-- 2 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.ManagersWithMoreServicesThanTheAverage" /></td>
			<td>${ManagersWithMoreServicesThanTheAverage }</td>
		</tr>
	</table>
	
		<!-- 3 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.AverageCategoriesPerRendezvous" /></td>
			<td>${AverageCategoriesPerRendezvous }</td>
		</tr>
	</table>
	
		<!-- 4 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.AverageServicesRequestedPerRende" /></td>
			<td>${AverageServicesRequestedPerRende }</td>
		</tr>
	</table>
	
		<!-- 5 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value.Min" /></th>
			<th><spring:message code="administrator.value.Max" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.MinMaxServicesRequestedPerRende" /></td>
			<td>${MinServicesRequestedPerRende }</td>
			<td>${MaxServicesRequestedPerRende }</td>
		</tr>
	</table>
	
		<!-- 6 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.StandardDesviationServicesRequestedPerRende" /></td>
			<td>${StandardDesviationServicesRequestedPerRende }</td>
		</tr>
	</table>
	
		<!-- 7 -->
	<table class="displayStyle">
		
		<tr>
			<th><spring:message code="administrator.query" /></th>
			<th><spring:message code="administrator.value" /></th>
		
		</tr>
		
		<tr>
			<td><spring:message code="administrator.top5SellingServices" /></td>
			<td>${top5SellingServices }</td>
		</tr>
	</table>
	
</security:authorize>
