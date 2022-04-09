package app.okaneko.base.data.dto

import app.okaneko.error.data.dto.FieldError
import io.ktor.http.*

interface ResourceError {
    val statusCode: HttpStatusCode
    val errors: List<FieldError>
}