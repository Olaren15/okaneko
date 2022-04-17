package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.dto.GeneratedTokens
import app.okaneko.authentication.error.CannotGenerateTokens
import app.okaneko.authentication.use_case.GenerateAccessToken
import app.okaneko.authentication.use_case.GenerateAccessTokenAndRefreshToken
import app.okaneko.authentication.use_case.GenerateRefreshToken
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError

class GenerateAccessTokenAndRefreshTokenImpl(
    private val generateAccessToken: GenerateAccessToken,
    private val generateRefreshToken: GenerateRefreshToken
) : GenerateAccessTokenAndRefreshToken {
    override suspend fun invoke(userId: String): Result<GeneratedTokens, CannotGenerateTokens> {
        return generateAccessToken(userId).andThen { accessToken ->
            generateRefreshToken(userId).andThen { refreshToken ->
                Ok(GeneratedTokens(accessToken, refreshToken))
            }
        }.mapError { CannotGenerateTokens }
    }
}