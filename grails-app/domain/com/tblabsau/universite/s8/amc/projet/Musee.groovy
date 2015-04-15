package com.tblabsau.universite.s8.amc.projet

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accessMetro
    String accessBus

    // Liaison 1-1
    static belongsTo = [
            adresse: Adresse
    ]

    // Liaison 1-*
    static hasOne = [
            gestionnaire: Gestionnaire
    ]

    static constraints = {
    }
}
