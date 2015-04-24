import com.tblabsau.universite.s8.amc.projet.MuseesService

class BootStrap {

    MuseesService museesService

    def init = { servletContext ->

        // Ce chemin sera utilisé par un "getResourceAsStream", et c'est pourquoi le fichier "Musee.csv" est dans le
        // dossier "conf" (pour être dans le classpath)
        String pathToInitialCSVFile = "initial-data/Musee.csv"
        museesService.loadFromCSVFile(pathToInitialCSVFile)

    }
    def destroy = {
    }
}
