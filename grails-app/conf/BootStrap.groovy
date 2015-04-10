import toulousemusee.JeuTestService

class BootStrap {

    JeuTestService jeuTestService

    def init = { servletContext ->
        jeuTestService.createJeuTestForMusee()
    }
    def destroy = {
    }
}
