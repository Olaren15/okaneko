package app.okaneko.authentication.di

import app.okaneko.authentication.repository.UserRepository
import app.okaneko.authentication.repository.implementation.MongoUserRepository
import app.okaneko.authentication.use_case.AuthenticationUseCases
import app.okaneko.authentication.use_case.RegisterUserWithEmailAndPassword
import app.okaneko.authentication.use_case.implementation.RegisterUserWithEmailAndPasswordImpl
import org.kodein.di.*

val authenticationServerModule = DI.Module("AuthenticationServer") {
    bind {
        singleton {
            AuthenticationUseCases(
                registerUserWithEmailAndPassword = instance()
            )
        }
    }

    bind<RegisterUserWithEmailAndPassword> {
        provider {
            RegisterUserWithEmailAndPasswordImpl(
                repository = instance(),
                validators = instance()
            )
        }
    }

    bind<UserRepository> {
        singleton {
            MongoUserRepository(instance())
        }
    }
}