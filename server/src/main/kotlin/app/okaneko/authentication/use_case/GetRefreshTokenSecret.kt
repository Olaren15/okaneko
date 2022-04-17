package app.okaneko.authentication.use_case

import app.okaneko.authentication.error.CannotGetRefreshTokenSecretError
import com.github.michaelbull.result.Result

interface GetRefreshTokenSecret {
    suspend operator fun invoke(): Result<String, CannotGetRefreshTokenSecretError>
}