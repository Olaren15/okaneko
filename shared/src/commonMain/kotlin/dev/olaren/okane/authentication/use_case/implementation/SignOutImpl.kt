package dev.olaren.okane.authentication.use_case.implementation

import dev.olaren.okane.authentication.repositories.AuthenticationRepository
import dev.olaren.okane.authentication.use_case.SignOut

class SignOutImpl(private val repository: AuthenticationRepository) : SignOut {
    override suspend operator fun invoke() {
        repository.signOut()
    }
}