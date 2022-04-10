package app.okaneko.user.di

import app.okaneko.user.data.validator.EmailValidator
import app.okaneko.user.data.validator.PasswordValidator
import app.okaneko.user.data.validator.UserValidators
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val authenticationSharedModule = DI.Module("AuthenticationShared") {
    bind {
        singleton {
            UserValidators(
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