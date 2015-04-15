package com.tblabsau.universite.s8.amc.projet

class DemandeVisiteMusee {

    Date dateDemande

    // Classe d'association
    static hasOne = [
            demandeVisite: DemandeVisite,
            musee: Musee
    ]

    static constraints = {
        dateDemande nullable: false
    }
}
