package app.okaneko.user.use_case

import app.okaneko.authentication.error.InvalidCredentialsError
import com.github.michaelbull.result.Result

interface GetUserIdFromEmailAndPassword {
    suspend operator fun invoke(email: String, password: String): Result<String, InvalidCredentialsError>
}