package dev.olaren.okane.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        authenticate {
            get("/authenticated") {
                val principal: JWTPrincipal = call.principal()!!

                call.respondText(
                    """
                  Issuer:  ${principal.issuer}
                  Audience: ${principal.audience}
                  ExpiresAt: ${principal.expiresAt}
                  IssuedAt: ${principal.issuedAt}
                  JwtId: ${principal.jwtId}
                  sub: ${principal.subject}
                """.trimIndent()
                )
            }
        }
    }

    routing {
        get("/public") {
            call.respondText("Non authenticated")
        }
    }
}
