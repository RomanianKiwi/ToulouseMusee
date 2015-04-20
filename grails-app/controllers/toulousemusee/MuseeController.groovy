package toulousemusee


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    MuseeService museeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def doSearchMusees() {
        def museeList = museeService.searchMusees(params.nom,params.codePostal, params.rue)
        def favList = museeService.museeFavoris
        render(view: 'index', model: [museeFavorisList: favList, museeInstanceList: museeList, museeInstanceCount: museeList.size()])
    }

    def addMuseeToFav() {
        def museeList = museeService.searchMusees(params.nom, null, null)
        Musee myFavMusee = museeList.get(0);
        def favList = museeService.addMuseeToFavorite(myFavMusee)
        render(view: 'index', model: [museeFavorisList: favList, museeInstanceList: Musee.list(), museeInstanceCount: Musee.list().size()])
    }

    def removeToFav() {
        Musee museeToRemove = Musee.findById(params.id)
        def favList = museeService.removeMuseeToFavorite(museeToRemove)
        render (view: 'index', model: [museeFavorisList: favList, museeInstanceList: Musee.list(), museeInstanceCount: Musee.list().size()])
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 5, 100)
        def favList = museeService.museeFavoris
        respond Musee.list(params), model: [museeFavorisList: favList, museeInstanceCount: Musee.count()]
    }

    def show(Musee museeInstance) {
        respond museeInstance
    }

    def create() {
        respond new Musee(params)
    }

    @Transactional
    def save(Musee museeInstance) {
        if (museeInstance == null) {
            notFound()
            return
        }

        if (museeInstance.hasErrors()) {
            respond museeInstance.errors, view: 'create'
            return
        }

        //museeInstance.save flush: true
        Gestionnaire gest = museeInstance.gestionnaire
        museeService.insertOrUpdateMuseeForGestionnaire(museeInstance,gest)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'musee.label', default: 'Musee'), museeInstance.id])
                redirect museeInstance
            }
            '*' { respond museeInstance, [status: CREATED] }
        }
    }

    def edit(Musee museeInstance) {
        respond museeInstance
    }

    @Transactional
    def update(Musee museeInstance) {
        if (museeInstance == null) {
            notFound()
            return
        }

        if (museeInstance.hasErrors()) {
            respond museeInstance.errors, view: 'edit'
            return
        }

        //museeInstance.save flush: true
        Gestionnaire gest = museeInstance.gestionnaire
        museeService.insertOrUpdateMuseeForGestionnaire(museeInstance,gest)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Musee.label', default: 'Musee'), museeInstance.id])
                redirect museeInstance
            }
            '*' { respond museeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Musee museeInstance) {

        if (museeInstance == null) {
            notFound()
            return
        }

        //museeInstance.delete flush: true
        museeService.deleteMusee(museeInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Musee.label', default: 'Musee'), museeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'musee.label', default: 'Musee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
