package dev.olaren.okane.authentication.di

import dev.olaren.okane.authentication.repositories.AuthenticationRepository
import dev.olaren.okane.authentication.repositories.implementation.FirebaseAuthenticationRepositoryImpl
import dev.olaren.okane.authentication.use_case.*
import dev.olaren.okane.authentication.use_case.implementation.*
import org.kodein.di.*

val authenticationCommonModule = DI.Module(name = "AuthenticationCommon") {
    bind<SignUpWithEmailAndPassword> {
        provider {
            SignUpWithEmailAndPasswordImpl(instance())
        }
    }

    bind<SignInWithEmailAndPassword> {
        provider {
            SignInWithEmailAndPasswordImpl(instance())
        }
    }

    bind<SignInAnonymously> {
        provider {
            SignInAnonymouslyImpl(instance())
        }
    }

    bind<SignOut> {
        provider {
            SignOutImpl(instance())
        }
    }

    bind<IsUserLoggedIn> {
        provider {
            IsUserLoggedInImpl(instance())
        }
    }

    bind {
        provider {
            AuthenticationUseCases(instance(), instance(), instance(), instance(), instance())
        }
    }

    bind<AuthenticationRepository> {
        singleton(sync = false) {
            FirebaseAuthenticationRepositoryImpl()
        }
    }
}