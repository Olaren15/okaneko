package dev.olaren.okane.authentication.use_case

import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.exceptions.InvalidCredentialsError
import dev.olaren.okane.authentication.repositories.AuthenticationRepository

class SignInWithEmailAndPassword(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()

        if (trimmedEmail.isEmpty() || trimmedPassword.isEmpty()) {
            return Result.failure(InvalidCredentialsError())
        }

        return repository.signInWithEmailAndPassword(trimmedEmail, trimmedPassword)
    }
}