package app.okaneko.user.di

import app.okaneko.authentication.repository.UserRepository
import app.okaneko.authentication.repository.implementation.MongoUserRepository
import app.okaneko.user.use_case.RegisterUserWithEmailAndPassword
import app.okaneko.user.use_case.UserUseCases
import app.okaneko.user.use_case.implementation.RegisterUserWithEmailAndPasswordImpl
import org.kodein.di.*

val userServerModule = DI.Module("UserServer") {
    bind {
        singleton {
            UserUseCases(
                registerUserWithEmailAndPassword = instance()
            )
        }
    }

    bind<RegisterUserWithEmailAndPassword> {
        provider {
            RegisterUserWithEmailAndPasswordImpl(
                repository = instance(),
                validators = instance(),
            )
        }
    }

    bind<UserRepository> {
        singleton {
            MongoUserRepository(instance())
        }
    }
}