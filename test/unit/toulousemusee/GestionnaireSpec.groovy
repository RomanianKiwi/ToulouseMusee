package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gestionnaire)
class GestionnaireSpec extends Specification {

    @Unroll
    void "test la validite d'un gestionnaire valide"(String unNom) {

        given: "un gestionnaire initialise correctement"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est valide"
        gestionnaire.validate() == true

        and: "il n'est gestionnaire d'aucun musée"
        !gestionnaire.musees

        where:
        unNom = "Dupont"

    }

    @Unroll
    void "test l'invalidite d'un gestionnaire non valide"(String unNom) {

        given: "un gestionnaire initialise de maniere non valide"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est invalide"
        gestionnaire.validate() == false

        where:
        unNom = ''

    }

    @Unroll
    void "test de l'affichage d'un gestionnaire"(String unNom){
        given: "un gestionnaire initialisé correctement"
        Gestionnaire unGestionnaire = new Gestionnaire(nom: unNom)

        expect: "le nom du gestionnaire"
        unGestionnaire.toString()== "Roletto"

        where:
        unNom = "Roletto"
    }
}
