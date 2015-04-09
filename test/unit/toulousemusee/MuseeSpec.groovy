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
    void "test la validite d'un musee valide"(String unNom, String lesHorairesOuverture, String unTelephone, String lesAccesMetro, String lesAccesBus, def _) {

        given: "un musee initialise correctement"
        Musee musee = new Musee(nom: unNom, horairesOuverture: lesHorairesOuverture, telephone: unTelephone, accesMetro: lesAccesMetro, accesBus: lesAccesBus, gestionnaire: Mock(Gestionnaire), adresse: Mock(Adresse))

        expect: "le musee est valide"
        musee.validate() == true

        where:
        unNom                             | lesHorairesOuverture | unTelephone      | lesAccesMetro | lesAccesBus | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"  | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | ""          | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | ""            | "Capitole"  | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | ""            | ""          | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | null          | "Capitole"  | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | null        | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | ""            | null        | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | null          | ""          | _

    }

    @Unroll
    void "test l' invalidite d'un musee non valide"(String unNom, String lesHorairesOuverture, String unTelephone, String lesAccesMetro, String lesAccesBus, Gestionnaire unGestionnaire, Adresse uneAdresse) {

        given: "un musee initialise de maniere non valide"
        Musee musee = new Musee(nom: unNom, horairesOuverture: lesHorairesOuverture, telephone: unTelephone, accesMetro: lesAccesMetro, accesBus: lesAccesBus, gestionnaire: unGestionnaire, adresse: uneAdresse)

        expect: "le musee est invalide"
        musee.validate() == false

        where:
        unNom                             | lesHorairesOuverture | unTelephone      | lesAccesMetro | lesAccesBus | unGestionnaire     | uneAdresse
        null                              | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"  | Mock(Gestionnaire) | Mock(Adresse)
        ""                                | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"  | Mock(Gestionnaire) | Mock(Adresse)
        "Ensemble onventuel Des Jacobins" | null                 | "05.61.22.21.92" | "Esquirol"    | "Capitole"  | Mock(Gestionnaire) | Mock(Adresse)
        "Ensemble onventuel Des Jacobins" | ""                   | "05.61.22.21.92" | "Esquirol"    | "Capitole"  | Mock(Gestionnaire) | Mock(Adresse)
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | null             | "Esquirol"    | "Capitole"  | Mock(Gestionnaire) | Mock(Adresse)
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | ""               | "Esquirol"    | "Capitole"  | Mock(Gestionnaire) | Mock(Adresse)
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"  | null               | Mock(Adresse)
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"  | Mock(Gestionnaire) | null

    }

    @Unroll
    void "test de l'affichage d'un musee"(String unNom, String lesHorairesOuverture, String unTelephone, String lesAccesMetro, String lesAccesBus, def _) {

        given: "un musee initialise correctement"
        Musee musee = new Musee(nom: unNom, horairesOuverture: lesHorairesOuverture, telephone: unTelephone, accesMetro: lesAccesMetro, accesBus: lesAccesBus, gestionnaire: Mock(Gestionnaire), adresse: Mock(Adresse))

        expect: "seul le nom du musee est affiche"
        musee.toString() == musee.nom

        where:
        unNom                             | lesHorairesOuverture | unTelephone      | lesAccesMetro | lesAccesBus | _
        "Ensemble onventuel Des Jacobins" | "9h à 12h"           | "05.61.22.21.92" | "Esquirol"    | "Capitole"  | _

    }
}
