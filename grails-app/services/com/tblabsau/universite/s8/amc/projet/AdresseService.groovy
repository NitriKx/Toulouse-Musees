package com.tblabsau.universite.s8.amc.projet

import grails.transaction.Transactional

@Transactional
class AdresseService {

    def insertOrUpdateAdresse(Adresse instance) {
        instance.save(flush: true)
    }
}
