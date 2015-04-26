package com.tblabsau.universite.s8.amc.projet

class DemandeVisite {

    Date dateDebutPeriode
    Date dateFinPeriode
    Integer nbPersonnes
    TypeVisiteEnum status

    static hasMany = [
            demandeVisiteMusee: DemandeVisiteMusee
    ]

    static constraints = {
        dateDebutPeriode nullable: false
        dateFinPeriode nullable: false, validator: { value, demandeVisite -> value.after(demandeVisite.dateDebutPeriode) }
        nbPersonnes nullable: false, min: 1
        status nullable: false
    }

    public String toString() {
        "${status} ${nbPersonnes} ${dateDebutPeriode} ${dateFinPeriode}"
    }
}
