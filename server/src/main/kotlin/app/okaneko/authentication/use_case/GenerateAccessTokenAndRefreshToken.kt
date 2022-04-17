package app.okaneko.authentication.use_case

import app.okaneko.authentication.dto.GeneratedTokens
import app.okaneko.authentication.error.CannotGenerateTokens
import com.github.michaelbull.result.Result

interface GenerateAccessTokenAndRefreshToken {
    suspend operator fun invoke(userId: String): Result<GeneratedTokens, CannotGenerateTokens>
}