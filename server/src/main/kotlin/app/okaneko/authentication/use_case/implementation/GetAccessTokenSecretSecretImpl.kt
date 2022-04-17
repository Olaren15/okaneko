package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.error.CannotGetAccessTokenSecretError
import app.okaneko.authentication.repository.AccessTokenSecretRepository
import app.okaneko.authentication.use_case.GetAccessTokenSecret
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapError

class GetAccessTokenSecretSecretImpl(private val repository: AccessTokenSecretRepository) : GetAccessTokenSecret {
    override suspend fun invoke(): Result<String, CannotGetAccessTokenSecretError> {
        return if (!repository.isValuePresent()) {
            val newRandomKey = List(32) {
                (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
            }.joinToString("")

            repository.setValue(newRandomKey)
        } else {
            repository.getValue()
        }.mapError { CannotGetAccessTokenSecretError }
    }
}