package app.okaneko.authentication.repositories

import app.okaneko.authentication.errors.SignInError
import app.okaneko.authentication.errors.SignUpError
import app.okaneko.user.data.dto.User
import com.github.michaelbull.result.Result

interface AuthenticationRepository {
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