package com.tblabsau.universite.s8.amc.projet

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Adresse)
class AdresseSpec extends Specification {

    @Unroll
    void "teste la validite d'une Adresse valide"(String unNumero, String uneRue, String unCodePostal, String uneVille) {

        given: "une Adresse initialisee avec ses parametres non nuls et non vides"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'Adresse est valide"
        adresse.validate() == true

        where:
        unNumero    |   uneRue              |   unCodePostal    |   uneVille
        "10"        |   "Rue de la Paix"    |   "31120"         |   "Roquettes"

    }

    @Unroll
    void "test l'invalidite d'une Adresse non valide"(String unNumero, String uneRue, String unCodePostal, String uneVille) {

        given: "une Adresse initialisee avec un de ses parametres vide ou null"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'Adresse est invalide"
        adresse.validate() == false

        where:
        unNumero    |   uneRue              |   unCodePostal    |   uneVille
        ""          |   "Rue de la Paix"    |   "31120"         |   "Roquettes"
        null        |   "Rue de la Paix"    |   "31120"         |   "Roquettes"
        "10"        |   ""                  |   "31120"         |   "Roquettes"
        "10"        |   null                |   "31120"         |   "Roquettes"
        "10"        |   "Rue de la Paix"    |   ""              |   "Roquettes"
        "10"        |   "Rue de la Paix"    |   null            |   "Roquettes"
        "10"        |   "Rue de la Paix"    |   "31120"         |   ""
        "10"        |   "Rue de la Paix"    |   "31120"         |   null

    }



}
