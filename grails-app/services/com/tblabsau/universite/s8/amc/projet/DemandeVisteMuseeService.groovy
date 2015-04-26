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
        demandeVisiteService.insertOrUpdate(demandeVisite)

        if (demandeVisite.hasErrors()) {
            throw new RuntimeException("Impossible de sauvegarder la demande de visite : " + demandeVisite.getErrors())
        }

        DemandeVisiteMusee nouvelleDemandeVisiteMusee = new DemandeVisiteMusee(dateDemande: new Date(), demandeVisite: demandeVisite, musee: musee)
        insertOrUpdate(nouvelleDemandeVisiteMusee)

        if (nouvelleDemandeVisiteMusee.hasErrors()) {
            throw new RuntimeException("Impossible de sauvegarder la demande de visite musee : " + nouvelleDemandeVisiteMusee.getErrors())
        }

        return nouvelleDemandeVisiteMusee
    }

}
