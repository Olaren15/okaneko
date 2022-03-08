package dev.olaren.okane.authentication.repositories

import com.github.michaelbull.result.Result
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.errors.SignInError
import dev.olaren.okane.authentication.errors.SignUpError

interface AuthenticationRepository {
    val auth: FirebaseAuth

    suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User, SignUpError>

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User, SignInError>

    suspend fun signInAnonymously(): Result<User, SignInError>

    suspend fun signOut()

    fun isUserLoggedIn(): Boolean
}