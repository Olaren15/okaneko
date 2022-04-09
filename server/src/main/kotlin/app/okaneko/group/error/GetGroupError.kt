package app.okaneko.group.error

import app.okaneko.base.data.dto.ResourceError
import app.okaneko.error.data.dto.FieldError
import io.ktor.http.*

sealed class GetGroupError(
    override val statusCode: HttpStatusCode,
    override val errors: List<FieldError>
) : ResourceError {
    object IdNotFound : GetGroupError(
        HttpStatusCode.NotFound,
        listOf(FieldError("id", "The group with the requested id was not found"))
    )

    object NotPermittedError : GetGroupError(
        HttpStatusCode.Forbidden,
        listOf(FieldError(null, "You are not allowed to access this group"))
    )
}