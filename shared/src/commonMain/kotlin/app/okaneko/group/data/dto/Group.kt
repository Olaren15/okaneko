package app.okaneko.group.data.dto

import app.okaneko.base.data.dto.Dto
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: String,
    val usersIds: List<String>,
    val name: String,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : Dto