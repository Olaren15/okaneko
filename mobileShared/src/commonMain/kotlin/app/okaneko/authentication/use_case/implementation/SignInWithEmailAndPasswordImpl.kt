package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.errors.SignInError
import app.okaneko.authentication.repositories.AuthenticationRepository
import app.okaneko.authentication.use_case.SignInWithEmailAndPassword
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result

class SignInWithEmailAndPasswordImpl(
    private val repository: AuthenticationRepository
) : SignInWithEmailAndPassword {
    override suspend operator fun invoke(
        email: String,
        password: String
    ): Result<User, SignInError> {
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()

        if (trimmedEmail.isEmpty() || trimmedPassword.isEmpty()) {
            return Err(SignInError.InvalidCredentialsError)
        }

        return repository.signInWithEmailAndPassword(trimmedEmail, trimmedPassword)
    }
}