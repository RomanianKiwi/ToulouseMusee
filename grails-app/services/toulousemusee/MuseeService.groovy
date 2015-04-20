package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    List<Musee> museeFavoris = new ArrayList<Musee>()

    def Musee insertOrUpdateMuseeForGestionnaire(Musee unMusee, Gestionnaire unGestionnaire) {
        unGestionnaire.save()
        unGestionnaire.addToMusees(unMusee)
        unMusee.save()
        unMusee
    }

    def deleteMusee(Musee unMusee) {
        unMusee.gestionnaire.removeFromMusees(unMusee)
        unMusee.delete()
    }

    def List<Musee> addMuseeToFavorite(Musee museeToAdd) {
        museeFavoris.add(museeToAdd)
        museeFavoris.sort {it.nom}
    }

    List<Musee> searchMusees(String inNomMusee, String inCodePostal, String inNomRue) {
        def criteria = Musee.createCriteria()
        List<Musee> res = criteria.list {
            if (inNomMusee) {
                like 'nom', "%${inNomMusee}%"
            }
            if (inCodePostal) {
                adresse {
                    like 'codePostal', "%${inCodePostal}%"
                }
            }
            if (inNomRue) {
                adresse {
                    like 'rue', "%${inNomRue}%"
                }
            }
            order('nom')
        }
        res
    }
}
