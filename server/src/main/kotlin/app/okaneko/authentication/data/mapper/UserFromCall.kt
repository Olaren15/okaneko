package app.okaneko.authentication.data.mapper

import app.okaneko.authentication.data.dto.User
import io.ktor.server.application.*

@Suppress("UNUSED_PARAMETER")
fun getUserFromCall(call: ApplicationCall): User {
    return User(id = "1", name = "jacob", email = "jacob@jacob.com", photoUrl = null, isAnonymous = false)
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
