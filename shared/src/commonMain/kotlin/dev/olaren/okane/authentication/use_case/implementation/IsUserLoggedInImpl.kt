package dev.olaren.okane.authentication.use_case.implementation

import dev.olaren.okane.authentication.repositories.AuthenticationRepository
import dev.olaren.okane.authentication.use_case.IsUserLoggedIn

class IsUserLoggedInImpl(private val repository: AuthenticationRepository) : IsUserLoggedIn {
    override suspend fun invoke(): Boolean {
        return repository.isUserLoggedIn()
    }
}