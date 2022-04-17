package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.error.CannotGenerateAccessTokenError
import app.okaneko.authentication.use_case.GenerateAccessToken
import app.okaneko.authentication.use_case.GetAccessTokenSecret
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.michaelbull.result.*
import io.ktor.server.config.*
import kotlinx.datetime.Clock
import java.util.*
import kotlin.time.Duration.Companion.minutes

class GenerateAccessTokenImpl(
    private val getAccessTokenSecret: GetAccessTokenSecret,
    private val appConfig: ApplicationConfig
) : GenerateAccessToken {
    override suspend fun invoke(userId: String): Result<String, CannotGenerateAccessTokenError> {
        return getAccessTokenSecret()
            .mapError { CannotGenerateAccessTokenError }
            .andThen {
                try {
                    val token = JWT.create()
                        .withAudience(appConfig.property("jwt.audience").getString())
                        .withIssuer(appConfig.property("jwt.issuer").getString())
                        .withSubject(userId)
                        .withExpiresAt(
                            Date(
                                Clock.System.now().plus(15.minutes).toEpochMilliseconds()
                            )
                        )
                        .withIssuedAt(Date(Clock.System.now().toEpochMilliseconds()))
                        .sign(Algorithm.HMAC256(it))

                    Ok(token)
                } catch (e: Exception) {
                    Err(CannotGenerateAccessTokenError)
                }
            }
    }
}
