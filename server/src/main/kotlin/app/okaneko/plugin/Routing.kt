package app.okaneko.plugin

import app.okaneko.authentication.route.authenticationRoutes
import app.okaneko.group.route.groupsRoutes
import app.okaneko.user.route.userRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        authenticationRoutes()
        groupsRoutes()
        userRoutes()
    }
}
