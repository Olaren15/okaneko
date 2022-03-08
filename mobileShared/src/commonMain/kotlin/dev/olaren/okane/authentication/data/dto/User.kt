package dev.olaren.okane.authentication.data.dto

data class User(
    val userId: String,
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val isAnonymous: Boolean
)