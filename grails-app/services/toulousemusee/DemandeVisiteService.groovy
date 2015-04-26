package toulousemusee

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {

    def ajoutDemandeVisitePourMusee(Musee unMusee, DemandeVisite uneDemande) {
        unMusee.addToDemandes(uneDemande)
        unMusee.save()
    }

    def supprimeDemandeVisitePourMusee(Musee unMusee, DemandeVisite uneDemande) {
        unMusee.removeFromDemandes(uneDemande)
        unMusee.save()
    }
}
