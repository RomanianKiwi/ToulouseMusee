package toulousemusee


import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {

    MuseeService museeService
    JeuTestService jeuTestService

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

        given: "une musée existant en base avec une adresse"
        Adresse adresseMusee = new Adresse(numero: 10, rue: "Chemin du Lilas", codePostal: "4500", ville: "Orléans")
        adresseMusee.save()
        Musee unMusee = new Musee(nom: "Musée X", horairesOuverture: "9h à 12h", telephone: "05.61.22.21.92", accesMetro: "Jean Jaurès", accesBus: "Capitole", adresse: adresseMusee)
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Roletto")
        unMusee = museeService.insertOrUpdateMuseeForGestionnaire(unMusee,unGestionnaire)

        when:"on tente de supprimer le musée"
        museeService.deleteMusee(unMusee)

        then:"le musée n'existe plus en base"
        Musee.findById(unMusee.id) == null

        and:"le gestionnaire n'a plus le musée dans sa liste de musée"
        !unGestionnaire.musees.contains(unMusee)
    }

    void "test du moteur de recherche sur les musées"() {

        given:"les musées, les gestionnaires et les adresses fournis par le jeu de test "
        jeuTestService

        when:"on cherche les musées dont le nom du musée contient 'eum' "
        List<Musee> res = museeService.searchMusees("eum",null,null)

        then:"on récupère uniquement les musées musée1 et musée3"
        res.size() == 2
        res*.id.contains(jeuTestService.musee1.id)
        res*.id.contains(jeuTestService.musee3.id)

        when:"on cherche les musées dont le code postal contient '45'"
        res = museeService.searchMusees(null,'45',null)

        then:"on récupère uniquement les musées musée1 et musée2"
        res.size() == 2
        res*.id.contains(jeuTestService.musee1.id)
        res*.id.contains(jeuTestService.musee2.id)

        and:"ils sont ordonnés suivant le nom du musée"
        res*.nom == [jeuTestService.musee2.nom, jeuTestService.musee1.nom]

        when:"on cherche la rue dont l'adresse contient 'Avenue' "
        res = museeService.searchMusees(null,null,'Avenue')

        then:"on recupère le musée 2"
        res.size() == 1
        res*.id.contains(jeuTestService.musee2.id)

        when:"on cherche les musées dont le nom du contient 'XYZ'"
        res = museeService.searchMusees("XYZ",null,null)

        then: "on ne récupère aucun musée"
        res.size() == 0

        when:"on positionne tous les critères à null"
        res = museeService.searchMusees(null, null, null)

        then: "on récupère tout les musées"
        res.size() == 3

        and:"ils sont ordonnés suivant le nom du musée"
        res*.nom == [jeuTestService.musee2.nom, jeuTestService.musee1.nom, jeuTestService.musee3.nom]
    }
}

