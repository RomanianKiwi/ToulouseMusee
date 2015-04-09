package toulousemusee

class Gestionnaire {

    String nom

    static constraints = {
        nom nullable: false, blank: false
    }

    static hasMany = [
            musees: Musee
    ]
}
