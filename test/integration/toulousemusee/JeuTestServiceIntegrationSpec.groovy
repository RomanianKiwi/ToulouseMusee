package toulousemusee

import spock.lang.*

/**
 * Created by Dylan on 10/04/2015.
 */
class JeuTestServiceIntegrationSpec extends Specification {
    JeuTestService jeuTestService

    void "test creation jeu de tests pour musées"() {

        given: "une base ne contenant pas de musées"
        Musee.count() == 0

        when: "on crée le jeu de test pour les musées"
        jeuTestService.createJeuTestForMusee()

        then: "3 nouveaux musées ont été créés en base"
        Musee.count() == 3


        when: " des musées exitent deja dans la base"
        Musee.count() == 3

        and: "on déclenche à nouveau la création du jeu de test pour musée"
        jeuTestService.createJeuTestForMusee()

        then: "aucun nouveau musée n'est créé"
        Musee.count() == 3
    }
}