package app.okaneko.plugins

import app.okaneko.group.routes.groupsRoutes
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        authenticate {
            groupsRoutes()
        }
    }

    routing {

    }
}
