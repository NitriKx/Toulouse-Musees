<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 26/04/15
  Time: 17:00
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Demande de visite</title>
</head>

<body>
    <h2>Vos musées favoris</h2>
    <p>Choissiez un musée à visiter</p>

    <g:if test="${nouvelleVisiteEnregistree}">
        <p>Votre demande de visite du musée ${nouvelleVisiteEnregistree.musee.nom} a été  enregistrée et sera traitée prochainement. Votre code est <b>${nouvelleVisiteEnregistree.id}.</b></p>
    </g:if>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:form url="[controller:'demandeVisiteMusee', action:'enregistrerVisite']" method="PUT" class="form-control" >

        <div class="form-group">
            <g:each in="${session.museesFav}" var="museeFav">
                <div>
                    <input type="radio" name="demandeMuseeId" id="musee${museeFav.key}" value="${museeFav.key}" required="" />
                    <label for="musee${museeFav.key}">${museeFav.value}</label>
                </div>
            </g:each>
        </div>

        <div class="form-group">
            <label>Date de début</label>
            <g:datePicker name="demandeDateDebut" precision="hour" required="" formatString="EEE MMM dd HH:mm:ss Z yyyy" />
        </div>

        <div class="form-group">
            <label>Date de fin</label>
            <g:datePicker name="demandeDateFin" precision="hour" required="" formatString="EEE MMM dd HH:mm:ss Z yyyy" />
        </div>

        <div class="form-group">
            <label>Nombre de personnes</label>
            <g:field type="number" name="demandeNbPersonnes" min="1" max="6" required="" />
        </div>

        <g:submitButton name="submitButton" value="Envoyer"/>

    </g:form>

</body>
</html>