package toulousemusee

class DemandeVisiteMusee {

    Date dateDemande
    Musee museeDeLaDemande
    DemandeVisite demandeVisiteMusee

    static constraints = {
    }

    static DemandeVisiteMusee link(museeDeLaDemande,demandeVisiteMusee){
        def m = DemandeVisiteMusee.findByMuseeDeLaDemandeAndDemandeVisiteMusee(museeDeLaDemande,demandeVisiteMusee)
        if (!m)
        {
            m = new DemandeVisiteMusee()
            museeDeLaDemande?.addToDemandes(m)
            demandeVisiteMusee?.addToDemandes(m)
            m.save()
        }
        return m
    }

    static void delink(museeDeLaDemande,demandeVisiteMusee){
        def m = DemandeVisiteMusee.findByMuseeDeLaDemandeAndDemandeVisiteMusee(museeDeLaDemande, demandeVisiteMusee)
        if (m)
        {
            museeDeLaDemande?.removeFromDemandes(m)
            demandeVisiteMusee?.removeFromDemandes(m)
            m.delete()
        }
    }
}
