package dev.olaren.okane.authentication.use_case

import dev.olaren.okane.authentication.repositories.AuthenticationRepository

class SignOut(private val repository: AuthenticationRepository) {
    operator fun invoke() {
        repository.signOut()
    }
}