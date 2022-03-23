package app.okaneko.authentication.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginOptions(
    val hashedPassword: String?,
)
