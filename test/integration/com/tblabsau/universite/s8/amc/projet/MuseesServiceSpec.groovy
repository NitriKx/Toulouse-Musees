package com.tblabsau.universite.s8.amc.projet

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MuseesService)
class MuseesServiceSpec extends Specification {

    def setup() {
        service.gestionnaireService = new GestionnaireService()
    }

    void "test chargement des musées à partir d'un fichier CSV"() {

        given: "un chemin dans le classpath vers un fichier CSV valide contenant des informations sur des musées"
        String cheminResourceFichierCSVMusee = "initial-data/Musee.csv"

        when: "le service charge la liste des musées du fichier et les enregistre dans la base de données"
        service.loadFromCSVFile(cheminResourceFichierCSVMusee)

        then: "le service doit avoir 12 musées"
        service.getMuseeList().size() == 12

        then: "la liste des musée ne doit pas contenir la ligne de titre du fichier CSV"
        !"EQ_NOM_EQUIPEMENT".equals(service.getMuseeList().get(0).getNom())
    }
}
