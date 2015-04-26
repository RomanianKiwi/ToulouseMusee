package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(DemandeVisiteMusee)
class DemandeVisiteMuseeSpec extends Specification {

    @Unroll
    void "test la validite d'une demande de visite de musee valide"() {

        given: "un musee et une demande de visite"
        Musee unMusee = Mock(Musee)
        DemandeVisite uneDemandeVisite = Mock(DemandeVisite)

        and: "une date"
        Date uneDate = new Date()

        when: "une demande de visite de musee est créée"
        DemandeVisiteMusee uneDemandeVisiteMusee = new DemandeVisiteMusee(dateDemande: uneDate, museeDeLaDemande: unMusee, demandeVisiteMusee: uneDemandeVisite)

        then: "la demande est valide"
        uneDemandeVisiteMusee.validate() == true

        then: "les propietes de la demande sont correctement mises à jour"
        uneDemandeVisiteMusee.dateDemande != null
        uneDemandeVisiteMusee.museeDeLaDemande == unMusee
        uneDemandeVisiteMusee.demandeVisiteMusee == uneDemandeVisite
    }

    @Unroll
    void "test l' invalidite d'une demande de visite non valide"() {

        given:"un musee et une demande de visite"
        Musee unMusee = Mock(Musee)
        DemandeVisite uneDemandeVisite = Mock(DemandeVisite)

        and:"une date"
        Date uneDate = new Date()

        when: "une demande de visite de musee sans date est créée"
        DemandeVisiteMusee uneDemandeVisiteMusee = new DemandeVisiteMusee(dateDemande: null,
                                                                          museeDeLaDemande: unMusee,
                                                                          demandeVisiteMusee: uneDemandeVisite)

        then: "la demande n'est pas validée"
        uneDemandeVisiteMusee.validate() == false

        when: "une demande de visite de musee sans musee est créée"
        uneDemandeVisiteMusee = new DemandeVisiteMusee(dateDemande: uneDate,
                                                       museeDeLaDemande: null,
                                                       demandeVisiteMusee: uneDemandeVisite)

        then: "la demande n'est pas validée"
        uneDemandeVisiteMusee.validate() == false

        when: "une demande de visite de musee sans demande de visite est créée"
        uneDemandeVisiteMusee = new DemandeVisiteMusee(dateDemande: uneDate,
                                                       museeDeLaDemande: unMusee,
                                                       demandeVisiteMusee: null)

        then: "la demande n'est pas validée"
        uneDemandeVisiteMusee.validate() == false
    }
}
