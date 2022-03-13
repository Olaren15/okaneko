package dev.olaren.okane.group.routes

import com.github.michaelbull.result.mapBoth
import dev.olaren.okane.authentication.data.mapper.getUserFromCall
import dev.olaren.okane.group.data.mapper.mapToRestError
import dev.olaren.okane.group.use_cases.GroupsUseCases
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.groupsRoutes() {
    route("/groups") {
        val groupsUseCases: GroupsUseCases by closestDI().instance()

        get("/@me") {
        }

        get("/{id}") {
            val user = getUserFromCall(call)
            val groupId = call.parameters.getOrFail("id")

            groupsUseCases.getGroupById(groupId, user.id).mapBoth(
                success = {
                    call.respond(it)
                },
                failure = {
                    call.respond(mapToRestError(it))
                })
        }

        post {

        }

        patch("/{id}") {

        }

        delete("/{id}") {

        }
    }
}

