package app.okaneko.base.routes.mapping

import app.okaneko.base.data.dto.ResourceError
import app.okaneko.error.data.dto.RestError
import io.ktor.http.*

fun mapRestError(error: ResourceError) =
    error.statusCode to RestError(
        status = error.statusCode.value,
        message = error.statusCode.description,
        details = error.detailedMessage
    )

fun mapOk(message: Any) = HttpStatusCode.OK to message