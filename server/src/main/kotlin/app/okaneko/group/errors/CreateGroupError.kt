package app.okaneko.group.errors

import app.okaneko.base.data.dto.ResourceError
import io.ktor.http.*

sealed class CreateGroupError(
    override val statusCode: HttpStatusCode,
    override val detailedMessage: String
) : ResourceError {
    object CreationError :
        CreateGroupError(HttpStatusCode.InternalServerError, "An error occurred while creating the group")
}