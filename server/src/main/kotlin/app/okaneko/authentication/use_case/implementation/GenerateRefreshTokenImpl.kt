package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.error.CannotGenerateRefreshTokenError
import app.okaneko.authentication.use_case.GenerateRefreshToken
import app.okaneko.authentication.use_case.GetRefreshTokenSecret
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.michaelbull.result.*
import io.ktor.server.config.*
import kotlinx.datetime.Clock
import java.util.*
import kotlin.time.Duration.Companion.days

class GenerateRefreshTokenImpl(
    private val getRefreshTokenSecret: GetRefreshTokenSecret,
    private val appConfig: ApplicationConfig
) : GenerateRefreshToken {
    override suspend fun invoke(userId: String): Result<String, CannotGenerateRefreshTokenError> {
        return getRefreshTokenSecret()
            .mapError { CannotGenerateRefreshTokenError }
            .andThen {
                try {
                    val token = JWT.create()
                        .withAudience(appConfig.property("jwt.audience").getString())
                        .withIssuer(appConfig.property("jwt.issuer").getString())
                        .withSubject(userId)
                        .withExpiresAt(
                            Date(Clock.System.now().plus(7.days).toEpochMilliseconds())
                        )
                        .withIssuedAt(Date(Clock.System.now().toEpochMilliseconds()))
                        .sign(Algorithm.HMAC256(it))

                    Ok(token)
                } catch (e: Exception) {
                    Err(CannotGenerateRefreshTokenError)
                }
            }
    }
}