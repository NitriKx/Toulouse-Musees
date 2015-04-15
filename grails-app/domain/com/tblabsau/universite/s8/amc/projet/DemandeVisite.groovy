package com.tblabsau.universite.s8.amc.projet

class DemandeVisite {

    Integer code
    Date dateDebutPeriode
    Date dateFinPeriode
    Integer nbPersonnes
    TypeVisiteEnum status

    static hasMany = [
            demandeVisiteMusee: DemandeVisiteMusee,
    ]

    static constraints = {
    }
}
