package app.okaneko.authentication.data.dto

import app.okaneko.base.data.dto.Dto
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val details: UserDetails,
    val isAnonymous: Boolean,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : Dto