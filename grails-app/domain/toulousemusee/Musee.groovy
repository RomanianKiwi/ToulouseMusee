package toulousemusee

class Musee {
    String nom
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus
    Gestionnaire gestionnaire
    Adresse adresse

    static constraints = {
        nom blank: false
        horairesOuverture blank: false
        telephone blank: false
        accesMetro nullable: true, blank: true
        accesBus nullable: true, blank: true
        gestionnaire nullable: false
        adresse nullable: false
    }

    static hasMany = [ demandes : DemandeVisite ]

    String toString(){
        "$nom"
    }
}