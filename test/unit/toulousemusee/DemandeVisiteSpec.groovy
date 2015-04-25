package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    @Unroll
    void "test la validite d'une demande de visite"(int unCode, Date uneDateFin, Date uneDateDebut, int unNbPersonnes, String unStatut) {

        given: "une demande de visite initialise correctement"
        DemandeVisite visite = new DemandeVisite(code: unCode, dateFinPeriode: uneDateFin, dateDebutPeriode: uneDateDebut, nbPersonnes: unNbPersonnes, statut: unStatut)

        expect: "la demande de visite est valide"
        visite.validate() == true

        where:
        unCode | uneDateFin   | uneDateDebut | unNbPersonnes | unStatut
        1      | Mock(Date)   | Mock(Date)   | 5             | "ok"

    }

    @Unroll
    void "test l' invalidite d'une demande de visite"(int unCode, Date uneDateFin, Date uneDateDebut, int unNbPersonnes, String unStatut) {

        given: "une demande de visite mal initialise"
        DemandeVisite visite = new DemandeVisite(code: unCode, dateFinPeriode: uneDateFin, dateDebutPeriode: uneDateDebut, nbPersonnes: unNbPersonnes, statut: unStatut)

        expect: "la demande de visite est valide"
        visite.validate() == false

        where:
        unCode | uneDateFin   | uneDateDebut | unNbPersonnes | unStatut
        1      | null         | Mock(Date)   | 4             | "ok"
        1      | Mock(Date)   | null         | 4             | "ok"
        1      | Mock(Date)   | Mock(Date)   | 0             | "ok"
        1      | Mock(Date)   | Mock(Date)   | 7             | "ok"
        1      | Mock(Date)   | Mock(Date)   | 4             | null
        1      | Mock(Date)   | Mock(Date)   | 4             | ""
    }
}
