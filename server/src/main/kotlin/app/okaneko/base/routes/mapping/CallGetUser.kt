package app.okaneko.base.routes.mapping

import app.okaneko.base.data.error.CannotRetrieveUserError
import app.okaneko.user.data.dto.User
import app.okaneko.user.data.dto.UserDetails
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.server.application.*
import kotlinx.datetime.Clock

fun ApplicationCall.getUser(): Result<User, CannotRetrieveUserError> {
    return Ok(
        User(
            id = "1",
            UserDetails(name = "jacob", email = "jacob@jacob.com", photoUrl = null),
            isAnonymous = false,
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now(),
        )
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
