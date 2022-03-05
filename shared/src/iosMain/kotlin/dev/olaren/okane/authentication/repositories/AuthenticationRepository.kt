package dev.olaren.okane.authentication.repositories

import cocoapods.FirebaseAuth.*
import dev.olaren.okane.authentication.data.dto.User
import kotlinx.coroutines.CompletableDeferred
import cocoapods.FirebaseCore.FIRApp
import dev.olaren.okane.authentication.data.mapper.UserMapper
import dev.olaren.okane.authentication.exceptions.*
import kotlinx.cinterop.*
import platform.Foundation.NSError

actual class AuthenticationRepository {
    private val auth: FIRAuth

    init {
        // Initialise firebase app if not done yet
        if (FIRApp.defaultApp() == null) {
            FIRApp.configure()
        }

        auth = FIRAuth.auth()
    }

    actual suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User> {
        val job = CompletableDeferred<FIRAuthDataResult>()
        auth.createUserWithEmail(email, password) { result, error ->
            if (error == null) {
                if (result != null) {
                    job.complete(result)
                } else {
                    job.completeExceptionally(SignUpError())
                }
            } else {
                when (error.code) {
                    FIRAuthErrorCodeInvalidEmail -> {
                        job.completeExceptionally(InvalidEmailError())
                    }

                    FIRAuthErrorCodeEmailAlreadyInUse -> {
                        job.completeExceptionally(UserAlreadyExistsError())
                    }

                    FIRAuthErrorCodeWeakPassword -> {
                        job.completeExceptionally(PasswordTooWeakError())
                    }

                    else -> {
                        job.completeExceptionally(SignUpError())
                    }
                }
            }
        }

        return try {
            val user = job.await().user
            Result.success(UserMapper().map(user))
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    actual suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User> {
        return signInWithCredentials(FIREmailAuthProvider.credentialWithEmail(email = email, password = password))
    }

    actual suspend fun signInAnonymously(): Result<User> {
        val job = CompletableDeferred<FIRAuthDataResult>()
        auth.signInAnonymouslyWithCompletion { result, error ->
            if (error == null) {
                if (result != null) {
                    job.complete(result)
                } else {
                    job.completeExceptionally(SignInError())
                }
            } else {
                job.completeExceptionally(SignInError())
            }
        }

        return try {
            val user = job.await().user
            Result.success(UserMapper().map(user))
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    actual fun signOut(): Boolean {
        var isSuccess = true

        memScoped {
            val errorPtr: ObjCObjectVar<NSError?> = alloc()
            auth.signOut(errorPtr.ptr)
            isSuccess = errorPtr.value == null
        }

        return isSuccess
    }

    actual fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    private suspend fun signInWithCredentials(credentials: FIRAuthCredential): Result<User> {
        val job = CompletableDeferred<FIRAuthDataResult>()
        auth.signInWithCredential(credentials) { result, error ->
            if (error == null) {
                if (result != null) {
                    job.complete(result)
                } else {
                    job.completeExceptionally(SignInError())
                }
            } else {
                when (error.code) {
                    FIRAuthErrorCodeInvalidCredential,
                    FIRAuthErrorCodeUserDisabled,
                    FIRAuthErrorCodeWrongPassword,
                    FIRAuthErrorCodeInvalidEmail,
                    -> {
                        job.completeExceptionally(InvalidCredentialsError())
                    }

                    FIRAuthErrorCodeAccountExistsWithDifferentCredential -> {
                        job.completeExceptionally(UserAlreadyExistsError())
                    }

                    else -> {
                        job.completeExceptionally(SignInError())
                    }
                }
            }
        }

        return try {
            val user = job.await().user
            Result.success(UserMapper().map(user))
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}