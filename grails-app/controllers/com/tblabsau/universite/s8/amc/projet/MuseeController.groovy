package com.tblabsau.universite.s8.amc.projet



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    MuseesService museesService

    AdresseService adresseService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


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

    def faireRecherche(String rechercheNomMusee, String rechercheCodePostal, String rechercheNomRueMusee, Integer max, Integer offset) {

        params.max = max ?: 5
        params.offset = offset ?: 0

        def resultatRecherche = museesService.searchMusees(rechercheNomMusee, rechercheCodePostal, rechercheNomRueMusee)
        def totalResultCount = resultatRecherche.size()

        def dernierElement = params.max + params.offset <= resultatRecherche.size() ? params.max + params.offset  : resultatRecherche.size()
        resultatRecherche = resultatRecherche.subList(params.offset, dernierElement)

        render(view: "search", model: [resultatRecherche: resultatRecherche, resultatRechercheCount: totalResultCount,
                                       rechercheNomMusee: rechercheNomMusee, rechercheCodePostal: rechercheCodePostal, rechercheNomRueMusee: rechercheNomRueMusee])
    }

    def ajouterMuseePref() {

        def max = (params.max) ? Integer.parseInt(params.max) : null
        def offset = (params.offset) ? Integer.parseInt(params.offset) : null

        def mapMusee = session.museesFav ?: new HashMap<Long, Musee>()
        if ( !mapMusee.containsKey(params.museeFavID.toLong()) ) {
            mapMusee.put(params.museeFavID.toLong(), Musee.findById(params.museeFavID))
        }
        session.museesFav = mapMusee
        System.out.println("Liste des musées préférées :")
        for(Musee m : mapMusee.values())
            System.out.println("- Name : ${m.nom}")
        faireRecherche(params.rechercheNomMusee, params.rechercheCodePostal, params.rechercheNomRueMusee, max, offset)
    }

    def deleteMuseePref() {

        def max = (params.max) ? Integer.parseInt(params.max) : null
        def offset = (params.offset) ? Integer.parseInt(params.offset) : null

        def mapMusee = session.museesFav ?: new HashMap<Long, Musee>()
        if ( mapMusee.containsKey(params.museeFavID.toLong()) ) {
            mapMusee.remove(params.museeFavID.toLong())
        }
        session.museesFav = mapMusee
        System.out.println("Liste des musées préférées :")
        for(Musee m : mapMusee.values())
            System.out.println("- Name : ${m.nom}")
        faireRecherche(params.rechercheNomMusee, params.rechercheCodePostal, params.rechercheNomRueMusee, max, offset)
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
