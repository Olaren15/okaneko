package app.okaneko.user.use_case.implementation

import app.okaneko.authentication.error.InvalidCredentialsError
import app.okaneko.user.repository.UserRepository
import app.okaneko.user.use_case.GetUserIdFromEmailAndPassword
import app.okaneko.user.use_case.VerifyUserPassword
import com.github.michaelbull.result.*

class GetUserIdFromEmailAndPasswordImpl(
    private val userRepository: UserRepository,
    private val verifyUserPassword: VerifyUserPassword
) : GetUserIdFromEmailAndPassword {
    override suspend fun invoke(email: String, password: String): Result<String, InvalidCredentialsError> {
        return userRepository.getUserByEmail(email)
            .mapError { InvalidCredentialsError }
            .andThen { user ->
                user.loginOptions.hashedPassword.toResultOr { InvalidCredentialsError }
                    .andThen { hashedPassword ->
                        if (verifyUserPassword(password, hashedPassword))
                            Ok(user.id.toString())
                        else
                            Err(InvalidCredentialsError)
                    }
            }
    }
}