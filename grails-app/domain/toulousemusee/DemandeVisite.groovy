package toulousemusee

class DemandeVisite {

    int code
    Date dateDebutPeriode
    Date dateFinPeriode
    int nbPersonnes
    String statut

    static constraints = {
        nbPersonnes min: 1 , max: 6
        statut blank : false
    }

    static hasMany = [museeDemande: Musee]

    static belongsTo = [Musee]
}
