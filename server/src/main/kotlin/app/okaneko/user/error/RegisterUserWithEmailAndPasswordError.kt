package app.okaneko.user.error

import app.okaneko.base.data.dto.ResourceError
import app.okaneko.error.data.dto.FieldError
import io.ktor.http.*

sealed class RegisterUserWithEmailAndPasswordError(
    override val statusCode: HttpStatusCode,
    override val errors: List<FieldError>,
) : ResourceError {
    object InvalidEmail :
        RegisterUserWithEmailAndPasswordError(
            HttpStatusCode.BadRequest,
            listOf(FieldError("email", "The format is invalid"))
        )

    data class InvalidPassword(val passwordValidationErrors: List<PasswordValidationError>) :
        RegisterUserWithEmailAndPasswordError(HttpStatusCode.BadRequest, passwordValidationErrors.first().run {
            listOf(
                FieldError(
                    "password", when (this) {
                        is PasswordValidationError.AtLeastEightCharacters -> "Must contain at least eight characters"
                        is PasswordValidationError.AtLeastOneLowerCaseLetter -> "Must contain at least one lower case letter"
                        is PasswordValidationError.AtLeastOneSpecialCharacter -> "Must contain at least one special character"
                        is PasswordValidationError.AtLeastOneUpperCaseLetter -> "Must contain at least one upper case letter"
                    }
                )
            )
        })

    object EmailAlreadyInUse :
        RegisterUserWithEmailAndPasswordError(
            HttpStatusCode.Conflict,
            listOf(FieldError("email", "Email is already in use"))
        )

    object CreationError :
        RegisterUserWithEmailAndPasswordError(
            HttpStatusCode.InternalServerError,
            listOf(FieldError(null, "An error occurred while creating the user"))
        )
}
