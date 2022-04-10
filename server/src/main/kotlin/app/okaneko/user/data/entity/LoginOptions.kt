package app.okaneko.user.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginOptions(
    val hashedPassword: String?,
)
