package app.okaneko.authentication.route

import app.okaneko.authentication.use_case.AuthenticationUseCases
import app.okaneko.base.routes.mapping.mapOk
import app.okaneko.base.routes.mapping.mapRestError
import com.github.michaelbull.result.mapBoth
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.authenticationRoutes() {
    val authenticationUseCases: AuthenticationUseCases by closestDI().instance()

    route("/auth") {
        post("/authorize") {
            // TODO: Verify credentials and use proper user Id
            val (status, message) = authenticationUseCases.generateAccessTokenAndRefreshToken("1")
                .mapBoth(::mapOk, ::mapRestError)

            call.respond(status, message)
        }

        post("/revoke") {

        }

        post("/token") {

        }
    }
}