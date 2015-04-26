<%@ page import="com.tblabsau.universite.s8.amc.projet.DemandeVisiteMusee" %>



<div class="fieldcontain ${hasErrors(bean: demandeVisiteMuseeInstance, field: 'dateDemande', 'error')} required">
	<label for="dateDemande">
		<g:message code="demandeVisiteMusee.dateDemande.label" default="Date Demande" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDemande" precision="day"  value="${demandeVisiteMuseeInstance?.dateDemande}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteMuseeInstance, field: 'demandeVisite', 'error')} required">
	<label for="demandeVisite">
		<g:message code="demandeVisiteMusee.demandeVisite.label" default="Demande Visite" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="demandeVisite" name="demandeVisite.id" from="${com.tblabsau.universite.s8.amc.projet.DemandeVisite.list()}" optionKey="id" required="" value="${demandeVisiteMuseeInstance?.demandeVisite?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteMuseeInstance, field: 'musee', 'error')} required">
	<label for="musee">
		<g:message code="demandeVisiteMusee.musee.label" default="Musee" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="musee" name="musee.id" from="${com.tblabsau.universite.s8.amc.projet.Musee.list()}" optionKey="id" required="" value="${demandeVisiteMuseeInstance?.musee?.id}" class="many-to-one"/>

</div>

