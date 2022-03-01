package dev.olaren.okane.authentication.use_case

import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.exceptions.InvalidCredentials
import dev.olaren.okane.authentication.repositories.AuthenticationRepository

class SignInWithEmailAndPassword(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            return Result.failure(InvalidCredentials())
        }

        return repository.signInWithEmailAndPassword(email, password)
    }
}