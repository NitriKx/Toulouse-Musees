<%@ page import="com.tblabsau.universite.s8.amc.projet.Musee" %>



<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'nom', 'error')} required">
    <label for="nom">
        <g:message code="musee.nom.label" default="Nom"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="nom" required="" value="${museeInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'horairesOuverture', 'error')} required">
    <label for="horairesOuverture">
        <g:message code="musee.horairesOuverture.label" default="Horaires Ouverture"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="horairesOuverture" required="" value="${museeInstance?.horairesOuverture}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'telephone', 'error')} required">
    <label for="telephone">
        <g:message code="musee.telephone.label" default="Telephone"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="telephone" required="" value="${museeInstance?.telephone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accessMetro', 'error')} required">
    <label for="accessMetro">
        <g:message code="musee.accessMetro.label" default="Access Metro"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="accessMetro" required="" value="${museeInstance?.accessMetro}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accessBus', 'error')} required">
    <label for="accessBus">
        <g:message code="musee.accessBus.label" default="Access Bus"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="accessBus" required="" value="${museeInstance?.accessBus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse', 'error')} required">
    <label for="adresse">
        <g:message code="musee.adresse.label" default="Adresse"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="adresse" name="adresse.id" from="${com.tblabsau.universite.s8.amc.projet.Adresse.list()}"
              optionKey="id" required="" value="${museeInstance?.adresse?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'demandeVisiteMusees', 'error')} ">
    <label for="demandeVisiteMusees">
        <g:message code="musee.demandeVisiteMusees.label" default="Demande Visite Musees"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${museeInstance?.demandeVisiteMusees ?}" var="d">
            <li><g:link controller="demandeVisiteMusee" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="demandeVisiteMusee" action="create"
                    params="['musee.id': museeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'gestionnaire', 'error')} required">
    <label for="gestionnaire">
        <g:message code="musee.gestionnaire.label" default="Gestionnaire"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="gestionnaire" name="gestionnaire.id"
              from="${com.tblabsau.universite.s8.amc.projet.Gestionnaire.list()}" optionKey="id" required=""
              value="${museeInstance?.gestionnaire?.id}" class="many-to-one"/>

</div>

