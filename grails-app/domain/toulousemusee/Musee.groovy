package toulousemusee

class Musee {
    String nom
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus

    static constraints = {
        nom blank: false
        horairesOuverture blank: false
        telephone blank: false
        accesMetro nullable: true, blank: true
        accesBus nullable: true, blank: true
    }
}