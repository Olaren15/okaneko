package dev.olaren.okane.authentication.data.mapper

import dev.olaren.okane.authentication.data.dto.User
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun getUserFromCall(call: ApplicationCall): User {
    val principal: JWTPrincipal = call.principal()!!
    return mapJwtToUser(principal)
}

fun mapJwtToUser(principal: JWTPrincipal): User {
    return User(
        id = principal.subject.orEmpty(),
        name = null,
        email = principal.getClaim("email", String::class),
        photoUrl = null,
        isAnonymous = principal.getClaim("firebase.sign_in_provider", String::class).equals("anonymous")
    )
}
