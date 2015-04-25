package toulousemusee

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {

    DemandeVisiteMusee demandeVisiteMusee

    def DemandeVisiteMusee ajoutDemandeVisitePourMusee(Musee unMusee, DemandeVisite uneDemande) {
        println unMusee
        println uneDemande
        demandeVisiteMusee.link(unMusee,uneDemande)
    }

    def Musee supprimeDemandeVisitePourMusee(Musee unMusee, DemandeVisite uneDemande) {
        demandeVisiteMusee.delink(unMusee,uneDemande)
    }
}
