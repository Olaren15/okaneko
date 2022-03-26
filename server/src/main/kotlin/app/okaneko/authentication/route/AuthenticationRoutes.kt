package app.okaneko.authentication.route

import app.okaneko.authentication.use_case.AuthenticationUseCases
import app.okaneko.base.routes.mapping.mapOk
import app.okaneko.base.routes.mapping.mapRestError
import com.github.michaelbull.result.mapBoth
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.authenticationRoutes() {
    val authenticationUseCases: AuthenticationUseCases by closestDI().instance()

    route("/auth") {
        post("/register") {
            val (status, message) = authenticationUseCases.registerUserWithEmailAndPassword(call.receive())
                .mapBoth(::mapOk, ::mapRestError)

            call.respond(status, message)
        }

        post("/authorize") {

        }

        post("/revoke") {

        }

        post("/token") {

        }
    }
}