package com.tblabsau.universite.s8.amc.projet

import grails.transaction.Transactional

@Transactional
class GestionnaireService {

    def insertOrUpdateGestionnaire(Gestionnaire instance) {
        instance.save(flush: true)
    }
}
