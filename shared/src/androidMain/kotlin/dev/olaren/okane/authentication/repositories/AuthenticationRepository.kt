package dev.olaren.okane.authentication.repositories

import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.data.mapper.UserMapper
import dev.olaren.okane.authentication.exceptions.InvalidCredentials
import dev.olaren.okane.authentication.exceptions.SignInError
import kotlinx.coroutines.tasks.await

actual class AuthenticationRepository {
    private val auth = Firebase.auth

    actual suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User> {
        return signInWithCredentials(EmailAuthProvider.getCredential(email, password))
    }

    actual suspend fun signInAnonymously(): Result<User> {
        val user = auth.signInAnonymously().await().user
        return if (user != null)
            Result.success(UserMapper().map(user))
        else
            Result.failure(SignInError())
    }

    actual suspend fun signOut() {
        auth.signOut()
    }

    actual fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    private suspend fun signInWithCredentials(credentials: AuthCredential): Result<User> {
        return try {
            val user = auth.signInWithCredential(credentials).await().user
            if (user != null)
                Result.success(UserMapper().map(user))
            else
                Result.failure(SignInError())
        } catch (e: FirebaseAuthInvalidUserException) {
            Result.failure(InvalidCredentials())
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(InvalidCredentials())
        } catch (e: FirebaseAuthUserCollisionException) {
            Result.failure(SignInError())
        }
    }
}