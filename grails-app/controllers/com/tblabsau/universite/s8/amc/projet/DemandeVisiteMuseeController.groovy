package com.tblabsau.universite.s8.amc.projet

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteMuseeController {

    DemandeVisteMuseeService demandeVisteMuseeService


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DemandeVisiteMusee.list(params), model: [demandeVisiteMuseeInstanceCount: DemandeVisiteMusee.count()]
    }

    def show(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        respond demandeVisiteMuseeInstance
    }

    def edit(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        respond demandeVisiteMuseeInstance
    }

    def demandeVisite() {

        if (!session.museesFav || session.museesFav.size() <= 0) {
            redirect(controller: 'musee', action: 'search')
        }

        render view: 'demandeVisite'
    }

    def enregistrerVisite(Integer demandeMuseeId, String demandeDateDebut, String demandeDateFin, Integer demandeNbPersonnes) {

        Date dateDebut = new Date(year: params.demandeDateDebut_year.toInteger(), month: params.demandeDateDebut_month.toInteger(), date: params.demandeDateDebut_day.toInteger(), hours: params.demandeDateDebut_hour.toInteger())
        Date dateFin = new Date(year: params.demandeDateFin_year.toInteger(), month: params.demandeDateFin_month.toInteger(), date: params.demandeDateFin_day.toInteger(), hours: params.demandeDateFin_hour.toInteger())

        try {
            def nouvelleVisiteEnregistree = demandeVisteMuseeService.enregistrerDemandeVisite(demandeMuseeId, dateDebut, dateFin, demandeNbPersonnes, TypeVisiteEnum.EN_COURS_DE_TRAITEMENT)
            render view: 'demandeVisite', model: [ nouvelleVisiteEnregistree: nouvelleVisiteEnregistree ]

        } catch (Exception e) {
            log.error("Impossible d'enregistrer la visite", e)
            flash.message = e.getMessage()
            render view: 'demandeVisite'
        }

    }

    @Transactional
    def save(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteMuseeInstance.hasErrors()) {
            respond demandeVisiteMuseeInstance.errors, view: 'create'
            return
        }

        demandeVisteMuseeService.insertOrUpdate demandeVisiteMuseeInstance

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect demandeVisiteMuseeInstance
            }
            '*' { respond demandeVisiteMuseeInstance, [status: CREATED] }
        }
    }

    @Transactional
    def update(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteMuseeInstance.hasErrors()) {
            respond demandeVisiteMuseeInstance.errors, view: 'edit'
            return
        }

        demandeVisteMuseeService.insertOrUpdate demandeVisiteMuseeInstance

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DemandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect demandeVisiteMuseeInstance
            }
            '*' { respond demandeVisiteMuseeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DemandeVisiteMusee demandeVisiteMuseeInstance) {

        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        demandeVisteMuseeService.delete demandeVisiteMuseeInstance

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DemandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
