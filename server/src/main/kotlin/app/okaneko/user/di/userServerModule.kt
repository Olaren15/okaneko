package app.okaneko.user.di

import app.okaneko.user.repository.UserRepository
import app.okaneko.user.repository.implementation.MongoUserRepository
import app.okaneko.user.use_case.*
import app.okaneko.user.use_case.implementation.GetUserIdFromEmailAndPasswordImpl
import app.okaneko.user.use_case.implementation.HashUserPasswordBcryptImpl
import app.okaneko.user.use_case.implementation.RegisterUserWithEmailAndPasswordImpl
import app.okaneko.user.use_case.implementation.VerifyUserPasswordBcryptImpl
import org.kodein.di.*

val userServerModule = DI.Module("UserServer") {
    bind {
        provider {
            UserUseCases(
                registerUserWithEmailAndPassword = instance(),
                getUserIdFromEmailAndPassword = instance(),
            )
        }
    }

    bind<RegisterUserWithEmailAndPassword> {
        provider {
            RegisterUserWithEmailAndPasswordImpl(
                repository = instance(),
                validators = instance(),
                hashUserPassword = instance()
            )
        }
    }

    bind<GetUserIdFromEmailAndPassword> {
        provider {
            GetUserIdFromEmailAndPasswordImpl(
                userRepository = instance(),
                verifyUserPassword = instance()
            )
        }
    }

    bind<HashUserPassword> {
        provider {
            HashUserPasswordBcryptImpl()
        }
    }

    bind<VerifyUserPassword> {
        provider {
            VerifyUserPasswordBcryptImpl()
        }
    }

    bind<UserRepository> {
        singleton {
            MongoUserRepository(instance())
        }
    }
}