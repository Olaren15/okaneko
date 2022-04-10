package app.okaneko.authentication.use_case

import app.okaneko.authentication.errors.SignInError
import app.okaneko.user.data.dto.User
import com.github.michaelbull.result.Result

interface SignInWithEmailAndPassword {
    suspend operator fun invoke(email: String, password: String): Result<User, SignInError>
}