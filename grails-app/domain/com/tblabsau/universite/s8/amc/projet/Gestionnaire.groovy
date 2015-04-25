package com.tblabsau.universite.s8.amc.projet

class Gestionnaire {

    String nom

    // un Gestionnaire a plusieurs Musees
    static hasMany = [
            musees: Musee
    ]

    static constraints = {
        nom nullable: false, blank: false
    }

    public String toString() {
        "${nom}"
    }
}
