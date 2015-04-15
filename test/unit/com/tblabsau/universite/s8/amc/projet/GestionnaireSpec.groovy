package com.tblabsau.universite.s8.amc.projet

import grails.test.mixin.TestFor
import org.codehaus.groovy.transform.stc.GroovyTypeCheckingExtensionSupport
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gestionnaire)
class GestionnaireSpec extends Specification {

    @Unroll
    void "teste la validite d'un Gestionnaire valide"(String unNom) {

        given: "un Gestionnaire initialise avec un nom non vide et non nul"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le Gestionnaire est valide"
        gestionnaire.validate() == true

        where:
        unNom << ["Terry"]

    }

    @Unroll
    void "test l'invalidite d'un Gestionnaire non valide"(String unNom) {

        given: "un Gestionnaire initialise avec un nom vide ou null"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le Gestionnaire est invalide"
        gestionnaire.validate() == false

        where:
        unNom << ["", null]

    }
}
