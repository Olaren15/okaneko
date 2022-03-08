package dev.olaren.okane.authentication.use_case.implementation

import com.github.michaelbull.result.Result
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.errors.SignInError
import dev.olaren.okane.authentication.repositories.AuthenticationRepository
import dev.olaren.okane.authentication.use_case.SignInAnonymously

class SignInAnonymouslyImpl(private val repository: AuthenticationRepository) :
    SignInAnonymously {
    override suspend operator fun invoke(): Result<User, SignInError> {
        return repository.signInAnonymously()
    }
}