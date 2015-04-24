package com.tblabsau.universite.s8.amc.projet

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accessMetro
    String accessBus

    static hasOne = [
            gestionnaire: Gestionnaire,
            adresse: Adresse
    ]

    static hasMany = [
            demandeVisiteMusee: DemandeVisiteMusee
    ]

    static constraints = {
        nom nullable: false, blank: false
        horairesOuverture nullable: false, blank: false
        telephone nullable: false, blank: false
        accessMetro nullable: false, blank: true
        accessBus nullable: false, blank: true
    }
}
