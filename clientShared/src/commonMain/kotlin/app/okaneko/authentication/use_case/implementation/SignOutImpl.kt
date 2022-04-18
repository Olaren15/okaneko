package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.repositories.AuthenticationRepository
import app.okaneko.authentication.use_case.SignOut

class SignOutImpl(private val repository: AuthenticationRepository) : SignOut {
    override suspend operator fun invoke() {
        repository.signOut()
    }
}