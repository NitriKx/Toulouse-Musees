
<%@ page import="com.tblabsau.universite.s8.amc.projet.DemandeVisiteMusee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-demandeVisiteMusee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-demandeVisiteMusee" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list demandeVisiteMusee">
			
				<g:if test="${demandeVisiteMuseeInstance?.dateDemande}">
				<li class="fieldcontain">
					<span id="dateDemande-label" class="property-label"><g:message code="demandeVisiteMusee.dateDemande.label" default="Date Demande" /></span>
					
						<span class="property-value" aria-labelledby="dateDemande-label"><g:formatDate date="${demandeVisiteMuseeInstance?.dateDemande}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteMuseeInstance?.demandeVisite}">
				<li class="fieldcontain">
					<span id="demandeVisite-label" class="property-label"><g:message code="demandeVisiteMusee.demandeVisite.label" default="Demande Visite" /></span>
					
						<span class="property-value" aria-labelledby="demandeVisite-label"><g:link controller="demandeVisite" action="show" id="${demandeVisiteMuseeInstance?.demandeVisite?.id}">${demandeVisiteMuseeInstance?.demandeVisite?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteMuseeInstance?.musee}">
				<li class="fieldcontain">
					<span id="musee-label" class="property-label"><g:message code="demandeVisiteMusee.musee.label" default="Musee" /></span>
					
						<span class="property-value" aria-labelledby="musee-label"><g:link controller="musee" action="show" id="${demandeVisiteMuseeInstance?.musee?.id}">${demandeVisiteMuseeInstance?.musee?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:demandeVisiteMuseeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${demandeVisiteMuseeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
