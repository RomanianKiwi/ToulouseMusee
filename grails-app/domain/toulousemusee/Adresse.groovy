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

    static List listUnique() {

        def adresses = Adresse.list()
        List<String> codePostaux = new ArrayList<String>()

        for(int i = 0; i < adresses.size(); i++)
            codePostaux.add(i, adresses.get(i).codePostal)

        codePostaux.unique()
    }
}
