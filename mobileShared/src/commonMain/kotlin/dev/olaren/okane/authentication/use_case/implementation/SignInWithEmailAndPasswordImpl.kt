package dev.olaren.okane.authentication.use_case.implementation

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.errors.SignInError
import dev.olaren.okane.authentication.repositories.AuthenticationRepository
import dev.olaren.okane.authentication.use_case.SignInWithEmailAndPassword

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