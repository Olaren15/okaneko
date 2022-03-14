package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.errors.SignInError
import app.okaneko.authentication.repositories.AuthenticationRepository
import app.okaneko.authentication.use_case.SignInAnonymously
import com.github.michaelbull.result.Result

class SignInAnonymouslyImpl(private val repository: AuthenticationRepository) :
    SignInAnonymously {
    override suspend operator fun invoke(): Result<User, SignInError> {
        return repository.signInAnonymously()
    }
}