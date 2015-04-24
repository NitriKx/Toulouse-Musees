package com.tblabsau.universite.s8.amc.projet

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Musee)
class MuseeSpec extends Specification {

    @Unroll
    void "teste la validite d'un Musee valide"(String unNom, String unHoraireOuverture, String unTelephone, String unAccessMetro, String unAccessBus, Gestionnaire unGestionnaire, Adresse uneAdresse) {

        given: "une Musee initialise avec ses parametres non null et non vides"
        Musee musee = new Musee(nom: unNom,
                horairesOuverture: unHoraireOuverture,
                telephone: unTelephone,
                accessMetro: unAccessMetro,
                accessBus: unAccessBus,
                gestionnaire: unGestionnaire,
                adresse: uneAdresse)

        expect: "le Musee est valide"
        musee.validate() == true

        where:
        unNom       | unHoraireOuverture    | unTelephone   | unAccessMetro | unAccessBus   | unGestionnaire        | uneAdresse
        "Louvre"    | "8h00"                | "6876798687"  | "oui"         | "oui"         | new Gestionnaire()    | new Adresse()


    }

    @Unroll
    void "test l'invalidite d'un Musee non valide"(String unNom, String unHoraireOuverture, String unTelephone, String unAccessMetro, String unAccessBus, Gestionnaire unGestionnaire, Adresse uneAdresse) {

        given: "un Musee initialisee avec un de ses parametres vide ou null"
        Musee musee = new Musee(nom: unNom,
                horairesOuverture: unHoraireOuverture,
                telephone: unTelephone,
                accessMetro: unAccessMetro,
                accessBus: unAccessBus,
                gestionnaire: unGestionnaire,
                adresse: uneAdresse)

        expect: "le Musee est invalide"
        musee.validate() == false

        where:
        unNom       | unHoraireOuverture    | unTelephone   | unAccessMetro | unAccessBus   | unGestionnaire        | uneAdresse
        ""          | "8h00"                | "6876798687"  | "oui"         | "oui"         | new Gestionnaire()    | new Adresse()
        null        | "8h00"                | "6876798687"  | "oui"         | "oui"         | new Gestionnaire()    | new Adresse()
        "Louvre"    | ""                    | "6876798687"  | "oui"         | "oui"         | new Gestionnaire()    | new Adresse()
        "Louvre"    | null                  | "6876798687"  | "oui"         | "oui"         | new Gestionnaire()    | new Adresse()
        "Louvre"    | "8h00"                | ""            | "oui"         | "oui"         | new Gestionnaire()    | new Adresse()
        "Louvre"    | "8h00"                | null          | "oui"         | "oui"         | new Gestionnaire()    | new Adresse()
        "Louvre"    | "8h00"                | "6876798687"  | ""            | "oui"         | new Gestionnaire()    | new Adresse()
        "Louvre"    | "8h00"                | "6876798687"  | null          | "oui"         | new Gestionnaire()    | new Adresse()
        "Louvre"    | "8h00"                | "6876798687"  | "oui"         | ""            | new Gestionnaire()    | new Adresse()
        "Louvre"    | "8h00"                | "6876798687"  | "oui"         | null          | new Gestionnaire()    | new Adresse()

    }
}
