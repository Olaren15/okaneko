package app.okaneko.authentication.route

import app.okaneko.authentication.use_case.AuthenticationUseCases
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.authenticationRoutes() {
    val authenticationUseCases: AuthenticationUseCases by closestDI().instance()

    route("/auth") {
        post("/authorize") {

        }

        post("/revoke") {

        }

        post("/token") {

        }
    }
}