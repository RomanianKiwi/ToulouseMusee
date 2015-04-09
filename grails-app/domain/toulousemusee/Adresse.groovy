package toulousemusee

class Adresse {
    int numero
    String rue
    String codePostal
    String ville

    static constraints = {
        numero min: 1
        rue blank: false
        codePostal blank: false
        ville blank: false
    }

    String toString(){
        "$numero $rue"
    }
}
