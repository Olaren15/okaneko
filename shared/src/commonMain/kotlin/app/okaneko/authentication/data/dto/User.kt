package app.okaneko.authentication.data.dto

data class User(
    val id: String,
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val isAnonymous: Boolean
)