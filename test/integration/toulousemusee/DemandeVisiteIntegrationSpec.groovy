package toulousemusee

import spock.lang.*

/**
 * Created by Dylan on 25/04/2015.
 */
class DemandeVisiteIntegrationSpec extends Specification {
    JeuTestService jeuTestService
    DemandeVisiteService demandeVisiteService

    void "test insertion d'une demande de visite pour un musée"() {

        given:"un musée et une demande de visite pour ce musée"
        Musee unMusee = jeuTestService.musee1
        DemandeVisite unedemandeVisite = new DemandeVisite(code: 12, dateDebutPeriode: Mock(Date), dateFinPeriode: Mock(Date), statut: "en attente", nbPersonnes: 5).save(failOnError : true)

        when: "on tente d'ajouter la demande de visite pour ce musée"
        Musee resultMusee = demandeVisiteService.ajoutDemandeVisitePourMusee(unMusee,unedemandeVisite)

        then: "le musée resultant pointe sur le musée initial"
        resultMusee == unMusee

        and:"le musée résultant n'a pas d'erreur"
        !resultMusee.hasErrors()

        and: "le musée a une demande de visite"
        resultMusee.demandes.contains(unedemandeVisite)

        and:"la demande de visite a dans sa liste de musée le musée passé en paramètre"
        unedemandeVisite.musees.contains(resultMusee)
    }

    void "test suppression d'une demande de visite pour un musée"() {

        given:"un musée possédant une demande de visite pour ce musée"
        Musee unMusee = jeuTestService.musee1
        DemandeVisite unedemandeVisite = new DemandeVisite(code: 13, dateDebutPeriode: Mock(Date), dateFinPeriode: Mock(Date), statut: "en attente", nbPersonnes: 4)
        unedemandeVisite.save()
        unMusee = demandeVisiteService.ajoutDemandeVisitePourMusee(unMusee,unedemandeVisite)

        when: "on tente d'ajouter la demande de visite pour ce musée"
        Musee resultMusee = demandeVisiteService.supprimeDemandeVisitePourMusee(unMusee,unedemandeVisite)

        then: "le musée resultant pointe sur le musée initial"
        resultMusee == unMusee

        and:"le musée résultant n'a pas d'erreur"
        !resultMusee.hasErrors()

        and: "le musée n'a plus cette demande dans sa liste"
        !resultMusee.demandes.contains(unedemandeVisite)

        and:"la demande de visite n'a pas dans sa liste de musée le musée passé en paramètre"
        !unedemandeVisite.musees.contains(resultMusee)
    }
}
