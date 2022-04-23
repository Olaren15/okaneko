package app.okaneko.authentication.error

import app.okaneko.base.data.dto.ResourceError
import app.okaneko.error.data.dto.FieldError
import io.ktor.http.*

object InvalidCredentialsError : ResourceError {
    override val statusCode = HttpStatusCode.Forbidden
    override val errors =
        listOf(FieldError("email", "invalid credentials"), FieldError("password", "invalid credentials"))
}