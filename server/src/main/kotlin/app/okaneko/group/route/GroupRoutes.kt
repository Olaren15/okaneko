package app.okaneko.group.route

import app.okaneko.authentication.data.mapper.getUserFromCall
import app.okaneko.group.data.dto.GroupCreation
import app.okaneko.group.use_case.GroupsUseCases
import com.github.michaelbull.result.mapBoth
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.groupsRoutes() {
    route("/groups") {
        val groupsUseCases: GroupsUseCases by closestDI().instance()

        get("/@me") {
            val user = getUserFromCall(call)

            val groups = groupsUseCases.getGroupsForUser(user)
            call.respond(HttpStatusCode.OK, groups)
        }

        get("/{id}") {
            val user = getUserFromCall(call)
            val groupId = call.parameters.getOrFail("id")

            groupsUseCases.getGroupById(groupId, user.id).mapBoth(
                success = {
                    call.respond(HttpStatusCode.OK, it)
                },
                failure = {
                    call.respond(it.statusCode, it.toRestError())
                })
        }

        post {
            val user = getUserFromCall(call)
            val group = call.receive<GroupCreation>()
            groupsUseCases.createGroup(group, user).mapBoth(
                success = {
                    call.respond(HttpStatusCode.Created, it)
                },
                failure = {
                    call.respond(it.statusCode, it.toRestError())
                }
            )
        }

        patch("/{id}") {

        }

        delete("/{id}") {

        }
    }
}

