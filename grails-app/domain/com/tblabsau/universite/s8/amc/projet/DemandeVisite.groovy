package com.tblabsau.universite.s8.amc.projet

class DemandeVisite {

    Integer code
    Date dateDebutPeriode
    Date dateFinPeriode
    Integer nbPersonnes
    TypeVisiteEnum status

    static hasMany = [
            demandeVisiteMusee: DemandeVisiteMusee
    ]

    static constraints = {
        code nullable: false, min: 0
        dateDebutPeriode nullable: false
        dateFinPeriode nullable: false
        nbPersonnes nullable: false, min: 1
        status nullable: false
    }
}
