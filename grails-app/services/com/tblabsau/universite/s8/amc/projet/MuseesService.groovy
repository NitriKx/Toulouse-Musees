package com.tblabsau.universite.s8.amc.projet

import grails.transaction.Transactional

@Transactional
class MuseesService {

    GestionnaireService gestionnaireService

    def insertOrUpdateMusee(Musee newMusee) {
        newMusee.save(flush: true)
    }

    def deleteMusee(Musee musee) {
        musee.delete(flush: true)
    }

    def getMuseeList() {
        return Musee.list()
    }

    def searchMusees(String rechercheNomMusee, String rechercheCodePostal, String rechercheNomRueMusee) {

        def criteria = Musee.createCriteria()
        List<Musee> res = criteria.list {
            if (rechercheNomMusee) {
                ilike 'nom', "%${rechercheNomMusee}%"
            }

            if (rechercheCodePostal) {
                adresse {
                    eq('codePostal',rechercheCodePostal)
                }
            }
            if (rechercheNomRueMusee) {
                adresse {
                    ilike 'rue', "%${rechercheNomMusee}%"
                }
            }

            order('nom')
        }
        res
    }


    def loadFromCSVFile(String pathToFile) {

        this.class.getClassLoader().getResourceAsStream(pathToFile).toCsvReader([skipLines: 1, separatorChar: ';']).eachLine({ tokens ->

            def nomMusee = tokens[0]
            def horairesMusee = tokens[2]
            def telephoneMusee = tokens[4]
            def accesMetroMusee = tokens[5]
            def accesBusMusee = tokens[6]

            def nomGestionnaireMusee = tokens[1]

            def numeroAdresseMusee = tokens[7]
            def rueAdresseMusee = tokens[8]
            def codePostalAdresseMusee = tokens[9]
            def villeAdresseMusee = tokens[10]

            // Pas utilisés pour le moment
            def siteWebMusee = tokens[3]
            def secteurAdresseMusee = tokens[11]
            def quartierAdresseMusee = tokens[12]
            def xCC3Musee = tokens[13]
            def yCC3Musee = tokens[14]
            def xWGS84Musee = tokens[15]
            def yWGS84Musee = tokens[16]

            def musee = new Musee(
                    nom: nomMusee,
                    horairesOuverture: horairesMusee,
                    telephone: telephoneMusee,
                    accessMetro: accesMetroMusee,
                    accessBus: accesBusMusee
            )
            musee = Musee.find(musee) ?: musee

            def gestionnaire = Gestionnaire.findByNom(nomGestionnaireMusee) ?: new Gestionnaire(nom: nomGestionnaireMusee)

            def adresse = new Adresse(
                    numero: numeroAdresseMusee,
                    rue: rueAdresseMusee,
                    ville: villeAdresseMusee,
                    codePostal: codePostalAdresseMusee,
            )
            adresse = Adresse.find(adresse) ?: adresse

            musee.adresse = adresse
            insertOrUpdateMusee(musee)

            // Puis ajout de la référence du musée dans les classes liées
            gestionnaire.addToMusees(musee)
            gestionnaireService.insertOrUpdateGestionnaire(gestionnaire)
        })

    }

}
