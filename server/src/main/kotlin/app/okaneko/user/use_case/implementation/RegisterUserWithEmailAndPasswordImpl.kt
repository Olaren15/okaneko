package app.okaneko.user.use_case.implementation

import app.okaneko.authentication.data.dto.EmailPasswordRegistration
import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.data.entity.LoginOptions
import app.okaneko.authentication.data.entity.UserEntity
import app.okaneko.authentication.data.validator.AuthenticationValidators
import app.okaneko.authentication.error.RegisterUserWithEmailAndPasswordError
import app.okaneko.authentication.repository.UserRepository
import app.okaneko.user.use_case.RegisterUserWithEmailAndPassword
import at.favre.lib.crypto.bcrypt.BCrypt
import com.github.michaelbull.result.*
import kotlinx.datetime.Clock

class RegisterUserWithEmailAndPasswordImpl(
    private val repository: UserRepository,
    private val validators: AuthenticationValidators
) : RegisterUserWithEmailAndPassword {
    override suspend fun invoke(registration: EmailPasswordRegistration): Result<User, RegisterUserWithEmailAndPasswordError> {
        val trimmedEmail = registration.email.trim()
        val trimmedPassword = registration.password.trim()

        return validators.emailValidator(trimmedEmail)
            .mapError { RegisterUserWithEmailAndPasswordError.InvalidEmail }
            .andThen {
                validators.passwordValidator(trimmedPassword)
                    .mapError { RegisterUserWithEmailAndPasswordError.InvalidPassword(it) }
            }
            .andThen {
                repository.getUserByEmail(trimmedEmail)
                    .mapBoth(
                        success = { Err(RegisterUserWithEmailAndPasswordError.EmailAlreadyInUse) },
                        failure = { Ok(Unit) },
                    )
            }
            .andThen {
                val hashedPassword = BCrypt.withDefaults().hashToString(12, trimmedPassword.toCharArray())

                val newUser = UserEntity(
                    id = null,
                    email = trimmedEmail,
                    name = registration.details?.name?.trim(),
                    photoUrl = registration.details?.photoUrl?.trim(),
                    loginOptions = LoginOptions(
                        hashedPassword = hashedPassword
                    ),
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )

                repository.insert(newUser).mapEither(
                    success = { it.toDto() },
                    failure = { RegisterUserWithEmailAndPasswordError.CreationError },
                )
            }
    }
}