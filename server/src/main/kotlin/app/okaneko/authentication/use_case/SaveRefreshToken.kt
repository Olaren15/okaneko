package app.okaneko.authentication.use_case

import app.okaneko.authentication.error.RefreshTokenNotSavedError
import com.github.michaelbull.result.Result
import kotlin.time.Duration

interface SaveRefreshToken {
    suspend operator fun invoke(
        userId: String,
        refreshToken: String,
        timeToLive: Duration
    ): Result<String, RefreshTokenNotSavedError>
}