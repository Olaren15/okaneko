package app.okaneko.group.errors

import app.okaneko.base.data.dto.ResourceError
import io.ktor.http.*

sealed class GetGroupError(
    override val statusCode: HttpStatusCode,
    override val detailedMessage: String
) : ResourceError {
    object IdNotFound : GetGroupError(HttpStatusCode.NotFound, "The group with the requested id was not found")
    object NotPermittedError : GetGroupError(HttpStatusCode.Forbidden, "You are not allowed to access this group")
}