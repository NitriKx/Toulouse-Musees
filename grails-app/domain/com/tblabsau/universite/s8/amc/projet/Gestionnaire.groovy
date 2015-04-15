package com.tblabsau.universite.s8.amc.projet

class Gestionnaire {

    String nom

    static hasMany = [
            museesGere: Musee
    ]

    static constraints = {
    }
}
