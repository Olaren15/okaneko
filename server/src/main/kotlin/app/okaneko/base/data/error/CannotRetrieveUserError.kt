package app.okaneko.base.data.error

import app.okaneko.base.data.dto.ResourceError
import io.ktor.http.*

object CannotRetrieveUserError : ResourceError {
    override val statusCode = HttpStatusCode.Forbidden
    override val detailedMessage = "Could not extract user from token"
}