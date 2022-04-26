package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.dto.GeneratedTokens
import app.okaneko.authentication.error.CannotGenerateTokens
import app.okaneko.authentication.use_case.GenerateAccessToken
import app.okaneko.authentication.use_case.GenerateAccessTokenAndRefreshToken
import app.okaneko.authentication.use_case.GenerateRefreshToken
import app.okaneko.authentication.use_case.SaveRefreshToken
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError
import kotlin.time.Duration.Companion.days


class GenerateAccessTokenAndRefreshTokenImpl(
    private val generateAccessToken: GenerateAccessToken,
    private val generateRefreshToken: GenerateRefreshToken,
    private val saveRefreshToken: SaveRefreshToken,
) : GenerateAccessTokenAndRefreshToken {
    override suspend fun invoke(userId: String): Result<GeneratedTokens, CannotGenerateTokens> {
        return generateAccessToken(userId).andThen { accessToken ->
            generateRefreshToken(userId).andThen { refreshToken ->
                saveRefreshToken(userId, refreshToken, 7.days)
                Ok(GeneratedTokens(accessToken, refreshToken))
            }
        }.mapError { CannotGenerateTokens }
    }
}