package app.okaneko.plugin

import app.okaneko.authentication.route.authenticationRoutes
import app.okaneko.group.route.groupsRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
//        authenticate {
        groupsRoutes()
//        }
    }

    routing {
        authenticationRoutes()
    }
}
