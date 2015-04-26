package com.tblabsau.universite.s8.amc.projet

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {

    def insertOrUpdate(DemandeVisite demandeVisite) {
        demandeVisite.save(flush: true)
    }
}
