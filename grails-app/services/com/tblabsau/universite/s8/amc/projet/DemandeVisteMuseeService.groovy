package com.tblabsau.universite.s8.amc.projet

import grails.transaction.Transactional

@Transactional
class DemandeVisteMuseeService {

    DemandeVisiteService demandeVisiteService

    def insertOrUpdate(DemandeVisiteMusee demandeVisiteMusee) {
        demandeVisiteMusee.save(flush: true)
    }

    def delete(DemandeVisiteMusee demandeVisiteMusee) {
        demandeVisiteMusee.delete(flush: true)
    }

    def enregistrerDemandeVisite(Integer idMusee, Date dateDebut, Date dateFin, Integer nbPersonnes, TypeVisiteEnum typeVisiteEnum) {

        Musee musee = Musee.findById(idMusee)

        DemandeVisite demandeVisite = new DemandeVisite(dateDebutPeriode: dateDebut, dateFinPeriode: dateFin, nbPersonnes: nbPersonnes, status: typeVisiteEnum)
        if (demandeVisite.validate() == false) {
            String errorMessage = "Demande invalide: \n"
            demandeVisite.errors.fieldErrors.each {
                errorMessage += "  - Le champs ${it.field} est invalide. \n"
            }
            throw new RuntimeException(errorMessage)
        }
        demandeVisiteService.insertOrUpdate(demandeVisite)

        DemandeVisiteMusee nouvelleDemandeVisiteMusee = new DemandeVisiteMusee(dateDemande: new Date(), demandeVisite: demandeVisite, musee: musee)
        if (nouvelleDemandeVisiteMusee.validate() == false) {
            String errorMessage = "Demande invalide: \n"
            demandeVisite.errors.fieldErrors.each {
                errorMessage += "  - Le champs ${it.field} est invalide.\n"
            }
            throw new RuntimeException(errorMessage)
        }
        insertOrUpdate(nouvelleDemandeVisiteMusee)

        return nouvelleDemandeVisiteMusee
    }

}
