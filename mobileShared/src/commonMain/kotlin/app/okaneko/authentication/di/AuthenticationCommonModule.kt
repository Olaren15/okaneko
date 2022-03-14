package app.okaneko.authentication.di

import app.okaneko.authentication.repositories.AuthenticationRepository
import app.okaneko.authentication.repositories.implementation.FirebaseAuthenticationRepositoryImpl
import app.okaneko.authentication.use_case.*
import app.okaneko.authentication.use_case.implementation.*
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