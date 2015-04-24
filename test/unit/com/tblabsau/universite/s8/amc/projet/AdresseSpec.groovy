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
    void "teste la validite d'une Adresse valide"(String unNumero, String uneRue, String unCodePostal, String uneVille, Musee unMusee) {

        given: "une Adresse initialisee avec ses parametres non nuls et non vides"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille, musee: unMusee)

        expect: "l'Adresse est valide"
        adresse.validate() == true

        where:
        unNumero    | uneRue            | unCodePostal  | uneVille      | unMusee
        "10"        | "Rue de la Paix"  | "31120"       | "Roquettes"   | new Musee()

    }

    @Unroll
    void "test l'invalidite d'une Adresse non valide"(String unNumero, String uneRue, String unCodePostal, String uneVille, Musee unMusee) {

        given: "une Adresse initialisee avec un de ses parametres vide ou null"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille, musee: unMusee)

        expect: "l'Adresse est invalide"
        adresse.validate() == false

        where:
        unNumero    | uneRue            | unCodePostal  | uneVille      | unMusee
        ""          | "Rue de la Paix"  | "31120"       | "Roquettes"   | new Musee()
        null        | "Rue de la Paix"  | "31120"       | "Roquettes"   | new Musee()
        "10"        | ""                | "31120"       | "Roquettes"   | new Musee()
        "10"        | null              | "31120"       | "Roquettes"   | new Musee()
        "10"        | "Rue de la Paix"  | ""            | "Roquettes"   | new Musee()
        "10"        | "Rue de la Paix"  | null          | "Roquettes"   | new Musee()
        "10"        | "Rue de la Paix"  | "31120"       | ""            | new Musee()
        "10"        | "Rue de la Paix"  | "31120"       | null          | new Musee()

    }



}
