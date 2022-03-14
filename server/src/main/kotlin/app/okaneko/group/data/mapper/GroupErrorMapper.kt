package app.okaneko.group.data.mapper

import app.okaneko.errors.data.dto.RestError
import app.okaneko.group.errors.GetGroupError
import io.ktor.http.*

fun mapToRestError(error: GetGroupError): RestError {
    return when (error) {
        GetGroupError.IdNotFound -> RestError(
            status = HttpStatusCode.NotFound.value,
            message = "The group with the requested id was not found",
            details = ""
        )
        GetGroupError.NotPermittedError -> RestError(
            status = HttpStatusCode.Forbidden.value,
            message = "You do not have access to the requested group",
            details = ""
        )
    }
}