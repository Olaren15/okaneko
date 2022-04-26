package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.error.RefreshTokenNotSavedError
import app.okaneko.authentication.repository.RefreshTokenRepository
import app.okaneko.authentication.use_case.SaveRefreshToken
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapError
import kotlin.time.Duration

class SaveRefreshTokenImpl(private val refreshTokenRepositoryFactory: (userId: String, refreshToken: String) -> RefreshTokenRepository) :
    SaveRefreshToken {
    override suspend fun invoke(
        userId: String,
        refreshToken: String,
        timeToLive: Duration
    ): Result<String, RefreshTokenNotSavedError> {
        val repository = refreshTokenRepositoryFactory(userId, refreshToken)

        return repository.setValue("", timeToLive).mapError { RefreshTokenNotSavedError }
    }
}