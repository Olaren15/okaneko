package app.okaneko.base.data.dto

import io.ktor.http.*

interface ResourceError {
    val statusCode: HttpStatusCode
    val detailedMessage: String
}