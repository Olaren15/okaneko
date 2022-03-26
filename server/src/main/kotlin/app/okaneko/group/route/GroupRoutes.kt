package app.okaneko.group.route

import app.okaneko.base.routes.mapping.getUser
import app.okaneko.base.routes.mapping.mapOk
import app.okaneko.base.routes.mapping.mapRestError
import app.okaneko.group.use_case.GroupsUseCases
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapBoth
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import java.util.*

fun Route.groupsRoutes() {
    route("/groups") {
        val groupsUseCases: GroupsUseCases by closestDI().instance()

        get("/@me") {
            val (status, message) = call.getUser()
                .andThen {
                    Ok(groupsUseCases.getGroupsForUser(it))
                }.mapBoth(::mapOk, ::mapRestError)

            call.respond(status, message)
        }

        get("/{id}") {
            val (status, message) =
                call.getUser().andThen {
                    val id = call.parameters.getOrFail<UUID>("id")
                    groupsUseCases.getGroupById(id, it)
                }.mapBoth(::mapOk, ::mapRestError)

            call.respond(status, message)
        }

        post {
            val (status, message) =
                call.getUser().andThen {
                    groupsUseCases.createGroup(call.receive(), it)
                }.mapBoth(::mapOk, ::mapRestError)

            call.respond(status, message)
        }
    }

    patch("/{id}") {

    }

    delete("/{id}") {

    }
}


