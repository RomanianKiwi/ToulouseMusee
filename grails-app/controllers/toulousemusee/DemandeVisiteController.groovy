package toulousemusee


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteController {

    MuseeService museeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def favList = museeService.museeFavoris
        Musee museeToVisit = Musee.findById(params.idMusee)
        respond DemandeVisite.list(params), model: [demandeVisiteInstanceCount: DemandeVisite.count(),
                                                    museeFavorisList: favList,
                                                    museeVisiteInstance: museeToVisit]
    }

    def effectuerDemandeDeVisite() {
        Date dateDebut = params.dateDebut
        Date dateFin = params.dateFin
        int nbPersonnes = 0
        def messageCode;
        if(params.nbPersonnes != null) {
            nbPersonnes = Integer.parseInt(params.nbPersonnes)
        }

        if(dateDebut > dateFin) {
            messageCode = "Informations invalides, veuillez recommencer la saisie !"
        }
        else {
            messageCode = "Votre demande a ete effectue avec succes. Voici votre code de demande : !"
        }

        def favList = museeService.museeFavoris
        render(view: 'index', model: [demandeVisiteInstanceCount: DemandeVisite.count(),
                                      museeFavorisList: favList,
                                      code: messageCode])
    }

    def show(DemandeVisite demandeVisiteInstance) {
        respond demandeVisiteInstance
    }

    def create() {
        respond new DemandeVisite(params)
    }

    @Transactional
    def save(DemandeVisite demandeVisiteInstance) {
        if (demandeVisiteInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteInstance.hasErrors()) {
            respond demandeVisiteInstance.errors, view: 'create'
            return
        }

        demandeVisiteInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'demandeVisite.label', default: 'DemandeVisite'), demandeVisiteInstance.id])
                redirect demandeVisiteInstance
            }
            '*' { respond demandeVisiteInstance, [status: CREATED] }
        }
    }

    def edit(DemandeVisite demandeVisiteInstance) {
        respond demandeVisiteInstance
    }

    @Transactional
    def update(DemandeVisite demandeVisiteInstance) {
        if (demandeVisiteInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteInstance.hasErrors()) {
            respond demandeVisiteInstance.errors, view: 'edit'
            return
        }

        demandeVisiteInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DemandeVisite.label', default: 'DemandeVisite'), demandeVisiteInstance.id])
                redirect demandeVisiteInstance
            }
            '*' { respond demandeVisiteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DemandeVisite demandeVisiteInstance) {

        if (demandeVisiteInstance == null) {
            notFound()
            return
        }

        demandeVisiteInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DemandeVisite.label', default: 'DemandeVisite'), demandeVisiteInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'demandeVisite.label', default: 'DemandeVisite'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
