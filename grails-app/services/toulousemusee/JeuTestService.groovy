package toulousemusee

import grails.transaction.Transactional

@Transactional
class JeuTestService {

    Gestionnaire bernard
    Gestionnaire gerard
    Gestionnaire herve

    Adresse adresse1
    Adresse adresse2
    Adresse adresse3

    Musee musee1
    Musee musee2
    Musee musee3

    MuseeService museeService

    def createJeuTestForMusee() {
        if(Musee.count() == 0) {
            adresse1 = new Adresse(numero: 10,rue: "Chemin du Lilas",codePostal: "45000",ville: "Orléans").save()
            adresse2 = new Adresse(numero: 4,rue: "Avenue Général Charles de Gaulles",codePostal: "45000",ville: "Orléans").save()
            adresse3 = new Adresse(numero: 72,rue: "Chemin du Lilas",codePostal: "31000",ville: "Toulouse").save()
            bernard = new Gestionnaire(nom: "Bernard").save()
            gerard = new Gestionnaire(nom: "Gerard").save()
            herve = new Gestionnaire(nom: "Herve").save()
            musee1 = museeService.insertOrUpdateMuseeForGestionnaire(new Musee(nom: "Museum X",horairesOuverture: "9h-17h",telephone: "051651541",adresse: adresse1),gerard)
            musee2 = museeService.insertOrUpdateMuseeForGestionnaire(new Musee(nom: "Musee des Sciences",horairesOuverture: "9h-15h",telephone: "051650041",adresse: adresse2),bernard)
            musee3 = museeService.insertOrUpdateMuseeForGestionnaire(new Musee(nom: "Museum Y",horairesOuverture: "10h-17h",telephone: "051721541",adresse: adresse3),herve)
        }
    }
}
