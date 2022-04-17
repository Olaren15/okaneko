package app.okaneko.authentication.error

import app.okaneko.base.data.dto.ResourceError
import app.okaneko.error.data.dto.FieldError
import io.ktor.http.*

object CannotGenerateTokens : ResourceError {
    override val statusCode = HttpStatusCode.InternalServerError
    override val errors = listOf(FieldError(null, "An error occurred while generating the tokens"))
}