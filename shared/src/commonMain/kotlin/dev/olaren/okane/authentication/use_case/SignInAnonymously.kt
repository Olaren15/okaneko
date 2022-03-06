package dev.olaren.okane.authentication.use_case

import com.github.michaelbull.result.Result
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.errors.SignInError
import dev.olaren.okane.authentication.repositories.AuthenticationRepository

class SignInAnonymously(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(): Result<User, SignInError> {
        return repository.signInAnonymously()
    }
}