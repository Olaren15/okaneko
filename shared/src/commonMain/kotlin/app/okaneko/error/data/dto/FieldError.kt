package app.okaneko.error.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class FieldError(
    val field: String?,
    val message: String,
)
