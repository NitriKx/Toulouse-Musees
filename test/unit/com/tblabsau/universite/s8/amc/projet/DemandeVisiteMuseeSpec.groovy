package com.tblabsau.universite.s8.amc.projet

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

import java.util.Date

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisiteMusee)
class DemandeVisiteMuseeSpec extends Specification {

    @Unroll
    void "teste la validite d'un DemandeVisiteMusee valide"(Date uneDateDemande,
                                                            DemandeVisite uneDemandeVisite, Musee unMusee) {

        given: "un DemandeVisiteMusee initialise"
        DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(dateDemande: uneDateDemande,
                demandeVisite: uneDemandeVisite, musee: unMusee)

        expect: "le DemandeVisiteMusee est valide"
        demandeVisiteMusee.validate() == true

        where:
        uneDateDemande | uneDemandeVisite | unMusee
        new Date() | new DemandeVisite() | new Musee()

    }

    @Unroll
    void "test l'invalidite d'un DemandeVisiteMusee non valide"(Date uneDateDemande,
                                                                DemandeVisite uneDemandeVisite, Musee unMusee) {

        given: "un Gestionnaire initialise"
        DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(dateDemande: uneDateDemande,
                demandeVisite: uneDemandeVisite, musee: unMusee)

        expect: "le DemandeVisiteMusee est invalide"
        demandeVisiteMusee.validate() == false

        where:
        uneDateDemande | uneDemandeVisite | unMusee
        null    | new DemandeVisite() | new Musee()
    }
}
