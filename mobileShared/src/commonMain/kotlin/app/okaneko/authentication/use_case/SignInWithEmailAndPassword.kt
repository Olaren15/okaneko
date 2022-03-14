package app.okaneko.authentication.use_case

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.errors.SignInError
import com.github.michaelbull.result.Result

interface SignInWithEmailAndPassword {
    suspend operator fun invoke(email: String, password: String): Result<User, SignInError>
}