<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 25/04/15
  Time: 06:41
--%>

<%@ page import="com.tblabsau.universite.s8.amc.projet.Adresse" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
    <title>Toulouse Musée</title>
</head>

<body>
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </ul>
    </div>

    <div id="recherche-musee" class="content scaffold-list" role="main">
        <h1>Recherche de <g:message code="${entityName}" /></h1>

        <div class="searchForm">
            <!-- Affichage des messages d'erreurs -->
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:form url="[controller:'musee', action: 'faireRecherche']" method="POST">

                <fieldset class="form-group">
                    <label>Le nom du musée contient </label>
                    <g:textField name="rechercheNomMusee" value="${precedentRechercheNomMusee?:""}"/>
                </fieldset>

                <fieldset class="form-group">
                    <label>Code postal du musée</label>
                    <g:select id="rechercheCodePostal" name="rechercheCodePostal"
                              from="${Adresse.list([sort: "codePostal", order: "asc"]).codePostal.unique()}"
                              required=""
                              value="${precedentRechercheCodePostal?:""}" class="many-to-one"/>
                </fieldset>

                <fieldset class="form-group">
                    <label>Le nom de la rue du musée contient </label>
                    <g:textField name="rechercheNomRueMusee" value="${precedentRechercheNomRueMusee?:""}"/>
                </fieldset>

                <fieldset class="buttons">
                    <g:submitButton class="performSearch" name="submitButton"
                                    value="${message(code: 'default.button.search.label', default: 'Search')}"/>
                </fieldset>
            </g:form>

        </div>

        <g:if test="${resultatRecherche}">

            <g:if test="${resultatRecherche.size() <= 0}">
                Pas de résultats
            </g:if>

            <g:if test="${resultatRecherche.size() > 0}">

                <div class="searchResults">

                    <table>
                        <thead>
                        <tr>

                            <g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}"/>

                            <g:sortableColumn property="horairesOuverture"
                                              title="${message(code: 'musee.horairesOuverture.label', default: 'Horaires Ouverture')}"/>

                            <g:sortableColumn property="telephone"
                                              title="${message(code: 'musee.telephone.label', default: 'Telephone')}"/>

                            <g:sortableColumn property="accessMetro"
                                              title="${message(code: 'musee.accessMetro.label', default: 'Access Metro')}"/>

                            <g:sortableColumn property="accessBus"
                                              title="${message(code: 'musee.accessBus.label', default: 'Access Bus')}"/>

                            <th><g:message code="musee.adresse.label" default="Adresse"/></th>

                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${resultatRecherche}" status="i" var="museeInstance">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                <td><g:link action="show"
                                            id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>

                                <td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

                                <td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

                                <td>${fieldValue(bean: museeInstance, field: "accessMetro")}</td>

                                <td>${fieldValue(bean: museeInstance, field: "accessBus")}</td>

                                <td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
             </g:if>
        </g:if>
    </div>




</body>
</html>