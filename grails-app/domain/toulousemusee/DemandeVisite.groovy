package toulousemusee

class DemandeVisite {

    int code
    Date dateDebutPeriode
    Date dateFinPeriode
    int nbPersonnes
    String statut

    static constraints = {
        code nullable: true
        nbPersonnes min: 1 , max: 6
        statut blank : false
    }

    static hasMany = [musees: Musee]

}
