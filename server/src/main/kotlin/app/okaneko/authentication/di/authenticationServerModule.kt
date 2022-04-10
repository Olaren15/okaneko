package app.okaneko.authentication.di

import app.okaneko.authentication.use_case.AuthenticationUseCases
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val authenticationServerModule = DI.Module("AuthenticationServer") {
    bind {
        singleton {
            AuthenticationUseCases(Unit)
        }
    }
}