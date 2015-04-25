package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Adresse)
class AdresseSpec extends Specification {

    @Unroll
    void "test la validite d'une adresse valide"(int unNumero, String uneRue, String unCodePostal, String uneVille) {

        given: "une adresse initialisee correctement"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'adresse est valide"
        adresse.validate() == true

        where:
        unNumero | uneRue            | unCodePostal | uneVille
        10       | "Chemin du Lilas" | "45000"      | "Orléans"
        1        | "Chemin du Lilas" | "45000"      | "Orléans"

    }

    @Unroll
    void "test l' invalidite d'une adresse non valide"(int unNumero, String uneRue, String unCodePostal, String uneVille) {

        given: "une adresse initialisee de maniere non valide"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'adresse est invalide"
        adresse.validate() == false

        where:
        unNumero   | uneRue            | unCodePostal | uneVille
        0          | "Chemin du Lilas" | "45000"      | "Orléans"
        -10        | "Chemin du Lilas" | "45000"      | "Orléans"
        10         | null              | "45000"      | "Orléans"
        10         | ""                | "45000"      | "Orléans"
        10         | "Chemin du Lilas" | null         | "Orléans"
        10         | "Chemin du Lilas" | ""           | "Orléans"
        10         | "Chemin du Lilas" | "45000"      | null
        10         | "Chemin du Lilas" | "45000"      | ""

    }

    @Unroll
    void "test de l'affichage d'une adresse"(int unNumero, String uneRue, String unCodePostal, String uneVille) {

        given: "une adresse initialisee correctement"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'affichage d'une adresse est la concatenation du numero et du nom de la rue de l'adresse"
        adresse.toString() == adresse.numero + " " + adresse.rue

        where:
        unNumero   | uneRue            | unCodePostal | uneVille
        10         | "Chemin du Lilas" | "45000"      | "Orléans"

    }

    @Unroll
    void "test de recuperation d'une liste de code postaux sans doublon"() {

        given: "plusieurs adresses initialisee correctement"
        Adresse adresse1 = new Adresse(numero: 10, rue: "Avenue du Lilas", codePostal: "31000", ville: "Toulouse").save()
        Adresse adresse2 = new Adresse(numero: 11, rue: "Avenue du Lilas", codePostal: "31000", ville: "Toulouse").save()
        Adresse adresse3 = new Adresse(numero: 12, rue: "Avenue du Lilas", codePostal: "31000", ville: "Toulouse").save()
        Adresse adresse4 = new Adresse(numero: 25, rue: "Rue du Soleil", codePostal: "45000", ville: "Orléans").save()
        Adresse adresse5 = new Adresse(numero: 77, rue: "Place Beaumarchais", codePostal: "81000", ville: "Albi").save()

        expect: "la liste de code postaux contient 3 elements"
        adresse1.listUnique().size() == 3

    }
}
