package app.okaneko.authentication.data.mapper

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.data.dto.UserDetails
import io.ktor.server.application.*
import kotlinx.datetime.Clock

@Suppress("UNUSED_PARAMETER")
fun getUserFromCall(call: ApplicationCall): User {
    return User(
        id = "1",
        UserDetails(name = "jacob", email = "jacob@jacob.com", photoUrl = null),
        isAnonymous = false,
        createdAt = Clock.System.now(),
        updatedAt = Clock.System.now(),
    )
    //val principal: JWTPrincipal = call.principal()!!
    //return mapJwtToUser(principal)
}

//fun mapJwtToUser(principal: JWTPrincipal): User {
//    return User(
//        id = principal.subject.orEmpty(),
//        name = null,
//        email = principal.getClaim("email", String::class),
//        photoUrl = null,
//        isAnonymous = principal.getClaim("firebase.sign_in_provider", String::class).equals("anonymous")
//    )
//}
