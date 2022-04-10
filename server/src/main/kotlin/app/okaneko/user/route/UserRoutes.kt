package app.okaneko.user.route

import app.okaneko.base.routes.mapping.mapCreated
import app.okaneko.base.routes.mapping.mapRestError
import app.okaneko.user.use_case.UserUseCases
import com.github.michaelbull.result.mapBoth
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.userRoutes() {
    val userUseCases: UserUseCases by closestDI().instance()

    route("/user") {
        post {
            val (status, message) = userUseCases.registerUserWithEmailAndPassword(call.receive())
                .mapBoth(::mapCreated, ::mapRestError)

            call.respond(status, message)
        }
    }
}