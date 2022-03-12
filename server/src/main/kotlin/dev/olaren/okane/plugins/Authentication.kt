package dev.olaren.okane.plugins

import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.impl.PublicClaims
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.net.URL
import java.util.concurrent.TimeUnit

fun Application.configureAuthentication() {
    val audience = environment.config.property("jwt.audience").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val jwkUrl = environment.config.property("jwt.jwkUrl").getString()

    val jwkProvider = JwkProviderBuilder(URL(jwkUrl))
        .cached(10, 5, TimeUnit.HOURS)
        .rateLimited(10, 1, TimeUnit.MINUTES)
        .build()

    install(Authentication) {
        jwt {
            verifier(jwkProvider, issuer) {
                acceptLeeway(3)
                withAudience(audience)
                withClaimPresence(PublicClaims.SUBJECT)
            }

            validate { jwtCredential ->
                if (jwtCredential.payload.subject.isBlank()) {
                    null
                } else {
                    JWTPrincipal(jwtCredential.payload)
                }
            }
        }
    }
}