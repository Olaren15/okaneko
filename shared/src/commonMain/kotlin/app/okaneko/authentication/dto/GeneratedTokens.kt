package app.okaneko.authentication.dto

import kotlinx.serialization.Serializable

@Serializable
data class GeneratedTokens(
    val accessToken: String,
    val refreshToken: String,
)
