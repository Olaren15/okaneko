package app.okaneko.base.data.error

import app.okaneko.base.data.dto.ResourceError
import app.okaneko.error.data.dto.FieldError
import io.ktor.http.*

object CannotRetrieveUserError : ResourceError {
    override val statusCode = HttpStatusCode.Forbidden
    override val errors = listOf(FieldError(null, "Could not extract user from token"))
}