package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.repositories.AuthenticationRepository
import app.okaneko.authentication.use_case.IsUserLoggedIn

class IsUserLoggedInImpl(private val repository: AuthenticationRepository) : IsUserLoggedIn {
    override suspend fun invoke(): Boolean {
        return repository.isUserLoggedIn()
    }
}