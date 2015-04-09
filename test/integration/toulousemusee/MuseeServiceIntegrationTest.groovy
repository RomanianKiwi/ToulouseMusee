package toulousemusee


import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationTest extends Specification {

    MuseeService museeService

    void "test insertion ou mise à jour d'un musée avec un gestionnaire"() {

        given:"un musée et son adresse"
        Adresse adresseMusee = new Adresse(numero: 10, rue: "Chemin du Lilas", codePostal: "4500", ville: "Orléans")
        adresseMusee.save()
        Musee unMusee = new Musee(nom: "Musée X", horairesOuverture: "9h à 12h", telephone: "05.61.22.21.92", accesMetro: "Jean Jaurès", accesBus: "Capitole", adresse: adresseMusee)

        and: "un gestionnaire"
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Roletto")

        when: "on tente de répercuter en base la création ou la modification de l'activité"
        Musee resultMusee = museeService.insertOrUpdateMuseeForGestionnaire(unMusee,unGestionnaire)

        then: "le musée resultant pointe sur le musée initial"
        resultMusee == unMusee

        and:"le musée résultant n'a pas d'erreur"
        !resultMusee.hasErrors()

        and:"le musée résultant a un id"
        resultMusee.id

        and:"le musée est bien présent en base"
        Musee.findById(resultMusee.id) != null

        and: "le musée a pour gestionnaire le gestionnaire passé en paramètre"
        resultMusee.gestionnaire == unGestionnaire

        and:"le gestionnaire a dans sa liste de musée le musée passé en paramètre"
        unGestionnaire.musees.contains(resultMusee)
    }

    void "test suppression d'un musée"() {

        given: "une musée existant en base"
        Musee unMusee = new Musee(nom: "Musée X", horairesOuverture: "9h à 12h", telephone: "05.61.22.21.92", accesMetro: "Jean Jaurès", accesBus: "Capitole")
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Roletto")
        unMusee = museeService.insertOrUpdateMuseeForGestionnaire(unMusee,unGestionnaire)

        when:"on tente de supprimer le musée"
        museeService.deleteMusee(unMusee)

        then:"le musée n'existe plus en base"
        Musee.findById(unMusee.id) == null

        and:"le gestionnaire n'a plus le musée dans sa liste de musée"
        !unGestionnaire.musees.contains(unMusee)
    }
}

