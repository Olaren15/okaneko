package app.okaneko.base.data.dto

import app.okaneko.errors.data.dto.RestError
import io.ktor.http.*

interface ResourceError {
    val statusCode: HttpStatusCode
    val detailedMessage: String

    fun toRestError(): RestError {
        return RestError(
            status = statusCode.value,
            message = statusCode.description,
            details = detailedMessage,
        )
    }
}