package app.okaneko.authentication.use_case

import app.okaneko.authentication.error.CannotGenerateAccessTokenError
import com.github.michaelbull.result.Result

interface GenerateAccessToken {
    suspend operator fun invoke(userId: String): Result<String, CannotGenerateAccessTokenError>
}