package app.okaneko.authentication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDetails(
    val name: String?,
    val email: String?,
    val photoUrl: String?,
)