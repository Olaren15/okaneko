package app.okaneko.authentication.route

import app.okaneko.authentication.data.dto.EmailPasswordRegistration
import app.okaneko.authentication.use_case.AuthenticationUseCases
import com.github.michaelbull.result.mapBoth
import io.ktor.http.*
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
            val registration = call.receive<EmailPasswordRegistration>()

            authenticationUseCases.registerUserWithEmailAndPassword(registration).mapBoth(
                success = {
                    call.respond(HttpStatusCode.Created, it)
                },
                failure = {
                    call.respond(it.statusCode, it.toRestError())
                }
            )
        }

        post("/authorize") {

        }

        post("/revoke") {

        }

        post("/token") {

        }
    }
}