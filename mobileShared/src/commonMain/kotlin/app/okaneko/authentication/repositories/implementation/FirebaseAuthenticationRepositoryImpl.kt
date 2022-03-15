package app.okaneko.authentication.repositories.implementation

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.data.mapper.UserMapper
import app.okaneko.authentication.errors.SignInError
import app.okaneko.authentication.errors.SignUpError
import app.okaneko.authentication.repositories.AuthenticationRepository
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.auth

class FirebaseAuthenticationRepositoryImpl : AuthenticationRepository {
    override val auth = Firebase.auth

    override suspend fun signUpWithEmailAndPassword(
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

    override suspend fun signInWithEmailAndPassword(
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

    override suspend fun signInAnonymously(): Result<User, SignInError> {
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

    override suspend fun signOut() {
        auth.signOut()
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}