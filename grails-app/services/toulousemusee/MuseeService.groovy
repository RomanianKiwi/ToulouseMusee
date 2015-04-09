package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    def Musee insertOrUpdateMuseeForGestionnaire(Musee unMusee,Gestionnaire unGestionnaire) {
        unGestionnaire.save()
        unGestionnaire.addToMusees(unMusee)
        unMusee.save()
        unMusee
    }

    def deleteMusee(Musee unMusee){
        unMusee.gestionnaire.removeFromMusees(unMusee)
        unMusee.delete()
    }
}
