package app.okaneko.android.authentication.di

import app.okaneko.android.authentication.viewmodels.SignInViewModel
import app.okaneko.android.authentication.viewmodels.SignUpViewModel
import app.okaneko.android.groups.viewmodels.GroupsViewModel
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