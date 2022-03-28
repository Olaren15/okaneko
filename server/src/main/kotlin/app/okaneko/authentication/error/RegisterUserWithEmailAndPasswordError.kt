package app.okaneko.authentication.error

import app.okaneko.base.data.dto.ResourceError
import io.ktor.http.*

sealed class RegisterUserWithEmailAndPasswordError(
    override val statusCode: HttpStatusCode,
    override val detailedMessage: String,
) : ResourceError {
    object InvalidEmail :
        RegisterUserWithEmailAndPasswordError(HttpStatusCode.BadRequest, "The provided email is not valid.")

    object PasswordTooWeak :
        RegisterUserWithEmailAndPasswordError(HttpStatusCode.BadRequest, "The provided password is too weak.")

    object EmailAlreadyInUse :
        RegisterUserWithEmailAndPasswordError(HttpStatusCode.Conflict, "The provided email is already in use.")

    object CreationError :
        RegisterUserWithEmailAndPasswordError(
            HttpStatusCode.InternalServerError,
            "An error occurred while creating the user."
        )
}
