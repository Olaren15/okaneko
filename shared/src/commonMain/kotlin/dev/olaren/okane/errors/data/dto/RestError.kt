package dev.olaren.okane.errors.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RestError(
    val status: Int,
    val message: String,
    val details: String,
)