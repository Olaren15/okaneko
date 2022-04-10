package app.okaneko.user.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDetails(
    val name: String?,
    val email: String?,
    val photoUrl: String?,
)