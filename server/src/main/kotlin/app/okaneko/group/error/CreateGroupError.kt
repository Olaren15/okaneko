package app.okaneko.group.error

import app.okaneko.base.data.dto.ResourceError
import app.okaneko.error.data.dto.FieldError
import io.ktor.http.*

sealed class CreateGroupError(
    override val statusCode: HttpStatusCode,
    override val errors: List<FieldError>
) : ResourceError {
    object CreationError :
        CreateGroupError(
            HttpStatusCode.InternalServerError,
            listOf(FieldError(null, "An error occurred while creating the group"))
        )
}