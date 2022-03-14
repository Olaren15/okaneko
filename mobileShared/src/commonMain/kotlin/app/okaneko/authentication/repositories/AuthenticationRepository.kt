package app.okaneko.authentication.repositories

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.errors.SignInError
import app.okaneko.authentication.errors.SignUpError
import com.github.michaelbull.result.Result
import dev.gitlive.firebase.auth.FirebaseAuth

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