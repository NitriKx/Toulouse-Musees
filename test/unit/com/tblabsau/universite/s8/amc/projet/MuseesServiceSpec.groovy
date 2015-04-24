package com.tblabsau.universite.s8.amc.projet

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MuseesService)
class MuseesServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test csv data loading"() {

        given: "un chemin dans le classpath vers un fichier CSV valide contenant des informations sur des musées"
        String cheminResourceFichierCSVMusee = "initial-data/Musee.csv"

        expect: "le service a bien chargé la liste des musées du fichiers et les a enregistrés dans la base de données"
        MuseesService museesService = new MuseesService()
        museesService.loadFromCSVFile(cheminResourceFichierCSVMusee)

        museesService.getMuseeList().size() == 13
    }
}
