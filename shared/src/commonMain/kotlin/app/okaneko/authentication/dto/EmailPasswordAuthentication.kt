package app.okaneko.authentication.dto

import kotlinx.serialization.Serializable

@Serializable
data class EmailPasswordAuthentication(
    val email: String,
    val password: String,
)
