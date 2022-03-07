package dev.olaren.okane.android.authentication.di

import dev.olaren.okane.android.authentication.viewmodels.SignInViewModel
import dev.olaren.okane.android.authentication.viewmodels.SignUpViewModel
import dev.olaren.okane.android.groups.viewmodels.GroupsViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val authenticationAndroidModule = DI.Module("AuthenticationAndroidModule") {
    bind {
        provider {
            SignInViewModel(instance())
        }
    }

    bind {
        provider {
            SignUpViewModel(instance())
        }
    }

    bind {
        provider {
            GroupsViewModel(instance())
        }
    }
}