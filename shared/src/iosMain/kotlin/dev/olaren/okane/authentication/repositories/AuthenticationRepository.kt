package dev.olaren.okane.authentication.repositories

import dev.olaren.okane.authentication.data.dto.User

actual class AuthenticationRepository {
    actual suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }
}