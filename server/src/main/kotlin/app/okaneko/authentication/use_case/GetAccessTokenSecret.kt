package app.okaneko.authentication.use_case

import app.okaneko.authentication.error.CannotGetAccessTokenSecretError
import com.github.michaelbull.result.Result

interface GetAccessTokenSecret {
    suspend operator fun invoke(): Result<String, CannotGetAccessTokenSecretError>
}