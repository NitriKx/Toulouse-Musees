package com.tblabsau.universite.s8.amc.projet



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    MuseesService museesService

    AdresseService adresseService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", faireRecherche: "POST"]


    //
    //    PAGES CONTROLLERS
    //

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Musee.list(params), model:[museeInstanceCount: Musee.count()]
    }

    def show(Musee museeInstance) {
        respond museeInstance
    }

    def create() {
        respond new Musee(params)
    }

    def edit(Musee museeInstance) {
        respond museeInstance
    }

    def search() {
        respond view: "search"
    }


    //
    //    CONTROLLER ACTIONS
    //


    @Transactional
    def save(Musee museeInstance) {
        if (museeInstance == null) {
            notFound()
            return
        }

        if (museeInstance.hasErrors()) {
            respond museeInstance.errors, view:'create'
            return
        }

        museesService.insertOrUpdateMusee(museeInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'musee.label', default: 'Musee'), museeInstance.id])
                redirect museeInstance
            }
            '*' { respond museeInstance, [status: CREATED] }
        }
    }

    @Transactional
    def update(Musee museeInstance) {
        if (museeInstance == null) {
            notFound()
            return
        }

        if (museeInstance.hasErrors()) {
            respond museeInstance.errors, view:'edit'
            return
        }

        museesService.insertOrUpdateMusee(museeInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Musee.label', default: 'Musee'), museeInstance.id])
                redirect museeInstance
            }
            '*'{ respond museeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Musee museeInstance) {

        if (museeInstance == null) {
            notFound()
            return
        }

        museesService.deleteMusee(museeInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Musee.label', default: 'Musee'), museeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def faireRecherche(String rechercheNomMusee, String rechercheCodePostal, String rechercheNomRueMusee) {
            render(view: "search", model: [resultatRecherche: museesService.searchMusees(rechercheNomMusee, rechercheCodePostal, rechercheNomRueMusee),
                            precedentRechercheNomMusee: rechercheNomMusee, precedentRechercheCodePostal: rechercheCodePostal, precedentRechercheNomRueMusee: rechercheNomRueMusee])
    }

    def ajouterMuseePref() {
        def maliste = session.museesFav ?: new ArrayList<Musee>()
        maliste.add(Musee.findById(params.museeFavID))
        session.museesFav = maliste
        for(Musee m : maliste)
            System.out.println("Name : ${m.nom}")
        faireRecherche(params.rechercheNomMusee, params.rechercheCodePostal, params.rechercheNomRueMusee)
    }

    //
    //    ERRORS HANDLING
    //

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'musee.label', default: 'Musee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
