package app.okaneko.authentication.error

import app.okaneko.base.data.dto.ResourceError
import io.ktor.http.*

sealed class RegisterUserWithEmailAndPasswordError(
    override val statusCode: HttpStatusCode,
    override val detailedMessage: String,
) : ResourceError {
    object InvalidEmail :
        RegisterUserWithEmailAndPasswordError(HttpStatusCode.BadRequest, "The provided email is not valid.")

    data class InvalidPassword(val errors: List<PasswordValidationError>) :
        RegisterUserWithEmailAndPasswordError(HttpStatusCode.BadRequest, "") {
        override val detailedMessage: String
            get() {
                // TODO: Make this prettier?
                var message = "The provided password is invalid. It should meet the following requirements: "

                for ((index, error) in errors.withIndex()) {
                    message += when (error) {
                        is PasswordValidationError.AtLeastEightCharacters -> "contain at least eight characters"
                        is PasswordValidationError.AtLeastOneLowerCaseLetter -> "contain at least one lower case letter"
                        is PasswordValidationError.AtLeastOneSpecialCharacter -> "contain at least one special character"
                        is PasswordValidationError.AtLeastOneUpperCaseLetter -> "contain at least one upper case letter"
                    }

                    if (index + 1 == errors.size - 1)
                        message += " and "

                    if (index + 1 < errors.size - 1)
                        message += ", "
                }

                message += "."

                return message
            }
    }

    object EmailAlreadyInUse :
        RegisterUserWithEmailAndPasswordError(HttpStatusCode.Conflict, "The provided email is already in use.")

    object CreationError :
        RegisterUserWithEmailAndPasswordError(
            HttpStatusCode.InternalServerError,
            "An error occurred while creating the user."
        )
}
