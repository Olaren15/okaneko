package app.okaneko.authentication.use_case

import app.okaneko.authentication.error.CannotGenerateRefreshTokenError
import com.github.michaelbull.result.Result

interface GenerateRefreshToken {
    suspend operator fun invoke(userId: String): Result<String, CannotGenerateRefreshTokenError>
}