package app.okaneko.authentication.repositories.implementation

import app.okaneko.authentication.errors.SignInError
import app.okaneko.authentication.errors.SignUpError
import app.okaneko.authentication.repositories.AuthenticationRepository
import app.okaneko.user.data.dto.User
import app.okaneko.user.data.dto.UserDetails
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.datetime.Clock

class AuthenticationRepositoryImpl : AuthenticationRepository {
    private val user = User("1", UserDetails("name", "email", null), false, Clock.System.now(), Clock.System.now())

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User, SignUpError> {
        return Ok(user)
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User, SignInError> {
        return Ok(user)
    }

    override suspend fun signInAnonymously(): Result<User, SignInError> {
        return Ok(user)
    }

    override suspend fun signOut() {

    }

    override fun isUserLoggedIn(): Boolean {
        return false
    }
}