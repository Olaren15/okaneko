package app.okaneko.authentication.use_case.implementation

import app.okaneko.authentication.data.dto.EmailPasswordRegistration
import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.data.entity.LoginOptions
import app.okaneko.authentication.data.entity.UserEntity
import app.okaneko.authentication.error.RegisterUserWithEmailAndPasswordError
import app.okaneko.authentication.repository.UserRepository
import app.okaneko.authentication.use_case.RegisterUserWithEmailAndPassword
import at.favre.lib.crypto.bcrypt.BCrypt
import com.github.michaelbull.result.*
import kotlinx.datetime.Clock

class RegisterUserWithEmailAndPasswordImpl(private val repository: UserRepository) : RegisterUserWithEmailAndPassword {
    override suspend fun invoke(registration: EmailPasswordRegistration): Result<User, RegisterUserWithEmailAndPasswordError> {
        return repository.getUserByEmail(registration.email)
            .mapBoth(
                success = { Err(RegisterUserWithEmailAndPasswordError.EmailAlreadyInUse) },
                failure = { Ok(Unit) },
            )
            .andThen {
                val hashedPassword = BCrypt.withDefaults().hashToString(12, registration.password.toCharArray())

                val newUser = UserEntity(
                    id = null,
                    email = registration.email,
                    name = registration.details?.name,
                    photoUrl = registration.details?.photoUrl,
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