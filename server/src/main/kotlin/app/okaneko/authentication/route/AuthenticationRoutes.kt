package app.okaneko.authentication.route

import app.okaneko.authentication.dto.EmailPasswordAuthentication
import app.okaneko.authentication.use_case.AuthenticationUseCases
import app.okaneko.base.routes.mapping.mapOk
import app.okaneko.base.routes.mapping.mapRestError
import app.okaneko.user.use_case.UserUseCases
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapBoth
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.authenticationRoutes() {
    val authenticationUseCases: AuthenticationUseCases by closestDI().instance()
    val userUseCases: UserUseCases by closestDI().instance()

    route("/auth") {
        post("/authorize") {
            val authentication = call.receive<EmailPasswordAuthentication>()

            val (status, message) = userUseCases.getUserIdFromEmailAndPassword(
                authentication.email,
                authentication.password
            ).andThen {
                // TODO: Save refresh token in redis
                authenticationUseCases.generateAccessTokenAndRefreshToken(it)
            }.mapBoth(::mapOk, ::mapRestError)


            call.respond(status, message)
        }

        post("/revoke") {

        }

        post("/token") {

        }
    }
}