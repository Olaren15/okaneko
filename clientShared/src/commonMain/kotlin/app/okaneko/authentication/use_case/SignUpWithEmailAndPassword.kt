package app.okaneko.authentication.use_case

import app.okaneko.authentication.errors.SignUpError
import app.okaneko.user.data.dto.User
import com.github.michaelbull.result.Result

interface SignUpWithEmailAndPassword {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmedPassword: String
    ): Result<User, SignUpError>
}