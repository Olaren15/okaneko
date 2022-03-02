package dev.olaren.okane.authentication.repositories

import dev.olaren.okane.authentication.data.dto.User

expect class AuthenticationRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User>

    suspend fun signInAnonymously(): Result<User>

    suspend fun signOut()

    fun isUserLoggedIn(): Boolean
}