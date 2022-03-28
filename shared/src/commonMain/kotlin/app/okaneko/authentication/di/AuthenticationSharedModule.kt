package app.okaneko.authentication.di

import app.okaneko.authentication.data.validator.AuthenticationValidators
import app.okaneko.authentication.data.validator.EmailValidator
import app.okaneko.authentication.data.validator.PasswordValidator
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val authenticationSharedModule = DI.Module("AuthenticationShared") {
    bind {
        singleton {
            AuthenticationValidators(
                emailValidator = instance(),
                passwordValidator = instance(),
            )
        }
    }

    bind {
        singleton {
            EmailValidator()
        }
    }

    bind {
        singleton {
            PasswordValidator()
        }
    }
}