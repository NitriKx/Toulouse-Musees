package com.tblabsau.universite.s8.amc.projet

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import java.util.Date

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    @Unroll
    void "teste la validite d'un DemandeVisite valide"(Integer unCode, Date uneDateDebutPeriode, Date uneDateFinPeriode, Integer unNbPersonnes, TypeVisiteEnum unStatus,
                                                       Set<DemandeVisiteMusee> desDemandeVisiteMusee) {

        given: "un DemandeVisite initialise"
        DemandeVisite demandeVisite = new DemandeVisite(code: unCode, dateDebutPeriode: uneDateDebutPeriode, dateFinPeriode: uneDateFinPeriode, nbPersonnes: unNbPersonnes, status: unStatus,
                demandeVisiteMusee: desDemandeVisiteMusee)

        expect: "le DemandeVisite est valide"
        demandeVisite.validate() == true

        where:
        unCode  | uneDateDebutPeriode   | uneDateFinPeriode | unNbPersonnes | unStatus                  | desDemandeVisiteMusee
        77      | new Date()            | new Date()        | 15            | TypeVisiteEnum.CONFIRMEE  | new HashSet<DemandeVisiteMusee>()

    }

    @Unroll
    void "test l'invalidite d'un DemandeVisite non valide"(Integer unCode, Date uneDateDebutPeriode, Date uneDateFinPeriode, Integer unNbPersonnes, TypeVisiteEnum unStatus,
                                                           Set<DemandeVisiteMusee> desDemandeVisiteMusee) {

        given: "un DemandeVisite initialise"
        DemandeVisite demandeVisite = new DemandeVisite(code: unCode, dateDebutPeriode: uneDateDebutPeriode, dateFinPeriode: uneDateFinPeriode, nbPersonnes: unNbPersonnes, status: unStatus,
                demandeVisiteMusee: desDemandeVisiteMusee)

        expect: "le DemandeVisite est invalide"
        demandeVisite.validate() == false

        where:
        unCode  | uneDateDebutPeriode   | uneDateFinPeriode | unNbPersonnes | unStatus                  | desDemandeVisiteMusee
        null    | new Date()            | new Date()        | 15            | TypeVisiteEnum.CONFIRMEE  | new HashSet<DemandeVisiteMusee>()
    }
}
