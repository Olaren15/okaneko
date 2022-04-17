package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.error.CannotGetRefreshTokenSecretError
import app.okaneko.authentication.repository.RefreshTokenSecretRepository
import app.okaneko.authentication.use_case.GetRefreshTokenSecret
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapError

class GetRefreshTokenSecretImpl(private val repository: RefreshTokenSecretRepository) : GetRefreshTokenSecret {
    override suspend fun invoke(): Result<String, CannotGetRefreshTokenSecretError> {
        return if (!repository.isValuePresent()) {
            val newRandomKey = List(32) {
                (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
            }.joinToString("")

            repository.setValue(newRandomKey)
        } else {
            repository.getValue()
        }.mapError { CannotGetRefreshTokenSecretError }
    }
}