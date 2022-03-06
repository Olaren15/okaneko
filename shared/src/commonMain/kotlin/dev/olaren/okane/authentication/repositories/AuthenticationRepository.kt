package dev.olaren.okane.authentication.repositories

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.auth
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.data.dto.UserMapper
import dev.olaren.okane.authentication.errors.SignInError
import dev.olaren.okane.authentication.errors.SignUpError

class AuthenticationRepository {
    private val auth = Firebase.auth

    suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User, SignUpError> {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password).user
            if (user != null)
                Ok(UserMapper().map(user))
            else
                Err(SignUpError.UnknownError)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Err(SignUpError.InvalidEmailError)
        } catch (e: FirebaseAuthUserCollisionException) {
            Err(SignUpError.UserAlreadyExistsError)
        } catch (e: FirebaseAuthException) {
            Err(SignUpError.UnknownError)
        }
    }

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User, SignInError> {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).user
            if (user != null)
                Ok(UserMapper().map(user))
            else
                Err(SignInError.UnknownError)
        } catch (e: FirebaseAuthException) {
            Err(SignInError.InvalidCredentialsError)
        }
    }

    suspend fun signInAnonymously(): Result<User, SignInError> {
        return try {
            val user = auth.signInAnonymously().user
            if (user != null)
                Ok(UserMapper().map(user))
            else
                Err(SignInError.UnknownError)
        } catch (e: FirebaseAuthException) {
            Err(SignInError.UnknownError)
        }
    }

    suspend fun signOut() {
        auth.signOut()
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}