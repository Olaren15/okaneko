package dev.olaren.okane.authentication.use_case

import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.repositories.AuthenticationRepository

class SignInAnonymously(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(): Result<User> {
        return repository.signInAnonymously()
    }
}