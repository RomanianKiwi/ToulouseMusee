package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Musee)
class MuseeSpec extends Specification {

    @Unroll
    void "test la validite d'un musee valide"(String unNom, String lesHorairesOuverture, String unTelephone, String lesAccesMetro, String lesAccesBus) {

        given: "un musee initialise correctement"
        Musee musee = new Musee(nom: unNom, horairesOuverture: lesHorairesOuverture, telephone: unTelephone, accesMetro: lesAccesMetro, accesBus: lesAccesBus)

        expect: "le musee est valide"
        musee.validate() == true

        where:
        unNom                             | lesHorairesOuverture | unTelephone      | lesAccesMetro | lesAccesBus
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | ""
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | ""            | "Capitole"
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | ""            | ""
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | null          | "Capitole"
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | null
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | ""            | null
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | null          | ""

    }

    @Unroll
    void "test l' invalidite d'un musee non valide"(String unNom, String lesHorairesOuverture, String unTelephone, String lesAccesMetro, String lesAccesBus) {

        given: "un musee initialise de maniere non valide"
        Musee musee = new Musee(nom: unNom, horairesOuverture: lesHorairesOuverture, telephone: unTelephone, accesMetro: lesAccesMetro, accesBus: lesAccesBus)

        expect: "le musee est invalide"
        musee.validate() == false

        where:
        unNom                             | lesHorairesOuverture | unTelephone      | lesAccesMetro | lesAccesBus
        null                              | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"
        ""                                | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"
        "Ensemble onventuel Des Jacobins" | null                 | "05.61.22.21.92" | "Esquirol"    | "Capitole"
        "Ensemble onventuel Des Jacobins" | ""                   | "05.61.22.21.92" | "Esquirol"    | "Capitole"
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | null             | "Esquirol"    | "Capitole"
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | ""               | "Esquirol"    | "Capitole"

    }
}
