package app.okaneko.authentication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EmailPasswordRegistration(
    val email: String,
    val password: String,
    val details: UserDetails?,
)
